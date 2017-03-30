package rcrsystem.presentation.model;

import Modelo.Bulto;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class Bultos_Ctable_Model extends AbstractTableModel {

    public Bultos_Ctable_Model(int[] cols, List<Bulto> rows) {
        this.a_columnas = cols;
        this.a_filas = rows;
        inicializar_nombres_columnas();
    }
    
    public Bulto obtener_fila_a(int row) {
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
        Bulto bultoc = a_filas.get(row);
        switch (a_columnas[col]) {
            case ae_codigo:
                return bultoc.getCodigo();
            case ae_tipo:
                if (bultoc.getTipo() == 1) {
                    return "Paca";
                }
                return "Saca";
            case ae_peso:
                return bultoc.getPeso();
            case ae_codigo_material:
                return bultoc.getMaterial().getCodigo();
            case ae_nombre_material:
                return bultoc.getMaterial().getTmaterial().getNombre();
            default:
                return "";
        }
    }

    private void inicializar_nombres_columnas() {
        a_nombres_columnas[ae_codigo] = "Código";
        a_nombres_columnas[ae_tipo] = "Tipo";
        a_nombres_columnas[ae_peso] = "Peso";
        a_nombres_columnas[ae_codigo_material] = "Cód. material";
        a_nombres_columnas[ae_nombre_material] = "Material";
    }

    public static final int ae_codigo = 0;
    public static final int ae_tipo = 1;
    public static final int ae_peso = 2;
    public static final int ae_codigo_material = 3;
    public static final int ae_nombre_material = 4;
    private List<Bulto> a_filas;
    private int[] a_columnas;
    private String[] a_nombres_columnas = new String[6];
} // Fin de la clase Bultos_Ctable_Model