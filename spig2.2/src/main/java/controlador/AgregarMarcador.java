/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import modelo.Marcador;
import modelo.MarcadorDAO;
import modelo.Tema;
import modelo.TemaDAO;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;


/**
 *
 * @author desales
 */

@ManagedBean
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

    
    public void AgregarMarcador(){
        try {
            Marcador m = new Marcador();
            MarcadorDAO mdao = new MarcadorDAO();
            /*Tenemos que checar si el tema existe*/


            Tema t = new TemaDAO().find(this.tema);

            if(t == null) {
                FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                            "Tema no encontrado. Por favor intentar más tarde o "
                                    + "con otro tema.", null)); 
                return;
            }

            List<Marcador> marcadores = mdao.findAll();

            for(Marcador mc : marcadores)
                if (mc.getLatitud() == latitud && mc.getLongitud() == longitud &&
                        mc.getTema().equals(m.getTema())) {
                    FacesContext.getCurrentInstance().addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                    "Marcador ya existente.", null));
                    return;  
                } 

            /*Es un nuevo marcador*/
            m.setLatitud(this.latitud);
            m.setLongitud(this.longitud);
            m.setTema(t);
            mdao.save(m);
        } catch( Exception e) {
                FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Error al agregar el nuevo Marcador. Por favor"
                                    + "intentelo más tarde.", null));
        }
    }
}
