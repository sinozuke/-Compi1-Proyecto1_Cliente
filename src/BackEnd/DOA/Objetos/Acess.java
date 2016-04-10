/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BackEnd.DOA.Objetos;

/**
 *
 * @author sinozuke
 */
public class Acess {
    private String id;
    private String valor;
    private final StringBuilder reply;

    public Acess() {
        this.id = null;
        this.valor = null;
        this.reply = null;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public void concatenar(String conc){
        this.reply.append(conc);
    }

    public String getreply(){
        return this.reply.toString();
    }
}