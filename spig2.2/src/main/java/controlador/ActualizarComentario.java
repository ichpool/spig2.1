/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import modelo.Comentario;
import modelo.ComentarioDAO;

/**
 *
 * @author desales
 */
@ManagedBean
public class ActualizarComentario {
    /*]Id del comentario a actualizar*/
    private int idComentario;
    /*El nuevo contenido.*/
    private String contenido;

    public void setIdComentario(int idComentario) {
        this.idComentario = idComentario;
    }
    
    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    /*Hacerlo string o void??*/
    public String ActualizarComentario() {
        Comentario com = new ComentarioDAO().find(idComentario);
        if (com == null) {
            Mensajes.fatal("Error al conectar con el servidor. Favor de intentar"
                    + "lo más tarde.");
            return "al index"; //O en caso a void
        }
        
        ControladorSesion.UserLogged us = (ControladorSesion.UserLogged)
                FacesContext.getCurrentInstance().getExternalContext()
                        .getSessionMap().get("user");
        /*Si no estamos logeados*/
        if (us == null) {
            Mensajes.error("Es necesario primero iniciar sesión.");
            return "al index";
        }
        
        /*Hasta este punto estamos logeados, por lo que ahora rectificamos si
        somos la persona que ha publicado el comentario.*/
        if (com.getComentarista().getCorreo().equals(us.getCorreo())) { 
            com.setContenido(contenido);
            /*Actualizamos en la BD*/
            new ComentarioDAO().update(com);
            return "ActualizacionComentarioExitosa?faces-redirect=true";
        }
        return "ActualizacionComentarioInvalida?faces-redirect=true";
    }
}