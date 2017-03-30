package rcrsystem.presentation.controller;

import Modelo.ModeloUsuario;
import rcrsystem.presentation.view.VentanaInicio;

public class Usuario_Controlador {

    public Usuario_Controlador() {
        a_modelo_usuario = new ModeloUsuario();
    }

    public Usuario_Controlador(ModeloUsuario model, VentanaInicio view) {
        this.a_modelo_usuario = model;
        this.a_vista = view;
        view.setController(this);
        view.setModel(model);
    }
    
    public void colocar_usuario(){
        rcrsystem.Aplicacion.ae_usuario=a_modelo_usuario.getUsuarioCurrent();
    }

    public String validar_ingreso(String identificacion, String pass) {
        return a_modelo_usuario.validarIngreso(identificacion, pass);
    }
    
    private VentanaInicio a_vista;
    private ModeloUsuario a_modelo_usuario;
} // Fin de la clase Usuario_Controlador