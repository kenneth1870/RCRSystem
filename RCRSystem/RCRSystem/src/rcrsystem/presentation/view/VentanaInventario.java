package rcrsystem.presentation.view;

import Modelo.Usuario;
import java.awt.Toolkit;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import rcrsystem.presentation.controller.Bultos_C_Controlador;
import rcrsystem.presentation.controller.Editar_Inventario_Controlador;
import rcrsystem.presentation.model.Modelo;
import rcrsystem.presentation.controller.Inventario_Controlador;
import rcrsystem.presentation.controller.Progreso_Menu_Prin_Controlador;
import rcrsystem.presentation.controller.Usuario_Controlador;

public class VentanaInventario extends javax.swing.JFrame implements java.util.Observer {

    Inventario_Controlador controller;
    Modelo model;
    public static VentanaEdicionInventario vistaEdicion;

    public VentanaCarga ventana;

    public Inventario_Controlador getController() {
        return controller;
    }

    public void setController(Inventario_Controlador controller) {
        this.controller = controller;
    }

    public Modelo getModel() {
        return model;
    }

    public void setModel(Modelo model) {
        this.model = model;
        model.addObserver(this);
    }

    /*       VentIngresarRegCompra vistaCompras = new VentIngresarRegCompra();
        ComprasModelo comprasModelo = new ComprasModelo();
        ComprasControlador controladorCompras = new ComprasControlador(vistaCompras, comprasModelo);
        vistaCompras.inicializar();*/
    public VentanaInventario() {
        initComponents();
        //   vistaEdicion = new VentanaEdicionInventario(this, true);
        setIconImage(new ImageIcon(getClass().getResource("/rcrsystem/presentation/view/iconos/logoRCR.png")).getImage());
        setLocationRelativeTo(null);
        this.setResizable(false);
        ventana = new VentanaCarga();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaInventario = new javax.swing.JTable();
        editar_JButton = new javax.swing.JButton();
        volver_JButton = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        btn_cerrar_sesion = new javax.swing.JMenuItem();
        btn_salir = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        btn_calculadora = new javax.swing.JMenuItem();
        jMenu7 = new javax.swing.JMenu();
        btn_ayuda = new javax.swing.JMenuItem();

        jMenuItem1.setText("jMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Inventario");
        setAlwaysOnTop(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setLayout(new java.awt.GridBagLayout());

        tablaInventario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Código", "Material", "Cantidad", "Precio"
            }
        ));
        tablaInventario.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tablaInventario.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                tablaInventarioAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        tablaInventario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaInventarioMouseClicked(evt);
            }
        });
        tablaInventario.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                tablaInventarioPropertyChange(evt);
            }
        });
        jScrollPane1.setViewportView(tablaInventario);

        editar_JButton.setText("Editar");
        editar_JButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editar_JButtonActionPerformed(evt);
            }
        });

        volver_JButton.setText("Volver");
        volver_JButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                volver_JButtonActionPerformed(evt);
            }
        });

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
                .addGap(22, 22, 22)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 460, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(volver_JButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(editar_JButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(97, 97, 97)
                .addComponent(editar_JButton)
                .addGap(36, 36, 36)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(volver_JButton)
                .addContainerGap(138, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tablaInventarioPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_tablaInventarioPropertyChange

    }//GEN-LAST:event_tablaInventarioPropertyChange

    private void tablaInventarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaInventarioMouseClicked
        if (evt.getClickCount() == 2) {
            //int row = Integer.valueOf((String) this.tablaInventario.getValueAt(this.tablaInventario.getSelectedRow(), 0));

            rcrsystem.presentation.model.Modelo_B_C modelo = new rcrsystem.presentation.model.Modelo_B_C();

            VentConsultaMatInv vista = new VentConsultaMatInv();
            Bultos_C_Controlador control = new Bultos_C_Controlador(vista, modelo,
                    (String) this.tablaInventario.getValueAt(this.tablaInventario.getSelectedRow(), 0));
            vista.setVisible(true);

        }

    }//GEN-LAST:event_tablaInventarioMouseClicked

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
//        int opt = JOptionPane.showConfirmDialog(this,
//                "¿Desea cerrar la aplicación?", "Cerrar Aplicacion",
//                JOptionPane.YES_NO_OPTION);
//        if (opt == JOptionPane.YES_OPTION) {
//            System.exit(0);
//        }
    }//GEN-LAST:event_formWindowClosing

    private void editar_JButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editar_JButtonActionPerformed
        int row = this.tablaInventario.getSelectedRow();
        Editar_Inventario_Controlador controladorEdicion = new Editar_Inventario_Controlador(VentanaPrincipal.vistaEdicion, model);
        controller.abrir(row);        // TODO add your handling code here:

    }//GEN-LAST:event_editar_JButtonActionPerformed

    private void volver_JButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_volver_JButtonActionPerformed
        this.setVisible(false);// TODO add your handling code here:
        Progreso_Menu_Prin_Controlador v = new Progreso_Menu_Prin_Controlador(ventana);
        v.execute();
        // new VentanaPrincipal().setVisible(true);
    }//GEN-LAST:event_volver_JButtonActionPerformed

    private void tablaInventarioAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_tablaInventarioAncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_tablaInventarioAncestorAdded

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

    private void btn_ayudaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ayudaActionPerformed

        JOptionPane.showMessageDialog(this, "Estamos trabajando", "error", JOptionPane.ERROR_MESSAGE);
    }//GEN-LAST:event_btn_ayudaActionPerformed

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
            java.util.logging.Logger.getLogger(VentanaInventario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaInventario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaInventario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaInventario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaInventario().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem btn_ayuda;
    private javax.swing.JMenuItem btn_calculadora;
    private javax.swing.JMenuItem btn_cerrar_sesion;
    private javax.swing.JMenuItem btn_salir;
    public javax.swing.JButton editar_JButton;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaInventario;
    public javax.swing.JButton volver_JButton;
    // End of variables declaration//GEN-END:variables

    void init() {
        setVisible(true);
    }

    @Override
    public void update(java.util.Observable updatedModel, Object parametros) {
        if (parametros != Modelo.ae_inventario_modelo) {
            return;
        }
        this.tablaInventario.setModel(model.obtener_modelo_inventarios());
        this.revalidate();
        if (!model.obtener_reposiciones().isEmpty()) {
            String materiales = "¡Atención!\n";
            materiales = materiales + "Los siguientes materiales tienen suficiente cantidad para venta:\n";
            for (int i = 0; i < model.obtener_reposiciones().size(); i++) {
                materiales = materiales + model.obtener_reposiciones().get(i) + "\n";
            }
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(this, materiales, "", JOptionPane.INFORMATION_MESSAGE);
            model.colocar_reposiciones(new ArrayList());
        }
    }
}
