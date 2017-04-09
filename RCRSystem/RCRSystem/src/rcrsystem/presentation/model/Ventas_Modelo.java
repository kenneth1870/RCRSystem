package rcrsystem.presentation.model;

import Modelo.BD.Conexion;
import Modelo.Bulto;
import Modelo.Cliente;
import Modelo.ListEmpaque_U_Bulto;
import Modelo.ListaEmpaque;
import Modelo.Material;
import Modelo.TotalMaterialVendido;
import Modelo.Venta;
import Modelo.dao.BultoDAO;
import Modelo.dao.ClienteDAO;
import Modelo.dao.ConductorDAO;
import Modelo.dao.InventarioDAO;
import Modelo.dao.ListEmpaque_U_BultoDAO;
import Modelo.dao.ListaEmpaqueDAO;
import Modelo.dao.TotalMaterialVendidoDAO;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import rcrsystem.presentation.controller.Compras_Controlador;

public class Ventas_Modelo extends java.util.Observable {

    public Ventas_Modelo() {
        List<Venta> rows = new ArrayList<Venta>();
        colocar_materiales_venta(rows, 0);
        this.a_venta = new ArrayList();
        a_actual = new ListaEmpaque();
        a_mensaje = "";
        a_errores = new Hashtable();
        a_bulto_actual = new Bulto();
    }

    public void eliminar_errores() {
        a_mensaje = "";
        a_errores = new Hashtable();
    }

    public Bulto obtener_bulto_actual() {
        return a_bulto_actual;
    }

    public void colocar_bulto_actual(Bulto bultocurrent) {
        this.a_bulto_actual = bultocurrent;
    }

    public Hashtable<String, String> obtener_errores() {
        return a_errores;
    }

    public void colocar_errores(Hashtable<String, String> errores) {
        this.a_errores = errores;
    }

    public ListaEmpaque obtener_actual() {
        return a_actual;
    }

    public void colocar_actual(ListaEmpaque current) {
        this.a_actual = current;
        setChanged();
        notifyObservers(ae_modelo_ventas);
    }

    public String obtener_mensaje() {
        return a_mensaje;
    }

    public void colocar_mensaje(String mensaje) {
        this.a_mensaje = mensaje;
    }

    public Bultos_Vent_Modelo_Tabla obtener_modelo_material() {
        return a_modelo_material;
    }

    public void colocar_modelo_material(Bultos_Vent_Modelo_Tabla materialModel) {
        this.a_modelo_material = materialModel;
        setChanged();
        notifyObservers(ae_modelo_ventas);
    }

    public List<Venta> obtener_venta() {
        return a_venta;
    }

    public void colocar_venta(List<Venta> venta) {
        this.a_venta = venta;
    }

    public void colocar_materiales_venta(List<Venta> rows, int n) {
        int[] cols = {a_modelo_material.ae_numero, a_modelo_material.ae_codigo, a_modelo_material.ae_tipo, a_modelo_material.ae_nombre_material, a_modelo_material.ae_peso};
        colocar_modelo_material(new Bultos_Vent_Modelo_Tabla(cols, rows));
    }

    public List<Material> obtener_materiales() {
        return InventarioDAO.obtenerInventarioVenta();
    }

    public List<Cliente> obtener_clientes() {
        return ClienteDAO.getClientes();
    }

    public List<Bulto> obtener_bultos(Material m) {
        return BultoDAO.getBultosVentaXMaterial(m.getCodigo());
    }

    public void eliminar_venta(Venta v) throws Exception {
        a_venta.remove(v);
        for (int i = 0; i < a_venta.size(); i++) {
            a_venta.get(i).setNumero(i + 1);
        }
        setChanged();
        notifyObservers(ae_modelo_ventas);
    }

    public float calcular_peso_bruto() {
        float total = 0;
        for (Venta v : a_venta) {
            total += v.getBulto().getPeso();
        }
        return total;
    }

    public void guardar_lista_empaque(ListaEmpaque listaEmpaque) throws Exception {
         List<TotalMaterialVendido> l_material_vendido = new ArrayList();
        if (!ConductorDAO.existeConductor(listaEmpaque.getConductor().getId())) {
            ConductorDAO.grabar(listaEmpaque.getConductor());
        }
        ListaEmpaqueDAO.grabar(listaEmpaque);
        for (int i =0;i< a_venta.size();i++) {
            ListEmpaque_U_Bulto lUb = new ListEmpaque_U_Bulto();
            lUb.setListE(listaEmpaque);
            lUb.setBultoVendido(a_venta.get(i).getBulto());
            ListEmpaque_U_BultoDAO.grabar(lUb);
            TotalMaterialVendidoDAO.procedureIngresarTotalMaterialVendido(a_venta.get(i).getBulto().getMaterial().getTmaterial().getCodigo(), listaEmpaque.getCodigoL(), a_venta.get(i).getBulto().getPeso());
            BultoDAO.actualizar(a_venta.get(i).getBulto());
            String aux =a_venta.get(i).getBulto().getMaterial().getCodigo().substring(1);
        }
        if(listaEmpaque.getMedioTransporte()==1){
        generarReporteMarit(listaEmpaque.getCodigoL());
        }
        else{
        generarReporteTerr(listaEmpaque.getCodigoL());
        }
    }
/*public int cacular_indices( List<TotalMaterialVendido> l_material_vendido, String c_material){
int indice = -1;
        for(int i=0;i<l_material_vendido.size();i++){
        if (l_material_vendido.get(i).getMaterialVendido().getCodigo().equals(c_material)){
        indice=i;
        }
        }
        return indice;
}
    public void cargar_totales(List<Venta>  a_venta, List<TotalMaterialVendido> l_material_vendido, ListaEmpaque listaEmpaque ){
        int indice=-1;
        TotalMaterialVendido material_vendido = new TotalMaterialVendido();
     for (Venta v : a_venta) {
         indice=this.cacular_indices(l_material_vendido,v.getBulto().getMaterial().getTmaterial().getCodigo());
        if(indice!=-1){
        l_material_vendido.get(indice).setCantBultosV(l_material_vendido.get(indice).getCantBultosV()+1);
       l_material_vendido.get(indice).setListEmp(this.a_actual);
        l_material_vendido.get(indice).setPesoTotalV(l_material_vendido.get(indice).getPesoTotalV()+v.getBulto().getPeso());
        }
        else{
            material_vendido= new TotalMaterialVendido();
        material_vendido.setCantBultosV(1);
        material_vendido.setListEmp(listaEmpaque);
        material_vendido.setMaterialVendido(v.getBulto().getMaterial().getTmaterial());
        material_vendido.setPesoTotalV(v.getBulto().getPeso());
        l_material_vendido.add(material_vendido);
        }
     }
     for (TotalMaterialVendido t : l_material_vendido ){
   //  System.out.println(t.getCantBultosV()+" "+t.getListEmp().getCodigoL()+" "+t.getMaterialVendido().getNombre()+" "+t.getPesoTotalV()+" ");
     }
    }
  */
      public void generarReporteTerr(int num) {
        try {
            Map parametros = new HashMap();
            parametros.put("codigoListEm", num);
            JasperReport contenido = (JasperReport) JRLoader.loadObject(getClass().getResource("/rcrsystem/presentation/view/reporte/reporteListEmpTerr.jasper"));
        
            JasperPrint imprimir = JasperFillManager.fillReport(contenido, parametros, Conexion.getConnection(null, null, null));
          
            JasperViewer v = new JasperViewer(imprimir, false);
            v.setTitle("Lista de Empaque");
            v.setVisible(true);
        } catch (JRException ex) {
            JOptionPane.showMessageDialog(null, "Error al mostrar la lista de empaque");
            Logger.getLogger(Compras_Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void generarReporteMarit(int num) {
        try {
            Map parametros = new HashMap();
            parametros.put("codigoListEm", num);
            JasperReport contenido = (JasperReport) JRLoader.loadObject(getClass().getResource("/rcrsystem/presentation/view/reporte/reporteListEmpMarit.jasper"));
            JasperPrint imprimir = JasperFillManager.fillReport(contenido, parametros, Conexion.getConnection(null, null, null));
           
            JasperViewer v = new JasperViewer(imprimir, false);
            v.setTitle("Lista de Empaque");
            v.setVisible(true);
        } catch (JRException ex) {
            JOptionPane.showMessageDialog(null, "Error al mostrar la lista de empaque");
            Logger.getLogger(Compras_Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void addObserver(java.util.Observer o) {
        super.addObserver(o);
        setChanged();
        notifyObservers(ae_modelo_ventas);
    }

    public static Integer ae_modelo_ventas = 0;
    private Bultos_Vent_Modelo_Tabla a_modelo_material;
    private List<Venta> a_venta;
    private String a_mensaje;
    private ListaEmpaque a_actual;
    private Hashtable<String, String> a_errores;
    private Bulto a_bulto_actual;
} // Fin de la clase Ventas_Modelo