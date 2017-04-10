package rcrsystem.presentation.controller;

import Modelo.BD.Conexion;
import Modelo.Bulto;
import Modelo.Material;
import Modelo.MaterialT;
import Modelo.Proveedor;
import Modelo.RegCompra_U_Bulto;
import Modelo.RegistroCompra;
import Modelo.ReporteCompra;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import rcrsystem.presentation.model.Compras_Modelo;
import rcrsystem.presentation.view.VentIngresarRegCompra;

public class Compras_Controlador {

    public Compras_Controlador(VentIngresarRegCompra vista, Compras_Modelo modelo) {
        this.a_vista = vista;
        this.a_modelo = modelo;
        vista.setControlador(this);
        vista.setModelo(modelo);
        this.numero_bulto = 0;
        this.peso_total = 0;
        cargar();
    }

    public void cargar() {
        String secRegC = a_modelo.obtener_secuencia_registro_compra();
        a_vista.lbReg.setText("");
        a_vista.lbReg.setText(secRegC);
        a_vista.lbBulto.setText("B" + obtener_numero_bulto() + secRegC);
        a_vista.material_JComboBox.removeAllItems();
        List<Material> lista = new ArrayList<Material>();
        lista = a_modelo.obtener_materiales_2();
        for (int i = 0; i < lista.size(); i++) {
            a_vista.material_JComboBox.addItem("(" + lista.get(i).getCodigo() + ")    " + lista.get(i).getNombre());
        }
        List<Proveedor> lista2 = new ArrayList<Proveedor>();
        lista2 = a_modelo.obtener_proveedores();
        for (int i = 0; i < lista2.size(); i++) {
            a_vista.proveedor_JComboBox.addItem("(" + lista2.get(i).getCodigo() + ")    " + lista2.get(i).getNombre());
        }
    }

    public void limpia() {
        String secRegC = a_modelo.obtener_secuencia_registro_compra();
        a_vista.lbReg.setText("");
        a_vista.lbReg.setText(secRegC);
        a_vista.lbBulto.setText("B" + 0 + secRegC);
        a_vista.lbPesoTotal.setText("0.0");
        a_modelo.obtener_compra().clear();
        a_modelo.colocar_materiales_compra(a_modelo.obtener_compra(), 0);
        a_vista.txtChofer.setText("");
        a_vista.txtPesoBulto.setText("");
        a_vista.txtPlacaVehiculo.setText("");
    }

    public void agregar_bulto() {
        Bulto bulto = new Bulto();
        MaterialT mT = new MaterialT();
        Material m = new Material();
        bulto.setCodigo((a_vista.lbBulto.getText()));
        StringTokenizer st = new StringTokenizer(a_vista.material_JComboBox.getSelectedItem().toString(), "()");
        String codigo = "";
        String nombre = "";
        while (st.hasMoreTokens()) {

            codigo = st.nextToken();
            nombre = st.nextToken();

        }
        m.setCodigo(codigo);
        m.setNombre(nombre);
        mT.setTmaterial(m);
        if (a_vista.paca_JRadioButton.isSelected()) {
            mT.setCodigo("P" + codigo);
            bulto.setTipo(1);
        } else {
            mT.setCodigo("S" + codigo);
            bulto.setTipo(2);
        }
        bulto.setMaterial(mT);

        float peso = Float.parseFloat(a_vista.txtPesoBulto.getText());
        bulto.setPeso(peso);
        bulto.setEstado(1);
        a_modelo.obtener_compra().add(bulto);
        a_modelo.colocar_materiales_compra(a_modelo.obtener_compra(), 0);
        colocar_numero_bulto(numero_bulto + 1);
        colocar_peso_total(obtener_peso_total() + peso);
        a_vista.lbBulto.setText("B" + obtener_numero_bulto() + a_vista.lbReg.getText());
        a_vista.lbPesoTotal.setText(String.valueOf(obtener_peso_total()));
        a_vista.txtPesoBulto.setText("");
    }

    public void borrar_bulto(int row) {
        try {
            Bulto bulto = a_modelo.obtener_modelo_material().obtener_fila_a(row);
            a_modelo.eliminar_compra(bulto);
            float p = Float.parseFloat(a_vista.lbPesoTotal.getText()) - bulto.getPeso();
            a_vista.lbPesoTotal.setText(Float.toString(p));
        } catch (Exception ex) {
            a_modelo.colocar_mensaje("¡No se ha seleccionado ningún bulto!");
            a_modelo.colocar_materiales_compra(a_modelo.obtener_compra(), 0);
        }
        a_modelo.colocar_materiales_compra(a_modelo.obtener_compra(), 0);
    }

    public void borrar_todo() throws Exception {
        a_modelo.eliminar_todo();
        a_modelo.colocar_materiales_compra(a_modelo.obtener_compra(), 0);
    }

    public int obtener_numero_bulto() {
        return numero_bulto;
    }

    public void colocar_numero_bulto(int numBulto) {
        this.numero_bulto = numBulto;
    }

    public float obtener_peso_total() {
        return peso_total;
    }

    public void colocar_peso_total(float pesoTotal) {
        this.peso_total = pesoTotal;
    }

    public int confirmar_compra() {
        if (a_modelo.obtener_compra().isEmpty() == true) {
            return 0;
        } else if (Conexion.Conectar() == 0) {
            return 2;
        } else {
            RegistroCompra regCompra = new RegistroCompra();
            Proveedor p = new Proveedor();
            List<Bulto> compras = new ArrayList<>();
            regCompra.setNumCompra(Integer.parseInt(a_vista.lbReg.getText()));
            regCompra.setPesoTotal(obtener_peso_total());
            a_modelo.grabar_registro_compra(regCompra);
            compras = a_modelo.obtener_compra();
            for (int i = 0; i < compras.size(); i++) {
                a_modelo.grabar_bulto_comprado(compras.get(i));
                RegCompra_U_Bulto reg_U_B = new RegCompra_U_Bulto();
                reg_U_B.setBulto(compras.get(i));
                reg_U_B.setRegCompra(regCompra);
                a_modelo.grabar_registro_compra_u_bulto(reg_U_B);
                a_modelo.procedimiento_ingresar_total_material_comprado(compras.get(i).getMaterial().getTmaterial().getCodigo(), regCompra.getNumCompra(), compras.get(i).getPeso());
            }
            ReporteCompra reporte = new ReporteCompra();
            reporte.setChofer(a_vista.txtChofer.getText());
            reporte.setPlacaVehiculo(a_vista.txtPlacaVehiculo.getText());
            reporte.setRegCompra(regCompra);
            StringTokenizer st = new StringTokenizer(a_vista.proveedor_JComboBox.getSelectedItem().toString(), "()");
            String codigo = "";
            while (st.hasMoreTokens()) {

                codigo = st.nextToken();

                st.nextToken();
            }
            p.setCodigo(codigo);
            reporte.setProveedor(p);
            a_modelo.grabar_reporte_compra(reporte);
            generar_reporte(regCompra.getNumCompra());
            return 1;
        }
    }

     public void generar_reporte(int numeroCompa) {
        try {
            Map parametros = new HashMap();
            parametros.put("numCompra", numeroCompa); 
            JasperReport contenido = (JasperReport) JRLoader.loadObject(getClass().getResource("/rcrsystem/presentation/view/reporte/reporteCompra.jasper"));
         // JasperReport contenido = JasperCompileManager.compileReport("C:/RCRSystem/RCRSystem/RCRSystem/src/rcrsystem/presentation/view/reporte/reporteCompra
          JasperPrint imprimir = JasperFillManager.fillReport(contenido, parametros, Conexion.getConnection(null, null, null));
       
            JasperViewer v = new JasperViewer(imprimir, false);
            v.setTitle("Reporte de ingreso");
            v.setVisible(true);
        } catch (JRException ex) {
            JOptionPane.showMessageDialog(null, "Error al mostrar el Reporte");
            Logger.getLogger(Compras_Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private Compras_Modelo a_modelo = new Compras_Modelo();
    private VentIngresarRegCompra a_vista = new VentIngresarRegCompra();
    private int numero_bulto = 0;
    private float peso_total = 0;
} // Fin de la clase Compras_Controlador