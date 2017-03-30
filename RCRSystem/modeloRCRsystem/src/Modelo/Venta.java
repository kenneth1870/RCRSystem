package Modelo;


public class Venta {
    Bulto bulto;
    int numero;

    public Venta() {
        this.bulto = new Bulto();
        this.numero = -1;
    }
    
    public Venta(Bulto bulto, int numero) {
        this.bulto = bulto;
        this.numero = numero;
    }

    public Bulto getBulto() {
        return bulto;
    }

    public void setBulto(Bulto bulto) {
        this.bulto = bulto;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }
    
}
