package Modelo;


public class AdministrarMaterial {
    Material material;
    float precioSaca;
    float precioPaca;

    public AdministrarMaterial(Material material, float precioSaca, float precioPaca) {
        this.material = material;
        this.precioSaca = precioSaca;
        this.precioPaca = precioPaca;
    }

     public AdministrarMaterial() {
        this.material = new Material();
        this.precioSaca = 0;
        this.precioPaca = 0;
    }
     
    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public float getPrecioSaca() {
        return precioSaca;
    }

    public void setPrecioSaca(float precioSaca) {
        this.precioSaca = precioSaca;
    }

    public float getPrecioPaca() {
        return precioPaca;
    }

    public void setPrecioPaca(float precioPaca) {
        this.precioPaca = precioPaca;
    }
    
    
}
