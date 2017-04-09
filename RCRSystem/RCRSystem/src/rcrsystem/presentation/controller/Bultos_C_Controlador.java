package rcrsystem.presentation.controller;

import Modelo.Material;
import Modelo.dao.MaterialDAO;
import rcrsystem.presentation.view.VentConsultaMatInv;
import rcrsystem.presentation.model.Modelo_B_C;
import rcrsystem.presentation.view.VentanaInventario;

public class Bultos_C_Controlador {

    public Bultos_C_Controlador(VentConsultaMatInv vista, Modelo_B_C modelo, String id, String nombre, VentanaInventario a_inventario) {
        this.a_vista = vista;
        this.a_modelo_b_c = modelo;
        cargar_bultos(id);
        vista.setController(this);
        vista.setModel(modelo);
        this.nombre="";
        this.a_inventario=a_inventario;
    }
    
    public void colocar_nombre(String nombre){
        this.nombre=nombre;
    }
    
    public String obtener_nombre(){
        return this.nombre;
    }
    
    public void cerrar_ventana(){
    this.a_vista.setVisible(false);
    this.a_inventario.setVisible(true);
    }
    
    public void cargar_bultos(String id) {
        Material material=MaterialDAO.getMaterial(id);
        a_modelo_b_c.colocar_bulto_c(rcrsystem.Aplicacion.ae_modelo_bulto_dao.getBultosCompXMaterial(id));
        a_vista.letrero_jLabel.setText("Bultos disponibles del material "+material.getNombre());
    }
    
    private VentConsultaMatInv a_vista;
    private Modelo_B_C a_modelo_b_c;
    private String nombre;
    private VentanaInventario a_inventario;
} // Fin de la clase Bultos_C_Controlador