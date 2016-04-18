/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BackEnd.DOA.Interfaces;

/**
 *
 * @author sinozuke
 */

public interface Enlace_EnvioDAO {
    boolean Conectar(String usuario,String contraseña);
    boolean Consola(String peticion);
    boolean Crear_Usuario(String id,String nombre, String apellido, String password, String telefono, String email,String dirreccion);
    boolean Crear_Tienda(String codigo, String propietario, String nombre, String dirreccion, String telefono, String path);
    boolean Modificar_Tienda(String codigo, String propietario, String nombre, String dirreccion, String telefono);
    boolean Elimnar_Tienda(String codigo, String propietario, String nombre, String dirreccion, String telefono);
    boolean CrearProducto(String codigo,String nombre, String Cantidad, String marca, String color, String Tamaño, String path, String sucursal);
    boolean ModificarProducto(String codigo,String nombre, String Cantidad, String marca, String color, String Tamaño, String sucursal);
    boolean Eliminar_Producto(String codigo,String nombre, String Cantidad, String marca, String color, String Tamaño, String sucursal);
    boolean get_Tiendas(String propietario);
    boolean get_Productos(String propietario, String sucursal);
}