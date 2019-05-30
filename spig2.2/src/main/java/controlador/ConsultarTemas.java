package controlador;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
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
public class ConsultarTemas implements Serializable {

    private List<Tema> temas;

    @PostConstruct
    public void init() {
        ControladorSesion.UserLogged us = (ControladorSesion.UserLogged) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
        TemaDAO tdb = new TemaDAO();
        temas = tdb.buscaPorInformador(us.toInformador());
    }

    public List<Tema> obtenerTemas() {
        return temas;
    }
}
