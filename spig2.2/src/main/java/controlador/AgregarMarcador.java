/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.Serializable;
import javax.inject.Named;
//import modelo.ComentarioDAO;
//import modelo.Comentario;
import modelo.Comentarista;
import modelo.ComentaristaDAO;
import modelo.Marcador;
import modelo.MarcadorDAO;
import modelo.Tema;
import modelo.TemaDAO;
import modelo.Informador;
import modelo.InformadorDAO;
import java.util.Random;


/**
 *
 * @author desales
 */

@Named
public class AgregarMarcador implements Serializable{
   
    private int idmarcador;
    private String descripcion;
    private String datosExtra;
    private double longitud;
    private double latitud;
    private int tema;
    
    public int getIdmarcador() {
        return this.idmarcador;
    }
    
    public void setIdmarcador(int idmarcador) {
        this.idmarcador = idmarcador;
    }
    public String getDescripcion() {
        return this.descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public String getDatosExtra() {
        return this.datosExtra;
    }
    
    public void setDatosExtra(String datosExtra) {
        this.datosExtra = datosExtra;
    }
    public double getLongitud() {
        return this.longitud;
    }
    
    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }
    public double getLatitud() {
        return this.latitud;
    }
    
    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }
    public int getTema() {
        return this.tema;
    }
    
    public void setTema(int tema) {
        this.tema = tema;
    }

    
    public void AgregarMarcador(){ //Necesitamos el tema, el usuario que lo agrega
        Marcador m = new Marcador();
        MarcadorDAO mdao = new MarcadorDAO();
        Tema t = new TemaDAO().find(1); //sistuiur por el tema que se busca
        Informador i = new InformadorDAO().buscaPorCorreoContrasenia("desales@gmail.com","desales");//Sustituir por el informador en cuestion
        
        mdao.save(m);
        
    }
}
