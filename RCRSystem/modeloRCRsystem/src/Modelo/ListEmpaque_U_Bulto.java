package Modelo;

import Modelo.ListaEmpaque;

public class ListEmpaque_U_Bulto {

    ListaEmpaque listE;
    Bulto bultoVendido;

    public ListEmpaque_U_Bulto() {
    }

    public ListEmpaque_U_Bulto(ListaEmpaque listE, Bulto bultoVendido) {
        this.listE = listE;
        this.bultoVendido = bultoVendido;
    }

    public ListaEmpaque getListE() {
        return listE;
    }

    public void setListE(ListaEmpaque listE) {
        this.listE = listE;
    }

    public Bulto getBultoVendido() {
        return bultoVendido;
    }

    public void setBultoVendido(Bulto bultoVendido) {
        this.bultoVendido = bultoVendido;
    }

}
