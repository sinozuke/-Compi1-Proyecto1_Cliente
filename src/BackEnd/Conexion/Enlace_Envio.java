/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BackEnd.Conexion;

import BackEnd.DOA.Interfaces.Enlace_EnvioDAO;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.text.MessageFormat;

/**
 *
 * @author sinozuke
 */
public class Enlace_Envio implements Enlace_EnvioDAO{
    
        private Socket Cliente;
        private DataOutputStream enviado;
    
    private boolean enlazar(){
        try{
            Cliente = new Socket(InetAddress.getLocalHost().getHostAddress(),3500);
            enviado = new DataOutputStream(Cliente.getOutputStream());
            System.out.println("enlace Creado con Exito");
            return true;
        }catch(Exception ex){
            System.out.println("Conexion Fallida: " + ex.getMessage());
            return false;
        }
    }
        
    private void Terminar_Conexion() {
            try {
                Cliente.close();
            } catch (IOException ex) {
                System.out.println(MessageFormat.format("Error al intentar Cerrar Puerto:{0}", ex.getCause()));
            }
    }

    @Override
    public boolean Conectar(String usuario, String contraseña) {
        if(this.enlazar()){
            try{
                enviado.writeUTF(MessageFormat.format(  "$request$\n" +
                                                            "$InicioUsuario$\n" +
                                                                "$id${0}$id-$\n" +
                                                                "$password${1}$password-$\n" +
                                                            "$InicioUsuario-$\n" +
                                                        "$request-$", usuario, contraseña));
                this.Terminar_Conexion();
                return true;
            }catch(Exception ex){
                System.out.println(ex.getCause());
                return false;
            }
        }else{
            System.out.println("imposible conectar al socket");
            return false;
        }
    }

    @Override
    public boolean Consola(String peticion) {
        if(this.enlazar()){
            try{
                enviado.writeUTF(peticion);
                this.Terminar_Conexion();
                return true;
            }catch(Exception ex){
                System.out.println(ex.getCause());
                this.Terminar_Conexion();
                return false;
            }        
        }else{
            System.out.println("imposible conectar al socket");
            return false;
        }
    }

    @Override
    public boolean Crear_Usuario(String id, String nombre, String apellido, String password, String telefono, String email, String dirreccion) {
        if(this.enlazar()){
            try{
                enviado.writeUTF(MessageFormat.format("$request$\n"+
                                                            "$CrearUsuario$\n"+
                                                                "$id${0}$id-$\n"+
                                                                "$nombre$\"{1}\"$nombre-$\n"+
                                                                "$apellido$\"{2}\"$apellido-$\n"+
                                                                "$password${3}$password-$\n"+
                                                                "$telefono${4}$telefono-$\n"+
                                                                "$email${5}$email-$\n"+
                                                                "$direccion$\"{6}\"$direccion-$\n"+
                                                            "$CrearUsuario-$\n"+
                                                        "$request-$", id, nombre, apellido, password, telefono,email,dirreccion));
                this.Terminar_Conexion();
                return true;
            }catch(Exception ex){
                System.out.println(ex.getCause());
                return false;
            }
        }else{
            System.out.println("imposible conectar al socket");
            return false;
        }
    }

    @Override
    public boolean Crear_Tienda(String codigo, String propietario, String nombre, String dirreccion, String telefono, String path) {
        if(this.enlazar()){
                    try{
                        enviado.writeUTF(MessageFormat.format("$request$\n" +
                                                "$tienda tipo=\"crear\"$\n" +
                                                    "$codigo${0}$codigo-$\n" +
                                                    "$propietario${1}$propietario-$\n" +
                                                    "$nombre$\"{2}\"$nombre-$\n" +
                                                    "$direccion$\"{3}\"$direccion-$\n" +
                                                    "$telefono${4}$telefono-$\n" +
                                                    "$img$\"{5}\"$img-$\n" +
                                                "$tienda-$\n" +
                                            "$request-$", codigo,propietario,nombre,dirreccion,telefono,path));
                this.Terminar_Conexion();
                return true;
            }catch(Exception ex){
                System.out.println(ex.getCause());
                this.Terminar_Conexion();
                return false;
            }
        }else{
            System.out.println("imposible conectar al socket");
            return false;
        }

    }

    @Override
    public boolean Modificar_Tienda(String codigo, String propietario, String nombre, String dirreccion, String telefono) {
        if(this.enlazar()){
            try{
                enviado.writeUTF(MessageFormat.format("$request$\n" +
                                                        "$tienda tipo=\"modificacion\", " +
                                                            "codigo={0}, " +
                                                            "propietario={1}, " +
                                                            "nombre=\"{2}\", " +
                                                            "direccion=\"{3}\", " +
                                                            "telefono={4} " +
                                                        "-$\n" +
                                                    "$request-$", codigo,propietario,nombre,dirreccion,telefono));
                this.Terminar_Conexion();
                return true;
            }catch(Exception ex){
                System.out.println(ex.getCause());
                this.Terminar_Conexion();
                return false;
            }
        }else{
            System.out.println("imposible conectar al socket");
            return false;
        }
    }
    
    @Override
    public boolean Elimnar_Tienda(String codigo, String propietario, String nombre, String dirreccion, String telefono) {
        if(this.enlazar()){
            try{
                enviado.writeUTF(MessageFormat.format("$request$\n" +
                                                        "$tienda tipo=\"Eliminar\", " +
                                                            "codigo={0}, " +
                                                            "propietario={1}, " +
                                                            "nombre=\"{2}\", " +
                                                            "direccion=\"{3}\", " +
                                                            "telefono={4} " +
                                                        "-$\n" +
                                                    "$request-$", codigo,propietario,nombre,dirreccion,telefono));
                this.Terminar_Conexion();
                return true;
            }catch(Exception ex){
                System.out.println(ex.getCause());
                this.Terminar_Conexion();
                return false;
            }   
        }else{
            System.out.println("imposible conectar al socket");
            return false;
        }
    }
    
    @Override
    public boolean CrearProducto(String codigo, String nombre, String Cantidad, String marca, String color, String Tamaño, String path, String sucursal) {
        if(this.enlazar()){
            try{
                enviado.writeUTF(MessageFormat.format("$request$\n" +
                                                        "$producto tipo=\"crear\"$\n" +
                                                            "$codigo${0}$codigo-$\n" +
                                                            "$nombre$\"{1}\"$nombre-$\n" +
                                                            "$cantidad${2}$cantidad-$\n" +
                                                            "$marca$\"{3}\"$marca-$\n" +
                                                            "$color$\"{4}\"$color-$\n" +
                                                            "$tamaño${5}$tamaño-$\n" +
                                                            "$img$\"{6}\"$img-$\n" +
                                                            "$sucursal${7}$sucursal-$\n" +
                                                        "$producto-$\n" +
                                                    "$request-$", codigo, nombre, Cantidad, marca, color, Tamaño, path, sucursal));
                this.Terminar_Conexion();
                return true;
            }catch(Exception ex){
                System.out.println(ex.getCause());
                this.Terminar_Conexion();
                return false;
            }
        }else{
            System.out.println("imposible conectar al socket");
            return false;
        }
    }

    @Override
    public boolean ModificarProducto(String codigo, String nombre, String Cantidad, String marca, String color, String Tamaño, String sucursal) {
        if(this.enlazar()){
            try{
                enviado.writeUTF(MessageFormat.format("$request$\n" +
                                                        "$producto tipo=\"modificar\", " +
                                                            "codigo={0}, " +
                                                            "nombre=\"{1}\", " +
                                                            "cantidad={2}, " +
                                                            "marca=\"{3}\", " +
                                                            "color=\"4\", " +
                                                            "tamaño={5}, " +
                                                            "sucursal={6} " +
                                                        "-$\n" +
                                                    "$request-$", codigo, nombre, Cantidad, marca, color, Tamaño, sucursal));
                this.Terminar_Conexion();
                return true;
            }catch(Exception ex){
                System.out.println(ex.getCause());
                this.Terminar_Conexion();
                return false;
            }
        }else{
            System.out.println("imposible conectar al socket");
            return false;
        }
    }
    
    @Override
    public boolean Eliminar_Producto(String codigo, String nombre, String Cantidad, String marca, String color, String Tamaño, String sucursal) {
        if(this.enlazar()){
            try{
                enviado.writeUTF(MessageFormat.format("$request$\n" +
                                                        "$producto tipo=\"eliminar\", " +
                                                            "codigo={0}, " +
                                                            "nombre=\"{1}\", " +
                                                            "cantidad={2}, " +
                                                            "marca=\"{3}\", " +
                                                            "color=\"4\", " +
                                                            "tamaño={5}, " +
                                                            "sucursal={6} " +
                                                        "$producto-$\n" +
                                                    "$request-$", codigo, nombre, Cantidad, marca, color, Tamaño, sucursal));
                this.Terminar_Conexion();
                return true;
            }catch(Exception ex){
                System.out.println(ex.getCause());
                this.Terminar_Conexion();
                return false;
            }     
        }else{
            System.out.println("imposible conectar al socket");
            return false;
        }
    }

    @Override
    public boolean get_Tiendas(String propietario) {
        if(this.enlazar()){
            try{
                enviado.writeUTF(MessageFormat.format(  "$request$\n" +
                                                            "$get tipo=\"tiendas\" propietario={0} -$\n" +
                                                        "$request-$", propietario));
                this.Terminar_Conexion();
                return true;
            }catch(Exception ex){
                System.out.println(ex.getCause());
                return false;
            }
        }else{
            System.out.println("imposible conectar al socket");
            return false;
        }
    }

    @Override
    public boolean get_Productos(String propietario, String sucursal) {
        if(this.enlazar()){
            try{
                enviado.writeUTF(MessageFormat.format(  "$request$\n" +
                                                            "$get tipo=\"productos\" propietario={0} sucursal={1} -$\n" +
                                                        "$request-$", propietario, sucursal));
                this.Terminar_Conexion();
                return true;
            }catch(Exception ex){
                System.out.println(ex.getCause());
                return false;
            }
        }else{
            System.out.println("imposible conectar al socket");
            return false;
        }
    }
}