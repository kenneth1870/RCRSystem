package Modelo;

public class Usuario {

    String identificacion;
    String nombre;
    int numtelefono;
    int puesto;
    String contraseña;

    public Usuario() {
        this.identificacion = "";
        this.nombre = "";
        this.numtelefono = 0;
        this.puesto = 0;
        this.contraseña = "";
    }

    public Usuario(String identificacion, String nombre, int numtelefon, int puesto, String contraseña) {
        this.identificacion = identificacion;
        this.nombre = nombre;
        this.numtelefono = numtelefon;
        this.puesto = puesto;
        this.contraseña = contraseña;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNumtelefono() {
        return numtelefono;
    }

    public void setNumtelefono(int numtelefono) {
        this.numtelefono = numtelefono;
    }

    public int getPuesto() {
        return puesto;
    }

    public void setPuesto(int puesto) {
        this.puesto = puesto;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

}
