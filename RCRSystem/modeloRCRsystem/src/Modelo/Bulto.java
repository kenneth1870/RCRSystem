package Modelo;

import Modelo.MaterialT;
import java.io.Serializable;
import java.util.Objects;

public class Bulto implements Serializable {

    String codigo;
    int tipo;
    float peso;
    MaterialT material;
    int estado;

    public Bulto() {
        this.codigo = "";
        this.tipo = -1;
        this.material = new MaterialT();
        this.estado = -1;
    }

    public Bulto(String codigo, int tipo, float peso, MaterialT material, int estado) {
        this.codigo = codigo;
        this.tipo = tipo;
        this.peso = peso;
        this.material = material;
        this.estado = estado;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public MaterialT getMaterial() {
        return material;
    }

    public void setMaterial(MaterialT material) {
        this.material = material;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String toString() {
        if (tipo == 1) {
            return "Paca";
        }
        return "Saca";
    }

    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.codigo);
        return hash;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Bulto other = (Bulto) obj;
        if (!Objects.equals(this.codigo, other.codigo)) {
            return false;
        }
        return true;
    }
}
