package rcrsystem.presentation.controller;

import Modelo.Inventario;
import java.awt.Toolkit;
import javax.swing.JOptionPane;
import rcrsystem.presentation.view.VentanaInventario;
import rcrsystem.presentation.model.Modelo;

public class Inventario_Controlador {

    public Inventario_Controlador(VentanaInventario vista, Modelo modelo) {
        this.a_vista = vista;
        this.a_modelo = modelo;
        cargar();
        vista.setController(this);
        vista.setModel(modelo);
    }

    public void cargar() {
        a_modelo.colocar_inventario(rcrsystem.Aplicacion.ae_modelo.obtenerInventario(), 0);
    }

    public void abrir(int row) {
        try {
            Inventario seleccionada = a_modelo.obtener_modelo_inventarios().obtener_fila_a(row);
            a_modelo.colocar_inventario_actual(seleccionada);
            rcrsystem.presentation.view.VentanaPrincipal.vistaEdicion.setVisible(true);
        } catch (Exception ex) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, "¡No ha seleccionado ningún material!");
        }
    }
    
    private VentanaInventario a_vista;
    private Modelo a_modelo;
} // Fin de la clase Inventario_Controlador