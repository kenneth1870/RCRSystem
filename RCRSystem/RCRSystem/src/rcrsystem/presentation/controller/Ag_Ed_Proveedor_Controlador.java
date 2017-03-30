package rcrsystem.presentation.controller;

import Modelo.Proveedor;
import Modelo.dao.ProveedorDAO;
import java.util.List;
import rcrsystem.presentation.model.Proveedor_Modelo;
import rcrsystem.presentation.view.VentanaProveedor;

public class Ag_Ed_Proveedor_Controlador {

    public Ag_Ed_Proveedor_Controlador(VentanaProveedor vista, Proveedor_Modelo modelo, Admin_Proveedores_Controlador controlador) {
        this.a_vista = vista;
        this.a_modelo = modelo;
        this.a_controlador = controlador;
        vista.setControlador(this);
        vista.setModelo(modelo);
    }

    public void agregar() {
        Proveedor proveedor = new Proveedor();
        a_modelo.limpiar_errores();
        
        if (a_vista.jTextField_nombre.getText().length() == 0) {
            a_modelo.obtener_errores().put("nombre", "Debe ingresar el nombre del proveedor");
        } else {
            proveedor.setNombre(a_vista.jTextField_nombre.getText());
        }
        if (a_vista.jTextField_id.getText().length() == 0) {
            a_modelo.obtener_errores().put("id", "Debe ingresar la identificación del proveedor");
        } else {
            proveedor.setCodigo(a_vista.jTextField_id.getText());
        }
        if (a_vista.jTextField_telf.getText().length() == 0) {
            a_modelo.obtener_errores().put("Descripcion", "Debe ingresar una descripcion");
        } else {
            proveedor.setDescripcion(a_vista.jTextField_telf.getText());
        }

        if (a_modelo.obtener_errores().isEmpty()) {
            try {
                switch (a_modelo.obtener_modo()) {
                    case rcrsystem.Aplicacion.ae_modo_agregar:
                        ProveedorDAO.grabar(proveedor);
                        a_modelo.colocar_mensaje("¡Proveedor Agregado!");
                        a_modelo.colocar_proveedor_actual(new Proveedor());
                        a_modelo.colocar_mensaje("");
                        a_modelo.colocar_filtro(new Proveedor());
                        a_controlador.reiniciar_filtro();

                        List<Proveedor> proveedors = ProveedorDAO.getProveedores();
                        a_modelo.colocar_proveedores(proveedors, 0);
                        a_controlador.colocar_proveedoress(proveedors);
                        a_modelo.limpiar_errores();
                        a_vista.setVisible(false);
                        break;

                    case rcrsystem.Aplicacion.ae_modo_editar:

                        ProveedorDAO.actualizar(proveedor);
                        a_modelo.colocar_mensaje("¡Proveedor modificado!");
                        a_modelo.colocar_proveedor_actual(new Proveedor());
                        a_modelo.colocar_mensaje("");

                        a_modelo.colocar_filtro(new Proveedor());
                        a_controlador.reiniciar_filtro();
                        List<Proveedor> proveedors2 = ProveedorDAO.getProveedores();
                        a_modelo.colocar_proveedores(proveedors2, 0);
                        a_controlador.colocar_proveedoress(proveedors2);
                        a_modelo.limpiar_errores();
                        a_vista.setVisible(false);
                        break;
                }
            } catch (Exception e) {
                a_modelo.obtener_errores().put("id", "¡Proveedor ya existe!");
                a_modelo.colocar_mensaje("¡El proveedor ya está registrado!");
                a_modelo.colocar_proveedor_actual(proveedor);
            }
        } else {
            a_modelo.colocar_mensaje("¡Hay errores!");
            a_modelo.colocar_proveedor_actual(proveedor);
        }
    }
    private VentanaProveedor a_vista;
    private Proveedor_Modelo a_modelo;
    private Admin_Proveedores_Controlador a_controlador;
} // Fin de la clase Ag_Ed_Proveedor_Controlador