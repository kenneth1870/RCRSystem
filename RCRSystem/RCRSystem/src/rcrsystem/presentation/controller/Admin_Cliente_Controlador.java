package rcrsystem.presentation.controller;

import Modelo.Cliente;
import Modelo.dao.ClienteDAO;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import rcrsystem.presentation.model.Cliente_Modelo;
import rcrsystem.presentation.view.VentanaAdminClientes;
import rcrsystem.presentation.view.VentanaCliente;

public class Admin_Cliente_Controlador {
    
    public Admin_Cliente_Controlador(VentanaAdminClientes vista, Cliente_Modelo modelo) {
        this.a_vista = vista;
        this.a_modelo = modelo;
        a_clientes = new ArrayList();
        cargar();
        vista.setController(this);
        vista.setModel(modelo);
    }

    public void cargar() {
        this.colocar_clientes(ClienteDAO.getClientes());
        a_modelo.colocar_clientes(a_clientes, 0);
    }

    public List<Cliente> obtener_clientes() {
        return a_clientes;
    }

    public void colocar_clientes(List<Cliente> clientes) {
        this.a_clientes = clientes;
    }

    public void buscar() {
        a_modelo.colocar_filtro(new Cliente());
        a_modelo.obtener_filtro().setCodigoC(a_vista.jTextField_filtro.getText());
        List<Cliente> rows = new ArrayList();
        for (Cliente p : this.obtener_clientes()) {
            if (p.getCodigoC().contains(a_modelo.obtener_filtro().getCodigoC())) {
                rows.add(p);
            }
        }
        a_modelo.colocar_clientes(rows, 0);
    }
    
    public void reiniciar_filtro(){
        a_vista.jTextField_filtro.setText("");
    }
    
    public void borrar(int row) {
        try {
            Cliente seleccionada = a_modelo.obtener_modelo_cliente().obtener_fila_a(row);
            int ax = JOptionPane.showConfirmDialog(null, "¿Seguro (a) que desea eliminar el elemento?");
            if (ax == JOptionPane.YES_OPTION) {
              ClienteDAO.eliminar(seleccionada);
               JOptionPane.showMessageDialog(null, "¡Eliminado correctamente!");
              this.cargar();
            }
        } catch (Exception ex) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, "No ha seleccionado ningún elemento");
        }
        this.buscar();
    }
    
    public void editar(int row, VentanaCliente ventana) {
        try {
            a_modelo.limpiar_errores();
            Cliente seleccionada = a_modelo.obtener_modelo_cliente().obtener_fila_a(row);
            a_modelo.colocar_modo(rcrsystem.Aplicacion.ae_modo_editar);
            a_modelo.colocar_cliente_actual(seleccionada);
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
        Cliente usuario = new Cliente();
        a_modelo.colocar_cliente_actual(usuario);
    }
 
    private VentanaAdminClientes a_vista;
    private Cliente_Modelo a_modelo;
    private List<Cliente> a_clientes;
}  // Fin de la clase Admin_Cliente_Controlador