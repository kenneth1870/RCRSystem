package Modelo;

import java.util.ArrayList;
import java.util.List;

public class Facturar_Venta {

    private ListaEmpaque le;
    private List<Venta> venta;

    public Facturar_Venta(ListaEmpaque le, List<Venta> venta) {
        this.le = le;
        this.venta = venta;
    }

    public Facturar_Venta() {
        this.le = new ListaEmpaque();
        this.venta = new ArrayList();
    }

    public ListaEmpaque getLe() {
        return le;
    }

    public void setLe(ListaEmpaque le) {
        this.le = le;
    }

    public List<Venta> getVenta() {
        return venta;
    }

    public void setVenta(List<Venta> venta) {
        this.venta = venta;
    }

}
