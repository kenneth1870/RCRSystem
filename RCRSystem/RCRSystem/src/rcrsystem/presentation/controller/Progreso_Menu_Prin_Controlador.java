package rcrsystem.presentation.controller;

import javax.swing.SwingWorker;
import rcrsystem.presentation.view.VentanaCarga;
import rcrsystem.presentation.view.VentanaPrincipal;

public class Progreso_Menu_Prin_Controlador extends SwingWorker< Integer, String> {

    public Progreso_Menu_Prin_Controlador(VentanaCarga ventana) {
        this.a_ventana = ventana;
    }

    @Override
    protected Integer doInBackground() throws Exception {
        obtener_ventana().setVisible(true);
        obtener_ventana().jProgressBar1.setIndeterminate(true);

       new VentanaPrincipal().setVisible(true);

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
} // Fin de la clase Progreso_Menu_Prin_Controlador