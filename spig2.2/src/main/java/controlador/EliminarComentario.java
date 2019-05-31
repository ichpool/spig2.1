/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import modelo.ComentarioDAO;
import modelo.Comentario;
//import modelo.Comentarista;
//import modelo.ComentaristaDAO;
//import modelo.Marcador;
//import modelo.MarcadorDAO;
//import modelo.Marcador;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

/**
 *
 * @author desales
 */
@ManagedBean
public class EliminarComentario {
    
    /*id del comentario*/
    private int id;
    /*Recordemos que los informadores, administradores y los comentaristas son
    los que pueden eliminar; en el caso de los comentaristas sólo pueden 
    eliminar comentarios propios.
    Si existe la sesión, procedemos a checar si somos comentaristas. 
    Si somos comentaristas tuvimos que hacer publicado el comentario a eliminar*/
    
    public EliminarComentario () {}

    /**
     * 
     * @param id 
     */
    public void setId(int id) {
        this.id = id;
    }
    
    public void eliminarComentario(int id) {
        /*Encontramos al comentario*/
        ComentarioDAO cdao = new ComentarioDAO();
        Comentario c = cdao.find(id);
        if(c == null) {
            Mensajes.fatal("Error al conectar con el servidor. ");
          return;
        }
       
        /*Hay que proceder a checar si estamos logeados y si somos comentaristas
        tenemos que ser quien lo haya comentado*/
        ControladorSesion.UserLogged us = (ControladorSesion.UserLogged) 
                FacesContext.getCurrentInstance().getExternalContext()
                        .getSessionMap().get("user");
        
        if (us == null) {
            Mensajes.error("Primero es necesario iniciar sesión.");
            return;
        }
        /*Hasta este punto sabemos que estamos logeados; falta revisar el 
        rol que tiene el usuario para poder realizar dicha operación.*/
        
        /*Si es informador, hay que revissar que sea el informador que lo haya 
        publicado*/
        if(us.getRol().equals("informador")){ 
            if( ! c.getComentarista().getCorreo().equals(us.getCorreo())) {
                Mensajes.error("No tiene los permisos suficientes para eliminar"
                        + "el comentario");
                return;
            }
        }
        /*Entonces, a partir de aquí, sabemos que estamos logeados, y que 
        si somos un informador somos quien ha publicado el comentario. Si no es
        así, tenemos los permisos suficientes para poder realizar la transacción*/
        try{
            cdao.delete(c);
        } catch (Exception e) {
            Mensajes.fatal("Error durante la transacción. Por favor intente más"
                    + "tarde.");
        }
    } 
}
