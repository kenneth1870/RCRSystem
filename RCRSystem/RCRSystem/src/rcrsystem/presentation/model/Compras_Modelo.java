package rcrsystem.presentation.model;

import Modelo.Bulto;
import Modelo.Material;
import Modelo.Proveedor;
import Modelo.RegCompra_U_Bulto;
import Modelo.RegistroCompra;
import Modelo.ReporteCompra;
import Modelo.dao.BultoDAO;
import Modelo.dao.MaterialDAO;
import Modelo.dao.ProveedorDAO;
import Modelo.dao.RegCompra_U_BultoDAO;
import Modelo.dao.RegistroCompraDAO;
import Modelo.dao.ReporteCompraDAO;
import Modelo.dao.TotalMaterialCompradoDAO;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ComboBoxModel;

public class Compras_Modelo extends java.util.Observable {

    public Compras_Modelo() {
        List<Bulto> rows = new ArrayList<>();
        colocar_materiales_compra(rows, 0);
        a_compra = new ArrayList();
        a_mensaje = "";
    }

    public String obtener_secuencia_registro_compra() {
        return RegistroCompraDAO.getSecuenciaRegCompra();
    }

    public List<Material> obtener_materiales_2() {
        return MaterialDAO.getMateriales();
    }

    public List<Proveedor> obtener_proveedores() {
        return ProveedorDAO.getProveedores();
    }

    public int grabar_bulto_comprado(Bulto bc) {
        return BultoDAO.grabar(bc);
    }

    public int grabar_registro_compra(RegistroCompra reg) {
        return RegistroCompraDAO.grabar(reg);
    }

    public int grabar_registro_compra_u_bulto(RegCompra_U_Bulto reg) {
        return RegCompra_U_BultoDAO.grabar(reg);
    }

    public String procedimiento_ingresar_total_material_comprado(String tipoBultoComprado, int regCompra, float peso) {
        return TotalMaterialCompradoDAO.procedureIngresarTotalMaterialComprado(tipoBultoComprado, regCompra, peso);
    }

    public int grabar_reporte_compra(ReporteCompra reporte) {
        return ReporteCompraDAO.grabar(reporte);
    }

    public String obtener_mensaje() {
        return a_mensaje;
    }

    public void colocar_mensaje(String mensaje) {
        this.a_mensaje = mensaje;
    }

    public List<Bulto> obtener_compra() {
        return a_compra;
    }

    public void colocar_compra(List<Bulto> compra) {
        this.a_compra = compra;
    }

    public void eliminar_compra(Bulto BC) throws Exception {
        a_compra.remove(BC);
        setChanged();
        notifyObservers(ae_modelo_compras);
    }

    public void eliminar_todo() throws Exception {
        a_compra.clear();
        setChanged();
        notifyObservers(ae_modelo_compras);
    }

    public ComboBoxModel obtener_materiales() {
        return a_materiales;
    }

    public void colocar_materiales(ComboBoxModel materiales) {
        this.a_materiales = materiales;
        setChanged();
        notifyObservers(ae_modelo_compras);
    }

    public ComboBoxModel obtener_proveedores_2() {
        return a_proveedores;
    }

    public void colocar_proveedores(ComboBoxModel proveedores) {
        this.a_proveedores = proveedores;
        setChanged();
        notifyObservers(ae_modelo_compras);
    }

    public Material_Comp_Modelo_Tabla obtener_modelo_material() {
        return a_modelo_material;
    }

    public void colocar_modelo_material(Material_Comp_Modelo_Tabla materialModel) {
        this.a_modelo_material = materialModel;
        setChanged();
        notifyObservers(ae_modelo_compras);
    }

    public void colocar_materiales_compra(List<Bulto> rows, int n) {
        int[] cols = {Material_Comp_Modelo_Tabla.ae_bulto, Material_Comp_Modelo_Tabla.ae_material, Material_Comp_Modelo_Tabla.ae_peso, Material_Comp_Modelo_Tabla.ae_tipo};
        colocar_modelo_material(new Material_Comp_Modelo_Tabla(cols, rows));
    }

    @Override
    public void addObserver(java.util.Observer o) {
        super.addObserver(o);
        setChanged();
        notifyObservers(ae_modelo_compras);
    }
    
    private ComboBoxModel a_proveedores;
    private ComboBoxModel a_materiales;
    private Material_Comp_Modelo_Tabla a_modelo_material;
    private List<Bulto> a_compra;
    public static Integer ae_modelo_compras = 0;
    private String a_mensaje;
} // Fin de la clase Compras_Modelo