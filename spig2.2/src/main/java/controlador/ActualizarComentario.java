/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import modelo.Comentarista;
//import modelo.ComentaristaDAO;
import modelo.Comentario;
import modelo.ComentarioDAO;



/**
 *
 * @author desales
 */
public class ActualizarComentario {
    
    private int idComentario;
    private String contenido;
    private Comentarista comentarista;
    
    
    public void setContenido(String contenido) {
        this.contenido = contenido;
    }
    
    public void SetComentarista(Comentarista comentarista) {
        this.comentarista = comentarista;
    }
    
    public void setIdcomentarista(int idComentario) {
        this.idComentario = idComentario;
    }
    
    public String ActualizarComentario() {
        Comentario coment = new ComentarioDAO().find(idComentario);
        if (coment.getComentarista().equals(comentarista)) { //Aquí podemos mandar un email de actualización
            coment.setContenido(contenido);
            new ComentarioDAO().update(coment);
            return "ActualizacionComentarioExitosa?faces-redirect=true";
        }
        return "ActualizacionComentarioInvalida?faces-redirect=true";
    }
}
