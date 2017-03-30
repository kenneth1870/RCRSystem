package Modelo;

import java.util.ArrayList;
import java.util.List;


public class ModeloTablaInventario extends java.util.Observable {

    TablaInventario inventarioModel;
    public static Integer INVENTARIO_MODEL = 0;

    public ModeloTablaInventario() {
        this.init();
    }

    public TablaInventario getInventariosModel() {
        return inventarioModel;
    }

    public void setInventariosModel(TablaInventario inventarioModel) {
        this.inventarioModel = inventarioModel;
        setChanged();
        notifyObservers(INVENTARIO_MODEL);
    }

    public void setInventario(List<Inventario> rows) {
        int[] cols = {TablaInventario.CODIGO, TablaInventario.MATERIAL, TablaInventario.CANTIDAD, TablaInventario.PRECIO};
        setInventariosModel(new TablaInventario(cols, rows));
    }

    void init() {
        List<Inventario> rows = new ArrayList<Inventario>();
        setInventario(rows);
    }

    public void addObserver(java.util.Observer o) {
        super.addObserver(o);
        setChanged();
        notifyObservers(INVENTARIO_MODEL);
    }
}
