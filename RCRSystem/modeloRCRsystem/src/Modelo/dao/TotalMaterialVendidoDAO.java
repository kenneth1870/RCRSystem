package Modelo.dao;

import Modelo.BD.Conexion;
import Modelo.TotalMaterialVendido;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TotalMaterialVendidoDAO {

    public static String NOMBRE_TABLA = "TotalMaterialVendido";
    public static String SELECCIONAR_TODO = "select * from " + TotalMaterialVendidoDAO.NOMBRE_TABLA;

    public static List<TotalMaterialVendido> obtenerITotalMaterialesVendidos(int listE) {

        List<TotalMaterialVendido> resultado;
        resultado = new ArrayList<TotalMaterialVendido>();
        if (Conexion.Conectar() != 0) {//si no  hay conexion a la base
            ResultSet rs = null;
            try {
                rs = rs = Conexion.getRegistros(SELECCIONAR_TODO + " where listEmpaque = " + listE);
                while (rs.next()) {
                    TotalMaterialVendido cnt = new TotalMaterialVendido();
                    cnt.setMaterialVendido(MaterialDAO.getMaterial(rs.getString(1)));
                    cnt.setListEmp(ListaEmpaqueDAO.getListaEmpaque(rs.getString(2)));
                    cnt.setPesoTotalV(rs.getFloat(4));
                    cnt.setCantBultosV(rs.getInt(3));
                    cnt.setPrecioUnid(rs.getFloat(5));
                    cnt.setImporte(rs.getFloat(6));
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

    public static List<TotalMaterialVendido> obtenerITotalMaterialesVendidos() {

        List<TotalMaterialVendido> resultado;
        resultado = new ArrayList<TotalMaterialVendido>();
        if (Conexion.Conectar() != 0) {//si no  hay conexion a la base
            ResultSet rs = null;
            try {
                rs = Conexion.getRegistros(TotalMaterialCompradoDAO.SELECCIONAR_TODO);
                while (rs.next()) {
                    TotalMaterialVendido cnt = new TotalMaterialVendido();
                    cnt.setMaterialVendido(MaterialDAO.getMaterial(rs.getString(1)));
                    cnt.setListEmp(ListaEmpaqueDAO.getListaEmpaque(rs.getString(2)));
                    cnt.setPesoTotalV(rs.getFloat(4));
                    cnt.setCantBultosV(rs.getInt(3));
                    cnt.setPrecioUnid(rs.getFloat(5));
                    cnt.setImporte(rs.getFloat(6));
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

    public int grabar(TotalMaterialVendido cnt) {
        String sql = "INSERT INTO TotalMaterialVendido (materialVendido,listEmpaque,cantBultosV,pesoTotalV,precioUnid,importe) Values ("
                + cnt.getMaterialVendido().getCodigo() + ","
                + cnt.getListEmp().getCodigoL() + ","
                + cnt.getCantBultosV() + ","
                + cnt.getPesoTotalV() + ","
                + cnt.getPrecioUnid() + ","
                + cnt.getImporte()
                + ")";
        return Conexion.guardarRegistro(sql);
    }

    public static TotalMaterialVendido getTotalMaterialVendido(String codigoMaterial, int listE) {
        ResultSet rs = Conexion.getRegistros(SELECCIONAR_TODO + " where materialVendido = '" + codigoMaterial + "' "
                + "and listEmpaque=" + listE);
        TotalMaterialVendido cnt = null;
        try {
            if (rs.next()) {
                cnt = new TotalMaterialVendido();
                cnt.setMaterialVendido(MaterialDAO.getMaterial(rs.getString(1)));
                cnt.setListEmp(ListaEmpaqueDAO.getListaEmpaque(rs.getString(2)));
                cnt.setPesoTotalV(rs.getFloat(4));
                cnt.setCantBultosV(rs.getInt(3));
                cnt.setPrecioUnid(rs.getFloat(5));
                cnt.setImporte(rs.getFloat(6));
            }
            rs.close();
            Conexion.con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return cnt;
    }

    public static String procedureIngresarTotalMaterialVendido(String tipoBultoVendido, int listEmpaque, float peso) {
        String res = "";
        ResultSet rs = Conexion.getRegistros("SELECT ingresarTotalMaterialVendido('" + tipoBultoVendido + "'," + listEmpaque + "," + peso + ");");
        try {

            if (rs.next()) {
                res = rs.getString(1);
            }
            rs.close();
            Conexion.con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return res;
    }

    public int actualizar(TotalMaterialVendido cnt) {
        String sql = "UPDATE TotalMaterialVendido SET precioUnid=" + cnt.getPrecioUnid()
                + " where listEmpaque=" + cnt.getListEmp().getCodigoL()
                + "and materialVendido='" + cnt.getMaterialVendido().getCodigo() + "'";
        return Conexion.guardarRegistro(sql);
    }
}
