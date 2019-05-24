package controlador;

import modelo.Informador;
import modelo.Tema;
import modelo.TemaDAO;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author n-droid
 */

@ManagedBean
@ViewScoped
public class AgregarTema {
    private String nombre;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void agregaTema(){
        try {
            ControladorSesion.UserLogged us = (ControladorSesion.UserLogged) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
            Informador info = us.toInformador();
            Tema new_tema = new Tema(info, this.nombre.toLowerCase());
            TemaDAO udb = new TemaDAO();
            udb.save(new_tema);
            Mensajes.info("Tema Guardado");
            this.nombre = "";
        } catch (Exception ex) {
            Mensajes.error("Ya existe ese tema");
        }
    }
}
