/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rmisemaforos;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author Tiago
 */
public class TorreDeControleServer extends UnicastRemoteObject implements TorreDeControle{
    
    public TorreDeControleServer() throws RemoteException
    {
        super();
    }

    @Override
    public boolean Pouso() throws RemoteException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return true;
    }

    @Override
    public boolean PousoUrgencia() throws RemoteException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return true;
    }

    @Override
    public int Portao() throws RemoteException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return 1;
    }

    @Override
    public boolean Autorizacao() throws RemoteException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return true;
    }
    
    
    
    public static void main(String[] args){
        try{
            TorreDeControleServer servidor = new TorreDeControleServer();
            Naming.rebind("//localhost//TorreDeControle", servidor);
            
        }catch(Exception ex){ex.printStackTrace();}
    }
    
}
