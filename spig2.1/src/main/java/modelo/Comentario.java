package modelo;
// Generated Apr 8, 2019 4:54:20 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Comentario generated by hbm2java
 */
public class Comentario  implements java.io.Serializable {


     private int idcomentario;
     private Comentarista comentarista;
     private Marcador marcador;
     private String contenido;
     private double calificacion;
     private Set marcadors = new HashSet(0);

    public Comentario() {
    }

	
    public Comentario(int idcomentario, Comentarista comentarista, 
            Marcador marcador, String contenido, double calificacion) {
        this.idcomentario = idcomentario;
        this.comentarista = comentarista;
        this.marcador = marcador;
        this.contenido = contenido;
        this.calificacion = calificacion;
    }
    public Comentario(int idcomentario, Comentarista comentarista, 
            Marcador marcador, String contenido, double calificacion, 
            Set marcadors) {
       this.idcomentario = idcomentario;
       this.comentarista = comentarista;
       this.marcador = marcador;
       this.contenido = contenido;
       this.calificacion = calificacion;
       this.marcadors = marcadors;
    }
   
    public int getIdcomentario() {
        return this.idcomentario;
    }
    
    public void setIdcomentario(int idcomentario) {
        this.idcomentario = idcomentario;
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
    public Set getMarcadors() {
        return this.marcadors;
    }
    
    public void setMarcadors(Set marcadors) {
        this.marcadors = marcadors;
    }

}
