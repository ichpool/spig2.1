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
import javax.faces.bean.ManagedBean;

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

    public void crearComentario() {//Creación de comentario exitoso, reload de la página
        //Manda error porque el ID no se actualiza. 
        Comentario c = new Comentario();
        ComentarioDAO cdao = new ComentarioDAO();
        c.setContenido(contenido);
        MarcadorDAO mdao = new MarcadorDAO();
        //Cachar excepciones
        //Marcador m = mdao.find(marcador.getIdmarcador());
        Marcador m = mdao.find(1);
        c.setMarcador(m);
        ComentaristaDAO comdao = new ComentaristaDAO();
        //Comentarista com = comdao.find(comentarista.getCorreo());
        Comentarista com = comdao.find("desales@gmail.com");
        c.setComentarista(com);
        cdao.save(c);
    }
}
