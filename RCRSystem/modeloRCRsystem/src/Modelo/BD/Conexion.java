package Modelo.BD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexion {

    public static String bd = "RCRSystemDB";
    public static String login = "postgres";
    public static String password = "root";
    public static String url = "jdbc:postgresql://localhost:5432/" + Conexion.bd;
    public Statement stmt;
    public static Connection con;
    public ResultSet rs;
    public ResultSetMetaData rsMeta;

    private static final String MANEJADOR_DB = "org.postgresql.Driver";
    private static final String PROTOCOLO = "jdbc:postgresql:";
    private static final String SERVIDOR = "localhost";
    private static final String PUERTO = "5432";
    private static final String USUARIO = "postgres";
    private static final String CLAVE = "root";
    private static final String BASEDATOS = "RCRSystemDB";

    public static int Conectar() {
        int i = 0;
        try {
            Class.forName("org.postgresql.Driver").newInstance();
            con = DriverManager.getConnection(url, login, password);
            i = 1;
        } catch (Exception e) {
            System.out.println("ERROR DE CONEXION: " + e.getMessage());
        }
        return i;
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

    public static int guardarRegistro(String sql) {
        Conexion.Conectar();
        try {
            Statement st = Conexion.con.createStatement();
            int eu = st.executeUpdate(sql);

            st.close();
            Conexion.con.close();
            return eu;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public static ResultSet getRegistros(String sql) {
        Conexion.Conectar();
        try {
            Statement st = Conexion.con.createStatement();
            ResultSet eq = st.executeQuery(sql);
            return eq;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

}
