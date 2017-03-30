package rcrsystem.presentation.model;

import Modelo.Bulto;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ComboBoxModel;

public class Modelo_B_C extends java.util.Observable  {
    
    public Modelo_B_C() {
        this.inicializar();
    }
    
    public Bultos_Ctable_Model obtener_bultos_C_Modelo() {
        return a_bultos_modelo_c;
    }
    
    public void colocar_bultos_C_Modelo(Bultos_Ctable_Model bultosCModel ) {
        this.a_bultos_modelo_c = bultosCModel;
        setChanged();
        notifyObservers(ae_bultos_modelo_c);
    }
    
    public void colocar_bulto_c(List<Bulto> rows) {
        int[] cols = {Bultos_Ctable_Model.ae_codigo, Bultos_Ctable_Model.ae_tipo, Bultos_Ctable_Model.ae_peso, Bultos_Ctable_Model.ae_nombre_material};
        colocar_bultos_C_Modelo(new Bultos_Ctable_Model(cols, rows));
    }

    void inicializar() {
        List<Bulto> rows = new ArrayList<>();
        colocar_bulto_c(rows);
    }
    
     @Override
    public void addObserver(java.util.Observer o) {
        super.addObserver(o);
        setChanged();
        notifyObservers(ae_bultos_modelo_c);
    }
    
    private ComboBoxModel a_bultos;
    private Bultos_Ctable_Model a_bultos_modelo_c;
    public static Integer ae_bultos_modelo_c = 0;
} // Fin de la clase Modelo_B_C