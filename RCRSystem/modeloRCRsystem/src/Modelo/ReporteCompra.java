package Modelo;

import java.util.Date;

public class ReporteCompra {

    int numero;
    RegistroCompra regCompra;
    Date fecha;
    Proveedor proveedor;
    String chofer;
    String placaVehiculo;

    public ReporteCompra() {
    }

    public ReporteCompra(int numero, RegistroCompra regCompra, Date fecha, Proveedor proveedor, String chofer, String placaVehiculo) {
        this.numero = numero;
        this.regCompra = regCompra;
        this.fecha = fecha;
        this.proveedor = proveedor;
        this.chofer = chofer;
        this.placaVehiculo = placaVehiculo;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public RegistroCompra getRegCompra() {
        return regCompra;
    }

    public void setRegCompra(RegistroCompra regCompra) {
        this.regCompra = regCompra;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public String getChofer() {
        return chofer;
    }

    public void setChofer(String chofer) {
        this.chofer = chofer;
    }

    public String getPlacaVehiculo() {
        return placaVehiculo;
    }

    public void setPlacaVehiculo(String placaVehiculo) {
        this.placaVehiculo = placaVehiculo;
    }

}
