package rcrsystem.presentation.controller;

import javax.swing.SwingWorker;
import rcrsystem.presentation.view.VentanaCarga;
import rcrsystem.presentation.view.VentanaVenta;

public class Progreso_Venta_Controlador extends SwingWorker<Integer, String> {

    public Progreso_Venta_Controlador(VentanaCarga ventana, VentanaVenta ventas) {
        this.a_ventana = ventana;
        this.a_ventas = ventas;
        a_tipo = -1;
    }

    public int obtener_tipo() {
        return a_tipo;
    }

    public void colocar_tipo(int tipo) {
        this.a_tipo = tipo;
    }

    @Override
    protected Integer doInBackground() throws Exception {
        obtener_ventana().setVisible(true);
        obtener_ventana().jProgressBar1.setIndeterminate(true);

        if (a_tipo == 1) {
            this.a_ventas.cargarBulto(this.obtener_ventana());
        }
        if (a_tipo == 2) {
            this.a_ventas.agregarBulto();
        }
        if (a_tipo == 3) {
            this.a_ventas.generarListaEmpaque();
        }
        obtener_ventana().setVisible(false);
        obtener_ventana().jProgressBar1.setIndeterminate(false);
        return 0;
    }

    public VentanaCarga obtener_ventana() {
        return a_ventana;
    }

    public void colocar_ventana(VentanaCarga ventana) {
        this.a_ventana = ventana;
    }
    
    private VentanaCarga a_ventana;
    private VentanaVenta a_ventas;
    private int a_tipo = -1;
} // Fin de la clase Progreso_Venta_Controlador