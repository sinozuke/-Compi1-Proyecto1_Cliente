/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FrontEnd.Tienda;

import BackEnd.DOA.Objetos.Base64;
import BackEnd.DOA.Objetos.Tienda;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;


import static compi1.proyecto1_cliente.pkg201403775.Compi1Proyecto1_Cliente201403775.conexion;
import static compi1.proyecto1_cliente.pkg201403775.Compi1Proyecto1_Cliente201403775.Catalogo_Productos;
import static compi1.proyecto1_cliente.pkg201403775.Compi1Proyecto1_Cliente201403775.Log_in;
import static compi1.proyecto1_cliente.pkg201403775.Compi1Proyecto1_Cliente201403775.usuario;
import static compi1.proyecto1_cliente.pkg201403775.Compi1Proyecto1_Cliente201403775.tiendas;
import java.awt.Image;

/**
 *
 * @author sinozuke
 */
public class Interfaz_Tiendas extends javax.swing.JFrame {

    public static final Interfaz_Tienda_Nuevo Nueva_tienda = new Interfaz_Tienda_Nuevo();
    public static final Interfaz_Tienda_Modificar Modificar_Tienda = new Interfaz_Tienda_Modificar();
    public static Tienda modificar;


    /**
     * Creates new form Interfaz_Tiendas
     */

    public Interfaz_Tiendas() {
        initComponents();
        this.bloquear();
    }
    
    public void get_tiendas(){
        conexion.get_Tiendas(usuario.getId());
    }
    
    public void ingresar_tienda(Tienda tienda){
        tiendas.add(tienda);
    }
    
    public void cargar_tiendas(){
        tiendas.stream().forEach((t) -> {
            this.agregar_tienda(t);
        });
    }

    public void mostrar_mensaje(String mensaje){
        JOptionPane.showMessageDialog(this, "La Tienda ha sido: " + mensaje);
        this.setVisible(true);
    }
    
    public Tienda seleccionar_productos(){
        return this.localizar_tienda();
    }

    
    private void mostrar_info(){
        tiendas.stream().forEach((Tienda t)->{
            if(t.getNombre().equals((String)catalogo_tiendas.getSelectedItem())){
                this.desbloquear();
                txtcodigo.setText(String.valueOf(t.getCodigo()));
                txtid.setText(String.valueOf(t.getPropietario()));
                txtnombre.setText(t.getNombre());
                txtdirreccion.setText(t.getDirreccion());
                txttelefono.setText(String.valueOf(t.getTelefono()));
                if(t.getImg().equals("vacio")){
                    lblimg.setText("no imagen");
                }else{
                    try{
                        lblimg.setIcon(retornarimagen(t.getImg()));
                    }catch(Exception ex){
                        lblimg.setText("no imagen");
                    }
                }
                this.bloquear();
            }
        });
    }
    
    private void nueva_tienda(){
        this.setVisible(false);
        Interfaz_Tiendas.Nueva_tienda.setVisible(true);
    }
    
    private void salir(){
            Log_in.setVisible(true);
            this.dispose();
    }
    
    private void modificar_tienda(){
        if(localizar_tienda().getCodigo()!=0){
            Interfaz_Tiendas.modificar = localizar_tienda();
            this.setVisible(false);
            Interfaz_Tiendas.Modificar_Tienda.colocarinfo(modificar.getCodigo(), modificar.getPropietario(), modificar.getNombre(), modificar.getDirreccion(), modificar.getTelefono(), modificar.getImg());
            Interfaz_Tiendas.Modificar_Tienda.setVisible(true);
        }else{
            JOptionPane.showMessageDialog(this, "no se ha encontrado la tienda a modificar");
        }
    }
    
    private void Mostrar_Productos(){
        if(localizar_tienda().getCodigo()!=0){
            Interfaz_Tiendas.modificar = localizar_tienda();
            this.setVisible(false);
            Catalogo_Productos.get_productos();
            Catalogo_Productos.setVisible(true);
        }else{
            JOptionPane.showMessageDialog(this, "no se ha encontrado la tienda para mostrar sus productos");
        }
    }
    
    private Tienda localizar_tienda(){
        Tienda encontrada=new Tienda();
        tiendas.stream().forEach((Tienda t)->{
            if(t.getNombre().equals((String)catalogo_tiendas.getSelectedItem())){
                encontrada.setCodigo(t.getCodigo());
                encontrada.setDirreccion(t.getDirreccion());
                encontrada.setImg(t.getImg());
                encontrada.setNombre(t.getNombre());
                encontrada.setPropietario(t.getPropietario());
                encontrada.setTelefono(t.getTelefono());
            }
        });
        return encontrada;
    }
        
    private void bloquear(){
        this.info_panel.setEnabled(false);
    }
    
    private void desbloquear(){
        this.info_panel.setEnabled(true);
    }
    
    private ImageIcon retornarimagen(String codigo_img){
        try{
            Base64.decodeToFile(codigo_img, "outputImage.jpg");
            ImageIcon foto = new ImageIcon("outputImage.jpg");
            ImageIcon imagen = new ImageIcon(foto.getImage().getScaledInstance(lblimg.getWidth(), lblimg.getHeight(), Image.SCALE_DEFAULT));
            return imagen;
        }catch(Exception ex){
            System.out.println(ex.getCause());
            return null;
        }
    }
    
    private void agregar_tienda(Tienda tienda){
        catalogo_tiendas.addItem(tienda.getNombre());
    }
    
    private void eliminar(){
        if(localizar_tienda()!=null){
            Tienda eliminar = localizar_tienda();
            conexion.Elimnar_Tienda(eliminar.getCodigo(), eliminar.getPropietario(), eliminar.getNombre(),eliminar.getDirreccion(), eliminar.getTelefono());   
            this.setVisible(false);
            JOptionPane.showMessageDialog(this, "Esperando Respuesta Del Servidor");
        }else{
            JOptionPane.showMessageDialog(this, "Algo Mal Ocurrio");
        }
    }
    
    public void eliminardelista(int codigo){
        ArrayList<Tienda> nuevalista = new ArrayList();
        tiendas.stream().forEach((Tienda t)->{
            if(t.getCodigo()!=codigo){
                nuevalista.add(t);
            }
        });
        tiendas.clear();
        tiendas = (ArrayList<Tienda>)nuevalista.clone();
        catalogo_tiendas.removeAllItems();
        this.cargar_tiendas();
    }
    
    public void verificar_butones(){
       
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        btnmodi = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        btneliminar = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        btnmostrar = new javax.swing.JButton();
        catalogo_tiendas = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        info_panel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtcodigo = new javax.swing.JTextField();
        txtid = new javax.swing.JTextField();
        txtnombre = new javax.swing.JTextField();
        txtdirreccion = new javax.swing.JTextField();
        txttelefono = new javax.swing.JTextField();
        lblimg = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel3.setText("Acciones");

        btnmodi.setText("Modificar");
        btnmodi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnmodiActionPerformed(evt);
            }
        });

        jButton2.setText("Crear Nuevo");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        btneliminar.setText("Eliminar");
        btneliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneliminarActionPerformed(evt);
            }
        });

        jButton4.setText("Salir");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        btnmostrar.setText("Mostrar Productos");
        btnmostrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnmostrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btneliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnmodi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(btnmostrar, javax.swing.GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnmodi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnmostrar)
                .addGap(6, 6, 6)
                .addComponent(btneliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        catalogo_tiendas.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                catalogo_tiendasItemStateChanged(evt);
            }
        });

        jLabel1.setText("Listado de Tiendas:");
        jLabel1.setToolTipText("");

        info_panel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel2.setText("informacion...");

        jLabel4.setText("Codigo:");

        jLabel5.setText("Id Propietario:");

        jLabel6.setText("Nombre de la Tienda");

        jLabel7.setText("Dirreccion");

        jLabel8.setText("Telefono");

        txtcodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtcodigoKeyTyped(evt);
            }
        });

        txtid.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtidKeyTyped(evt);
            }
        });

        txtnombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtidKeyTyped(evt);
            }
        });

        txtdirreccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtidKeyTyped(evt);
            }
        });

        txttelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtidKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout info_panelLayout = new javax.swing.GroupLayout(info_panel);
        info_panel.setLayout(info_panelLayout);
        info_panelLayout.setHorizontalGroup(
            info_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(info_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(info_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(info_panelLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(info_panelLayout.createSequentialGroup()
                        .addGroup(info_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(info_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtcodigo, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
                            .addComponent(txtid)
                            .addComponent(txtnombre)
                            .addComponent(txtdirreccion)
                            .addComponent(txttelefono))))
                .addContainerGap())
        );
        info_panelLayout.setVerticalGroup(
            info_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(info_panelLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(info_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtcodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(info_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(info_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtnombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(info_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtdirreccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(info_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txttelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel11.setText("Imagen");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(55, 55, 55))
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(catalogo_tiendas, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(info_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(lblimg, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(info_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(8, 8, 8)
                        .addComponent(lblimg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(catalogo_tiendas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        catalogo_tiendas.getAccessibleContext().setAccessibleName("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        this.salir();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.nueva_tienda();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void catalogo_tiendasItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_catalogo_tiendasItemStateChanged
        this.mostrar_info();
    }//GEN-LAST:event_catalogo_tiendasItemStateChanged
    
    private void btneliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneliminarActionPerformed
        this.eliminar();
    }//GEN-LAST:event_btneliminarActionPerformed

    private void btnmodiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnmodiActionPerformed
        this.modificar_tienda();
    }//GEN-LAST:event_btnmodiActionPerformed

    private void btnmostrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnmostrarActionPerformed
        this.Mostrar_Productos();
    }//GEN-LAST:event_btnmostrarActionPerformed

    private void txtcodigoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcodigoKeyTyped
        evt.consume();
    }//GEN-LAST:event_txtcodigoKeyTyped

    private void txtidKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtidKeyTyped
        evt.consume();
    }//GEN-LAST:event_txtidKeyTyped
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btneliminar;
    public javax.swing.JButton btnmodi;
    public javax.swing.JButton btnmostrar;
    public javax.swing.JComboBox<String> catalogo_tiendas;
    private javax.swing.JPanel info_panel;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lblimg;
    private javax.swing.JTextField txtcodigo;
    private javax.swing.JTextField txtdirreccion;
    private javax.swing.JTextField txtid;
    private javax.swing.JTextField txtnombre;
    private javax.swing.JTextField txttelefono;
    // End of variables declaration//GEN-END:variables
}