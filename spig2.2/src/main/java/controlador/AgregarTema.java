/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import modelo.Informador;
import modelo.InformadorDAO;
import modelo.Tema;
import modelo.TemaDAO;

/**
 *Clase que nos permitirá agregar temas a la base de datos
 * @author desales
 */
public class AgregarTema {
    
    /*String nombre*/
    private String nombre;
    /*Informador que agrega el tema*/
    private Informador informador;
    /*id del tema*/
    private int id;

    /**
     * Constructor
     */
    public AgregarTema() {}
    
    /**
     * @param nombre 
     */
    public void setNombre(String nombre) {
        this.nombre = nombre.trim();
    }
    
    /**
     * Asigna el informador al tema
     * @param informador 
     */
    public void setInformador(Informador informador) {
        this.informador = informador;
    }
    
    /**
     * Le asigna el id al tema
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }
    
    /**
     * Método que agrega el tema construido. Para este método hay que tener 
     * en cuenta la existencia y el login válido por parte del informador.
     */
    public void agregarTema() {
        Informador inf = new InformadorDAO().buscaPorCorreo(
                informador.getCorreo());
        /*Si el informador no existe*/
        if(inf == null) {
          FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                            "Error al encontrar el usuario asociado. Por favor"
                                    + "iniciar sesión o intentarlo más tarde", 
                            null));
          return;
        }
        
        /*No aceptamos nombres vacíos*/
        if(nombre.equals("")) {
             FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                            "Ingrese un nombre válido.", 
                            null));
          return;
        }
        
        try {
            FilterSessionInfo fsi = new FilterSessionInfo();
            Tema t = new Tema();
            TemaDAO tdao = new TemaDAO();
            t.setInformador(inf);
            t.setNombre(nombre);
            t.setIdtema(id);
            List<Tema> temas = tdao.findAll();
            
            /*Tenemos que ver si el tema no ha sido ya agregado*/
            for(Tema tm : temas) {
                if (tm.getNombre().toLowerCase().equals(t.getNombre().toLowerCase())) {
                    FacesContext.getCurrentInstance().addMessage(null, 
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                            "Tema ya agregado.", null)); 
                    return;
                }
            }
            tdao.save(t); //Cómo mi objeto ahora tiene el id ???
        } catch( Exception ex) {
               FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                            "Error al agregar el tema. Por favor "
                                    + "inténtelo más tarde.", null));
        }
    }
    
}
