/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import modelo.ComentarioDAO;
import modelo.Comentario;
import modelo.Comentarista;
import modelo.ComentaristaDAO;
import modelo.Marcador;
import modelo.MarcadorDAO;
import modelo.Marcador;
import java.util.Random;
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
    private Comentarista comentarista; //??
    
    public EliminarComentario () {}

    /**
     * 
     * @param id 
     */
    public void setId(int id) {
        this.id = id;
    }
    
    public void eliminarComentario() {
        /*Encontramos al comentario*/
        ComentarioDAO cdao = new ComentarioDAO();
        Comentario c = cdao.find(id);
        if(c == null) {
            FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                            "Error al encontrar el comentario en la BD.", 
                            null));
          return;
        }
       
        /*Hay que proceder a checar si estamos logeados y si somos comentaristas
        tenemos que ser quien lo haya comentado*/
        
        /*Los filterssesion necesarios
        FilterSessionAdmin fsa = new FilterSessionAdmin();
        FilterSessionComentarista fsc = new FilterSessionComentarista();
        FilterSessionInfo fsi = new FilterSessionInfo();
        */
        
        try{
            cdao.delete(c);
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                            "Error al eliminar el comentario.", 
                            null));
        }
    }
    
    
    
}
