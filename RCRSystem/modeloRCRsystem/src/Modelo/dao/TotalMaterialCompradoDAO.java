package Modelo.dao;

import Modelo.BD.Conexion;
import Modelo.TotalMaterialComprado;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TotalMaterialCompradoDAO {

    public static String NOMBRE_TABLA = "TotalMaterialComprado";
    public static String SELECCIONAR_TODO = "select * from " + TotalMaterialCompradoDAO.NOMBRE_TABLA;

    public static List<TotalMaterialComprado> obtenerITotalMaterialesComprados() {
        List<TotalMaterialComprado> resultado;
        resultado = new ArrayList<TotalMaterialComprado>();
        if (Conexion.Conectar() != 0) {//si no  hay conexion a la base
            ResultSet rs = null;
            try {
                rs = Conexion.getRegistros(TotalMaterialCompradoDAO.SELECCIONAR_TODO);
                while (rs.next()) {
                    TotalMaterialComprado cnt = new TotalMaterialComprado();
                    cnt.setMaterialComprado(MaterialDAO.getMaterial(rs.getString(1)));
                    cnt.setRegComp(RegistroCompraDAO.getRegistroCompra(rs.getString(2)));
                    cnt.setPesoTotalC(rs.getFloat(3));
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

    public int grabar(TotalMaterialComprado cnt) {
        String sql = "INSERT INTO TotalMaterialComprado (materialComprado,regComp,pesoTotalC) Values ("
                + cnt.getMaterialComprado().getCodigo() + ","
                + cnt.getRegComp().getNumCompra()+ ","
                + cnt.getPesoTotalC()
                + ")";
        return Conexion.guardarRegistro(sql);
    }

    public int actualizar(TotalMaterialComprado cnt) {
        String sql = "UPDATE TotalMaterialComprado SET pesoTotalC=" + cnt.getPesoTotalC()
                + " where materialComprado='" + cnt.getMaterialComprado().getCodigo()
                + "'and regComp='" + cnt.getRegComp().getNumCompra() + "'";
        return Conexion.guardarRegistro(sql);
    }

    public int borrar(TotalMaterialComprado cnt) {
        String sql = "DELETE  FROM TotalMaterialComprado where materialComprado=" + cnt.getMaterialComprado().getCodigo()
                + "and regComp=" + cnt.getRegComp().getNumCompra();
        return Conexion.guardarRegistro(sql);
    }

    public static TotalMaterialComprado getTotalMaterialComprado(String codigoMaterial, String regC) {
        ResultSet rs = Conexion.getRegistros(SELECCIONAR_TODO + " where materialComprado = '" + codigoMaterial + "' "
                + "and regComp= '" + regC + "'");
        TotalMaterialComprado cnt = null;
        try {
            if (rs.next()) {
                cnt = new TotalMaterialComprado();
                cnt.setMaterialComprado(MaterialDAO.getMaterial(rs.getString(1)));
                cnt.setRegComp(RegistroCompraDAO.getRegistroCompra(rs.getString(2)));
                cnt.setPesoTotalC(rs.getFloat(3));
            }
            rs.close();
            Conexion.con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return cnt;
    }

    public static String procedureIngresarTotalMaterialComprado(String tipoBultoComprado, int regCompra, float peso) {
        String res = "";
        ResultSet rs = Conexion.getRegistros("SELECT ingresarTotalMaterialComprado('" + tipoBultoComprado + "'," + regCompra + "," + peso + ")");
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
}
