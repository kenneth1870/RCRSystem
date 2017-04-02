package rcrsystem.presentation.controller;

import rcrsystem.presentation.view.VentConsultaMatInv;
import rcrsystem.presentation.model.Modelo_B_C;

public class Bultos_C_Controlador {

    public Bultos_C_Controlador(VentConsultaMatInv vista, Modelo_B_C modelo, String id) {
        this.a_vista = vista;
        this.a_modelo_b_c = modelo;
        cargar_bultos(id);
        vista.setController(this);
        vista.setModel(modelo);
    }

    public void cargar_bultos(String id) {
        a_modelo_b_c.colocar_bulto_c(rcrsystem.Aplicacion.ae_modelo_bulto_dao.getBultosCompXMaterial(id));
    }
    
    private VentConsultaMatInv a_vista;
    private Modelo_B_C a_modelo_b_c;
} // Fin de la clase Bultos_C_Controlador