package Modelo.dao;

import Modelo.BD.Conexion;
import Modelo.Bulto;
import Modelo.Inventario;
import Modelo.Material;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InventarioDAO {

    public static String NOMBRE_TABLA = "Inventario";
    public static String SELECCIONAR_TODO = "select * from " + InventarioDAO.NOMBRE_TABLA;

    public static List<Inventario> obtenerInventario() {
        List<Inventario> resultado;
        resultado = new ArrayList<Inventario>();
        if (Conexion.Conectar() != 0) {//si no  hay conexion a la base
            ResultSet rs = null;
            try {
                rs = Conexion.getRegistros(InventarioDAO.SELECCIONAR_TODO);
                while (rs.next()) {
                    Inventario cnt = new Inventario();
                    cnt.setMaterial(MaterialDAO.getMaterial(rs.getString(1)));
                    cnt.setCantidad(rs.getFloat(2));
                    cnt.setPrecio(rs.getFloat(3));
                    resultado.add(cnt);
                }
                rs.close();
                Conexion.con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } else {
            resultado = null;
        }
        return resultado;
    }

    //solo de prueba
    public void imprimir(List<Inventario> i) {
        for (Inventario invent : i) {
            System.out.println(invent.getMaterial().getNombre());
        }
    }
    
    public int actualizar(Inventario inv) {
        String sql = "UPDATE inventario SET cantidadT= " + inv.getCantidad()
                + " where material='" + inv.getMaterial().getCodigo() + "'";
        return Conexion.guardarRegistro(sql);
    }
     public static List<Material> obtenerInventarioVenta() {
        List<Material> resultado;
        resultado = new ArrayList<Material>();
        if (Conexion.Conectar() != 0) {//si no  hay conexion a la base
            ResultSet rs = null;
            try {
                rs = Conexion.getRegistros("select material from Inventario where cantidadT >= 20000");
                while (rs.next()) {
                    Material material = new Material();
                    material=(MaterialDAO.getMaterial(rs.getString(1)));
                    resultado.add(material);
                }
                rs.close();
                Conexion.con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } else {
            resultado = null;
        }
        return resultado;
    }
     
  public static Inventario obtenerInventario(String material) {
        Inventario inv = new Inventario();
        if (Conexion.Conectar() != 0) {//si no  hay conexion a la base
            ResultSet rs = null;
            try {
                rs = Conexion.getRegistros("select * from Inventario where material="+"'"+material+"'");
                if (rs.next()) {
                    Inventario cnt = new Inventario();
                    cnt.setMaterial(MaterialDAO.getMaterial(rs.getString(1)));
                    cnt.setCantidad(rs.getFloat(2));
                    cnt.setPrecio(rs.getFloat(3));
                    inv=cnt;
                }
                rs.close();
                Conexion.con.close();
                return inv;
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } else {
          inv= null;
        }
        return inv;
    }
  public static int actualizarPeso(Bulto cnt) {
        String sql = "UPDATE Inventario SET  cantidadT= cantidadT-"+cnt.getPeso()
                + " where material='" + cnt.getMaterial().getCodigo()+"'";
        return Conexion.guardarRegistro(sql);
    }
}
