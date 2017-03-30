package Modelo;

import java.util.ArrayList;
import java.util.List;

public class ModeloTablaBultosComprados extends java.util.Observable {

    TablaBultosComprados bultosCompraModel;
    public static Integer BULTOSCOMPRADOS_MODEL = 0;

    public ModeloTablaBultosComprados() {
        this.init();
    }

    public TablaBultosComprados getInventariosModel() {
        return bultosCompraModel;
    }

    public void setIBultosCompradosModel(TablaBultosComprados inventarioModel) {
        this.bultosCompraModel = inventarioModel;
        setChanged();
        notifyObservers(BULTOSCOMPRADOS_MODEL);
    }

    public void setBultosComprados(List<Bulto> rows) {
        int[] cols = {TablaInventario.CODIGO, TablaInventario.MATERIAL, TablaInventario.CANTIDAD, TablaInventario.PRECIO};
        setIBultosCompradosModel(new TablaBultosComprados(cols, rows));
    }

    void init() {
        List<Bulto> rows = new ArrayList<Bulto>();
        setBultosComprados(rows);
    }

    public void addObserver(java.util.Observer o) {
        super.addObserver(o);
        setChanged();
        notifyObservers(BULTOSCOMPRADOS_MODEL);
    }
}
