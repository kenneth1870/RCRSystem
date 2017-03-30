package rcrsystem.presentation.controller;

import Modelo.TotalMaterialVendido;
import Modelo.dao.TotalMaterialVendidoDAO;
import java.util.List;
import rcrsystem.presentation.model.Facturar_Modelo;
import rcrsystem.presentation.view.VentanaPrecioUnidFacturacion;

public class Editar_Precio_Unidad_Factura_Controlador {

    public Editar_Precio_Unidad_Factura_Controlador(VentanaPrecioUnidFacturacion vista, Facturar_Modelo modelo) {
        this.a_vista = vista;
        this.a_modelo = modelo;
        a_modelo_logico = new TotalMaterialVendidoDAO();
        vista.setController(this);
        vista.setModel(modelo);
    }

    public void modificar() {
        float precioUnid = 0;
        boolean bandera = false;
        TotalMaterialVendido totalMV = a_modelo.obtener_total_material_vendido_lista_empaque_actual();
        a_modelo.limpiar_errores();
        if (a_vista.precio_JTextField.getText().length() != 0) {
            for (int i = 0; i < a_vista.precio_JTextField.getText().length(); i++) {
                if (!Character.isDigit(a_vista.precio_JTextField.getText().charAt(i)) && a_vista.precio_JTextField.getText().charAt(i) != '.') {
                    a_modelo.obtener_errores().put("Precio", "Digitó una letra");
                    precioUnid = -1;
                    bandera = true;
                }
            }
            if (precioUnid != -1) {
                precioUnid = Float.parseFloat(a_vista.precio_JTextField.getText());
            }
            if (precioUnid > 0 && bandera == false) {
                totalMV.setPrecioUnid(precioUnid);
                totalMV.setImporte(precioUnid * totalMV.getPesoTotalV());
            } else if (bandera == false) {
                a_modelo.obtener_errores().put("Precio", "Precio de material incorrecta");
            }
        } else {
            a_modelo.obtener_errores().put("Precio", "Precio de material incorrecta");
        }
        if (a_modelo.obtener_errores().isEmpty()) {
            try {
                a_modelo_logico.actualizar(totalMV);
                a_modelo.colocar_mensaje("¡Material modificado correctamente!");
                a_modelo.colocar_total_material_vendido_lista_empaque_actual(totalMV);
                a_modelo.colocar_mensaje("");
                List<TotalMaterialVendido> rowsMod = a_modelo_logico.obtenerITotalMaterialesVendidos();
                a_modelo.colocar_total_material_vendido_lista_empaque(rowsMod, 1);
                a_modelo.colocar_total_material_vendido_lista_empaque_actual(new TotalMaterialVendido());
                a_vista.setVisible(false);
                a_modelo.limpiar_errores();
                //modelo.setReposiciones(new ArrayList());
            } catch (Exception e) {
                System.out.println("Hubo un error editando el material");
            }
        } else {
            a_modelo.colocar_mensaje("¡Hay errores!");
            a_modelo.colocar_total_material_vendido_lista_empaque_actual(totalMV);
        }
    }

    public void cerrar() {
        this.a_vista.setVisible(false);
        a_modelo.colocar_total_material_vendido_lista_empaque_actual(new TotalMaterialVendido());
        a_modelo.limpiar_errores();
    }
    
    private VentanaPrecioUnidFacturacion a_vista;
    private Facturar_Modelo a_modelo;
    private TotalMaterialVendidoDAO a_modelo_logico;
}
