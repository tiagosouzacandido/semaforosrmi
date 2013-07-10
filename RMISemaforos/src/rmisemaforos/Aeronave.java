/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rmisemaforos;

import static java.lang.Thread.sleep;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author Tiago
 */
public class Aeronave extends Thread {

    private static TorreDeControle servidor;
    private boolean voando = false;
    private int combustivel = 2000;

    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry();
            servidor = (TorreDeControle) registry.lookup("//localhost//TorreDeControle");
            //servidor = (TorreDeControle)Naming.lookup("//localhost//TorreDeControle");
            for (int i = 0; i < 20; i++) {
                new Aeronave("Aeronave #" + i).start();
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public Aeronave(String nome) {
        super(nome);
    }

    @Override
    public void run() {
        //while(true){
        try {
            this.setPriority(1);
            while (!voando) {
                voando = servidor.Autorizacao();
            }
            System.out.println(getName() + " foi pra pista para decolar.");
            sleep(100);
            combustivel -= 100;
            System.out.println(getName() + " decolou.");
            servidor.LiberaPista();
            this.setPriority(5);
            sleep(1000);
            combustivel -= 1000;
            System.out.println(getName() + " pede permissão para pousar.");
            while (voando) {
                if (combustivel <= 300) {
                    this.setPriority(10);
                    servidor.PousoUrgencia();
                    System.out.println(getName() + " pousando.");
                    voando = false;
                } else {
                    if (servidor.Pouso()) {
                        System.out.println(getName() + " pousando.");
                        voando = false;
                    } else {
                        System.out.println(getName() + " aguardando permissão para pousar.");
                    }
                }
                sleep(100);
                combustivel -= 100;
            }
            
            System.out.println(getName() + " indo para um portão.");
            sleep(100);
            combustivel -= 100; 
            servidor.LiberaPista();
            System.out.println(getName() + " desembarcando no portão #" + servidor.Portao());
            servidor.Desembarca();
            sleep(100);
            System.out.println(getName() + " desembarcou os passageiros.");
            servidor.LiberaPortao();

        } catch (RemoteException | InterruptedException ex) {
            ex.printStackTrace();
        }
        //}
    }
}
