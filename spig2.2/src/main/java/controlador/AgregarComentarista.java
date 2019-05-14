/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import auxiliares.FileToStringer;
import modelo.InformadorDAO;
import modelo.Comentario;
import modelo.Informador;
import auxiliares.SendEmail;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;


import java.util.Random;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import modelo.Comentarista;
import modelo.ComentaristaDAO;

/**
 *
 * @author n-droid
 */

@ManagedBean
public class AgregarComentarista {
    private String nombre;
    private String correo;
    private String passwd;
    private String passwdConf;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getPasswdConf() {
        return passwd;
    }

    public void setPasswdConf(String passwd) {
        this.passwdConf = passwd;
    }

    public void agregaComentarista(){
        if(!passwd.equals(passwdConf)){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "No coinciden las contraseñas", null));
            //Logger.getLogger(RegistrarInformador.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
        Comentarista new_comentarista = new Comentarista(correo.toLowerCase(),nombre.toLowerCase(),passwd);
        ComentaristaDAO udb = new ComentaristaDAO();
        try {
            udb.save(new_comentarista);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registrado", null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ya existe ese correo", null));
            Logger.getLogger(RegistrarInformador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
