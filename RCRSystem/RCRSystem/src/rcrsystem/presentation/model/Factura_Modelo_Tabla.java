package rcrsystem.presentation.model;

import Modelo.Facturar_Venta;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class Factura_Modelo_Tabla extends AbstractTableModel {

    public Factura_Modelo_Tabla(int[] cols, List<Facturar_Venta> rows) {
        this.a_columnas = cols;
        this.a_filas = rows;
        inicializar_nombres_columnas();
    }
    
    public Facturar_Venta obtener_fila_a(int row) {
        return a_filas.get(row);
    }

    @Override
    public String getColumnName(int col) {
        return a_nombres_columnas[a_columnas[col]];
    }

    @Override
    public int getRowCount() {
        return a_filas.size();
    }

    @Override
    public int getColumnCount() {
        return a_columnas.length;
    }

    @Override
    public Object getValueAt(int row, int col) {
        Facturar_Venta v = a_filas.get(row);
        switch (a_columnas[col]) {
            case ae_codigo:
                return v.getLe().getCodigoL();
            case ae_cliente:
                return v.getLe().getCliente().getNombreC();
            case ae_destino:
                return v.getLe().getDestino();
            default:
                return "";
        }
    }

    private void inicializar_nombres_columnas() {
        a_nombres_columnas[ae_cliente] = "Cliente";
        a_nombres_columnas[ae_codigo] = "Código";
        a_nombres_columnas[ae_destino] = "Destino";
    }

    public static final int ae_codigo = 0;
    public static final int ae_cliente = 1;
    public static final int ae_destino = 2;
    private List<Facturar_Venta> a_filas;
    private int[] a_columnas;
    private String[] a_nombres_columnas = new String[3];
} // Fin de la clase Factura_Modelo_Tabla