package rcrsystem.presentation.model;

import Modelo.Proveedor;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class Proveedor_Modelo extends java.util.Observable {

    void inicializar() {
        List<Proveedor> rows = new ArrayList<Proveedor>();
        colocar_proveedores(rows, 0);
        a_proveedor_actual = new Proveedor();
        a_filtro = new Proveedor();
        this.inicializar_2();

    }

    public Proveedor obtener_filtro() {
        return a_filtro;
    }

    public void colocar_filtro(Proveedor filtro) {
        this.a_filtro = filtro;
    }

    public Proveedor_Modelo_Tabla obtener_modelo_proveedor() {
        return a_modelo_proveedor;
    }

    public void colocar_modelo_proveedor(Proveedor_Modelo_Tabla proveedorModel) {
        this.a_modelo_proveedor = proveedorModel;
        setChanged();
        notifyObservers(ae_modelo_usuario);
    }

    public void colocar_proveedores(List<Proveedor> rows, int n) {
        int[] cols = {Proveedor_Modelo_Tabla.ae_id, Proveedor_Modelo_Tabla.ae_nombre};
        colocar_modelo_proveedor(new Proveedor_Modelo_Tabla(cols, rows));
    }

    public List<Proveedor> obtener_proveedores() {
        return this.a_modelo_proveedor.a_filas;
    }

    @Override
    public void addObserver(java.util.Observer o) {
        super.addObserver(o);
        setChanged();
        notifyObservers(ae_modelo_usuario);
        setChanged();
        notifyObservers(ae_usuario_actual);
    }

    void inicializar_2() {
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

    public Proveedor obtener_proveedor_actual() {
        return a_proveedor_actual;
    }

    public void colocar_proveedor_actual(Proveedor proveedorCurrent) {
        this.a_proveedor_actual = proveedorCurrent;
        setChanged();
        notifyObservers(ae_usuario_actual);
    }
    
    private Proveedor_Modelo_Tabla a_modelo_proveedor;
    private Proveedor a_proveedor_actual;
    private Proveedor a_filtro;
    public static Integer ae_modelo_usuario = 0;
    public static Integer ae_usuario_actual = 1;
    private Hashtable<String, String> a_errores;
    private String a_mensaje;
    private int a_modo;
} // Fin de la clase Proveedor_Modelo