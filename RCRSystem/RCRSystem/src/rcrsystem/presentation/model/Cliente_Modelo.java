package rcrsystem.presentation.model;

import Modelo.Cliente;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class Cliente_Modelo extends java.util.Observable{
    
    public Cliente_Modelo(){
    }
    
    void inicializar() {
        List<Cliente> rows = new ArrayList<>();
        colocar_clientes(rows, 0);
        a_cliente_actual = new Cliente();
        a_filtro = new Cliente();
        this.inicializar_2();
    }

    public Cliente obtener_filtro() {
        return a_filtro;
    }

    public void colocar_filtro(Cliente filtro) {
        this.a_filtro = filtro;
    }

    public Cliente_Modelo_Tabla obtener_modelo_cliente() {
        return a_modelo_cliente;
    }

    public void colocar_modelo_cliente(Cliente_Modelo_Tabla clienteModel) {
        this.a_modelo_cliente = clienteModel;
        setChanged();
        notifyObservers(ae_modelo_usuario);
    }

    public void colocar_clientes(List<Cliente> rows, int n) {
        int[] cols = {Cliente_Modelo_Tabla.ae_id, Cliente_Modelo_Tabla.ae_nombre};
        colocar_modelo_cliente (new Cliente_Modelo_Tabla(cols, rows));
    }
    
    public List <Cliente> obtener_clientes(){
        return this.a_modelo_cliente.a_filas;
    }
 
    @Override
    public void addObserver(java.util.Observer o) {
        super.addObserver(o);
        setChanged();
        notifyObservers(ae_modelo_usuario);
          setChanged();
        notifyObservers(ae_usuario_actual);
    }
    
    void inicializar_2(){
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
   
    public Cliente obtener_cliente_actual() {
        return a_cliente_actual;
    }

    public void colocar_cliente_actual(Cliente clienteCurrent) {
        this.a_cliente_actual = clienteCurrent;
          setChanged();
        notifyObservers(ae_usuario_actual);
    }
    
    private Cliente_Modelo_Tabla a_modelo_cliente;
    private Cliente a_cliente_actual;
    private Cliente a_filtro;
    public static Integer ae_modelo_usuario = 0;
    public static Integer ae_usuario_actual = 1;
    private Hashtable<String, String> a_errores;
    private String a_mensaje;
    private int a_modo;
} // Fin de la clase Cliente_Modelo