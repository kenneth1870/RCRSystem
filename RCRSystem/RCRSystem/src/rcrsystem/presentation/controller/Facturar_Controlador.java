package rcrsystem.presentation.controller;

import Modelo.ListaEmpaque;
import Modelo.dao.ListaEmpaqueDAO;
import Modelo.dao.TotalMaterialVendidoDAO;
import java.awt.Toolkit;
import javax.swing.JOptionPane;
import rcrsystem.presentation.model.Facturar_Modelo;
import rcrsystem.presentation.view.VentanaFacturaExportacion;
import rcrsystem.presentation.view.VentanaFacturacion;

public class Facturar_Controlador {

    public Facturar_Controlador(VentanaFacturacion vista, Facturar_Modelo modelo) {
        this.a_vista = vista;
        this.a_modelo = modelo;
        cargar();
        vista.setController(this);
        vista.setModel(modelo);
    }

    public void cargar() {
        a_modelo.colocar_listas_empaques(ListaEmpaqueDAO.getListaEmpaque(), 0);
    }

    public void abrir(int row) {
        try {
            this.a_lista_empaque = a_modelo.obtener_modelo_factura().obtener_fila_a(row);
            a_modelo.colocar_lista_empaque_actual(a_lista_empaque);
            a_modelo.colocar_total_material_vendido_lista_empaque(TotalMaterialVendidoDAO.obtenerITotalMaterialesVendidos(a_lista_empaque.getCodigoL()), 0);
            VentanaFacturaExportacion v = new VentanaFacturaExportacion(a_vista, true);
            Factura_Exportacion_Controlador fc = new Factura_Exportacion_Controlador(v, a_modelo);
            v.setVisible(true);
        } catch (Exception ex) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, "¡No ha seleccionado ninguna lista de empaque!");
        }
    }
    
    private VentanaFacturacion a_vista;
    private Facturar_Modelo a_modelo;
    private ListaEmpaque a_lista_empaque;
} // Fin de la clase Facturar_Controlador