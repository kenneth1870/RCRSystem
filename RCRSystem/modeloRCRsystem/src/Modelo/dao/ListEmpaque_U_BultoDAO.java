package Modelo.dao;

import Modelo.BD.Conexion;
import Modelo.ListEmpaque_U_Bulto;

public class ListEmpaque_U_BultoDAO {

    public static String NOMBRE_TABLA = "ListEmpaque_U_Bulto";
    public static String SELECCIONAR_TODO = "select * from " + ListEmpaque_U_BultoDAO.NOMBRE_TABLA;

    public static int grabar(ListEmpaque_U_Bulto cnt) {
        String sql = "INSERT INTO ListEmpaque_U_Bulto (listEmpaque,bultoVendido) Values ("
                + cnt.getListE().getCodigoL() + ",'"
                + cnt.getBultoVendido().getCodigo()
                + "')";
        return Conexion.guardarRegistro(sql);
    }

}
