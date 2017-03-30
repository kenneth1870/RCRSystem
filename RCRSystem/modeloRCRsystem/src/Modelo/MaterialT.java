package Modelo;

public class MaterialT {

    String codigo;
    Material Tmaterial;
    float precio;

    public MaterialT() {
    }

    public MaterialT(String codigo, Material Tmaterial, float precio) {
        this.codigo = codigo;
        this.Tmaterial = Tmaterial;
        this.precio = precio;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Material getTmaterial() {
        return Tmaterial;
    }

    public void setTmaterial(Material Tmaterial) {
        this.Tmaterial = Tmaterial;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

}
