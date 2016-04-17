/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BackEnd.DOA.Objetos.Relevantes;

/**
 *
 * @author sinozuke
 */
public class Tienda {
    
    private int codigo=0;
    private int propietario=0;
    private String Nombre="vacio";
    private String Dirreccion="vacio";
    private String telefono="vacio";
    private String img="vacio";
    
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getPropietario() {
        return propietario;
    }

    public void setPropietario(int propietario) {
        this.propietario = propietario;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getDirreccion() {
        return Dirreccion;
    }

    public void setDirreccion(String Dirreccion) {
        this.Dirreccion = Dirreccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
    
}
