package rcrsystem.presentation.controller;

import Modelo.Inventario;
import Modelo.dao.InventarioDAO;
import java.util.ArrayList;
import java.util.List;
import rcrsystem.presentation.model.Modelo;
import rcrsystem.presentation.view.VentanaEdicionInventario;

public class Editar_Inventario_Controlador {
    
    public Editar_Inventario_Controlador(VentanaEdicionInventario vista, Modelo modelo) {
        this.a_vista = vista;
        this.a_modelo = modelo;
        a_modelo_logico = new InventarioDAO();
        vista.setController(this);
        vista.setModel(modelo);
    }

    public void modificar() {
        float cantidad = 0;
        boolean bandera = false;
        Inventario inventario = a_modelo.obtener_inventario_actual();
        a_modelo.limpiar_errores();
        if (a_vista.cantidad_JTextField.getText().length() != 0) {
            for (int i = 0; i < a_vista.cantidad_JTextField.getText().length(); i++) {
                if (!Character.isDigit(a_vista.cantidad_JTextField.getText().charAt(i)) && a_vista.cantidad_JTextField.getText().charAt(i) != '.') {
                    a_modelo.otener_errores().put("cantidad", "Digitó una letra");
                    cantidad = -1;
                    bandera = true;
                }
            }
            if (cantidad != -1) {
                cantidad = Float.parseFloat(a_vista.cantidad_JTextField.getText());
            }
            if (cantidad > 0 && bandera == false) {
                inventario.setCantidad(cantidad);
            } else if (bandera == false) {
                a_modelo.otener_errores().put("cantidad", "Cantidad de material incorrecta");
            }
        } else {
            a_modelo.otener_errores().put("cantidad", "Cantidad de material incorrecta");
        }
        if (a_modelo.otener_errores().isEmpty()) {
            try {
                a_modelo_logico.actualizar(inventario);
                a_modelo.colocar_mensaje("¡Material modificado correctamente!");
                a_modelo.colocar_inventario_actual(inventario);
                a_modelo.colocar_mensaje("");
                List<Inventario> rowsMod = a_modelo_logico.obtenerInventario();
                a_modelo.colocar_inventario(rowsMod, 1);
                a_modelo.colocar_inventario_actual(new Inventario());
                a_vista.setVisible(false);
                a_modelo.limpiar_errores();
                a_modelo.colocar_reposiciones(new ArrayList());
            } catch (Exception e) {
                System.out.println("Hubo un error editando el inventario");
            }
        } else {
            a_modelo.colocar_mensaje("¡Hay errores!");
            a_modelo.colocar_inventario_actual(inventario);
        }
    }
    
    public void cerrar() {
        this.a_vista.setVisible(false);
        a_modelo.colocar_inventario_actual(new Inventario());
        a_modelo.limpiar_errores();
    }
    
    private VentanaEdicionInventario a_vista;
    private Modelo a_modelo;
    private InventarioDAO a_modelo_logico;
} // Fin de la clase Editar_Inventario_Controlador