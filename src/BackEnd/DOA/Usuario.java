/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BackEnd.DOA;

import java.util.ArrayList;

/**
 *
 * @author sinozuke
 */
public class Usuario {
    
    private int id;
    private String nombre;
    private String apellido;
    private String password;
    private int telefono;
    private String email;
    private String dirreccion;
    public static ArrayList<Tienda> tiendas;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDirreccion() {
        return dirreccion;
    }

    public void setDirreccion(String dirreccion) {
        this.dirreccion = dirreccion;
    }

    public static ArrayList<Tienda> getTiendas() {
        return tiendas;
    }

    public static void setTiendas(ArrayList<Tienda> tiendas) {
        Usuario.tiendas = tiendas;
    }
    
}
