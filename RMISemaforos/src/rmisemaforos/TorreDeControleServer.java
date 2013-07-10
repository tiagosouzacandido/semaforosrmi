/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rmisemaforos;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Tiago
 */
public class TorreDeControleServer extends UnicastRemoteObject implements TorreDeControle{
    
    private static Semaphore pistas = new Semaphore(2, true);
    private static Semaphore portoes = new Semaphore(10, true);
    
    public TorreDeControleServer() throws RemoteException
    {
        super();
    }

    @Override
    public boolean Pouso() throws RemoteException {
        try {
            return pistas.tryAcquire(100, TimeUnit.MILLISECONDS);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
            return false;
        }      
    }

    @Override
    public boolean PousoUrgencia() throws RemoteException {
        try {
            pistas.acquire();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        return true;
    }

    @Override
    public int Portao() throws RemoteException {
        return portoes.availablePermits();
    }

    @Override
    public boolean Autorizacao() throws RemoteException {
        return pistas.tryAcquire();
    }    
    
    @Override
    public void LiberaPortao(){
        portoes.release();
    }
    
    @Override
    public void LiberaPista(){
        pistas.release();
    }
    
    public void Desembarca(){
        try {
            portoes.acquire();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
    
    public static void main(String[] args){
        try{
            TorreDeControleServer servidor = new TorreDeControleServer();
            Registry registry = LocateRegistry.createRegistry(1099);
            //Registry registry = LocateRegistry.getRegistry();
            registry.rebind("//localhost//TorreDeControle", servidor);
            //Naming.rebind("//localhost//TorreDeControle", servidor);
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
}
