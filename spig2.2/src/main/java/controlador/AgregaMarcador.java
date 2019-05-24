/* * To change this license header, choose License Headers in Project
 Properties. * To change this template file, choose Tools | Templates * and
 open the template in the editor. */

package controlador;


import modelo.Marcador;
import modelo.MarcadorDAO;
import modelo.Tema;
import modelo.TemaDAO;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.map.MarkerDragEvent;
import org.primefaces.event.map.PointSelectEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author n-droid

 */
@ManagedBean
@ViewScoped
public class AgregaMarcador implements Serializable {
    private Marker marcador;
    private MapModel simpleModel;
    private double longitud;
    private double latitud;
    private String nombreTema;
    private String descripcion;
    private String datos;
    private LatLng centro;

    @PostConstruct
    public void init(){
        centro = new LatLng(23.382390, -102.291477);
        simpleModel = new DefaultMapModel();
        marcador = new Marker(centro,"Arrastrame");
        marcador.setDraggable(true);
//      marcador.setClickable(true);
        simpleModel.addOverlay(marcador);
        this.latitud = marcador.getLatlng().getLat();
        this.longitud = marcador.getLatlng().getLng();
    }

    public LatLng getCentro() {
        return centro;
    }

    public Marker getMarcador() {
        return marcador;
    }

    public MapModel getSimpleModel() {
        return simpleModel;
    }

    public void onMarkerDrag(MarkerDragEvent event){
        marcador = event.getMarker();
        this.latitud = marcador.getLatlng().getLat();
        this.longitud = marcador.getLatlng().getLng();
    }

    public void onPointSelect(PointSelectEvent event) {
        LatLng latlng = event.getLatLng();
        marcador = simpleModel.getMarkers().get(0);
        marcador.setLatlng(latlng);
        this.latitud = latlng.getLat();
        this.longitud = latlng.getLng();
        this.centro = latlng;

    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }
    
    public String getNombreTema() {
        return nombreTema;
    }

    public void setNombreTema(String tema) {
        this.nombreTema = tema;
    }

    public String getDatos() {
        return datos;
    }

    public void setDatos(String datos) {
        this.datos = datos;
    }

    public List<String> autocompletaTema (String query) {
        ControladorSesion.UserLogged us = (ControladorSesion.UserLogged) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
        TemaDAO tdb = new TemaDAO();
        List<Tema> temas = tdb.buscaPorNombreLikeAndInformador(query, us.getCorreo());
        List<String> resultados = new ArrayList<>();
        for(Tema tem : temas){
            resultados.add(tem.getNombre());
        }
        return resultados;
    }

    public String agregaMarcador(){
        try{
            MarcadorDAO mdb = new MarcadorDAO();
            Marcador m = mdb.buscaPorLatLong(latitud, longitud);
            if(m!= null){
                this.descripcion ="";
                Mensajes.error("Ya existe un marcador con estas cordenadas \n" +"Lat: "+this.latitud +" Lng: "+this.longitud);
                return "";
            }
            m = new Marcador();
            ControladorSesion.UserLogged us = (ControladorSesion.UserLogged) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
            TemaDAO tdb = new TemaDAO();
            Tema tem = tdb.buscaPorNombreAndInformador(this.nombreTema,us.getCorreo());
            if(tem == null){
                Mensajes.error("No existe el Tema");
                return "";
            }
            m.setDescripcion(descripcion);
            m.setLatitud(latitud);
            m.setLongitud(longitud);
            m.setDatosExtra(datos);
            m.setTema(tem);
            mdb.save(m);
            Mensajes.info("Se guardo el marcador");
            this.nombreTema = "";
            this.descripcion = "";
            this.datos = "";
        } catch (Exception ex) {
            Mensajes.error("Error en la base de datos");
        }
        return "";
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }


}
