package rcrsystem.presentation.controller;

import Modelo.Cliente;
import Modelo.dao.ClienteDAO;
import java.util.List;
import rcrsystem.presentation.model.Cliente_Modelo;
import rcrsystem.presentation.view.VentanaCliente;

public class Ag_Ed_Cliente_Controlador {

    public Ag_Ed_Cliente_Controlador(VentanaCliente vista, Cliente_Modelo modelo, Admin_Cliente_Controlador controlador) {
        this.a_vista = vista;
        this.a_modelo = modelo;
        this.a_controlador = controlador;
        vista.setControlador(this);
        vista.setModelo(modelo);
    }

    public void agregar() {
        Cliente cliente = new Cliente();
        a_modelo.limpiar_errores();

        if (a_vista.jTextField_nombre.getText().length() == 0) {
            a_modelo.obtener_errores().put("nombre", "Debe ingresar el nombre del cliente");
        } else {
            cliente.setNombreC(a_vista.jTextField_nombre.getText());
        }
        if (a_vista.jTextField_id.getText().length() == 0) {
            a_modelo.obtener_errores().put("id", "Debe ingresar la identificación del cliente");
        } else {
            cliente.setCodigoC(a_vista.jTextField_id.getText());
        }
        
        if (a_modelo.obtener_errores().isEmpty()) {
            try {
                switch (a_modelo.obtener_modo()) {
                    case rcrsystem.Aplicacion.ae_modo_agregar:
                        ClienteDAO.grabar(cliente);
                        a_modelo.colocar_mensaje("¡Cliente Agregado!");
                        a_modelo.colocar_cliente_actual(new Cliente());
                        a_modelo.colocar_mensaje("");
                        a_modelo.colocar_filtro(new Cliente());
                        a_controlador.reiniciar_filtro();

                        List<Cliente> clientes = ClienteDAO.getClientes();
                        a_modelo.colocar_clientes(clientes, 0);
                        a_controlador.colocar_clientes(clientes);
                        a_modelo.limpiar_errores();
                        a_vista.setVisible(false);
                        break;

                    case rcrsystem.Aplicacion.ae_modo_editar:

                        ClienteDAO.actualizar(cliente);
                        a_modelo.colocar_mensaje("¡Cliente modificado!");
                        a_modelo.colocar_cliente_actual(new Cliente());
                        a_modelo.colocar_mensaje("");

                        a_modelo.colocar_filtro(new Cliente());
                        a_controlador.reiniciar_filtro();
                        List<Cliente> clientes2 = ClienteDAO.getClientes();
                        a_modelo.colocar_clientes(clientes2, 0);
                        a_controlador.colocar_clientes(clientes2);
                        a_modelo.limpiar_errores();
                        a_vista.setVisible(false);
                        break;
                }
            } catch (Exception e) {
                a_modelo.obtener_errores().put("id", "¡Cliente ya existe!");
                a_modelo.colocar_mensaje("¡El cliente ya está registrado!");
                a_modelo.colocar_cliente_actual(cliente);
            }
        } else {
            a_modelo.colocar_mensaje("¡Hay errores!");
            a_modelo.colocar_cliente_actual(cliente);
        }
    }
    private VentanaCliente a_vista;
    private Cliente_Modelo a_modelo;
    private Admin_Cliente_Controlador a_controlador;
} // Fin de la clase Ag_Ed_Cliente_Controlador
