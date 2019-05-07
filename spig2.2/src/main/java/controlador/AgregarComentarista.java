/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

//import javax.mail.MessagingException;
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

    // public void agregaComentarista(){
    //     Comentarista new_comentarista = new Comentarista();
    //     String contrasenia = nombre.toLowerCase() + Integer.toString(rn.nextInt() % 1000);
    //     i.setContrasenia(contrasenia);
    //     InformadorDAO udb = new InformadorDAO();
    //     try {
    //         udb.save(i);
    //         FileToStringer fts = new FileToStringer();
    //         String html_string = fts.readFile("informador_mail.html");
    //         html_string = html_string.replace("[[nombre]]",nombre);
    //         html_string = html_string.replace("[[password]]",contrasenia);
    //         SendEmail.send("vazquezlisandro673c@gmail.com",
    //                 "Bienvenido a SPIG",
    //                 html_string);
    //         FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registrado", null);
    //         FacesContext.getCurrentInstance().addMessage(null, message);
    //     } catch (Exception ex) {
    //         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ya existe ese correo", null));
    //         Logger.getLogger(RegistrarInformador.class.getName()).log(Level.SEVERE, null, ex);
    //     }
    // }
}
