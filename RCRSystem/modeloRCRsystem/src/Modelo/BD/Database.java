package Modelo.BD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Database {

    public static Connection cnx;

    public Database(String servidorArg, String usuarioArg, String claveArg) {
        if (servidorArg != null) {
            cnx = this.getConnection(servidorArg, usuarioArg, claveArg);

        } else {
            cnx = this.getConnection(null, null, null);
        }
    }

    public static Connection getConnection(String servidorArg, String usuarioArg, String claveArg) {
        try {
            String servidor = (servidorArg == null ? SERVIDOR : servidorArg);
            String usuario = (usuarioArg == null ? USUARIO : usuarioArg);
            String clave = (claveArg == null ? CLAVE : claveArg);
            String URL_conexion = PROTOCOLO + "//" + servidor + ":" + PUERTO + "/" + BASEDATOS + "?user=" + usuario + "&password=" + clave;
            Class.forName(MANEJADOR_DB).newInstance();
            return DriverManager.getConnection(URL_conexion);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.exit(-1);
        }
        return null;
    }

    public static int executeUpdate(String statement) {
        try {
            Statement stm = cnx.createStatement();
            stm.executeUpdate(statement);
            return stm.getUpdateCount();
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    public static ResultSet executeQuery2(String statement) {
        try {
            Statement stm = cnx.createStatement();
            return stm.executeQuery(statement);
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static ResultSet executeQuery(String sql) {
        Database.getConnection(null, null, null);
        try {
            Statement st = Database.cnx.createStatement();
            ResultSet eq = st.executeQuery(sql);
            return eq;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    private static final String MANEJADOR_DB = "org.postgresql.Driver";
    private static final String PROTOCOLO = "jdbc:postgresql:";
    private static final String SERVIDOR = "localhost";
    private static final String PUERTO = "5432";
    private static final String USUARIO = "postgres";
    private static final String CLAVE = "root";
    private static final String BASEDATOS = "RCRSystemDB";
}
