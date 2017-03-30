package rcrsystem.presentation.controller;

import Modelo.Usuario;
import Modelo.dao.UsuarioDAO;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import rcrsystem.presentation.model.Usuario_Modelo;
import rcrsystem.presentation.view.VentanaAdminUsuarios;
import rcrsystem.presentation.view.VentanaUsuario;

public class Admin_Usuario_Controlador {

    public Admin_Usuario_Controlador(VentanaAdminUsuarios vista, Usuario_Modelo modelo) {
        this.a_vista = vista;
        this.a_modelo = modelo;
        a_usuarios = new ArrayList();
        cargar();
        vista.setController(this);
        vista.setModel(modelo);
    }

    public void cargar() {
        this.colocar_usuarios(UsuarioDAO.getUsuarios(rcrsystem.Aplicacion.ae_usuario.getIdentificacion()));
        a_modelo.colocar_usuarios(a_usuarios, 0);
    }

    public List<Usuario> obtener_usuarios() {
        return a_usuarios;
    }

    public void colocar_usuarios(List<Usuario> usuarios) {
        this.a_usuarios = usuarios;
    }

    public void buscar() {
        a_modelo.colocar_filtro(new Usuario());
        a_modelo.obtener_filtro().setIdentificacion(a_vista.jTextField_filtro.getText());
        List<Usuario> rows = new ArrayList();
        for (Usuario p : this.obtener_usuarios()) {
            if (p.getIdentificacion().contains(a_modelo.obtener_filtro().getIdentificacion())) {
                rows.add(p);
            }
        }
        a_modelo.colocar_usuarios(rows, 0);
    }
    
    public void reiniciar_filtro(){
        a_vista.jTextField_filtro.setText("");
    }
    
    public void borrar(int row) {
        try {
            Usuario seleccionada = a_modelo.obtener_modelo_usuario().obtener_fila_a(row);
            int ax = JOptionPane.showConfirmDialog(null, "¿Seguro (a) que desea eliminar el elemento?");
            if (ax == JOptionPane.YES_OPTION) {
              UsuarioDAO.eliminar(seleccionada);
               JOptionPane.showMessageDialog(null, "¡Eliminado correctamente!");
              this.cargar();
            }
        } catch (Exception ex) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, "No ha seleccionado ningún elemento");
        }
        this.buscar();
    }
    
    public void editar(int row, VentanaUsuario ventana) {
        try {
            a_modelo.limpiar_errores();
            Usuario seleccionada = a_modelo.obtener_modelo_usuario().obtener_fila_a(row);
            a_modelo.colocar_modo(rcrsystem.Aplicacion.ae_modo_editar);
            a_modelo.colocar_usuario_actual(seleccionada);
            ventana.jTextField_id.setEnabled(false);
            ventana.setVisible(true);
           
        } catch (Exception e) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, "¡No ha seleccionado ningún elemento!");
        }
    }
     
 public void pre_agregar() {
        a_modelo.limpiar_errores();
        a_modelo.colocar_modo(rcrsystem.Aplicacion.ae_modo_agregar);
        Usuario usuario = new Usuario();
        a_modelo.colocar_usuario_actual(usuario);
    }
 
    private VentanaAdminUsuarios a_vista;
    private Usuario_Modelo a_modelo;
    private List<Usuario> a_usuarios;
} // Fin de la clase Admin_Usuario_Controlador