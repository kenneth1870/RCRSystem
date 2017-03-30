package Modelo.dao;

import Modelo.BD.Conexion;
import Modelo.BD.Database;
import Modelo.RegistroCompra;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RegistroCompraDAO {

    public static String NOMBRE_TABLA = "RegistroCompra";
    public static String SELECCIONAR_TODO = "select * from " + RegistroCompraDAO.NOMBRE_TABLA;

    public static int grabar(RegistroCompra cnt) {
        String sql = "INSERT INTO RegistroCompra (numCompra,pesoTotal) Values ("
                + cnt.getNumCompra() + ","
                + cnt.getPesoTotal()
                + ")";
        return Conexion.guardarRegistro(sql);
    }

    public int actualizar(RegistroCompra cnt) {
        String sql = "UPDATE RegistroCompra SET pesoTotal='" + cnt.getPesoTotal()
                + " where numCompra=" + cnt.getNumCompra();
        return Database.executeUpdate(sql);
    }

    public static List<RegistroCompra> getRegistrosCompras() {
        ResultSet usuario = Database.executeQuery(RegistroCompraDAO.SELECCIONAR_TODO);
        List<RegistroCompra> ListaRegistros = new ArrayList();

        try {
            while (usuario.next()) {
                RegistroCompra cnt = new RegistroCompra();
                cnt.setNumCompra(usuario.getInt(1));
                cnt.setPesoTotal(usuario.getFloat(2));
                ListaRegistros.add(cnt);
            }
            usuario.close();
            Database.cnx.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return ListaRegistros;
    }

    public static String getSecuenciaRegCompra() {
        String secRgC = "0";
        if (Conexion.Conectar() != 0) {//si no  hay conexion a la base
            ResultSet rs = null;
            try {
                rs = Conexion.getRegistros("select nextval('sec_numeroRegCompra');");
                if (rs.next()) {
                    secRgC = rs.getString(1);
                }
                rs.close();
                Conexion.con.close();

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } else {
            secRgC = "-1";
        }
        return secRgC;
    }

    public static RegistroCompra getRegistroCompra(String numCompra) {
        //Connection connection = null;
        //Conexion miConexion = new Conexion();
        // connection = Conexion.getConnection();
        RegistroCompra cnt = new RegistroCompra();
        if (Conexion.Conectar() != 0) {//si no  hay conexion a la base
            ResultSet rs = null;
            try {
                rs = Conexion.getRegistros(RegistroCompraDAO.SELECCIONAR_TODO + " where numCompra=  '" + numCompra + "'");
                if (rs.next()) {
                    cnt = new RegistroCompra();
                    cnt.setNumCompra(rs.getInt(1));
                    cnt.setPesoTotal(rs.getFloat(2));
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
