/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FrontEnd.Producto;

import BackEnd.DOA.Objetos.Relevantes.Producto;

import static compi1.proyecto1_cliente.pkg201403775.Compi1Proyecto1_Cliente201403775.conexion;
import static compi1.proyecto1_cliente.pkg201403775.Compi1Proyecto1_Cliente201403775.usuario;
import static compi1.proyecto1_cliente.pkg201403775.Compi1Proyecto1_Cliente201403775.Catalogo_Tiendas;
import static compi1.proyecto1_cliente.pkg201403775.Compi1Proyecto1_Cliente201403775.productos;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author sinozuke
 */
public class Interfaz_Productos extends javax.swing.JFrame {

    public static final Interfaz_Producto_Nuevo Nuevo_Producto = new Interfaz_Producto_Nuevo();
    public static final Interfaz_Producto_Modificar Modificar_Producto = new Interfaz_Producto_Modificar();
    public Producto modificar;
    public boolean esta=false;
    
    /**
     * Creates new form Interfaz_Productos
     */
    public Interfaz_Productos() {
        initComponents();
        this.bloquear();
    }

    public void get_productos(){
        productos.clear();
        this.txtcantidad.setText("");
        this.txtcodigo.setText("");
        this.txtcolor.setText("");
        this.txtmarca.setText("");
        this.txtnombre.setText("");
        this.txtsucursal.setText("");
        this.txttamaño.setText("");
        this.lblimg.setIcon(null);
        this.catalogo_productos.removeAllItems();
        conexion.get_Productos(String.valueOf(usuario.getId()),String.valueOf(Catalogo_Tiendas.seleccionar_productos().getCodigo()));
    }
    
    public void agregar_Producto(Producto producto){
        productos.add(producto);
    }
    
    public void carga_productos(){
        productos.stream().forEach((Producto p)->{
            catalogo_productos.addItem(p.getNombrre());
        });
    }
    
    private void selecionar_modificar(){
        productos.stream().forEach((Producto p)->{
            if(p.getNombrre().equals((String)catalogo_productos.getSelectedItem())){
                this.modificar=p;
            }
        });
    }
    
    private void mostrar_info(){
        productos.stream().forEach((Producto p)->{
            if(p.getNombrre().equals((String)catalogo_productos.getSelectedItem())){
                this.desbloquear();
                txtcodigo.setText(String.valueOf(p.getId()));
                txtnombre.setText(p.getNombrre());
                txtmarca.setText(p.getMarca());
                txtcolor.setText(p.getColor());
                txtcantidad.setText(String.valueOf(p.getCantidad()));
                txtsucursal.setText(String.valueOf(p.getSucursal()));
                txttamaño.setText(String.valueOf(p.getTamaño()));
                try{
                    lblimg.setIcon(retornarimagen(p.getImg()));
                }catch(Exception ex){
                    lblimg.setText("no imagen");
                }
                this.bloquear();
            }
        });
    }
    
    private void bloquear(){
        this.infopanel.setEnabled(false);
    }
    
    private void desbloquear(){
        this.infopanel.setEnabled(true);
    }
    
    private ImageIcon retornarimagen(String codigo_img){
        ImageIcon foto = new ImageIcon(codigo_img);
        ImageIcon imagen = new ImageIcon(foto.getImage().getScaledInstance(lblimg.getWidth(), lblimg.getHeight(), Image.SCALE_DEFAULT));
        return imagen;
    }
    
    private Producto localizar_producto(){
        Producto encontrado = new Producto();
        productos.stream().forEach((Producto p)->{
            if(p.getNombrre().equals((String)catalogo_productos.getSelectedItem())){
                encontrado.setCantidad(p.getCantidad());
                encontrado.setColor(p.getColor());
                encontrado.setId(p.getId());
                encontrado.setImg(p.getImg());
                encontrado.setNombrre(p.getNombrre());
                encontrado.setSucursal(p.getSucursal());
                encontrado.setTamaño(p.getTamaño());
                encontrado.setMarca(p.getMarca());
            }
        });
        return encontrado;
    }
    
    private void regresar(){
        this.modificar=null;
        productos.clear();
        Catalogo_Tiendas.setVisible(true);
        this.dispose();
    }
    
    private void eliminar(){
        if(this.localizar_producto()!=null){
            Producto eliminar = this.localizar_producto();
            conexion.Eliminar_Producto(String.valueOf(eliminar.getId()), eliminar.getNombrre(), String.valueOf(eliminar.getCantidad()), eliminar.getMarca(), eliminar.getColor(), String.valueOf(eliminar.getTamaño()), String.valueOf(eliminar.getSucursal()));
            this.setVisible(true);
            JOptionPane.showMessageDialog(this, "Esperando Respuesta del Servidor");
        }else{
            JOptionPane.showMessageDialog(this, "No se ha seleccionado un producto para realizar esta accion");
        }
    }
    
    private void Crear_Nuevo(){
        this.setVisible(false);
        Nuevo_Producto.setVisible(true);
    }
    
    private void Modificar(){
        this.selecionar_modificar();
        this.setVisible(false);
        Interfaz_Productos.Modificar_Producto.mostrarinfo(modificar.getId(),
                modificar.getNombrre(),
                String.valueOf(modificar.getCantidad()),
                modificar.getMarca(),
                modificar.getColor(),
                String.valueOf(modificar.getTamaño()),
                modificar.getImg(),
                modificar.getSucursal());
        Interfaz_Productos.Modificar_Producto.setVisible(true);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        catalogo_productos = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        infopanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtcodigo = new javax.swing.JTextField();
        txtnombre = new javax.swing.JTextField();
        txtcantidad = new javax.swing.JTextField();
        txtmarca = new javax.swing.JTextField();
        txtcolor = new javax.swing.JTextField();
        txttamaño = new javax.swing.JTextField();
        txtsucursal = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        btnmodi = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        btneli = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        lblimg = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        catalogo_productos.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                catalogo_productosItemStateChanged(evt);
            }
        });

        jLabel1.setText("Listado de Productos:");
        jLabel1.setToolTipText("");

        infopanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel2.setText("informacion...");

        jLabel4.setText("Codigo:");

        jLabel5.setText("Nombre:");

        jLabel6.setText("Cantidad");

        jLabel7.setText("Marca");

        jLabel8.setText("Color:");

        jLabel9.setText("Tamaño");

        jLabel10.setText("Sucursal:");

        txtcodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtcodigoKeyTyped(evt);
            }
        });

        txtnombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtcodigoKeyTyped(evt);
            }
        });

        txtcantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtcodigoKeyTyped(evt);
            }
        });

        txtmarca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtcodigoKeyTyped(evt);
            }
        });

        txtcolor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtcodigoKeyTyped(evt);
            }
        });

        txttamaño.setToolTipText("");
        txttamaño.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtcodigoKeyTyped(evt);
            }
        });

        txtsucursal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtcodigoKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout infopanelLayout = new javax.swing.GroupLayout(infopanel);
        infopanel.setLayout(infopanelLayout);
        infopanelLayout.setHorizontalGroup(
            infopanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(infopanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(infopanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(infopanelLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 174, Short.MAX_VALUE))
                    .addGroup(infopanelLayout.createSequentialGroup()
                        .addGroup(infopanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(infopanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtcodigo)
                            .addComponent(txtnombre)
                            .addComponent(txttamaño)
                            .addComponent(txtcantidad)
                            .addComponent(txtmarca)
                            .addComponent(txtcolor)
                            .addComponent(txtsucursal))))
                .addContainerGap())
        );
        infopanelLayout.setVerticalGroup(
            infopanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(infopanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(infopanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtcodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(infopanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtnombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(infopanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtcantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(infopanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtmarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(infopanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtcolor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(infopanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txttamaño, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(infopanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtsucursal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(24, Short.MAX_VALUE))
        );

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

        btneli.setText("Eliminar");
        btneli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneliActionPerformed(evt);
            }
        });

        jButton4.setText("Regresar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btneli, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnmodi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnmodi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE)
                    .addComponent(btneli, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jLabel11.setText("Imagen");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(catalogo_productos, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(infopanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(0, 161, Short.MAX_VALUE))
                    .addComponent(lblimg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(catalogo_productos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(infopanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblimg, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        this.esta=false;
        this.regresar();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void btnmodiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnmodiActionPerformed
        this.Modificar();
    }//GEN-LAST:event_btnmodiActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.Crear_Nuevo();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btneliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneliActionPerformed
        this.eliminar();
    }//GEN-LAST:event_btneliActionPerformed

    private void catalogo_productosItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_catalogo_productosItemStateChanged
        this.mostrar_info();
    }//GEN-LAST:event_catalogo_productosItemStateChanged

    private void txtcodigoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcodigoKeyTyped
        evt.consume();
    }//GEN-LAST:event_txtcodigoKeyTyped

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btneli;
    public javax.swing.JButton btnmodi;
    public javax.swing.JComboBox<String> catalogo_productos;
    private javax.swing.JPanel infopanel;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lblimg;
    private javax.swing.JTextField txtcantidad;
    private javax.swing.JTextField txtcodigo;
    private javax.swing.JTextField txtcolor;
    private javax.swing.JTextField txtmarca;
    private javax.swing.JTextField txtnombre;
    private javax.swing.JTextField txtsucursal;
    private javax.swing.JTextField txttamaño;
    // End of variables declaration//GEN-END:variables
}
