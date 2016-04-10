/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compi1.proyecto1_cliente.pkg201403775;

import BackEnd.Conexion.Buzon;
import BackEnd.Conexion.Enlace_Envio;
import BackEnd.DOA.Objetos.Usuario;
import FrontEnd.Consola.Interfaz_Consola;
import FrontEnd.Producto.Interfaz_Productos;
import FrontEnd.Tienda.Interfaz_Tiendas;
import FrontEnd.Usuario.Interfaz_Login;

/**
 *
 * @author sinozuke
 */
public class Compi1Proyecto1_Cliente201403775 {
    
    private static final Buzon Buzon1 = new Buzon();
    private static final Thread Buzon = new Thread(Buzon1);
    
    
    public static final Interfaz_Consola consola = new Interfaz_Consola();
    public static final Thread console = new Thread(consola);
    
    public static Usuario usuario = new Usuario();
    public static Interfaz_Login Log_in;
    public static Interfaz_Tiendas Catalogo_Tiendas;
    public static Interfaz_Productos Catalogo_Productos;
    public static Enlace_Envio conexion = new Enlace_Envio();
    
    
    public static void main(String[] args) {
        // TODO code application logic here
        Buzon.start();
        console.start();
        Log_in = new Interfaz_Login();
        Catalogo_Tiendas = new Interfaz_Tiendas();
        Catalogo_Productos = new Interfaz_Productos();
        Log_in.show(true);
    }
    
}
