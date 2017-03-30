package Modelo.dao;

import Modelo.BD.Conexion;
import Modelo.ReporteCompra;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReporteCompraDAO {

    public static String NOMBRE_TABLA = "ReporteCompra";
    public static String SELECCIONAR_TODO = "select * from " + ReporteCompraDAO.NOMBRE_TABLA;

    public static int grabar(ReporteCompra cnt) {
        String sql = "INSERT INTO ReporteCompra (regCompra,fecha,proveedor,chofer,placaVehiculo) Values ("
                + cnt.getRegCompra().getNumCompra() + ","
                + "current_date,'"
                + cnt.getProveedor().getCodigo() + "','"
                + cnt.getChofer() + "','"
                + cnt.getPlacaVehiculo()
                + "')";
        return Conexion.guardarRegistro(sql);
    }

    public int actualizar(ReporteCompra cnt) {
        String sql = "UPDATE ReporteCompra SET regCompra='" + cnt.getRegCompra().getNumCompra()
                + "',fecha=" + cnt.getFecha()
                + "',proveedor=" + cnt.getProveedor().getCodigo()
                + "',chofer=" + cnt.getChofer()
                + "',placaVehiculo=" + cnt.getPlacaVehiculo()
                + " where numero=" + cnt.getNumero();
        return Conexion.guardarRegistro(sql);
    }

    public int borrar(ReporteCompra cnt) {
        String sql = "DELETE  FROM ReporteCompra WHERE numero=" + cnt.getNumero();
        return Conexion.guardarRegistro(sql);
    }

    public static List<ReporteCompra> getReportesCompras() {
        ResultSet reporteCompra = Conexion.getRegistros(ReporteCompraDAO.SELECCIONAR_TODO);
        List<ReporteCompra> ListaBultos = new ArrayList();

        try {
            while (reporteCompra.next()) {
                ReporteCompra cnt = new ReporteCompra();
                cnt.setNumero(reporteCompra.getInt(1));
                cnt.setRegCompra(RegistroCompraDAO.getRegistroCompra(reporteCompra.getString(2)));
                cnt.setFecha(reporteCompra.getDate(3));
                cnt.setProveedor(ProveedorDAO.getProveedor(reporteCompra.getString(4)));
                cnt.setChofer(reporteCompra.getString(5));
                cnt.setPlacaVehiculo(reporteCompra.getString(6));
                ListaBultos.add(cnt);
            }

            reporteCompra.close();
            Conexion.con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return ListaBultos;
    }

    public static ReporteCompra getReporteCompra(String numero) {
        ResultSet reporteC = Conexion.getRegistros(ReporteCompraDAO.SELECCIONAR_TODO + " where numero = '" + numero + "'");
        ReporteCompra cnt = null;
        try {
            if (reporteC.next()) {
                cnt = new ReporteCompra();
                cnt.setNumero(reporteC.getInt(1));
                cnt.setRegCompra(RegistroCompraDAO.getRegistroCompra(reporteC.getString(2)));
                cnt.setFecha(reporteC.getDate(3));
                cnt.setProveedor(ProveedorDAO.getProveedor(reporteC.getString(4)));
                cnt.setChofer(reporteC.getString(5));
                cnt.setPlacaVehiculo(reporteC.getString(6));
            }
            reporteC.close();
            Conexion.con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return cnt;
    }

}
