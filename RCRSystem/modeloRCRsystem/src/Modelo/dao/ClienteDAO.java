package Modelo.dao;

import Modelo.BD.Conexion;
import Modelo.BD.Database;
import Modelo.Cliente;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Vector;

public class ClienteDAO {

    public static String NOMBRE_TABLA = "Cliente";
    public static String SELECCIONAR_TODO = "select * from " + ClienteDAO.NOMBRE_TABLA;
    static Database db = new Database(null, null, null);

    public static int grabar(Cliente cnt) {
        String sql = "INSERT INTO Cliente (codigoC,nombreC) Values ('"
                + cnt.getCodigoC() + "','"
                + cnt.getNombreC() + "')";
        return db.executeUpdate(sql);
    }

    public static int actualizar(Cliente cnt) {
        String sql = "UPDATE Cliente SET nombreC='" + cnt.getNombreC()
                + "' where codigoC='" + cnt.getCodigoC()+"'";
        return db.executeUpdate(sql);
    }

    public static Collection<Cliente> getCliente2() {
        Database baseDatos = new Database(null, null, null);
        ResultSet cliente = baseDatos.executeQuery(ClienteDAO.SELECCIONAR_TODO);
        java.util.Hashtable<String, Cliente> clientes = new java.util.Hashtable<String, Cliente>();

        try {
            while (cliente.next()) {
                Cliente cnt = new Cliente();
                cnt.setCodigoC(cliente.getString(1));
                cnt.setNombreC(cliente.getString(2));
                clientes.put(cnt.getCodigoC(), cnt);
            }
            cliente.close();
            baseDatos.cnx.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return new Vector(clientes.values());
    }

    public static List<Cliente> getClientes() {
        List<Cliente> resultado;
        resultado = new ArrayList<Cliente>();
        if (Conexion.Conectar() != 0) {//si no  hay conexion a la base
            ResultSet rs = null;
            try {
                rs = Conexion.getRegistros(ClienteDAO.SELECCIONAR_TODO);
                while (rs.next()) {
                    Cliente cnt = new Cliente();
                    cnt.setCodigoC(rs.getString(1));
                    cnt.setNombreC(rs.getString(2));
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

    public static Cliente getCliente(String codigo) {
        //Connection connection = null;
        //Conexion miConexion = new Conexion();
        // connection = Conexion.getConnection();
        Cliente cnt = new Cliente();
        if (Conexion.Conectar() != 0) {//si no  hay conexion a la base
            ResultSet rs = null;
            try {
                rs = Conexion.getRegistros(ClienteDAO.SELECCIONAR_TODO + " where codigoC=  '" + codigo + "'");
                if (rs.next()) {
                    cnt = new Cliente();
                    cnt.setCodigoC(rs.getString(1));
                    cnt.setNombreC(rs.getString(2));
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

    public static List<Cliente> getClientes(String id) {
        List<Cliente> ListaClientes = new ArrayList();
        if (Conexion.Conectar() != 0) {//si no  hay conexion a la base
            ResultSet rs = Conexion.getRegistros(ClienteDAO.SELECCIONAR_TODO + " where identificacion != '" + id + "'");

            try {
                while (rs.next()) {
                    Cliente cnt = new Cliente();
                    cnt.setCodigoC(rs.getString(1));
                    cnt.setNombreC(rs.getString(2));
                    ListaClientes.add(cnt);
                }
                rs.close();
                Conexion.con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } else {
            ListaClientes = null;
        }
        return ListaClientes;
    }

    static public int eliminar(Cliente cnt) {
        String sql = "delete from Cliente"
                + " where codigoC= '" + cnt.getCodigoC() + "'";
        return db.executeUpdate(sql);
    }

}
