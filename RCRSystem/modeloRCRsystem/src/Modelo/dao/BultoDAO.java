package Modelo.dao;

import Modelo.BD.Conexion;
import Modelo.Bulto;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BultoDAO {

    public static String NOMBRE_TABLA = "Bulto";
    public static String SELECCIONAR_TODO = "select * from " + BultoDAO.NOMBRE_TABLA;

    public static int grabar(Bulto cnt) {
        String sql = "INSERT INTO Bulto (codigoBulto,tipoBulto,pesoBulto,materialBulto,estado) Values ('"
                + cnt.getCodigo() + "',"
                + cnt.getTipo() + ","
                + cnt.getPeso() + ",'"
                + cnt.getMaterial().getCodigo() + "', "
                + cnt.getEstado()
                + ")";
        return Conexion.guardarRegistro(sql);
    }

    public static int actualizar(Bulto cnt) {
        String sql = "UPDATE Bulto SET estado=0 "
                + " where codigoBulto='" + cnt.getCodigo()+"'";
        return Conexion.guardarRegistro(sql);
    }

    public int borrar(Bulto cnt) {
        String sql = "DELETE  FROM Bulto WHERE codigoBulto=" + cnt.getCodigo();
        return Conexion.guardarRegistro(sql);
    }

    public static List<Bulto> getBultosComprados() {
        ResultSet bulto = Conexion.getRegistros(BultoDAO.SELECCIONAR_TODO);
        List<Bulto> ListaBultos = new ArrayList();

        try {
            while (bulto.next()) {
                Bulto cnt = new Bulto();
                cnt.setCodigo(bulto.getString(1));
                cnt.setTipo(bulto.getInt(2));
                cnt.setPeso(bulto.getFloat(3));
                cnt.setMaterial(MaterialTDAO.getMaterialTipo(bulto.getString(4)));
                cnt.setEstado(bulto.getInt(5));
                ListaBultos.add(cnt);
            }

            bulto.close();
            Conexion.con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return ListaBultos;
    }

    public static Bulto getBulto(String codigoBulto) {
        ResultSet bulto = Conexion.getRegistros(BultoDAO.SELECCIONAR_TODO + " where codigoBulto = '" + codigoBulto + "'");
        Bulto cnt = null;
        try {
            if (bulto.next()) {
                cnt = new Bulto();
                cnt.setCodigo(bulto.getString(1));
                cnt.setTipo(bulto.getInt(2));
                cnt.setPeso(bulto.getFloat(3));
                cnt.setMaterial(MaterialTDAO.getMaterialTipo(bulto.getString(4)));
                cnt.setEstado(bulto.getInt(5));
            }
            bulto.close();
            Conexion.con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return cnt;
    }

    //devuelve la lista de todos los bultos comprados que sean del tipo de materia codMaterial
    public static List<Bulto> getBultosCompXMaterial(String codMaterial) {
        ResultSet bulto = Conexion.getRegistros("Select * from Bulto where (materialBulto ='" + ("P" + codMaterial)
                + "' or materialBulto ='" + ("S" + codMaterial) + "') and estado = 1");
        List<Bulto> ListaBultos = new ArrayList();
        try {
            while (bulto.next()) {
                Bulto cnt = new Bulto();
                cnt.setCodigo(bulto.getString(1));
                cnt.setTipo(bulto.getInt(2));
                cnt.setPeso(bulto.getFloat(3));
                cnt.setMaterial(MaterialTDAO.getMaterialTipo(bulto.getString(4)));
                cnt.setEstado(bulto.getInt(5));
                ListaBultos.add(cnt);
            }
            bulto.close();
            Conexion.con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return ListaBultos;
    }

       public static List<Bulto> getBultosVentaXMaterial(String codMaterial) {
        ResultSet bulto = Conexion.getRegistros("Select * from Bulto where (materialBulto ='" + ("P" + codMaterial)
                + "' or materialBulto ='" + ("S" + codMaterial) + "') and estado = 0");
        List<Bulto> ListaBultos = new ArrayList();
        try {
            while (bulto.next()) {
                Bulto cnt = new Bulto();
                cnt.setCodigo(bulto.getString(1));
                cnt.setTipo(bulto.getInt(2));
                cnt.setPeso(bulto.getFloat(3));
                cnt.setMaterial(MaterialTDAO.getMaterialTipo(bulto.getString(4)));
                cnt.setEstado(bulto.getInt(5));
                ListaBultos.add(cnt);
            }
            bulto.close();
            Conexion.con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return ListaBultos;
    }

}
