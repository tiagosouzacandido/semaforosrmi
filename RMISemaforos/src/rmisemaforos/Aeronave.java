/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rmisemaforos;

import java.rmi.Naming;

/**
 *
 * @author Tiago
 */
public class Aeronave {
    public static void main(String[] args){
        try{
            TorreDeControle servidor = (TorreDeControle)Naming.lookup("//localhost//TorreDeControle");
            
        }catch(Exception ex){ex.printStackTrace();}
    }
}
