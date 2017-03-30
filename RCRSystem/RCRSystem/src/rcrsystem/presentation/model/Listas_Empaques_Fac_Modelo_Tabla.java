package rcrsystem.presentation.model;

import Modelo.ListaEmpaque;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class Listas_Empaques_Fac_Modelo_Tabla extends AbstractTableModel {

    public Listas_Empaques_Fac_Modelo_Tabla(int[] cols, List<ListaEmpaque> rows) {
        this.a_columnas = cols;
        this.a_filas = rows;
        inicializar_nombres_columnas();
    }
    
    public ListaEmpaque obtener_fila_a(int row) {
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
        ListaEmpaque listaEmpaque = a_filas.get(row);
        switch (a_columnas[col]) {
            case ae_codigo:
                return listaEmpaque.getCodigoL();
            case ae_fecha:
                return listaEmpaque.getFecha();
            case ae_cliente:
                return listaEmpaque.getCliente().getNombreC();
            default:
                return "";
        }
    }

    private void inicializar_nombres_columnas() {
        a_nombres_columnas[ae_codigo] = "Código";
        a_nombres_columnas[ae_fecha] = "Fecha";
        a_nombres_columnas[ae_cliente] = "Cliente";
    }

    public static final int ae_codigo = 0;
    public static final int ae_fecha = 1;
    public static final int ae_cliente = 2;
    private List<ListaEmpaque> a_filas;
    private int[] a_columnas;
    private String[] a_nombres_columnas = new String[4];
} // Fin de la clase Listas_Empaques_Fac_Modelo_Tabla