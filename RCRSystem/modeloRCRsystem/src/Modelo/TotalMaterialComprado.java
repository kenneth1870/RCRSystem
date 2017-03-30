package Modelo;

public class TotalMaterialComprado {

    Material materialComprado;
    RegistroCompra regComp;
    float pesoTotalC;

    public TotalMaterialComprado() {

    }

    public TotalMaterialComprado(Material materialComprado, RegistroCompra regComp, float pesoTotalC) {
        this.materialComprado = materialComprado;
        this.regComp = regComp;
        this.pesoTotalC = pesoTotalC;
    }

    public Material getMaterialComprado() {
        return materialComprado;
    }

    public void setMaterialComprado(Material materialComprado) {
        this.materialComprado = materialComprado;
    }

    public RegistroCompra getRegComp() {
        return regComp;
    }

    public void setRegComp(RegistroCompra regComp) {
        this.regComp = regComp;
    }

    public float getPesoTotalC() {
        return pesoTotalC;
    }

    public void setPesoTotalC(float pesoTotalC) {
        this.pesoTotalC = pesoTotalC;
    }

}
