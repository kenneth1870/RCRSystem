package Modelo;

public class RegCompra_U_Bulto {

    RegistroCompra regCompra;
    Bulto bulto;

    public RegCompra_U_Bulto() {
    }

    public RegCompra_U_Bulto(RegistroCompra regCompra, Bulto bulto) {
        this.regCompra = regCompra;
        this.bulto = bulto;
    }

    public RegistroCompra getRegCompra() {
        return regCompra;
    }

    public void setRegCompra(RegistroCompra regCompra) {
        this.regCompra = regCompra;
    }

    public Bulto getBulto() {
        return bulto;
    }

    public void setBulto(Bulto bulto) {
        this.bulto = bulto;
    }

}
