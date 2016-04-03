/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BackEnd.Conexion;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
/**
 *
 * @author sinozuke
 */
public class Buzon implements Runnable{

    private ServerSocket Buzon1;
    private DataInputStream recividos;
    private Socket via;
   
    public Buzon(){
        try{
            Buzon1 = new ServerSocket(3501);
            System.out.println("Buzon Creado con Exito...");
        }catch(Exception ex){
            System.out.println("Algo Salio mal con el buzon" + ex.getCause());
        }
    }

    @Override
    public void run() {
        String Recivido;
        while(true){
            try {
                via = Buzon1.accept();
                recividos = new DataInputStream(via.getInputStream());
                Recivido = recividos.readUTF();
                System.out.println(Recivido);
                via.close();
            } catch (IOException ex) {
                System.out.println(ex.getCause());
            }
            
        }
    }
    
}
