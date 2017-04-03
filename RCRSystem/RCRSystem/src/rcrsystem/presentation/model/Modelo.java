package rcrsystem.presentation.model;

import Modelo.Inventario;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class Modelo extends java.util.Observable {

    public Modelo() {
        this.inicializar();
        a_errores = new Hashtable<String, String>();
        a_reposiciones = new ArrayList();
    }

    public String obtener_mensaje() {
        return this.a_mensaje;
    }

    public Inventario_Table_Model obtener_modelo_inventarios() {
        return a_inventario_modelo;
    }

    public List<String> obtener_reposiciones() {
        return this.a_reposiciones;
    }

    public void colocar_reposiciones(List<String> reposiciones) {
        this.a_reposiciones = reposiciones;
    }

    public void colocar_modelo_inventarios(Inventario_Table_Model inventarioModel) {
        this.a_inventario_modelo = inventarioModel;
        setChanged();
        notifyObservers(ae_inventario_modelo);
    }

    public void colocar_inventario(List<Inventario> rows, int n) {
        int[] cols = {Inventario_Table_Model.ae_codigo, Inventario_Table_Model.ae_material, Inventario_Table_Model.ae_peso, Inventario_Table_Model.ae_precio};
        a_reposiciones = new ArrayList();
        if (n == 0) {
            revisar_inventario(rows);
        }
        colocar_modelo_inventarios(new Inventario_Table_Model(cols, rows));
    }

    public void revisar_inventario(List<Inventario> rows) {
        for (Inventario inv : rows) {
            if (inv.getCantidad() >= 20000) { //si tiene mas de 20 toneladas está para una venta
                a_reposiciones.add(inv.getMaterial().getNombre());
            }
        }
    }

    public void colocar_mensaje(String mensaje) {
        this.a_mensaje = mensaje;
    }

    public Inventario obtener_inventario_actual() {
        return this.a_inventario_actual;
    }

    public void colocar_inventario_actual(Inventario inventarioCurrent) {
        this.a_inventario_actual = inventarioCurrent;
        setChanged();
        notifyObservers(ae_inventario_actual);
    }

    public Hashtable<String, String> otener_errores() {
        return a_errores;
    }

    public void colocar_errores(Hashtable<String, String> errores) {
        this.a_errores = errores;
    }

    public void limpiar_errores() {
        colocar_errores(new Hashtable<String, String>());
        colocar_mensaje("");

    }

    void inicializar() {
        List<Inventario> rows = new ArrayList<Inventario>();
        colocar_inventario(rows, 0);
        a_inventario_actual = new Inventario();
        a_mensaje = "";
    }

    @Override
    public void addObserver(java.util.Observer o) {
        super.addObserver(o);
        setChanged();
        notifyObservers(ae_inventario_modelo);
    }

    private Inventario_Table_Model a_inventario_modelo;
    private Inventario a_inventario_actual;
    public static Integer ae_inventario_modelo = 0;
    public static Integer ae_inventario_actual = 1;
    private List<String> a_reposiciones;
    private String a_mensaje;
    private Hashtable<String, String> a_errores;
} // Fin de la clase Modelo