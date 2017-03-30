package rcrsystem.presentation.controller;

import Modelo.AdministrarMaterial;
import Modelo.dao.AdministrarMaterialDAO;
import java.util.List;
import rcrsystem.presentation.model.Material_Modelo;
import rcrsystem.presentation.view.VentanaAdminMaterial;

public class Admin_Material_Controlador {

    public Admin_Material_Controlador(VentanaAdminMaterial vista, Material_Modelo modelo, Materiales_Controlador controlador) {
        this.a_vista = vista;
        this.a_modelo = modelo;
        this.a_controlador = controlador;
        vista.setControlador(this);
        vista.setModelo(modelo);
    }
    
  public void agregar(){
     AdministrarMaterial material = new AdministrarMaterial();
        a_modelo.limpiar_errores();
    
        material.getMaterial().setCodigo(a_vista.jTextField_codigo.getText());
    if(a_vista.jTextField_codigo.getText().length()==0){
        a_modelo.obtener_errores().put("codigo","Debe digitar el código del material");
    }
    
    material.getMaterial().setNombre(a_vista.jTextField_descripcion.getText());
     if(a_vista.jTextField_descripcion.getText().length()==0){
        a_modelo.obtener_errores().put("descripcion","Debe digitar la descripción del material");
    }
      if(a_vista.jTextField_paca.getText().length()==0){
        a_modelo.obtener_errores().put("paca","Debe digitar el precio de la paca");
    }else{
         float paca = Float.parseFloat(a_vista.jTextField_paca.getText());
         material.setPrecioPaca(paca);
      }
     if(a_vista.jTextField_saca.getText().length()==0){
        a_modelo.obtener_errores().put("saca","Debe digitar el precio de la saca");
    }else{
         float saca = Float.parseFloat(a_vista.jTextField_saca.getText());
         material.setPrecioSaca(saca);
      }
     if (a_modelo.obtener_errores().isEmpty()) {
            try {
                switch (a_modelo.obtener_modo()) {
                    case rcrsystem.Aplicacion.ae_modo_agregar:
                          AdministrarMaterialDAO.grabar(material);
                         AdministrarMaterialDAO.grabarTipo(material);
                         a_modelo.setFiltro(new AdministrarMaterial());
                       
                        a_modelo.colocar_mensaje("¡Material Agregado!");
                        a_modelo.colocar_material_actual(new AdministrarMaterial());
                        a_modelo.colocar_mensaje("");
                         a_controlador.reiniciar_filtro();
                        List<AdministrarMaterial> materiales =  AdministrarMaterialDAO.getMateriales();
                        a_modelo.colocar_materiales(materiales, 0);          
                        a_modelo.limpiar_errores();
                        a_vista.setVisible(false);
                         a_vista.setVisible(false);
                       
                        break;

                    case rcrsystem.Aplicacion.ae_modo_editar:
                         AdministrarMaterialDAO.actualizar(material);
                         AdministrarMaterialDAO.actualizarTipo(material);
                        a_modelo.colocar_mensaje("¡Material modificado!");

                        a_modelo.colocar_material_actual(new AdministrarMaterial());
                        a_modelo.colocar_mensaje("");
                        a_modelo.setFiltro(new AdministrarMaterial());
                        a_controlador.reiniciar_filtro();

                        List<AdministrarMaterial> materiales2 =  AdministrarMaterialDAO.getMateriales();
                        a_modelo.colocar_materiales(materiales2, 0);          
                        a_modelo.limpiar_errores();
                        a_vista.setVisible(false);
                        break;
                }
            } catch (Exception e) {
                a_modelo.obtener_errores().put("id", "¡Material ya existe!");
                a_modelo.colocar_mensaje("¡El material ya está registrado!");
                a_modelo.colocar_material_actual(material);
            }
        } else {
            a_modelo.colocar_mensaje("¡Hay errores!");
            a_modelo.colocar_material_actual(material);
        }
    }

    private VentanaAdminMaterial a_vista;
    private Material_Modelo a_modelo;
    private Materiales_Controlador a_controlador;
} // Fin de la clase Admin_Material_Controlador

