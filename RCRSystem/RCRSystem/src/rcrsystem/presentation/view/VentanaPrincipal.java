package rcrsystem.presentation.view;

import Modelo.Usuario;
import rcrsystem.presentation.view.VentIngresarRegCompra;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import rcrsystem.presentation.controller.Admin_Cliente_Controlador;
import rcrsystem.presentation.controller.Admin_Proveedores_Controlador;
import rcrsystem.presentation.controller.Admin_Usuario_Controlador;
import rcrsystem.presentation.controller.Compras_Controlador;
import rcrsystem.presentation.controller.Editar_Inventario_Controlador;
import rcrsystem.presentation.controller.Inventario_Controlador;
import rcrsystem.presentation.controller.Materiales_Controlador;
import rcrsystem.presentation.controller.Usuario_Controlador;
import rcrsystem.presentation.controller.Ventas_Controlador;
import rcrsystem.presentation.model.Cliente_Modelo;
import rcrsystem.presentation.model.Compras_Modelo;
import rcrsystem.presentation.model.Material_Modelo;
import rcrsystem.presentation.model.Modelo;
import rcrsystem.presentation.model.Proveedor_Modelo;
import rcrsystem.presentation.model.Usuario_Modelo;
import rcrsystem.presentation.model.Ventas_Modelo;

public class VentanaPrincipal extends javax.swing.JFrame {

    public VentanaPrincipal() {
        initComponents();
        vista = new VentanaInventario();
        Modelo modelo = new Modelo();
        vistaEdicion = new VentanaEdicionInventario(vista, true);
        vistaCompras = new VentIngresarRegCompra();
        Editar_Inventario_Controlador controlador = new Editar_Inventario_Controlador(vistaEdicion, modelo);
        Compras_Modelo comprasModelo = new Compras_Modelo();
        Compras_Controlador controladorCompras = new Compras_Controlador(vistaCompras, comprasModelo);

        vistaVentas = new VentanaVenta();
        Ventas_Modelo ventasModelo = new Ventas_Modelo();
        Ventas_Controlador controladorVentas = new Ventas_Controlador(ventasModelo, vistaVentas);
        setIconImage(new ImageIcon(getClass().getResource("/rcrsystem/presentation/view/iconos/logoRCR.png")).getImage());
        this.setResizable(false);
        setLocationRelativeTo(null);
    }
    public static VentanaInventario vista;
    public static VentanaEdicionInventario vistaEdicion;
    public static VentIngresarRegCompra vistaCompras;
    public static VentanaVenta vistaVentas;

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        btnVentInv = new javax.swing.JButton();
        btnVentVentas = new javax.swing.JButton();
        btnVentCom = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        btn_cerrar_sesion = new javax.swing.JMenuItem();
        btn_salir = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        btn_calculadora = new javax.swing.JMenuItem();
        jMenu7 = new javax.swing.JMenu();
        btn_ayuda = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Menú Principal");
        setResizable(false);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Módulos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14))); // NOI18N

        btnVentInv.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnVentInv.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rcrsystem/presentation/view/iconos/inventario.png"))); // NOI18N
        btnVentInv.setText("Inventario");
        btnVentInv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVentInvActionPerformed(evt);
            }
        });

        btnVentVentas.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnVentVentas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rcrsystem/presentation/view/iconos/ventas.png"))); // NOI18N
        btnVentVentas.setText("Ventas");
        btnVentVentas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVentVentasActionPerformed(evt);
            }
        });

        btnVentCom.setBackground(new java.awt.Color(255, 255, 255));
        btnVentCom.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnVentCom.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rcrsystem/presentation/view/iconos/compras.png"))); // NOI18N
        btnVentCom.setText("Compras");
        btnVentCom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVentComActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(btnVentInv, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnVentVentas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(btnVentCom, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnVentInv)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnVentVentas)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnVentCom)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rcrsystem/presentation/view/iconos/logoRCR.png"))); // NOI18N

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Administrar", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14))); // NOI18N

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rcrsystem/presentation/view/iconos/user.png"))); // NOI18N
        jButton1.setText("Usuarios");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rcrsystem/presentation/view/iconos/box.png"))); // NOI18N
        jButton2.setText("Materiales");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rcrsystem/presentation/view/iconos/Cliente.png"))); // NOI18N
        jButton4.setText("Clientes");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rcrsystem/presentation/view/iconos/proveedor.png"))); // NOI18N
        jButton3.setText("Proveedores");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton3))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton3)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rcrsystem/presentation/view/iconos/folder.png"))); // NOI18N
        jMenu1.setText("Archivo");

        btn_cerrar_sesion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rcrsystem/presentation/view/iconos/disconnect.png"))); // NOI18N
        btn_cerrar_sesion.setText("Cerrar Sesión");
        btn_cerrar_sesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cerrar_sesionActionPerformed(evt);
            }
        });
        jMenu1.add(btn_cerrar_sesion);

        btn_salir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rcrsystem/presentation/view/iconos/door_out.png"))); // NOI18N
        btn_salir.setText("Salir");
        btn_salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_salirActionPerformed(evt);
            }
        });
        jMenu1.add(btn_salir);

        jMenuBar1.add(jMenu1);

        jMenu6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rcrsystem/presentation/view/iconos/cog.png"))); // NOI18N
        jMenu6.setText("Herramientas");

        btn_calculadora.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rcrsystem/presentation/view/iconos/calculator.png"))); // NOI18N
        btn_calculadora.setText("Calculadora");
        btn_calculadora.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_calculadoraActionPerformed(evt);
            }
        });
        jMenu6.add(btn_calculadora);

        jMenuBar1.add(jMenu6);

        jMenu7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rcrsystem/presentation/view/iconos/help.png"))); // NOI18N
        jMenu7.setText("Ayuda");

        btn_ayuda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rcrsystem/presentation/view/iconos/comment.png"))); // NOI18N
        btn_ayuda.setText("Acerca de");
        btn_ayuda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ayudaActionPerformed(evt);
            }
        });
        jMenu7.add(btn_ayuda);

        jMenuBar1.add(jMenu7);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(85, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnVentComActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVentComActionPerformed
        this.setVisible(false);
        vistaCompras.setVisible(true);
    }//GEN-LAST:event_btnVentComActionPerformed

    private void btnVentVentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVentVentasActionPerformed
        this.setVisible(false);
        vistaVentas.setVisible(true);
    }//GEN-LAST:event_btnVentVentasActionPerformed

    private void btnVentInvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVentInvActionPerformed
        this.setVisible(false);
        VentanaInventario aplicacion = new VentanaInventario();

        rcrsystem.presentation.model.Modelo modelo = new rcrsystem.presentation.model.Modelo();

        VentanaInventario vista = new VentanaInventario();
        Inventario_Controlador controlador = new Inventario_Controlador(vista, modelo);
        this.setVisible(false);
        vista.setVisible(true);
    }//GEN-LAST:event_btnVentInvActionPerformed

    private void btn_ayudaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ayudaActionPerformed

        JOptionPane.showMessageDialog(this, "Estamos trabajando", "error", JOptionPane.ERROR_MESSAGE);
    }//GEN-LAST:event_btn_ayudaActionPerformed

    private void btn_calculadoraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_calculadoraActionPerformed
        Process runtimeProcess;
        try {

            System.out.println(System.getProperty("os.name"));
            String comandoCalculadora = System.getProperty("os.name").startsWith("Windows") ? "calc" : "gcalctool";
            runtimeProcess = Runtime.getRuntime().exec(comandoCalculadora);

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btn_calculadoraActionPerformed

    private void btn_cerrar_sesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cerrar_sesionActionPerformed
        this.setVisible(false);
        rcrsystem.Aplicacion.ae_usuario = new Usuario();
        rcrsystem.Aplicacion.ae_vista_principal = new VentanaInicio();
        rcrsystem.Aplicacion.ae_vista_principal.setController(new Usuario_Controlador());
        rcrsystem.Aplicacion.ae_vista_principal.setVisible(true);
    }//GEN-LAST:event_btn_cerrar_sesionActionPerformed

    private void btn_salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_salirActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btn_salirActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.setVisible(false);
        VentanaAdminUsuarios vista = new VentanaAdminUsuarios();
        Usuario_Modelo modelo = new Usuario_Modelo();
        Admin_Usuario_Controlador controlador = new Admin_Usuario_Controlador(vista, modelo);
        vista.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.setVisible(false);
        VentanaMaterial vista = new VentanaMaterial();
        Material_Modelo modelo = new Material_Modelo();
        Materiales_Controlador controlador = new Materiales_Controlador(vista, modelo);
        vista.setVisible(true);   // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        this.setVisible(false);
        VentanaAdminProveedor vista = new VentanaAdminProveedor();
        Proveedor_Modelo modelo = new Proveedor_Modelo();
        Admin_Proveedores_Controlador controlador = new Admin_Proveedores_Controlador(vista, modelo);
        vista.setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        this.setVisible(false);
        VentanaAdminClientes vista = new VentanaAdminClientes();
        Cliente_Modelo modelo = new Cliente_Modelo();
        Admin_Cliente_Controlador controlador = new Admin_Cliente_Controlador(vista, modelo);
        vista.setVisible(true);
    }//GEN-LAST:event_jButton4ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
            //javax.swing.UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnVentCom;
    private javax.swing.JButton btnVentInv;
    private javax.swing.JButton btnVentVentas;
    private javax.swing.JMenuItem btn_ayuda;
    private javax.swing.JMenuItem btn_calculadora;
    private javax.swing.JMenuItem btn_cerrar_sesion;
    private javax.swing.JMenuItem btn_salir;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    // End of variables declaration//GEN-END:variables

    void init() {
        setVisible(true);

    }
}
