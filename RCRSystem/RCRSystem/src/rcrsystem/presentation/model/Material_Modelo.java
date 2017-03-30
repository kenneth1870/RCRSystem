package rcrsystem.presentation.model;

import Modelo.AdministrarMaterial;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;


public class Material_Modelo extends java.util.Observable {
    
    public void inicializar() {
        List<AdministrarMaterial> rows = new ArrayList<AdministrarMaterial>();
        colocar_materiales(rows, 0);
        a_material_actual = new AdministrarMaterial();
        a_filtro= new AdministrarMaterial();
        inicializar_2();
    }

    public AdministrarMaterial obtener_filtro() {
        return a_filtro;
    }

    public void setFiltro(AdministrarMaterial filtro) {
        this.a_filtro = filtro;
    }

   
    public Modelo_Tabla_Material obtener_modelo_material() {
       return this.a_modelo_material;
    }
  
    public void colocar_modelo_material(Modelo_Tabla_Material materialModel) {
        this.a_modelo_material = materialModel;
        setChanged();
        notifyObservers(ae_modelo_material);
    }

    public void colocar_materiales(List<AdministrarMaterial> rows, int n) {
        int[] cols = {Modelo_Tabla_Material.ae_codigo, Modelo_Tabla_Material.ae_descripcion, Modelo_Tabla_Material.ae_precio_paca, Modelo_Tabla_Material.ae_precio_saca};
        colocar_modelo_material (new Modelo_Tabla_Material(cols, rows));
    }
      
    @Override
    public void addObserver(java.util.Observer o) {
        super.addObserver(o);
        setChanged();
        notifyObservers(ae_modelo_material);
          setChanged();
        notifyObservers(ae_material_actual);
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
    
    public AdministrarMaterial obtener_material_actual() {
        return a_material_actual;
    }
   
    public void colocar_material_actual(AdministrarMaterial materialCurrent) {
        this.a_material_actual = materialCurrent;
        setChanged();
        notifyObservers(ae_material_actual);
    }
          
    private Modelo_Tabla_Material a_modelo_material;
    private AdministrarMaterial a_material_actual;
    private AdministrarMaterial a_filtro;
    public static Integer ae_modelo_material = 0;
    public static Integer ae_material_actual = 1;
    private Hashtable<String, String> a_errores;
    private String a_mensaje;
    private int a_modo;
} // Fin de la clase Material_Modelo