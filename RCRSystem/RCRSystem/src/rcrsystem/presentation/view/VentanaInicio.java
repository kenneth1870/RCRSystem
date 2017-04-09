package rcrsystem.presentation.view;

import rcrsystem.presentation.view.VentIngresarRegCompra;
import Modelo.ModeloUsuario;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import rcrsystem.presentation.controller.Compras_Controlador;
import rcrsystem.presentation.controller.Progreso_Autentificacion_Controlador;
import rcrsystem.presentation.controller.Usuario_Controlador;
import rcrsystem.presentation.model.Compras_Modelo;
import rcrsystem.presentation.model.Ventas_Modelo;
import rcrsystem.presentation.controller.Ventas_Controlador;

public class VentanaInicio extends javax.swing.JFrame implements java.util.Observer {

    Usuario_Controlador controlador;
    ModeloUsuario modeloU;
     VentanaCarga ventana;

    public VentanaInicio() {
        initComponents();
        ventana= new VentanaCarga();
        labelUsuarioContraInco.setVisible(false);
        setIconImage(new ImageIcon(getClass().getResource("/rcrsystem/presentation/view/iconos/logoRCR.png")).getImage());
        this.setResizable(false);
        ((JPanel) getContentPane()).setOpaque(false);
        ImageIcon uno = new ImageIcon(this.getClass().getResource("/rcrsystem/presentation/view/iconos/fondo.jpg"));
        JLabel fondo = new JLabel();
        fondo.setIcon(uno);
        getLayeredPane().add(fondo, JLayeredPane.FRAME_CONTENT_LAYER);
        fondo.setBounds(0, 0, uno.getIconWidth(), uno.getIconHeight());

        setMinimumSize(new Dimension(200, 200));
        setLocationRelativeTo(null);
        pack();
    }

    public void setModel(ModeloUsuario model) {
        this.modeloU = model;
        model.addObserver(this);
    }

    public void setController(Usuario_Controlador controller) {
        this.controlador = controller;
    }

    public void login() {
        campoUser.setBorder(null);
        campoUser.setBorder(BorderFactory.createEmptyBorder());
        campoContraseña.setBorder(BorderFactory.createEmptyBorder());
        String resp = controlador.validar_ingreso(campoUser.getText(), campoContraseña.getText());
        controlador.colocar_usuario();
        switch (resp) {
            case "invalido":
                ventana.setVisible(false);
                labelUsuarioContraInco.setText("Usuario o contraseña incorrecta");
                labelUsuarioContraInco.setVisible(true);
                campoUser.setBorder(BorderFactory.createLineBorder(Color.red));
                campoContraseña.setBorder(BorderFactory.createLineBorder(Color.red));
                campoContraseña.setText("");
                this.setVisible(true);
                break;
            case "desconectado":
                ventana.setVisible(false);
                JOptionPane.showMessageDialog(null, "No se pudo conectar a la Base de Datos, "
                        + "verifique que se encuentre el linea", "Error de Conexion", JOptionPane.ERROR_MESSAGE);
                break;

            case "errores":
                ventana.setVisible(false);
                labelUsuarioContraInco.setText("Ingrese su usuario y contraseña");
                labelUsuarioContraInco.setVisible(true);
                campoUser.setBorder(BorderFactory.createLineBorder(Color.red));
                campoContraseña.setBorder(BorderFactory.createLineBorder(Color.red));
                campoContraseña.setText("");
                break;

            case "1"://puesto 
               
                VentanaPrincipal aplicacion = new VentanaPrincipal();
                setVisible(false);
                ventana.setVisible(false);
               aplicacion.init();
                break;

            case "2"://puesto 
               
                VentIngresarRegCompra vistaCompras = new VentIngresarRegCompra();
                setVisible(false);
                Compras_Modelo comprasModelo = new Compras_Modelo();
                Compras_Controlador controladorCompras = new Compras_Controlador(vistaCompras, comprasModelo);
                  vistaCompras.jButton2.setEnabled(false);
                   ventana.setVisible(false);
                vistaCompras.init();
                break;
                case "3"://puesto 
                VentanaVenta vistaVentas = new VentanaVenta();
                setVisible(false);
                Ventas_Modelo ventasModelo = new Ventas_Modelo();
                Ventas_Controlador controladorVentas = new Ventas_Controlador( ventasModelo,vistaVentas);
              vistaVentas.jButton2.setEnabled(false);
                ventana.setVisible(false);
                vistaVentas.setVisible(true);
                break;
            default:
                break;
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        campoUser = new javax.swing.JTextField();
        btnLogin = new javax.swing.JButton();
        campoContraseña = new javax.swing.JPasswordField();
        labelUsuarioContraInco = new javax.swing.JLabel();

        jPanel1.setLayout(new java.awt.GridLayout(1, 0));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Iniciar Sesión");
        setResizable(false);
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                formKeyReleased(evt);
            }
        });

        jPanel2.setOpaque(false);
        jPanel2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jPanel2KeyPressed(evt);
            }
        });

        jLabel1.setText("Usuario: ");

        jLabel2.setText("Contraseña: ");

        campoUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoUserActionPerformed(evt);
            }
        });
        campoUser.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                campoUserKeyPressed(evt);
            }
        });

        btnLogin.setText("Log In");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        campoContraseña.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoContraseñaActionPerformed(evt);
            }
        });
        campoContraseña.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                campoContraseñaKeyPressed(evt);
            }
        });

        labelUsuarioContraInco.setForeground(new java.awt.Color(204, 0, 0));
        labelUsuarioContraInco.setText("Usuario o contraseña incorrecta");
        labelUsuarioContraInco.setToolTipText("");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(campoContraseña, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoUser, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(24, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(labelUsuarioContraInco)
                        .addGap(64, 64, 64))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(btnLogin)
                        .addGap(103, 103, 103))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelUsuarioContraInco)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(campoUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(campoContraseña, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnLogin)
                .addGap(7, 7, 7))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(33, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
 //ventana = new VentanaCarga();
        Progreso_Autentificacion_Controlador carga = new Progreso_Autentificacion_Controlador(ventana);
        btnLogin.setEnabled(false);
         this.setVisible(false);
        carga.execute();
    }//GEN-LAST:event_btnLoginActionPerformed

    private void campoUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoUserActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoUserActionPerformed

    private void campoContraseñaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoContraseñaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoContraseñaActionPerformed

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
   char car = (char) evt.getKeyCode();
        if (car == KeyEvent.VK_ENTER) {
            ventana = new VentanaCarga();
            Progreso_Autentificacion_Controlador p = new Progreso_Autentificacion_Controlador(ventana);
            this.setVisible(false);
            p.execute();
        }
    }//GEN-LAST:event_formKeyPressed

    private void jPanel2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPanel2KeyPressed
 char car = (char) evt.getKeyCode();
        if (car == KeyEvent.VK_ENTER) {
            ventana = new VentanaCarga();
            Progreso_Autentificacion_Controlador p = new Progreso_Autentificacion_Controlador(ventana);
            this.setVisible(false);
            p.execute();
        }        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel2KeyPressed

    private void formKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_formKeyReleased

    private void campoUserKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoUserKeyPressed
 char car = (char) evt.getKeyCode();
        if (car == KeyEvent.VK_ENTER) {
            ventana = new VentanaCarga();
            Progreso_Autentificacion_Controlador p = new Progreso_Autentificacion_Controlador(ventana);
            this.setVisible(false);
            p.execute();
        }            // TODO add your handling code here:
    }//GEN-LAST:event_campoUserKeyPressed

    private void campoContraseñaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoContraseñaKeyPressed

      char car = (char) evt.getKeyCode();
        if (car == KeyEvent.VK_ENTER) {
            ventana = new VentanaCarga();
            Progreso_Autentificacion_Controlador p = new Progreso_Autentificacion_Controlador(ventana);
            this.setVisible(false);
            p.execute();
        }      
    }//GEN-LAST:event_campoContraseñaKeyPressed

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
            //javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
            //JFrame.setDefaultLookAndFeelDecorated(true);
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VentanaInicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaInicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaInicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaInicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaInicio().setVisible(true);
            }
        });
    }

    public void init() {
        setVisible(true);

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnLogin;
    private javax.swing.JPasswordField campoContraseña;
    public javax.swing.JTextField campoUser;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel labelUsuarioContraInco;
    // End of variables declaration//GEN-END:variables

    @Override
    public void update(java.util.Observable updatedModel, Object parametros) {
//        if (parametros != ModeloUsuario.CLIENTE_CURRENT) {
//            return;
//        }
//        //this.tablaInventario.setModel(model.getInventariosModel());
//        this.revalidate();
    }

}
