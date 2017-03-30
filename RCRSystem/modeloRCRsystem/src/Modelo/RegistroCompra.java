package Modelo;

public class RegistroCompra {

    int numCompra;
    float pesoTotal;

    public RegistroCompra() {
    }

    public RegistroCompra(int numCompra, float pesoTotal) {
        this.numCompra = numCompra;
        this.pesoTotal = pesoTotal;
    }

    public int getNumCompra() {
        return numCompra;
    }

    public void setNumCompra(int numCompra) {
        this.numCompra = numCompra;
    }

    public float getPesoTotal() {
        return pesoTotal;
    }

    public void setPesoTotal(float pesoTotal) {
        this.pesoTotal = pesoTotal;
    }

}
