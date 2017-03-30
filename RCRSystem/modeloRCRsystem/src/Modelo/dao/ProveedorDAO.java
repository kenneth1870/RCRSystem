package Modelo.dao;

import Modelo.BD.Conexion;
import Modelo.BD.Database;
import Modelo.Cliente;
import Modelo.Proveedor;
import static Modelo.dao.ClienteDAO.db;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Vector;

public class ProveedorDAO {

    public static String NOMBRE_TABLA = "PROVEEDOR";
    public static String SELECCIONAR_TODO = "select * from " + ProveedorDAO.NOMBRE_TABLA;
    static Database db = new Database(null, null, null);

    public static int grabar(Proveedor cnt) {
        String sql = "INSERT INTO Proveedor (codigo,nombre,descripcion) Values ('"
                + cnt.getCodigo() + "','"
                + cnt.getNombre() + "','"
                + cnt.getDescripcion()
                + "')";
        return db.executeUpdate(sql);
    }

    public static int actualizar(Proveedor cnt) {
        String sql = "UPDATE Proveedor set nombre='" + cnt.getNombre()
                + "',descripcion='" + cnt.getDescripcion()
                + "' where codigo='" + cnt.getCodigo()+"'";
        return db.executeUpdate(sql);
    }

    public static Collection<Proveedor> getProveedores2() {
        Database baseDatos = new Database(null, null, null);
        ResultSet proveedor = baseDatos.executeQuery(ProveedorDAO.SELECCIONAR_TODO);
        java.util.Hashtable<String, Proveedor> proveedores = new java.util.Hashtable<String, Proveedor>();

        try {
            while (proveedor.next()) {
                Proveedor cnt = new Proveedor();
                cnt.setCodigo(proveedor.getString(1));
                cnt.setNombre(proveedor.getString(2));
                cnt.setDescripcion(proveedor.getString(3));
                proveedores.put(cnt.getCodigo(), cnt);
            }
            proveedor.close();
            baseDatos.cnx.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return new Vector(proveedores.values());
    }

    public static List<Proveedor> getProveedores() {
        List<Proveedor> resultado;
        resultado = new ArrayList<Proveedor>();
        if (Conexion.Conectar() != 0) {//si no  hay conexion a la base
            ResultSet rs = null;
            try {
                rs = Conexion.getRegistros(ProveedorDAO.SELECCIONAR_TODO);
                while (rs.next()) {
                    Proveedor cnt = new Proveedor();
                    cnt.setCodigo(rs.getString(1));
                    cnt.setNombre(rs.getString(2));
                    cnt.setDescripcion(rs.getString(3));
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

    public static Proveedor getProveedor(String codigo) {
        //Connection connection = null;
        //Conexion miConexion = new Conexion();
        // connection = Conexion.getConnection();
        Proveedor cnt = new Proveedor();
        if (Conexion.Conectar() != 0) {//si no  hay conexion a la base
            ResultSet rs = null;
            try {
                rs = Conexion.getRegistros(ProveedorDAO.SELECCIONAR_TODO + " where codigo=  '" + codigo + "'");
                if (rs.next()) {
                    cnt = new Proveedor();
                    cnt.setCodigo(rs.getString(1));
                    cnt.setNombre(rs.getString(2));
                    cnt.setDescripcion(rs.getString(3));
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
    
        public static List<Proveedor> getProveedores(String id) {
        List<Proveedor> ListaProveedores = new ArrayList();
        if (Conexion.Conectar() != 0) {//si no  hay conexion a la base
            ResultSet rs = Conexion.getRegistros(ProveedorDAO.SELECCIONAR_TODO + " where identificacion != '" + id + "'");

            try {
                while (rs.next()) {
                    Proveedor cnt = new Proveedor();
                    cnt.setCodigo(rs.getString(1));
                    cnt.setNombre(rs.getString(2));
                    cnt.setDescripcion(rs.getString(3));
                    ListaProveedores.add(cnt);
                }
                rs.close();
                Conexion.con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } else {
            ListaProveedores = null;
        }
        return ListaProveedores;
    }

    static public int eliminar(Proveedor cnt) {
        String sql = "delete from Proveedor"
                + " where codigo= '" + cnt.getCodigo() + "'";
        return db.executeUpdate(sql);
    }

}
