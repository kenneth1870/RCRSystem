package Modelo;

import java.util.Observable;
import java.util.Observer;

public class Modelo extends Observable {

    //<editor-fold desc="constructores" defaultstate="collapsed">
    public Modelo() {
        tablaInventario = new TablaInventario();
    }
    //</editor-fold>

    //<editor-fold desc="MVC" defaultstate="collapsed">
    @Override
    public void addObserver(Observer obs) {
        super.addObserver(obs);
        setChanged();
        notifyObservers();
    }
    //</editor-fold>

    //<editor-fold desc="get y set" defaultstate="collapsed">
    //Llama a todos get y set de la calse TableApartado
    public TablaInventario getTableModelInventario() {
        return tablaInventario;
    }

    //</editor-fold>
    TablaInventario tablaInventario;
}
