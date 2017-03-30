package rcrsystem.presentation.controller;

import Modelo.AdministrarMaterial;
import Modelo.dao.AdministrarMaterialDAO;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import rcrsystem.presentation.model.Material_Modelo;
import rcrsystem.presentation.view.VentanaAdminMaterial;
import rcrsystem.presentation.view.VentanaMaterial;

public class Materiales_Controlador {

    public Materiales_Controlador(VentanaMaterial vista, Material_Modelo modelo) {
        this.a_vista = vista;
        this.a_modelo = modelo;
        a_materiales = new ArrayList();
        cargar();
        vista.setController(this);
        vista.setModel(modelo);
    }

    public void cargar() {
        this.colocar_materiales(AdministrarMaterialDAO.getMateriales());
        a_modelo.colocar_materiales(a_materiales, 0);
    }
    
    public void editar(int row, VentanaAdminMaterial ventana) {
        try {
            a_modelo.limpiar_errores();
            AdministrarMaterial seleccionada = a_modelo.obtener_modelo_material().obtener_fila_a(row);
            a_modelo.colocar_modo(rcrsystem.Aplicacion.ae_modo_editar);
            a_modelo.colocar_material_actual(seleccionada);
            ventana.jTextField_codigo.setEnabled(false);
            ventana.setVisible(true);
           
        } catch (Exception e) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, "¡No ha seleccionado ningún elemento!");
        }
    }

    public List<AdministrarMaterial> obtener_materiales() {
        return a_materiales;
    }

    public void colocar_materiales (List<AdministrarMaterial> materiales) {
        this.a_materiales = materiales;
    }

    public void buscar() {
        a_modelo.setFiltro(new AdministrarMaterial());
        a_modelo.obtener_filtro().getMaterial().setCodigo(a_vista.jTextField_filtro.getText());
        List<AdministrarMaterial> rows = new ArrayList();
        for (AdministrarMaterial p : this.obtener_materiales()) {
            if (p.getMaterial().getCodigo().contains(a_modelo.obtener_filtro().getMaterial().getCodigo())) {
                rows.add(p);
            }
        }
        a_modelo.colocar_materiales(rows, 0);
    }
    
    public void reiniciar_filtro(){
        a_vista.jTextField_filtro.setText("");
    }
     
    public void pre_agregar() {
        a_modelo.limpiar_errores();
        a_modelo.colocar_modo(rcrsystem.Aplicacion.ae_modo_agregar);
        AdministrarMaterial material = new AdministrarMaterial();
        a_modelo.colocar_material_actual(material);
    }
    
    private VentanaMaterial a_vista;
    private Material_Modelo a_modelo;
    private List<AdministrarMaterial> a_materiales;
} // Fin de la clase Materiales_Controlador