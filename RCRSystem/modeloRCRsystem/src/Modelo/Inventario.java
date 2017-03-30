package Modelo;

public class Inventario {

    Material material;
    float cantidad;
    float precio;

    public Inventario(Material material, float cantidad, float precio) {
        this.material = material;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    public Inventario() {
        this.material = new Material();
        this.cantidad = 0;
        this.precio = 0;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public float getCantidad() {
        return cantidad;
    }

    public void setCantidad(float cantidad) {
        this.cantidad = cantidad;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

}
