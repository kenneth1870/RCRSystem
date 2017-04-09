package Modelo;

public class TotalMaterialVendido {

    Material materialVendido;
    ListaEmpaque listEmp;
    int cantBultosV;
    float pesoTotalV;
    float precioUnid;
    float importe;

    public TotalMaterialVendido() {
materialVendido=new Material();
    listEmp=new ListaEmpaque();
    cantBultosV=0;
    pesoTotalV=0;
    precioUnid=0;
    importe=0;
    }

    public TotalMaterialVendido(Material materialVendido, ListaEmpaque listEmp, int cantBultosV, float pesoTotalV, float precioUnid, float importe) {
        this.materialVendido = materialVendido;
        this.listEmp = listEmp;
        this.cantBultosV = cantBultosV;
        this.pesoTotalV = pesoTotalV;
        this.precioUnid = precioUnid;
        this.importe = importe;
    }

    public Material getMaterialVendido() {
        return materialVendido;
    }

    public void setMaterialVendido(Material materialVendido) {
        this.materialVendido = materialVendido;
    }

    public ListaEmpaque getListEmp() {
        return listEmp;
    }

    public void setListEmp(ListaEmpaque listEmp) {
        this.listEmp = listEmp;
    }

    public int getCantBultosV() {
        return cantBultosV;
    }

    public void setCantBultosV(int cantBultosV) {
        this.cantBultosV = cantBultosV;
    }

    public float getPesoTotalV() {
        return pesoTotalV;
    }

    public void setPesoTotalV(float pesoTotalV) {
        this.pesoTotalV = pesoTotalV;
    }

    public float getPrecioUnid() {
        return precioUnid;
    }

    public void setPrecioUnid(float precioUnid) {
        this.precioUnid = precioUnid;
    }

    public float getImporte() {
        return importe;
    }

    public void setImporte(float importe) {
        this.importe = importe;
    }

}
