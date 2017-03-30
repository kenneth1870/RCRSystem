package Modelo.dao;

import Modelo.BD.Conexion;
import Modelo.BD.Database;
import Modelo.Material;
import Modelo.AdministrarMaterial;
import Modelo.MaterialT;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class AdministrarMaterialDAO {
       public static String NOMBRE_TABLA = "Materiales";
    public static String SELECCIONAR_TODO = "select * from " + MaterialDAO.NOMBRE_TABLA;
static Database db = new Database(null,null,null);

    public static int grabar(AdministrarMaterial cnt) {
        String sql = "INSERT INTO Materiales (codigoMaterial,nombre) Values ('"
                + cnt.getMaterial().getCodigo() + "','"
                + cnt.getMaterial().getNombre()
                + "')";
        System.out.println(sql);
        return db.executeUpdate(sql);
    }
 
  public static int grabarTipo(AdministrarMaterial cnt) {
        String sql = "INSERT INTO TipoMaterial (codigoTM,Tmaterial,precio) Values ('"+"P"
                + cnt.getMaterial().getCodigo()+ "','"
                + cnt.getMaterial().getCodigo()+ "', "
                + cnt.getPrecioPaca()
                + ")";
        db.executeUpdate(sql);
        sql = "INSERT INTO TipoMaterial (codigoTM,Tmaterial,precio) Values ('"+"S"
                + cnt.getMaterial().getCodigo()+ "','"
                + cnt.getMaterial().getCodigo()+ "', "
                + cnt.getPrecioSaca()
                + ")";
        return db.executeUpdate(sql);
    }
  
    public  static int actualizar(AdministrarMaterial cnt) {
        String sql = "UPDATE Materiales SET nombre='" + cnt.getMaterial().getNombre()
                + "' where codigoMaterial= '" + cnt.getMaterial().getCodigo()+"'";
        System.out.println(sql);
        return db.executeUpdate(sql);
    }
  public static int actualizarTipo(AdministrarMaterial cnt) {
        String sql = "UPDATE tipomaterial SET precio= " + cnt.getPrecioPaca()
                + " where codigotm=" +"'P"+ cnt.getMaterial().getCodigo()+"'";
      System.out.println(sql);
        db.executeUpdate(sql);
         sql = "UPDATE tipomaterial SET precio= " + cnt.getPrecioSaca()
                + " where codigotm=" +"'S"+ cnt.getMaterial().getCodigo()+"'";
      System.out.println(sql);
       return db.executeUpdate(sql);
    }
  
    public int borrar(Material cnt) {
        String sql = "DELETE  FROM Materiales WHERE codigoMaterial=" + cnt.getCodigo();
        return Database.executeUpdate(sql);
    }

    public static List<AdministrarMaterial> getMateriales() {
        List<AdministrarMaterial> resultado;
        resultado = new ArrayList<AdministrarMaterial>();
        if (Conexion.Conectar() != 0) {//si no  hay conexion a la base
            ResultSet rs = null;
            try {
                rs = Conexion.getRegistros(MaterialDAO.SELECCIONAR_TODO);
                while (rs.next()) {
                    AdministrarMaterial cnt = new AdministrarMaterial();
                    cnt.getMaterial().setCodigo(rs.getString(1));
                    cnt.getMaterial().setNombre(rs.getString(2));
                    cnt= MaterialTDAO.getprecios(cnt);
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
