package rcrsystem.presentation.model;

import Modelo.AdministrarMaterial;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class Modelo_Tabla_Material extends AbstractTableModel {
    
    public Modelo_Tabla_Material(int[] cols, List<AdministrarMaterial> rows) {
        this.a_columnas = cols;
        this.a_filas = rows;
        inicializar_nombres_columnas();
    }
    
    public AdministrarMaterial obtener_fila_a(int row) {
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
        AdministrarMaterial admin = a_filas.get(row);
        switch (a_columnas[col]) {
            case ae_codigo:
                return admin.getMaterial().getCodigo();
            case ae_descripcion:
                return admin.getMaterial().getNombre();
            case ae_precio_paca:
                return admin.getPrecioPaca();
            case ae_precio_saca:
              return admin.getPrecioSaca();
            default:
                return "";
        }
    }

    private void inicializar_nombres_columnas() {
        a_nombres_columnas[ae_codigo] = "Código";
        a_nombres_columnas[ae_descripcion] = "Descripción";
        a_nombres_columnas[ae_precio_paca] = "Precio PACA";
        a_nombres_columnas[ae_precio_saca] = "Precio SACA";
    }
    
    public static final int ae_codigo = 0;
    public static final int ae_descripcion = 1;
    public static final int ae_precio_paca = 2;
    public static final int ae_precio_saca = 3;
    private List<AdministrarMaterial> a_filas;
    private int[] a_columnas;
    private String[] a_nombres_columnas = new String[5];
} // Fin de la clase Modelo_Tabla_Material