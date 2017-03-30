package Modelo;

import Modelo.dao.UsuarioDAO;
import java.util.List;
import java.util.Observer;

public class ModeloUsuario extends java.util.Observable {

    UsuarioDAO cnl;
    private List<Usuario> usuarios;
    final int SELECCION = 0;

    public ModeloUsuario() {
        cnl = new UsuarioDAO();
    }

    public Usuario getUsuario(int indice) {
        return usuarios.get(indice);
    }

    public void addObserver(java.util.Observer o) {
        super.addObserver(o);
        setChanged();
        notifyObservers(CLIENTE_CURRENT);
        setChanged();

    }

    private void initUsuario() {

        setUsuarioCurrent(new Usuario());
        //clearErrors();
    }

    public Usuario getUsuarioCurrent() {
        return usuarioCurrent;
    }

    public void setUsuarioCurrent(Usuario usuarioCurrent) {
        this.usuarioCurrent = usuarioCurrent;
      //  setChanged();
       // notifyObservers(CLIENTE_CURRENT);
    }

    public String validarIngreso(String identificacion, String pass) {
        Usuario miUsuario = UsuarioDAO.getUsuario(identificacion, pass);
        
        if (!"".equals(identificacion) || !"".equals(pass)) {
            if (miUsuario != null) {
                if (pass.equals(miUsuario.getContraseña())) {
                    this.setUsuarioCurrent(miUsuario);
                    return Integer.toString(miUsuario.getPuesto());
                } else {
                    return "invalido";//si el usuario o contraseña son incorrectos 
                }

            } else {
                return "desconectado";//si no hay conexion a la base
            }
        }
        return "errores";
    }

    public static Integer CLIENTE_CURRENT = 1;
    Usuario usuarioCurrent;

}
