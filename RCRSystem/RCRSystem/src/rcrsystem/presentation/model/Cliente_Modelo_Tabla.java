package rcrsystem.presentation.model;

import Modelo.Cliente;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class Cliente_Modelo_Tabla extends AbstractTableModel{
    
    public Cliente_Modelo_Tabla(int[] cols, List<Cliente> rows) {
        this.a_columnas = cols;
        this.a_filas = rows;
        inicializar_nombres_columnas();
    }
    
    public Cliente obtener_fila_a(int row) {
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
        Cliente cliente = a_filas.get(row);
        switch (a_columnas[col]) {
            case ae_id:
                return cliente.getCodigoC();
            case ae_nombre:
                return cliente.getNombreC();
            default:
                return "";
        }
    }

    private void inicializar_nombres_columnas() {
        a_nombres_columnas[ae_id] = "Identificación";
        a_nombres_columnas[ae_nombre] = "Nombre";
    }

    public static final int ae_id = 0;
    public static final int ae_nombre = 1;
    public List<Cliente> a_filas;
    private int[] a_columnas;
    private String[] a_nombres_columnas = new String[2];
} // Fin de la clase Cliente_Modelo_Tabla