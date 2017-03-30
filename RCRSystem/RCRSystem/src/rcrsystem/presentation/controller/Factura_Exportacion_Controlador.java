package rcrsystem.presentation.controller;

import Modelo.ListaEmpaque;
import Modelo.TotalMaterialVendido;
import java.awt.Toolkit;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JOptionPane;
import rcrsystem.presentation.model.Facturar_Modelo;
import rcrsystem.presentation.view.VentanaFacturaExportacion;
import rcrsystem.presentation.view.VentanaPrecioUnidFacturacion;

public class Factura_Exportacion_Controlador {

    public Factura_Exportacion_Controlador(VentanaFacturaExportacion vista, Facturar_Modelo modelo) {
        this.a_vista = vista;
        this.a_modelo = modelo;
        vista.setController(this);
        vista.setModel(modelo);
    }

    public void abrir(int row) {
        try {
            TotalMaterialVendido lE = a_modelo.obtener_total_material_lista_empaque().obtener_fila_a(row);
            a_modelo.colocar_total_material_vendido_lista_empaque_actual(lE);
            VentanaPrecioUnidFacturacion v = new VentanaPrecioUnidFacturacion(a_vista, true);
            Editar_Precio_Unidad_Factura_Controlador fc = new Editar_Precio_Unidad_Factura_Controlador(v, a_modelo);
            v.setVisible(true);
        } catch (Exception ex) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, "¡ERRORF!");
        }
    }

    public String formato_fecha() {
        return String.valueOf(a_vista.diaFactufa.getSelectedItem())
                + "-" + this.numero_mes() + "-" + a_vista.añoFactura.getText();
    }

    public String obtener_fecha() {
        Calendar c1 = new GregorianCalendar();
        String dia = "";
        String mes = "";
        String año = "";

        if (c1.get(Calendar.DATE) < 10) {
            dia = "0" + String.valueOf(c1.get(Calendar.DATE));
        } else {
            dia = String.valueOf(c1.get(Calendar.DATE));
        }
        if ((c1.get(Calendar.MONTH) + 1) < 10) {
            mes = "0" + String.valueOf(c1.get(Calendar.MONTH) + 1);
        } else {
            mes = String.valueOf(c1.get(Calendar.MONTH) + 1);
        }
        año = String.valueOf(c1.get(Calendar.YEAR));
        return dia + "-" + mes + "-" + año;
    }

    String numero_mes() {
        String m = String.valueOf(a_vista.mesFactura.getSelectedItem());
        switch (m) {
            case "Enero":
                return "01";
            case "Febrero":
                return "02";
            case "Marzo":
                return "03";
            case "Abril":
                return "04";
            case "Mayo":
                return "05";
            case "Junio":
                return "06";
            case "Julio":
                return "07";
            case "Agosto":
                return "08";
            case "Setiembre":
                return "09";
            case "Octubre":
                return "10";
            case "Noviembre":
                return "11";
            case "Diciembre":
                return "12";
        }
        return "";
    }

    public String mes_numero(String mes) {
        switch (mes) {
            case "01":
                return "Enero";
            case "02":
                return "Febrero";
            case "03":
                return "Marzo";
            case "04":
                return "Abril";
            case "05":
                return "Mayo";
            case "06":
                return "Junio";
            case "07":
                return "Julio";
            case "08":
                return "Agosto";
            case "09":
                return "Setiembre";
            case "10":
                return "Octubre";
            case "11":
                return "Noviembre";
            case "12":
                return "Diciembre";
        }
        return "";
    }
    
    public void cerrar() {
        this.a_vista.setVisible(false);
        a_modelo.colocar_lista_empaque_actual(new ListaEmpaque());
    }
    
    private VentanaFacturaExportacion a_vista;
    private Facturar_Modelo a_modelo;
} // Fin de la clase Factura_Exportacion_Controlador