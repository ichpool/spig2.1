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
public class CrearComentario {
    
    private int idcomentario;
    private String contenido;
    private double calificacion;
    private Comentarista comentarista;
    private Marcador marcador;
    
    public int getIdcomentario() {
        return this.idcomentario;
    }
    
    public void setIdcomentario(int idcomentario) {
        this.idcomentario = idcomentario;
    }
    
    public String getContenido() {
        return this.contenido;
    }
    
    public void setContenido(String contenido) {
        this.contenido = contenido;
    }
    
    public double getCalificacion() {
        return this.calificacion;
    }
    
    public void setCalificacion(double calificacion) {
        this.calificacion = calificacion;
    }
    
    public Comentarista getComentarista() {
        return this.comentarista;
    }
    
    public void setComentarista(Comentarista comentarista) {
        this.comentarista = comentarista;
    }
    
    public Marcador getMarcador() {
        return this.marcador;
    }
    
    public void setMarcador(Marcador marcador) {
        this.marcador = marcador;
    }

    public void crearComentario() {
        /*Encontramos el comentarista primero*/
        Comentarista com = 
                new ComentaristaDAO().find(comentarista.getCorreo());
        
        /*Si no encontramos al comentarista*/
        if( com == null) {
            FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                            "No existe ese comentarista. Por favor intente"
                                    + "iniciar sesión de nuevo o pruebe con "
                                    + "otro usuario", null));
                return;
        }
        
        /*Si el contenido es vacío*/   
        if("".equals(contenido)) {
            FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                            "Comentario inválido.", null));
                return;
        }
        
        Marcador m = new MarcadorDAO().find(marcador.getIdmarcador());
       
        /*Si no encontramos el marcador*/
        if (m == null) {
                       FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                            "No existe ese marcador o ha habido un error "
                                    + "al tratar de validarlo. Por favor "
                                    + "intentar más tarde.", null));
                return;
        }
        
        
        /*Creamos el comentario*/
        try {
            Comentario c = new Comentario();
            ComentarioDAO cdao = new ComentarioDAO();
            /*Asignamos valores*/
            c.setContenido(contenido); 
            c.setMarcador(m);    
            c.setComentarista(com);
            /*Guardamos en la BD*/
            cdao.save(c); 
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, 
                    "Comentario publicado.", null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } catch (Exception ex) {
              FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                            "Error al publicar el comentario. Por favor"
                                    + "intentar más tarde.", null));
        }

    }
}
