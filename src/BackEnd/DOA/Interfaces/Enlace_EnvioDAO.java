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
    boolean Crear_Usuario(int id,String nombre, String apellido, String password, int telefono, String email,String dirreccion);
    boolean Crear_Tienda(int codigo, int propietario, String nombre, String dirreccion, int telefono, String path);
    boolean Modificar_Tienda(int codigo, int propietario, String nombre, String dirreccion, int telefono);
    boolean Elimnar_Tienda(int codigo, int propietario, String nombre, String dirreccion, int telefono);
    boolean CrearProducto(int codigo,String nombre, String Cantidad, String marca, String color, String Tamaño, String path, int sucursal);
    boolean ModificarProducto(int codigo,String nombre, String Cantidad, String marca, String color, String Tamaño, int sucursal);
    boolean Eliminar_Producto(int codigo,String nombre, String Cantidad, String marca, String color, String Tamaño, int sucursal);
    boolean get_Tiendas(int propietario);
    boolean get_Productos(int propietario, int sucursal);
}