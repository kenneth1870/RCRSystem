package Modelo;

public class ListaEmpaque {

    int codigoL;
    String fecha;
    int medioTransporte;
    Cliente cliente;
    String destino;
    float pesoBruto;
    float pesoNeto;
    Conductor conductor;
    String placa;
    String marca;
    String chasis;
    String furgon;

    public ListaEmpaque() {
        this.codigoL = 0;
        this.fecha = "";
        this.medioTransporte = 1;
        this.cliente = new Cliente();
        this.destino = "";
        this.pesoBruto = 0;
        this.pesoNeto = 0;
        this.conductor = new Conductor();
        this.placa = "";
        this.marca = "";
        this.chasis = "";
        this.furgon = "";
    }

    public ListaEmpaque(int codigoL, String fecha, int medioTransporte, Cliente cliente, String destino, float pesoBruto, float pesoNeto, Conductor conductor, String placa, String marca, String chasis, String furgon) {
        this.codigoL = codigoL;
        this.fecha = fecha;
        this.medioTransporte = medioTransporte;
        this.cliente = cliente;
        this.destino = destino;
        this.pesoBruto = pesoBruto;
        this.pesoNeto = pesoNeto;
        this.conductor = conductor;
        this.placa = placa;
        this.marca = marca;
        this.chasis = chasis;
        this.furgon = furgon;
    }

    public int getCodigoL() {
        return codigoL;
    }

    public void setCodigoL(int codigoL) {
        this.codigoL = codigoL;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getMedioTransporte() {
        return medioTransporte;
    }

    public void setMedioTransporte(int medioTransporte) {
        this.medioTransporte = medioTransporte;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public Conductor getConductor() {
        return conductor;
    }

    public void setConductor(Conductor conductor) {
        this.conductor = conductor;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getChasis() {
        return chasis;
    }

    public void setChasis(String chasis) {
        this.chasis = chasis;
    }

    public String getFurgon() {
        return furgon;
    }

    public void setFurgon(String furgon) {
        this.furgon = furgon;
    }

    public float getPesoBruto() {
        return pesoBruto;
    }

    public void setPesoBruto(float pesoBruto) {
        this.pesoBruto = pesoBruto;
    }

    public float getPesoNeto() {
        return pesoNeto;
    }

    public void setPesoNeto(float pesoNeto) {
        this.pesoNeto = pesoNeto;
    }

}
