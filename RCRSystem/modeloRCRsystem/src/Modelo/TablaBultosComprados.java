package Modelo;

import Modelo.dao.BultoDAO;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class TablaBultosComprados extends AbstractTableModel {

    List<Bulto> rows;
    int[] cols;
    BultoDAO cnl;

    public Bulto getRowAt(int row) {
        return rows.get(row);
    }

    public TablaBultosComprados(List<Bulto> inventario) {
        cnl = new BultoDAO();
        this.rows = inventario;
    }

    public TablaBultosComprados() {
        cnl = new BultoDAO();
        rows = cnl.getBultosComprados();
    }

    public TablaBultosComprados(int[] cols, List<Bulto> rows) {
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
        Bulto bulto = rows.get(row);
        switch (cols[col]) {
            case CODIGO:
                return bulto.getCodigo();
            case TIPO:
                return bulto.getTipo();
            case PESO:
                return bulto.getPeso();
            case MATERIAL:
                return bulto.getMaterial().getTmaterial().getNombre();
            default:
                return "";
        }
    }

    String[] colNames = new String[5];

    private void initColNames() {
        colNames[CODIGO] = "Código Bulto";
        colNames[TIPO] = "Tipo";
        colNames[PESO] = "Peso";
        colNames[MATERIAL] = "Material";
    }

    public static final int CODIGO = 0;
    public static final int TIPO = 1;
    public static final int PESO = 2;
    public static final int MATERIAL = 3;
}
