package Modelo;

import Modelo.dao.InventarioDAO;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class TablaInventario extends AbstractTableModel {

    List<Inventario> rows;
    int[] cols;
    InventarioDAO cnl;

    public Inventario getRowAt(int row) {
        return rows.get(row);
    }

    public TablaInventario(List<Inventario> inventario) {
        cnl = new InventarioDAO();
        this.rows = inventario;
    }

    public TablaInventario() {
        cnl = new InventarioDAO();
        rows = cnl.obtenerInventario();
    }

    public TablaInventario(int[] cols, List<Inventario> rows) {
        this.cols = cols;
        this.rows = rows;
        initColNames();
    }

    public String getColumnName(int col) {
        return colNames[cols[col]];
    }

    public int getRowCount() {
        return rows.size();
    }

    public int getColumnCount() {
        return cols.length;
    }

    @Override
    public Object getValueAt(int row, int col) {
        Inventario inventario = rows.get(row);
        switch (cols[col]) {
            case CODIGO:
                return inventario.getMaterial().getCodigo();
            case MATERIAL:
                return inventario.getMaterial().getNombre();
            case CANTIDAD:
                return inventario.getCantidad();
            case PRECIO:
                return inventario.getPrecio();
            default:
                return "";
        }
    }

    String[] colNames = new String[5];

    private void initColNames() {
        colNames[CODIGO] = "Código";
        colNames[MATERIAL] = "Material";
        colNames[CANTIDAD] = "Cantidad";
        colNames[PRECIO] = "Precio";
    }

    public static final int CODIGO = 0;
    public static final int MATERIAL = 1;
    public static final int CANTIDAD = 2;
    public static final int PRECIO = 3;
}
