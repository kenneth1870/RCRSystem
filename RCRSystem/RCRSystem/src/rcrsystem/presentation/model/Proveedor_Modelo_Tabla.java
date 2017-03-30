package rcrsystem.presentation.model;

import Modelo.Proveedor;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class Proveedor_Modelo_Tabla extends AbstractTableModel {

    public Proveedor_Modelo_Tabla(int[] cols, List<Proveedor> rows) {
        this.a_columnas = cols;
        this.a_filas = rows;
        inicializar_nombres_columnas();
    }
    
    public Proveedor obtener_fila_a(int row) {
        return a_filas.get(row);
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
    public String getColumnName(int col) {
        return a_nombres_columnas[a_columnas[col]];
    }

    @Override
    public Object getValueAt(int row, int col) {
        Proveedor proveedor = a_filas.get(row);
        switch (a_columnas[col]) {
            case ae_id:
                return proveedor.getCodigo();
            case ae_nombre:
                return proveedor.getNombre();
            case ae_desc:
                return proveedor.getDescripcion();
            default:
                return "";
        }
    }

    private void inicializar_nombres_columnas() {
        a_nombres_columnas[ae_id] = "Identificación";
        a_nombres_columnas[ae_nombre] = "Nombre";
        a_nombres_columnas[ae_desc] = "Descripción";
    }

    public static final int ae_id = 0;
    public static final int ae_nombre = 1;
    public static final int ae_desc = 2;
    public  List<Proveedor> a_filas;
    private int[] a_columnas;
    private String[] a_nombres_columnas = new String[3];
} // Fin de la clase Proveedor_Modelo_Tabla