package Modelo.dao;

import Modelo.Facturar_Venta;
import java.util.ArrayList;
import java.util.List;


public class Facturar_VentaDAO {
  private static List<Facturar_Venta> listas;

    public Facturar_VentaDAO(List<Facturar_Venta> listas) {
        this.listas = listas;
    }
 public Facturar_VentaDAO() {
        this.listas = new ArrayList();
    }
    public List<Facturar_Venta> getListas() {
        return listas;
    }

    public void setListas(List<Facturar_Venta> listas) {
        this.listas = listas;
    }
    
         public static void addFacturaVenta(Facturar_Venta fv) throws Exception{
            listas.add(fv);
    }
}
