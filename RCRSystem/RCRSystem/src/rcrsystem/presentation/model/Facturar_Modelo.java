package rcrsystem.presentation.model;

import Modelo.ListaEmpaque;
import Modelo.TotalMaterialVendido;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class Facturar_Modelo extends java.util.Observable {

    public Facturar_Modelo() {
        this.inicializar();
        a_errores = new Hashtable<String, String>();
        a_reposiciones = new ArrayList();
    }

    public Hashtable<String, String> obtener_errores() {
        return a_errores;
    }

    public void colocar_errores(Hashtable<String, String> errores) {
        this.a_errores = errores;
    }

    public Total_Material_Lista_Empaque_Modelo_Tabla obtener_total_material_lista_empaque() {
        return a_total_material_lista_empaque;
    }

    public void colocar_total_material_lista_empaque(Total_Material_Lista_Empaque_Modelo_Tabla totalMLE) {
        this.a_total_material_lista_empaque = totalMLE;
        setChanged();
        notifyObservers(ae_total_material_lista_empaque_modelo);
    }

    public Listas_Empaques_Fac_Modelo_Tabla obtener_modelo_factura() {
        return a_modelo_factura;
    }

    public void colocar_modelo_factura(Listas_Empaques_Fac_Modelo_Tabla facturaModel) {
        this.a_modelo_factura = facturaModel;
        setChanged();
        notifyObservers(ae_modelo_factura);
    }

    public ListaEmpaque obtener_lista_empaque_actual() {
        return a_lista_empaque_actual;
    }

    public void colocar_lista_empaque_actual(ListaEmpaque listaEmpCurrent) {
        this.a_lista_empaque_actual = listaEmpCurrent;
        setChanged();
        notifyObservers(ae_lista_empaque_actual);
    }

    public TotalMaterialVendido obtener_total_material_vendido_lista_empaque_actual() {
        return a_total_material_vendido_lista_empaque_actual;
    }

    public void colocar_total_material_vendido_lista_empaque_actual(TotalMaterialVendido totalMVLEcurrent) {
        this.a_total_material_vendido_lista_empaque_actual = totalMVLEcurrent;
        setChanged();
        notifyObservers(ae_total_lista_material_vendido_lista_empaque_actual);
    }

    public void colocar_listas_empaques(List<ListaEmpaque> rows, int n) {
        int[] cols = {Inventario_Table_Model.ae_codigo, Inventario_Table_Model.ae_material, Inventario_Table_Model.ae_peso, Inventario_Table_Model.ae_precio};
        a_reposiciones = new ArrayList();
        if (n == 0) {
            revisar_lista_empaque(rows);
        }
        colocar_modelo_factura(new Listas_Empaques_Fac_Modelo_Tabla(cols, rows));
    }

    public void colocar_total_material_vendido_lista_empaque(List<TotalMaterialVendido> rows, int n) {
        int[] cols = {Total_Material_Lista_Empaque_Modelo_Tabla.ae_material, Total_Material_Lista_Empaque_Modelo_Tabla.ae_cantidad, Total_Material_Lista_Empaque_Modelo_Tabla.ae_descripcion, Total_Material_Lista_Empaque_Modelo_Tabla.ae_cantidad_bultos, Total_Material_Lista_Empaque_Modelo_Tabla.ae_precio_unitario, Total_Material_Lista_Empaque_Modelo_Tabla.ae_importe};
        a_reposiciones = new ArrayList();
        colocar_total_material_lista_empaque(new Total_Material_Lista_Empaque_Modelo_Tabla(cols, rows));
    }

    public void revisar_lista_empaque(List<ListaEmpaque> rows) {
        for (ListaEmpaque l : rows) {
            a_reposiciones.add(l.getCodigoL());
        }
    }

    public void inicializar() {
        List<ListaEmpaque> rows = new ArrayList<ListaEmpaque>();
        List<TotalMaterialVendido> rows2 = new ArrayList<TotalMaterialVendido>();
        colocar_listas_empaques(rows, 0);
        colocar_total_material_vendido_lista_empaque(rows2, 0);
        a_lista_empaque_actual = new ListaEmpaque();
        a_total_material_vendido_lista_empaque_actual = new TotalMaterialVendido();
        a_mensaje = "";
    }

    @Override
    public void addObserver(java.util.Observer o) {
        super.addObserver(o);
        setChanged();
        notifyObservers(ae_modelo_factura);
        setChanged();
        notifyObservers(ae_lista_empaque_actual);
        setChanged();
        notifyObservers(ae_total_material_lista_empaque_modelo);
        setChanged();
        notifyObservers(ae_total_lista_material_vendido_lista_empaque_actual);
    }

    public String obtener_mensaje() {
        return this.a_mensaje;
    }

    public void colocar_mensaje(String mensaje) {
        this.a_mensaje = mensaje;
    }

    public void limpiar_errores() {
        colocar_errores(new Hashtable<String, String>());
        colocar_mensaje("");
    }
    
    private Listas_Empaques_Fac_Modelo_Tabla a_modelo_factura;
    private Total_Material_Lista_Empaque_Modelo_Tabla a_total_material_lista_empaque;
    private ListaEmpaque a_lista_empaque_actual;
    private TotalMaterialVendido a_total_material_vendido_lista_empaque_actual;
    public static Integer ae_modelo_factura = 0;
    public static Integer ae_lista_empaque_actual = 1;
    public static Integer ae_total_material_lista_empaque_modelo = 2;
    public static Integer ae_total_lista_material_vendido_lista_empaque_actual = 3;
    private List<Integer> a_reposiciones;
    private Hashtable<String, String> a_errores;
    private String a_mensaje;
} // Fin de la clase Facturar_Modelo