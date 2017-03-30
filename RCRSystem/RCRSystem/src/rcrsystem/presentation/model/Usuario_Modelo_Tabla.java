package rcrsystem.presentation.model;

import Modelo.Usuario;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class Usuario_Modelo_Tabla extends AbstractTableModel{
        
    public Usuario_Modelo_Tabla(int[] cols, List<Usuario> rows) {
        this.a_columnas = cols;
        this.a_filas = rows;
        incializar_nombres_columnas();
    }
    
    public Usuario obtener_fila_a(int row) {
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
        Usuario usuario = a_filas.get(row);
        switch (a_columnas[col]) {
            case ae_id:
                return usuario.getIdentificacion();
            case ae_nombre:
                return usuario.getNombre();
            case ae_telefono:
                return usuario.getNumtelefono();
            case ae_permiso:
                if(usuario.getPuesto()==1){
                return "Administrador";
                }
                if(usuario.getPuesto()==2){
                return "Compras";
                }
                  if(usuario.getPuesto()==3){
                return "Ventas";
                }
            default:
                return "";
        }
    }

    private void incializar_nombres_columnas() {
        a_nombres_columnas[ae_id] = "Identificación";
        a_nombres_columnas[ae_nombre] = "Nombre";
        a_nombres_columnas[ae_telefono] = "Teléfono";
        a_nombres_columnas[ae_permiso] = "Permiso";
    }

    public static final int ae_id = 0;
    public static final int ae_nombre = 1;
    public static final int ae_telefono = 2;
    public static final int ae_permiso = 3;
    public List<Usuario> a_filas;
    private int[] a_columnas;
    private String[] a_nombres_columnas = new String[5];
} // Fin de la clase Usuario_Modelo_Tabla