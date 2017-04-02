package Modelo.dao;

import Modelo.BD.Conexion;
import Modelo.BD.Database;
import Modelo.Usuario;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    public static String NOMBRE_TABLA = "usuarios";
    public static String SELECCIONAR_TODO = "select * from " + UsuarioDAO.NOMBRE_TABLA;
    static Database db = new Database(null, null, null);

    public static int grabar(Usuario cnt) {

        String sql = "INSERT INTO Usuarios (identificacion,nombre,numTelefono,puesto,contraseña) values ('"
                + cnt.getIdentificacion() + "' ,'"
                + cnt.getNombre() + "',"
                + cnt.getNumtelefono() + ", "
                + cnt.getPuesto() + ", '"
                + cnt.getContraseña()
                + "')";
        return db.executeUpdate(sql);
    }

    static public int actualizar(Usuario cnt) {
        String sql = "UPDATE Usuarios set nombre='" 
                + cnt.getNombre() + "',numTelefono=" 
                + cnt.getNumtelefono() + ",puesto=" 
                + cnt.getPuesto() + ", contraseña= '" 
                + cnt.getContraseña() + "' where identificacion= '" 
                + cnt.getIdentificacion() + "'";
        return db.executeUpdate(sql);
    }

    public static List<Usuario> getUsuarios(String id) {
        List<Usuario> ListaUsuarios = new ArrayList();
        if (Conexion.Conectar() != 0) {//si no  hay conexion a la base
            ResultSet rs = Conexion.getRegistros(UsuarioDAO.SELECCIONAR_TODO + " where identificacion != '" + id + "'");

            try {
                while (rs.next()) {
                    Usuario cnt = new Usuario();
                    cnt.setIdentificacion(rs.getString(1));
                    cnt.setNombre(rs.getString(2));
                    cnt.setNumtelefono(rs.getInt(3));
                    cnt.setPuesto(rs.getInt(4));
                    cnt.setContraseña(rs.getString(5));
                    ListaUsuarios.add(cnt);
                }
                rs.close();
                Conexion.con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } else {
            ListaUsuarios = null;
        }
        return ListaUsuarios;
    }

    public static Usuario getUsuario(String identificacion, String pass) {
        Usuario cnt = new Usuario();
        if (Conexion.Conectar() != 0) {//si no  hay conexion a la base
            ResultSet rs = null;
            try {
                rs = Conexion.getRegistros(UsuarioDAO.SELECCIONAR_TODO + " where identificacion=  '" + identificacion + "' and contraseña= '" + pass + "'");
                if (rs.next()) {
                    cnt = new Usuario();
                    cnt.setIdentificacion(rs.getString(1));
                    cnt.setNombre(rs.getString(2));
                    cnt.setNumtelefono(rs.getInt(3));
                    cnt.setPuesto(rs.getInt(4));
                    cnt.setContraseña(rs.getString(5));
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

    static public int eliminar(Usuario cnt) {
        String sql = "delete from Usuarios"
                + " where identificacion= '" + cnt.getIdentificacion() + "'";
        return db.executeUpdate(sql);
    }

}
