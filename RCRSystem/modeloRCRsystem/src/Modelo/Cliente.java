package Modelo;

public class Cliente {

    String codigoC;
    String nombreC;

    public Cliente(String codigoC, String nombreC) {
        this.codigoC = codigoC;
        this.nombreC = nombreC;
    }

    public Cliente() {
        codigoC = "";
        nombreC = "";
    }

    public String getCodigoC() {
        return codigoC;
    }

    public void setCodigoC(String codigoC) {
        this.codigoC = codigoC;
    }

    public String getNombreC() {
        return nombreC;
    }

    public void setNombreC(String nombreC) {
        this.nombreC = nombreC;
    }

}
