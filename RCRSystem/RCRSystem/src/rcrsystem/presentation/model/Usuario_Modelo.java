package rcrsystem.presentation.model;

import Modelo.Usuario;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class Usuario_Modelo extends java.util.Observable{
    
    void inicializar() {
        List<Usuario> rows = new ArrayList<Usuario>();
        colocar_usuarios(rows, 0);
        a_usuario_actual = new Usuario();
        a_filtro= new Usuario();
        this.incializar_2();
    }

    public Usuario obtener_filtro() {
        return a_filtro;
    }

    public void colocar_filtro(Usuario filtro) {
        this.a_filtro = filtro;
    }

    public Usuario_Modelo_Tabla obtener_modelo_usuario() {
        return a_modelo_usuario;
    }

    public void colocar_modelo_usuario(Usuario_Modelo_Tabla usuarioModel) {
        this.a_modelo_usuario = usuarioModel;
          setChanged();
        notifyObservers(ae_modelo_usuario);
    }

     public void colocar_usuarios(List<Usuario> rows, int n) {
        int[] cols = {Usuario_Modelo_Tabla.ae_id, Usuario_Modelo_Tabla.ae_nombre, Usuario_Modelo_Tabla.ae_telefono, Usuario_Modelo_Tabla.ae_permiso};
        colocar_modelo_usuario (new Usuario_Modelo_Tabla(cols, rows));
    }
     
    public List <Usuario> obtener_usuarios(){
        return this.a_modelo_usuario.a_filas;
    }
 
    @Override
    public void addObserver(java.util.Observer o) {
        super.addObserver(o);
        setChanged();
        notifyObservers(ae_modelo_usuario);
          setChanged();
        notifyObservers(ae_usuario_actual);
    }
    
    public void incializar_2(){
        a_errores = new Hashtable<String, String>();
        a_mensaje = "";
    }

    public Hashtable<String, String> obtener_errores() {
        return a_errores;
    }

    public void colocar_errores(Hashtable<String, String> errores) {
        this.a_errores = errores;
    }

    public String obtener_mensaje() {
        return a_mensaje;
    }

    public void colocar_mensaje(String mensaje) {
        this.a_mensaje = mensaje;
    }

    public int obtener_modo() {
        return a_modo;
    }

    public void colocar_modo(int modo) {
        this.a_modo = modo;
    }
    
    public void limpiar_errores() {
        colocar_errores(new Hashtable<String, String>());
        colocar_mensaje("");
    }
   
    public Usuario obtener_usuario_actual() {
        return a_usuario_actual;
    }

    public void colocar_usuario_actual(Usuario usuarioCurrent) {
        this.a_usuario_actual = usuarioCurrent;
          setChanged();
        notifyObservers(ae_usuario_actual);
    }
    
    private Usuario_Modelo_Tabla a_modelo_usuario;
    private Usuario a_usuario_actual;
    private Usuario a_filtro;
    public static Integer ae_modelo_usuario = 0;
    public static Integer ae_usuario_actual = 1;
    private Hashtable<String, String> a_errores;
    private String a_mensaje;
    private int a_modo;
} // Fin de la clase Usuario_Modelo