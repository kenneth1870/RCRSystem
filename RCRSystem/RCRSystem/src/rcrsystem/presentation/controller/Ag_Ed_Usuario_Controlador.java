package rcrsystem.presentation.controller;

import Modelo.Usuario;
import Modelo.dao.UsuarioDAO;
import java.util.List;
import rcrsystem.presentation.model.Usuario_Modelo;
import rcrsystem.presentation.view.VentanaUsuario;

public class Ag_Ed_Usuario_Controlador {

    public Ag_Ed_Usuario_Controlador(VentanaUsuario vista, Usuario_Modelo modelo, Admin_Usuario_Controlador controlador) {
        this.a_vista = vista;
        this.a_modelo = modelo;
        this.a_controlador = controlador;
        vista.setControlador(this);
        vista.setModelo(modelo);
    }

    public void agregar() {
        Usuario usuario = new Usuario();
        a_modelo.limpiar_errores();
        
        if (a_vista.jTextField_nombre.getText().length() == 0) {
            a_modelo.obtener_errores().put("nombre", "Debe ingresar el nombre del usuario");
        } else {
            usuario.setNombre(a_vista.jTextField_nombre.getText());
        }
        if (a_vista.jTextField_id.getText().length() == 0) {
            a_modelo.obtener_errores().put("id", "Debe ingresar la identificación del usuario");
        } else {
            usuario.setIdentificacion(a_vista.jTextField_id.getText());
        }
        if (a_vista.jTextField_telf.getText().length() == 0) {
            a_modelo.obtener_errores().put("telefono", "Debe ingresar el número de teléfono del usuario");
        } else {
            usuario.setNumtelefono(Integer.parseInt(a_vista.jTextField_telf.getText()));
        }
        usuario.setContraseña(a_vista.jPasswordField_normal.getText());
        if (a_vista.jPasswordField_normal.getText().length() < 6 || a_vista.jPasswordField_normal.getText().length() > 6) {
            a_modelo.obtener_errores().put("contraseña", "La contraseña debe ser de 6 digitos");
        }
        if (a_vista.jPasswordField_confirmar.getText().length() == 0 && a_vista.jPasswordField_confirmar.getText().length() == 0) {

            a_modelo.obtener_errores().put("confirmar", "Debe repetir la contraseña ingresada");
        } else if (a_vista.jPasswordField_confirmar.getText().length() == 0) {
            a_modelo.obtener_errores().put("confirmar", "Debe repetir la contraseña ingresada");
            usuario.setContraseña(a_vista.jPasswordField_normal.getText());
        } else if (!a_vista.jPasswordField_normal.getText().equals(a_vista.jPasswordField_confirmar.getText())) {
            a_modelo.obtener_errores().put("coinciden", "Las contraseñas ingresadas no coinciden");
            usuario.setContraseña(a_vista.jPasswordField_normal.getText());
        } else {
            usuario.setContraseña(a_vista.jPasswordField_normal.getText());
        }

        if (a_vista.jRadioButton_admin.isSelected()) {
            usuario.setPuesto(1);
        }
        if (a_vista.jRadioButton_compras.isSelected()) {
            usuario.setPuesto(2);
        }
        if (a_vista.jRadioButton_venta.isSelected()) {
            usuario.setPuesto(3);
        }

        if (a_modelo.obtener_errores().isEmpty()) {
            try {
                switch (a_modelo.obtener_modo()) {
                    case rcrsystem.Aplicacion.ae_modo_agregar:
                        UsuarioDAO.grabar(usuario);
                        a_modelo.colocar_mensaje("¡Usuario Agregado!");
                        a_modelo.colocar_usuario_actual(new Usuario());
                        a_modelo.colocar_mensaje("");
                        a_modelo.colocar_filtro(new Usuario());
                        a_controlador.reiniciar_filtro();

                        List<Usuario> usuarios = UsuarioDAO.getUsuarios(rcrsystem.Aplicacion.ae_usuario.getIdentificacion());
                        a_modelo.colocar_usuarios(usuarios, 0);
                        a_controlador.colocar_usuarios(usuarios);
                        a_modelo.limpiar_errores();
                        a_vista.setVisible(false);
                        break;

                    case rcrsystem.Aplicacion.ae_modo_editar:

                        UsuarioDAO.actualizar(usuario);
                        a_modelo.colocar_mensaje("¡Usuario modificado!");
                        a_modelo.colocar_usuario_actual(new Usuario());
                        a_modelo.colocar_mensaje("");

                        a_modelo.colocar_filtro(new Usuario());
                        a_controlador.reiniciar_filtro();
                        List<Usuario> usuarios2 = UsuarioDAO.getUsuarios(rcrsystem.Aplicacion.ae_usuario.getIdentificacion());
                        a_modelo.colocar_usuarios(usuarios2, 0);
                        a_controlador.colocar_usuarios(usuarios2);
                        a_modelo.limpiar_errores();
                        a_vista.setVisible(false);
                        break;
                }
            } catch (Exception e) {
                a_modelo.obtener_errores().put("id", "¡Usuario ya existe!");
                a_modelo.colocar_mensaje("¡El usuario ya está registrado!");
                a_modelo.colocar_usuario_actual(usuario);
            }
        } else {
            a_modelo.colocar_mensaje("¡Hay errores!");
            a_modelo.colocar_usuario_actual(usuario);
        }
    }
    private VentanaUsuario a_vista;
    private Usuario_Modelo a_modelo;
    private Admin_Usuario_Controlador a_controlador;
} // Fin de la clase Ag_Ed_Usuario_Controlador