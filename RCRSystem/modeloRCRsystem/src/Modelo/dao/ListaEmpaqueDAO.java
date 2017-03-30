package Modelo.dao;

import Modelo.BD.Conexion;
import Modelo.Conductor;
import Modelo.ListaEmpaque;
import Modelo.ReporteCompra;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ListaEmpaqueDAO {

    public static String NOMBRE_TABLA = "ListaEmpaque";
    public static String SELECCIONAR_TODO = "select * from " + ListaEmpaqueDAO.NOMBRE_TABLA;

    public static int grabar(ListaEmpaque cnt) {
        String sql = "INSERT INTO ListaEmpaque (codigoListEm,fechaLE,medioTransporte,clienteLE,destino,pesoBruto,pesoNeto,conductor,placa,marca,chasis,furgon) Values ("
                + cnt.getCodigoL() + ","
                + "current_date,'"
                + cnt.getMedioTransporte() + "','"
                + cnt.getCliente().getCodigoC() + "','"
                + cnt.getDestino() + "',"
                + cnt.getPesoBruto() + ","
                + cnt.getPesoNeto() + ",'"
                + cnt.getConductor().getId() + "','"
                + cnt.getPlaca() + "','"
                + cnt.getMarca() + "','"
                + cnt.getChasis() + "','"
                + cnt.getFurgon()
                + "')";
        return Conexion.guardarRegistro(sql);
    }

    public static List<ListaEmpaque> getListaEmpaque() {
        ResultSet listaEmpaque = Conexion.getRegistros(ListaEmpaqueDAO.SELECCIONAR_TODO);
        List<ListaEmpaque> Lista = new ArrayList();

        try {
            while (listaEmpaque.next()) {
                ListaEmpaque cnt = new ListaEmpaque();
                cnt.setCodigoL(listaEmpaque.getInt(1));
                cnt.setFecha(listaEmpaque.getString(2));
                cnt.setMedioTransporte(listaEmpaque.getInt(3));
                cnt.setCliente(ClienteDAO.getCliente(listaEmpaque.getString(4)));
                cnt.setDestino(listaEmpaque.getString(5));
                cnt.setPesoBruto(listaEmpaque.getFloat(6));
                cnt.setPesoNeto(listaEmpaque.getFloat(7));
                cnt.setConductor(ConductorDAO.getConductor(listaEmpaque.getString(8)));
                cnt.setPlaca(listaEmpaque.getString(9));
                cnt.setMarca(listaEmpaque.getString(10));
                cnt.setFurgon(listaEmpaque.getString(11));
                Lista.add(cnt);
            }

            listaEmpaque.close();
            Conexion.con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return Lista;
    }

    public static ListaEmpaque getListaEmpaque(String co) {
        ResultSet listaEmpaque = Conexion.getRegistros(ListaEmpaqueDAO.SELECCIONAR_TODO + " where codigoListEm = '" + co + "'");
        ListaEmpaque cnt = null;
        try {
            if (listaEmpaque.next()) {
                cnt = new ListaEmpaque();
                cnt.setCodigoL(listaEmpaque.getInt(1));
                cnt.setFecha(listaEmpaque.getString(2));
                cnt.setMedioTransporte(listaEmpaque.getInt(3));
                cnt.setCliente(ClienteDAO.getCliente(listaEmpaque.getString(4)));
                cnt.setDestino(listaEmpaque.getString(5));
                cnt.setPesoBruto(listaEmpaque.getFloat(6));
                cnt.setPesoNeto(listaEmpaque.getFloat(7));
                cnt.setConductor(ConductorDAO.getConductor(listaEmpaque.getString(8)));
                cnt.setPlaca(listaEmpaque.getString(9));
                cnt.setMarca(listaEmpaque.getString(10));
                cnt.setFurgon(listaEmpaque.getString(11));
            }
            listaEmpaque.close();
            Conexion.con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return cnt;
    }
    
    
     public static int getMax() {
        ResultSet listaEmpaque = Conexion.getRegistros("select max(codigoListEm) from ListaEmpaque");
       int num = 0;
        try {
            if (listaEmpaque.next()) {
                num=listaEmpaque.getInt(1);
            }
            listaEmpaque.close();
            Conexion.con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return num;
    }


}

