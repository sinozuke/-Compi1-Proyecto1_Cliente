/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BackEnd.Conexion;

import java.net.ServerSocket;
import java.net.Socket;
import BackEnd.Analizadores.Lexico_reply;
import BackEnd.Analizadores.AnalizadorSintactico;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import static compi1.proyecto1_cliente.pkg201403775.Compi1Proyecto1_Cliente201403775.consola;
/**
 *
 * @author sinozuke
 */
public class Buzon implements Runnable{

    private ServerSocket Buzon1;
    private Socket via;
    protected Lexico_reply ANAL_LEX;
    protected AnalizadorSintactico ANAL_SIN;
    
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
        while(true){
            try {
                via = Buzon1.accept();
                String respuesta = new DataInputStream(via.getInputStream()).readUTF();
                consola.txtsalida.setText(respuesta);
                ANAL_LEX = new Lexico_reply(new ByteArrayInputStream(respuesta.getBytes()));
                ANAL_SIN = new AnalizadorSintactico(ANAL_LEX);
                ANAL_SIN.parse();
                via.close();
            } catch (Exception ex) {
                System.out.println(ex.getCause());
            }            
        }
    }
    
}
