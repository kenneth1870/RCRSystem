package Modelo.dao;

import Modelo.BD.Conexion;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BultoCompradoDAO {

    public static String NOMBRE_TABLA = "BultoComprado";
    public static String SELECCIONAR_TODO = "select * from " + BultoCompradoDAO.NOMBRE_TABLA;

   /* public static int grabar(BultoComprado cnt) {
        String sql = "INSERT INTO BultoComprado (codigoBultoComprado,tipoBultoComprado,pesoBultoComprado,materialBultoComprado) Values ('"
                + cnt.getCodigo() + "',"
                + cnt.getTipo() + ","
                + cnt.getPeso() + ",'"
                + cnt.getMaterial().getCodigo()
                + "')";
        return Conexion.guardarRegistro(sql);
    }

    public int actualizar(BultoComprado cnt) {
        String sql = "UPDATE BultoComprado SET tipoBultoComprado='" + cnt.getTipo()
                + "',pesoBultoComprado=" + cnt.getPeso()
                + "',materialBultoComprado=" + cnt.getMaterial()
                + " where codigoBultoComprado=" + cnt.getCodigo();
        return Conexion.guardarRegistro(sql);
    }

    public int borrar(BultoComprado cnt) {
        String sql = "DELETE  FROM BultoComprado WHERE codigoBultoComprado=" + cnt.getCodigo();
        return Conexion.guardarRegistro(sql);
    }

    public static List<BultoComprado> getBultosComprados() {
        ResultSet bulto = Conexion.getRegistros(BultoCompradoDAO.SELECCIONAR_TODO);
        List<BultoComprado> ListaBultos = new ArrayList();

        try {
            while (bulto.next()) {
                BultoComprado cnt = new BultoComprado();
                cnt.setCodigo(bulto.getString(1));
                cnt.setTipo(bulto.getInt(2));
                cnt.setPeso(bulto.getFloat(3));
                cnt.setMaterial(MaterialTDAO.getMaterialTipo(bulto.getString(4)));
                ListaBultos.add(cnt);
            }

            bulto.close();
            Conexion.con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return ListaBultos;
    }

    public static BultoComprado getBulto(String codigoBulto) {
        ResultSet bulto = Conexion.getRegistros(BultoCompradoDAO.SELECCIONAR_TODO + " where codigoBultoComprado = '" + codigoBulto + "'");
        BultoComprado cnt = null;
        try {
            if (bulto.next()) {
                cnt = new BultoComprado();
                cnt.setCodigo(bulto.getString(1));
                cnt.setTipo(bulto.getInt(2));
                cnt.setPeso(bulto.getFloat(3));
                cnt.setMaterial(MaterialTDAO.getMaterialTipo(bulto.getString(4)));
            }
            bulto.close();
            Conexion.con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return cnt;
    }

    //devuelve la lista de todos los bultos comprados que sean del tipo de materia codMaterial
    public static List<BultoComprado> getBultosCompXMaterial(String codMaterial) {
        ResultSet bulto = Conexion.getRegistros("Select * from BultoComprado where materialBultoComprado ='" + ("P" + codMaterial)
                + "' or materialBultoComprado ='" + ("S" + codMaterial) + "'");
        List<BultoComprado> ListaBultos = new ArrayList();
        try {
            while (bulto.next()) {
                BultoComprado cnt = new BultoComprado();
                cnt.setCodigo(bulto.getString(1));
                cnt.setTipo(bulto.getInt(2));
                cnt.setPeso(bulto.getFloat(3));
                cnt.setMaterial(MaterialTDAO.getMaterialTipo(bulto.getString(4)));
                ListaBultos.add(cnt);
            }
            bulto.close();
            Conexion.con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return ListaBultos;
    }
*/
}
