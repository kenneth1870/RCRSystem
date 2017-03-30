package rcrsystem.presentation.controller;

import Modelo.Proveedor;
import Modelo.dao.ProveedorDAO;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import rcrsystem.presentation.model.Proveedor_Modelo;
import rcrsystem.presentation.view.VentanaAdminProveedor;
import rcrsystem.presentation.view.VentanaProveedor;

public class Admin_Proveedores_Controlador {

    public Admin_Proveedores_Controlador(VentanaAdminProveedor vista, Proveedor_Modelo modelo) {
        this.a_vista = vista;
        this.a_modelo = modelo;
        a_proveedores = new ArrayList();
        cargar();
        vista.setController(this);
        vista.setModel(modelo);
    }

    public void cargar() {
        this.colocar_proveedoress(ProveedorDAO.getProveedores());
        a_modelo.colocar_proveedores(a_proveedores, 0);
    }

    public List<Proveedor> obtener_proveedoress() {
        return a_proveedores;
    }

    public void colocar_proveedoress(List<Proveedor> proveedores) {
        this.a_proveedores = proveedores;
    }

    public void buscar() {
        a_modelo.colocar_filtro(new Proveedor());
        a_modelo.obtener_filtro().setCodigo(a_vista.jTextField_filtro.getText());
        List<Proveedor> rows = new ArrayList();
        for (Proveedor p : this.obtener_proveedoress()) {
            if (p.getCodigo().contains(a_modelo.obtener_filtro().getCodigo())) {
                rows.add(p);
            }
        }
        a_modelo.colocar_proveedores(rows, 0);
    }
    
    public void reiniciar_filtro(){
   a_vista.jTextField_filtro.setText("");
    }
    
    public void borrar(int row) {
        try {
            Proveedor seleccionada = a_modelo.obtener_modelo_proveedor().obtener_fila_a(row);
            int ax = JOptionPane.showConfirmDialog(null, "¿Seguro (a) que desea eliminar el elemento?");
            if (ax == JOptionPane.YES_OPTION) {
              ProveedorDAO.eliminar(seleccionada);
               JOptionPane.showMessageDialog(null, "¡Eliminado correctamente!");
              this.cargar();
            }
        } catch (Exception ex) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, "No ha seleccionado ningún elemento");
        }
        this.buscar();
    }
    
    public void editar(int row, VentanaProveedor ventana) {
        try {
            a_modelo.limpiar_errores();
            Proveedor seleccionada = a_modelo.obtener_modelo_proveedor().obtener_fila_a(row);
            a_modelo.colocar_modo(rcrsystem.Aplicacion.ae_modo_editar);
            a_modelo.colocar_proveedor_actual(seleccionada);
            ventana.jTextField_id.setEnabled(false);
            ventana.setVisible(true);
           
        } catch (Exception e) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, "¡No ha seleccionado ningún elemento!");
        }
    }
     
 public void pre_agregar() {
        a_modelo.limpiar_errores();
        a_modelo.colocar_modo(rcrsystem.Aplicacion.ae_modo_agregar);
        Proveedor proveedor = new Proveedor();
        a_modelo.colocar_proveedor_actual(proveedor);
    }
 
    private VentanaAdminProveedor a_vista;
    private Proveedor_Modelo a_modelo;
    private List<Proveedor> a_proveedores;
} // Fin de la clase Admin_Proveedores_Controlador