package rcrsystem.presentation.controller;

import javax.swing.SwingWorker;
import rcrsystem.presentation.view.VentIngresarRegCompra;
import rcrsystem.presentation.view.VentanaCarga;

public class Progreso_Confirmar_Reg_Compra extends SwingWorker<Integer, String> {

    public Progreso_Confirmar_Reg_Compra(VentanaCarga ventana, VentIngresarRegCompra ventIn) {
        this.a_ventana = ventana;
        this.a_ventana_registro_compra = ventIn;
    }

    @Override
    protected Integer doInBackground() throws Exception {
        obtener_ventana().setVisible(true);
        obtener_ventana().jProgressBar1.setIndeterminate(true);

        this.a_ventana_registro_compra.confirmar();

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
    private VentIngresarRegCompra a_ventana_registro_compra;
}
