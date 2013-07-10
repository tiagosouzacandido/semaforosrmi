/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rmisemaforos;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Tiago
 */
public interface TorreDeControle extends Remote {
    public boolean Pouso() throws RemoteException;
    public boolean PousoUrgencia() throws RemoteException;
    public int Portao() throws RemoteException;
    public boolean Autorizacao() throws RemoteException;
    public void Desembarca() throws RemoteException;
    public void LiberaPista() throws RemoteException;
    public void LiberaPortao() throws RemoteException;
}
