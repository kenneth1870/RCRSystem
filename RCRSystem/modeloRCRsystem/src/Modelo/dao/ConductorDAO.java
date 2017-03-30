package Modelo.dao;

import Modelo.BD.Conexion;
import Modelo.Conductor;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConductorDAO {

    public static String NOMBRE_TABLA = "Conductor";
    public static String SELECCIONAR_TODO = "select * from " + ConductorDAO.NOMBRE_TABLA;

    public static int grabar2(Conductor cnt) {
        String sql = "INSERT INTO Conductor (identificacionConductor,nombreConductor,nacionalidadConductor,fechaNacimientoConductor) Values ('"
                + cnt.getId() + "','"
                + cnt.getNombre() + "','"
                + cnt.getNacionalidad() + "','"
                + cnt.getFechaNacimiento() + "')";
        return Conexion.guardarRegistro(sql);
    }
 public static int grabar(Conductor cnt) {
        String sql = "INSERT INTO Conductor (identificacionConductor,nombreConductor,nacionalidadConductor) Values ('"
                + cnt.getId() + "','"
                + cnt.getNombre() + "','"
                + cnt.getNacionalidad() + "')";
        return Conexion.guardarRegistro(sql);
    }
    public static boolean existeConductor(String id) {
        //Connection connection = null;
      boolean bandera=false;  //Conexion miConexion = new Conexion();
        // connection = Conexion.getConnection();
        Conductor cnt = new Conductor();
        if (Conexion.Conectar() != 0) {//si no  hay conexion a la base
            ResultSet rs = null;
            try {
                rs = Conexion.getRegistros(ConductorDAO.SELECCIONAR_TODO + " where identificacionConductor=  '" + id + "'");
                if (rs.next()) {
                   bandera= true;
                }
                rs.close();
                Conexion.con.close();

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } else {
          bandera=false;
        }
return bandera;
    }
    
    public static Conductor getConductor(String id) {
        //Connection connection = null;
        //Conexion miConexion = new Conexion();
        // connection = Conexion.getConnection();
        Conductor cnt = new Conductor();
        if (Conexion.Conectar() != 0) {//si no  hay conexion a la base
            ResultSet rs = null;
            try {
                rs = Conexion.getRegistros(ConductorDAO.SELECCIONAR_TODO + " where identificacionConductor=  '" + id + "'");
                if (rs.next()) {
                    cnt = new Conductor();
                    cnt.setId(rs.getString(1));
                    cnt.setNombre(rs.getString(2));
                    cnt.setNacionalidad(rs.getString(3));
                    cnt.setFechaNacimiento(rs.getString(4));
                }
                rs.close();
                Conexion.con.close();

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } else {
            cnt = null;
        }

        return cnt;
    }

}
