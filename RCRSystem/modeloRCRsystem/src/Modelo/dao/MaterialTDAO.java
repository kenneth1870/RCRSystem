package Modelo.dao;

import Modelo.AdministrarMaterial;
import Modelo.BD.Conexion;
import Modelo.BD.Database;
import Modelo.MaterialT;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MaterialTDAO {

    public static String NOMBRE_TABLA = "TipoMaterial";
    public static String SELECCIONAR_TODO = "select * from " + MaterialTDAO.NOMBRE_TABLA;

    public int grabar(MaterialT cnt) {
        String sql = "INSERT INTO TipoMaterial (codigoTM,Tmaterial,precio) Values ("
                + cnt.getCodigo() + ",'"
                + cnt.getTmaterial() + ",'"
                + cnt.getPrecio()
                + ")";
        return Database.executeUpdate(sql);
    }

    public int actualizar(MaterialT cnt) {
        String sql = "UPDATE TipoMaterial SET Tmaterial='" + cnt.getTmaterial()
                + "',precio=" + cnt.getPrecio()
                + " where codigoTM=" + cnt.getCodigo();
        return Database.executeUpdate(sql);
    }

    public int borrar(MaterialT cnt) {
        String sql = "DELETE  FROM TipoMaterial WHERE codigoTM=" + cnt.getCodigo();
        return Database.executeUpdate(sql);
    }

    public List<MaterialT> getMaterialesTipo() {
        ResultSet material = Database.executeQuery(MaterialTDAO.SELECCIONAR_TODO);
        List<MaterialT> ListaMaterialesTipo = new ArrayList();

        try {
            while (material.next()) {
                MaterialT cnt = new MaterialT();
                cnt.setCodigo(material.getString(1));
                cnt.setTmaterial(MaterialDAO.getMaterial(material.getString(2)));
                cnt.setPrecio(material.getFloat(3));
                ListaMaterialesTipo.add(cnt);
            }
            material.close();
            Database.cnx.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return ListaMaterialesTipo;
    }

    public static MaterialT getMaterialTipo(String codigoMaterial) {
        ResultSet rs = Conexion.getRegistros(MaterialTDAO.SELECCIONAR_TODO + " where codigoTM = '" + codigoMaterial + "'");
        MaterialT cnt = null;
        try {
            if (rs.next()) {
                cnt = new MaterialT();
                cnt.setCodigo(rs.getString(1));
                cnt.setTmaterial(MaterialDAO.getMaterial(rs.getString(2)));
                cnt.setPrecio(rs.getFloat(3));
            }
            rs.close();
            Conexion.con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return cnt;
    }
    
   public static AdministrarMaterial getprecios(AdministrarMaterial material) {
        ResultSet rs = Conexion.getRegistros("select precio from TipoMaterial" + " where tmaterial = '" + material.getMaterial().getCodigo() + "'");
      //  int cnt = null;
        try {
            if (rs.next()) {
                material.setPrecioPaca(rs.getFloat(1));
              if(rs.next()){
                material.setPrecioSaca(rs.getFloat(1));
              }
            }
            rs.close();
            Conexion.con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return material;
    }
}
