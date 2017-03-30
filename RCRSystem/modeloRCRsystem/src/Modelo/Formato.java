package Modelo;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;


public class Formato extends DefaultTableCellRenderer {

    //Clase que le dara formato a la tabla, en este caso le dara el color
    //segun tipo el tipo de empleado
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        if (isSelected) {
            this.setOpaque(true);
            this.setBackground(Color.RED);
            this.setForeground(Color.YELLOW);
        } else {
            this.setOpaque(true);
            this.setBackground(Color.BLUE);
            this.setForeground(Color.YELLOW);
        }
        return this;
    }
}
