package rcrsystem.presentation.controller;

import Modelo.Bulto;
import Modelo.Cliente;
import Modelo.Inventario;
import Modelo.Conductor;
import Modelo.ListaEmpaque;
import Modelo.Venta;
import Modelo.dao.BultoDAO;
import Modelo.dao.InventarioDAO;
import Modelo.dao.ListaEmpaqueDAO;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.List;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;
import rcrsystem.presentation.model.Ventas_Modelo;
import rcrsystem.presentation.view.VentanaCarga;
import rcrsystem.presentation.view.VentanaVenta;

public class Ventas_Controlador {

    public Ventas_Controlador(Ventas_Modelo modelo, VentanaVenta vista) {
        this.a_vista = vista;
        this.a_modelo = modelo;
        vista.setControlador(this);
        vista.setModelo(modelo);
        this.cargar();
    }

    public void cargar() {
        a_vista.cliente_jComboBox.removeAllItems();
        List<Cliente> clientes = new ArrayList<Cliente>();
        int numFactura = ListaEmpaqueDAO.getMax() + 1;
        clientes = a_modelo.obtener_clientes();
        for (int i = 0; i < clientes.size(); i++) {
            a_vista.cliente_jComboBox.addItem("(" + clientes.get(i).getCodigoC() + ")    " + clientes.get(i).getNombreC());
        }
        if (rcrsystem.Aplicacion.ae_modelo_factura.getListas().isEmpty()) {
            a_vista.venta_jLabel.setText(String.valueOf(numFactura));
        } else {
            int numero = rcrsystem.Aplicacion.ae_modelo_factura.getListas().size() + numFactura;
            a_vista.venta_jLabel.setText(String.valueOf(numero));
        }
        a_vista.fecha_jLabel.setText(this.obtener_fecha());

    }

    public ListaEmpaque temporal_actual() {
        ListaEmpaque venta = new ListaEmpaque();
        Conductor conductor = new Conductor();

        if (a_vista.maritimo_jRadioButton.isSelected()) {
            venta.setMedioTransporte(1);
        } else {
            venta.setMedioTransporte(2);
        }

        venta.setCliente(this.obtener_cliente());

        venta.setDestino(a_vista.destino_jTextField.getText());

        if (a_vista.pesoNeto_jTextField.getText().length() != 0) {
            float peso = Float.parseFloat(a_vista.pesoNeto_jTextField.getText());
            venta.setPesoNeto(peso);
        } else {
            a_vista.pesoNeto_jTextField.setText("");
        }

        venta.setPesoBruto(Float.parseFloat(a_vista.pesoBruto_jLabel.getText()));

        if (a_vista.idtrans_jTextField.getText().length() != 0) {

            conductor.setId(a_vista.idtrans_jTextField.getText());
        }
        if (a_vista.nacionTrans_jTextField.getText().length() != 0) {

            conductor.setNacionalidad(a_vista.nacionTrans_jTextField.getText());
        }
        if (a_vista.nombreTrans_jTextField.getText().length() != 0) {
            conductor.setNombre(a_vista.nombreTrans_jTextField.getText());
        }
        venta.setConductor(conductor);

        if (a_vista.placa_jTextField.getText().length() != 0) {
            venta.setPlaca(a_vista.placa_jTextField.getText());
        }
        if (a_vista.año_jTextField.getText().length() != 0) {
            venta.setFecha(this.formato_fecha());
        }
        if (a_vista.marca_jTextField.getText().length() != 0) {
            venta.setMarca(a_vista.marca_jTextField.getText());
        }

        if (a_vista.furgon_jTextField.getText().length() != 0) {
            venta.setFurgon(a_vista.furgon_jTextField.getText());
        }

        if (a_vista.chasis_jTextField.getText().length() != 0) {
            venta.setChasis(a_vista.chasis_jTextField.getText());
        }
        return venta;
    }

    public int validar_lista_empaque() {
        ListaEmpaque venta = new ListaEmpaque();
        try {
            Conductor conductor = new Conductor();
            a_modelo.eliminar_errores();
            venta.setCodigoL(Integer.parseInt(a_vista.venta_jLabel.getText()));

            if (a_vista.maritimo_jRadioButton.isSelected()) {
                venta.setMedioTransporte(1);
            } else {
                venta.setMedioTransporte(2);
            }

            venta.setCliente(this.obtener_cliente());

            if (a_vista.destino_jTextField.getText().length() == 0) {
                a_modelo.obtener_errores().put("destino", "Debe llenar el campo");
            } else {
                venta.setDestino(a_vista.destino_jTextField.getText());
            }
            if (a_vista.pesoNeto_jTextField.getText().length() == 0) {
                a_modelo.obtener_errores().put("peso", "Debe llenar el campo");
            } else {
                float peso = Float.parseFloat(a_vista.pesoNeto_jTextField.getText());
                if (peso < 0.0) {
                    a_modelo.obtener_errores().put("peso", "Cantidad incorrecta");
                } else {
                    venta.setPesoNeto(Float.parseFloat(a_vista.pesoNeto_jTextField.getText()));
                }
            }
            venta.setPesoBruto(Float.parseFloat(a_vista.pesoBruto_jLabel.getText()));
            if (venta.getMedioTransporte() == 2) {
                if (a_vista.año_jTextField.getText().length() == 0) {
                    a_modelo.obtener_errores().put("año", "Debe llenar el campo");
                } else {
                    venta.setFecha(this.formato_fecha());
                }
                if (a_vista.idtrans_jTextField.getText().length() == 0) {
                    a_modelo.obtener_errores().put("idT", "Debe llenar el campo");
                } else {
                    conductor.setId(a_vista.idtrans_jTextField.getText());
                }
                if (a_vista.nacionTrans_jTextField.getText().length() == 0) {
                    a_modelo.obtener_errores().put("nacionalidadT", "Debe llenar este campo");
                } else {
                    conductor.setNacionalidad(a_vista.nacionTrans_jTextField.getText());
                }
                if (a_vista.año_jTextField.getText().length() == 0) {
                    a_modelo.obtener_errores().put("añoT", "Debe llenar este campo");
                } else if(a_vista.año_jTextField.getText().length() < 4){
                 a_modelo.obtener_errores().put("añoT2", "Error en el formato, debe ser aaaa. Ejemplo: 2017");
                }
                
                else {
                    String d = (String) a_vista.dia_jComboBox.getSelectedItem();
                    String m = numero_mes();
                    String a = a_vista.año_jTextField.getText();
                    conductor.setFechaNacimiento(d + "-" + m + "-" + a);
                }
                if (a_vista.nombreTrans_jTextField.getText().length() == 0) {
                    a_modelo.obtener_errores().put("nombreT", "Debe llenar este campo");
                } else {
                    conductor.setNombre(a_vista.nombreTrans_jTextField.getText());
                }

                venta.setConductor(conductor);
            }
            if (a_vista.placa_jTextField.getText().length() == 0) {
                a_modelo.obtener_errores().put("placa", "Debe llenar el campo");
            } else {
                venta.setPlaca(a_vista.placa_jTextField.getText());
            }
            if (a_vista.marca_jTextField.getText().length() == 0) {
                a_modelo.obtener_errores().put("marca", "Debe llenar este campo");
            } else {
                venta.setMarca(a_vista.marca_jTextField.getText());
            }
            if (a_vista.furgon_jTextField.getText().length() == 0) {
                a_modelo.obtener_errores().put("furgon", "Debe llenar este campo");
            } else {
                venta.setFurgon(a_vista.furgon_jTextField.getText());
            }

            if (a_vista.chasis_jTextField.getText().length() == 0) {
                a_modelo.obtener_errores().put("chasis", "Debe llenar este campo");
            } else {
                venta.setChasis(a_vista.chasis_jTextField.getText());
            }
            
            if (!a_modelo.obtener_errores().isEmpty() || a_modelo.obtener_venta().isEmpty()) {
                a_modelo.colocar_actual(venta);
               if(!a_modelo.obtener_errores().isEmpty())
                return 1;
               else
                   return 2;
            } else {
                a_modelo.colocar_actual(venta);
                return 0;
            }
        } catch (Exception e) {
            System.out.print(e);
        }
        
        return 0;
        }
    

    public void reportar_errores() {
        a_modelo.colocar_mensaje("¡Hay Errores!");
        a_modelo.colocar_actual(a_modelo.obtener_actual());
    }

    public void reportar_error_venta_nula() {
        a_modelo.colocar_mensaje("¡No ha ingresado bultos en la tabla!");
        a_modelo.colocar_actual(a_modelo.obtener_actual());
    }
    
    public void generar_lista_empaque() {
        ListaEmpaque venta = new ListaEmpaque();
        try {

            Conductor conductor = new Conductor();
            a_modelo.eliminar_errores();
            venta.setCodigoL(Integer.parseInt(a_vista.venta_jLabel.getText()));
            if (a_vista.año_jTextField.getText().length() == 0) {
                a_modelo.obtener_errores().put("año", "Debe llenar el campo");
            } else {
                venta.setFecha(this.formato_fecha());
            }
            if (a_vista.maritimo_jRadioButton.isSelected()) {
                venta.setMedioTransporte(1);
            } else {
                venta.setMedioTransporte(2);
            }

            venta.setCliente(this.obtener_cliente());

            if (a_vista.destino_jTextField.getText().length() == 0) {
                a_modelo.obtener_errores().put("destino", "Debe llenar el campo");
            } else {
                venta.setDestino(a_vista.destino_jTextField.getText());
            }
            if (a_vista.pesoNeto_jTextField.getText().length() == 0) {
                a_modelo.obtener_errores().put("peso", "Debe llenar el campo");
            } else {
                float peso = Float.parseFloat(a_vista.pesoNeto_jTextField.getText());
                if (peso < 0.0) {
                    a_modelo.obtener_errores().put("peso", "Cantidad incorrecta");
                } else {
                    venta.setPesoNeto(Float.parseFloat(a_vista.pesoNeto_jTextField.getText()));
                }
            }
            venta.setPesoBruto(Float.parseFloat(a_vista.pesoBruto_jLabel.getText()));

            if (a_vista.idtrans_jTextField.getText().length() == 0) {
                a_modelo.obtener_errores().put("idT", "Debe llenar el campo");
            } else {
                int id = Integer.parseInt(a_vista.idtrans_jTextField.getText());
                if (id < 0 || a_vista.idtrans_jTextField.getText().length() < 9 || a_vista.idtrans_jTextField.getText().length() > 9) {
                    a_modelo.obtener_errores().put("idT", "ID incorrecta");
                } else {
                    conductor.setId(a_vista.idtrans_jTextField.getText());
                }
            }
            if (a_vista.nacionTrans_jTextField.getText().length() == 0) {
                a_modelo.obtener_errores().put("nacionalidadT", "Debe llenar este campo");
            } else {
                conductor.setNacionalidad(a_vista.nacionTrans_jTextField.getText());
            }
            if (a_vista.año_jTextField.getText().length() == 0) {
                a_modelo.obtener_errores().put("añoT", "Debe llenar este campo");
            } else {
                String d = (String) a_vista.dia_jComboBox.getSelectedItem();
                String m = numero_mes();
                String a = a_vista.año_jTextField.getText();
                conductor.setFechaNacimiento(d + "-" + m + "-" + a);
            }
            if (a_vista.nombreTrans_jTextField.getText().length() == 0) {
                a_modelo.obtener_errores().put("nombreT", "Debe llenar este campo");
            } else {
                conductor.setNombre(a_vista.nombreTrans_jTextField.getText());
            }

            venta.setConductor(conductor);
            if (a_vista.placa_jTextField.getText().length() == 0) {
                a_modelo.obtener_errores().put("placa", "Debe llenar el campo");
            } else {
                int placa = Integer.parseInt(a_vista.placa_jTextField.getText());
                if (placa < 0) {
                    a_modelo.obtener_errores().put("idT", "Placa incorrecta");
                } else {
                    venta.setPlaca(a_vista.placa_jTextField.getText());
                }
            }
            if (a_vista.marca_jTextField.getText().length() == 0) {
                a_modelo.obtener_errores().put("marca", "Debe llenar este campo");
            } else {
                venta.setMarca(a_vista.marca_jTextField.getText());
            }
            if (a_vista.furgon_jTextField.getText().length() == 0) {
                a_modelo.obtener_errores().put("furgon", "Debe llenar este campo");
            } else {
                venta.setFurgon(a_vista.furgon_jTextField.getText());
            }

            if (a_vista.chasis_jTextField.getText().length() == 0) {
                a_modelo.obtener_errores().put("chasis", "Debe llenar este campo");
            } else {
                venta.setChasis(a_vista.chasis_jTextField.getText());
            }
            if (!a_modelo.obtener_errores().isEmpty() || a_modelo.obtener_venta().isEmpty()) {
                a_modelo.colocar_mensaje("¡Hay Errores!");
                a_modelo.colocar_actual(venta);
            } else {
                int opcion = JOptionPane.showConfirmDialog(null, "¿Seguro que desea ingresar la lista de empaque?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (opcion == JOptionPane.YES_OPTION) {
                    a_modelo.guardar_lista_empaque(venta);
                    Toolkit.getDefaultToolkit().beep();
                    JOptionPane.showMessageDialog(null, "¡Lista de empaque registrada correctamente!", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                    this.limpiar_datos();
                    this.limpiar_bulto();
                    opcion = JOptionPane.showConfirmDialog(null, "¿Desea seguir registrando listas de empaque?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (opcion == JOptionPane.YES_OPTION) {
                        this.limpiar_datos();
                        this.limpiar_bulto();
                    } else {
                        a_vista.setVisible(false);
                    }
                }
            }
        } catch (Exception e) {
            System.out.print(e);
        }
    }

    public void guardar_lista_empaque() {
        try{
        a_modelo.guardar_lista_empaque(a_modelo.obtener_actual());
        }catch(Exception e){
        
        }
    }

    public void procesar_lista_empaque(VentanaCarga ventana) {
        this.a_vista.setVisible(false);
        this.guardar_lista_empaque();
        ventana.setVisible(false);
        Toolkit.getDefaultToolkit().beep();
        this.a_vista.setVisible(true);
        JOptionPane.showMessageDialog(null, "¡Lista de empaque registrada correctamente!", "Aviso", JOptionPane.INFORMATION_MESSAGE);
        this.limpiar_datos();
        this.limpiar_bulto();
        int opcion = JOptionPane.showConfirmDialog(null, "¿Desea seguir registrando listas de empaque?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (opcion == JOptionPane.YES_OPTION) {
            this.limpiar_datos();
            this.limpiar_bulto();
            int numFactura = Integer.parseInt(a_vista.venta_jLabel.getText());
            a_vista.venta_jLabel.setText(String.valueOf(numFactura));
          }
        else {
            eliminar_errores();
            limpiar_bulto();
            limpiar_datos();
            a_vista.setVisible(false);
            Progreso_Menu_Prin_Controlador v = new Progreso_Menu_Prin_Controlador(new VentanaCarga());
            v.execute();
        }
    }

    public void limpiar_datos() {
        a_modelo.colocar_venta(new ArrayList());
        List<Venta> rows = new ArrayList<Venta>();
        a_modelo.colocar_materiales_venta(rows, 0);
        a_modelo.colocar_actual(new ListaEmpaque());
        a_vista.numero_jlabel.setText("1");
        a_vista.venta_jLabel.setText(String.valueOf(ListaEmpaqueDAO.getMax() + 1));
    }

    public void eliminar_errores() {
        a_modelo.colocar_errores(new Hashtable());
    }

    public String obtener_fecha() {
        Calendar c1 = new GregorianCalendar();
        String dia = "";
        String mes = "";
        String año = "";

        if (c1.get(Calendar.DATE) < 10) {
            dia = "0" + String.valueOf(c1.get(Calendar.DATE));
        } else {
            dia = String.valueOf(c1.get(Calendar.DATE));
        }
        if ((c1.get(Calendar.MONTH) + 1) < 10) {
            mes = "0" + String.valueOf(c1.get(Calendar.MONTH) + 1);
        } else {
            mes = String.valueOf(c1.get(Calendar.MONTH) + 1);
        }
        año = String.valueOf(c1.get(Calendar.YEAR));
        return dia + "-" + mes + "-" + año;
    }

    public String formato_fecha() {
        return String.valueOf(a_vista.dia_jComboBox.getSelectedItem())
                + "-" + this.numero_mes() + "-" + a_vista.año_jTextField.getText();
    }

    public void cargar_bulto(VentanaCarga carga) {
        Bulto bulto = new Bulto();
        String material = "";
        Inventario inv = new Inventario();
        bulto = BultoDAO.getBulto(a_vista.codigo_bultojTextField.getText());
        if (bulto != null) {
            material = bulto.getMaterial().getCodigo();
            String aux = material.substring(1);
            inv = InventarioDAO.obtenerInventario(aux);
            if (inv.getCantidad()>= 20000 && bulto.getEstado() == 1) {
                this.a_vista.material_bultojTextField.setText(bulto.getMaterial().getTmaterial().getNombre());
                if (bulto.getTipo() == 1) {
                    a_vista.tipo_boton.setText("Paca");
                } else {
                    a_vista.tipo_boton.setText("Saca");
                }
                a_modelo.colocar_bulto_actual(bulto);
                a_vista.agregarbtn.setEnabled(true);
                a_vista.jButton1.setEnabled(true);
            } else {
               
                a_vista.jButton1.setEnabled(true);
                Toolkit.getDefaultToolkit().beep();
                 carga.setVisible(false);
                if(inv.getCantidad()<2000){
                JOptionPane.showMessageDialog(null, "¡Material menor de 20 tons!", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                }else{
                JOptionPane.showMessageDialog(null, "¡El bulto no está disponible para la venta!", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                }
                this.limpiar_bulto();
            }
        } else {
            a_vista.jButton1.setEnabled(true);
             carga.setVisible(false);
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, "¡El bulto no existe!", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            this.limpiar_bulto();
        }
    }

    boolean existe_bulto(String codigo) {
        for (Venta v : a_modelo.obtener_venta()) {
            if (v.getBulto().getCodigo().equals(codigo)) {
                return true;
            }
        }
        return false;
    }

    public void limpiar_bulto() {
        a_vista.material_bultojTextField.setText("");
        a_vista.tipo_boton.setText("");
        a_modelo.colocar_bulto_actual(new Bulto());
        a_vista.agregarbtn.setEnabled(false);
        a_vista.codigo_bultojTextField.setText("");
    }

    public void agregar_bulto() {
        Venta v = new Venta();
        Bulto bulto = a_modelo.obtener_bulto_actual();
        ListaEmpaque le = this.temporal_actual();
        if (bulto != null) {
            if (!this.existe_bulto(bulto.getCodigo())) {
                v.setBulto(bulto);
                v.setNumero(Integer.parseInt(a_vista.numero_jlabel.getText()));
                a_modelo.obtener_venta().add(v);
                this.limpiar_bulto();
                a_modelo.colocar_actual(le);
                a_modelo.colocar_materiales_venta(a_modelo.obtener_venta(), 0);
                a_vista.numero_jlabel.setText(String.valueOf(v.getNumero() + 1));
            } else {
                Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(null, "¡Bulto duplicado!", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                this.limpiar_bulto();
            }
        } else {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, "¡No seleccionó ningún bulto!", "Aviso", JOptionPane.INFORMATION_MESSAGE);

        }
    }

    public void borrar_bulto(int row) {
        try {
            int opcion = JOptionPane.showConfirmDialog(null, "¿Seguro que desea eliminar el bulto seleccionado?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (row > -1) {
                if (opcion == JOptionPane.YES_OPTION) {
                    Venta v = a_modelo.obtener_modelo_material().obtener_fila_a(row);
                    a_modelo.eliminar_venta(v);
                    if (a_modelo.obtener_venta().size() > 0) {
                        a_vista.numero_jlabel.setText(String.valueOf(a_modelo.obtener_venta().get(a_modelo.obtener_venta().size() - 1).getNumero() + 1));
                    } else {
                        a_vista.numero_jlabel.setText("1");
                    }
                }
                a_vista.eliminar_jButton.setEnabled(true);
            } else {
                Venta v = a_modelo.obtener_modelo_material().obtener_fila_a(row);
            }
        } catch (Exception ex) {
            a_modelo.colocar_mensaje("¡No se ha seleccionado ningún bulto!");
            a_vista.eliminar_jButton.setEnabled(true);
            a_modelo.colocar_materiales_venta(a_modelo.obtener_venta(), 0);
        }
        a_modelo.colocar_materiales_venta(a_modelo.obtener_venta(), 0);
    }

    public Cliente obtener_cliente() {
        Cliente cliente = new Cliente();
        StringTokenizer st = new StringTokenizer(a_vista.cliente_jComboBox.getSelectedItem().toString(), "()");
        String id = "";
        String nombre = "";
        while (st.hasMoreTokens()) {
            id = st.nextToken();
            nombre = st.nextToken();
        }
        cliente.setCodigoC(id);
        cliente.setNombreC(nombre);
        return cliente;
    }

    public float obtener_peso_bruto() {
        return a_modelo.calcular_peso_bruto();
    }

    public void definir_cliente(Cliente c) {
        a_vista.cliente_jComboBox.setSelectedItem("(" + c.getCodigoC() + ")    " + c.getNombreC());
    }

    String numero_mes() {
        String m = String.valueOf(a_vista.mes_jComboBox.getSelectedItem());
        switch (m) {
            case "Enero":
                return "01";
            case "Febrero":
                return "02";
            case "Marzo":
                return "03";
            case "Abril":
                return "04";
            case "Mayo":
                return "05";
            case "Junio":
                return "06";
            case "Julio":
                return "07";
            case "Agosto":
                return "08";
            case "Setiembre":
                return "09";
            case "Octubre":
                return "10";
            case "Noviembre":
                return "11";
            case "Diciembre":
                return "12";
        }
        return "";
    }

    public String mes_numero(String mes) {
        switch (mes) {
            case "01":
                return "Enero";
            case "02":
                return "Febrero";
            case "03":
                return "Marzo";
            case "04":
                return "Abril";
            case "05":
                return "Mayo";
            case "06":
                return "Junio";
            case "07":
                return "Julio";
            case "08":
                return "Agosto";
            case "09":
                return "Setiembre";
            case "10":
                return "Octubre";
            case "11":
                return "Noviembre";
            case "12":
                return "Diciembre";
        }
        return "";
    }

    private Ventas_Modelo a_modelo = new Ventas_Modelo();
    private VentanaVenta a_vista = new VentanaVenta();
} // Fin de la clase Ventas_Controlador