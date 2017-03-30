package Modelo.dao;

import Modelo.BD.Conexion;
import Modelo.BD.Database;
import Modelo.Material;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MaterialDAO {

    public static String NOMBRE_TABLA = "Materiales";
    public static String SELECCIONAR_TODO = "select * from " + MaterialDAO.NOMBRE_TABLA;

    public int grabar(Material cnt) {
        String sql = "INSERT INTO Materiales (codigoMaterial,nombre) Values ("
                + cnt.getCodigo() + ",'"
                + cnt.getNombre()
                + ")";
        return Database.executeUpdate(sql);
    }

    public int actualizar(Material cnt) {
        String sql = "UPDATE Materiales SET nombre='" + cnt.getNombre()
                + " where codigoMaterial=" + cnt.getCodigo();
        return Database.executeUpdate(sql);
    }

    public int borrar(Material cnt) {
        String sql = "DELETE  FROM Materiales WHERE codigoMaterial=" + cnt.getCodigo();
        return Database.executeUpdate(sql);
    }

    public static List<Material> getMateriales() {
        List<Material> resultado;
        resultado = new ArrayList<Material>();
        if (Conexion.Conectar() != 0) {//si no  hay conexion a la base
            ResultSet rs = null;
            try {
                rs = Conexion.getRegistros(MaterialDAO.SELECCIONAR_TODO);
                while (rs.next()) {
                    Material cnt = new Material();
                    cnt.setCodigo(rs.getString(1));
                    cnt.setNombre(rs.getString(2));
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

    public static Material getMaterial(String codigoMaterial) {
        ResultSet rs = Conexion.getRegistros(MaterialDAO.SELECCIONAR_TODO + " where codigoMaterial = '" + codigoMaterial + "'");
        Material cnt = null;
        try {
            if (rs.next()) {
                cnt = new Material();
                cnt.setCodigo(rs.getString(1));
                cnt.setNombre(rs.getString(2));
            }
            rs.close();
            Conexion.con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return cnt;
    }

}
