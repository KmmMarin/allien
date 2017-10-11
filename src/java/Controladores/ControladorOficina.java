/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Entidades.Oficina;
import Modelo.FachadaOficina;
import java.io.Serializable;
import java.util.Collection;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Desarrollo
 */
@ManagedBean(name = "controladorOficina")
@SessionScoped
public class ControladorOficina implements Serializable{
    //Oficina en creación o actualización
    private Oficina oficina;

    //Fachada de acceso a los datos
    @EJB
    private FachadaOficina fachadaOficina;

    /*
    * Constructor vacio
    */
    public ControladorOficina() {
    }

    /*
    * Devuelve la oficina en creación o edición   
    * @return oficina a crear o actualizar
    */
    public Oficina getOficina() {

        // Si la oficina esta vacio crear uno 
        if (oficina == null) {
            oficina = new Oficina();
        }

        // Retorna el oficina actual
        return oficina;
    }

    /**
     * Actualiza la oficina en edición o creación
     *
     * @param oficina oficina actual
     */
    public void setOficina(Oficina oficina) {
        this.oficina = oficina;
    }

    /**
     * Devuelve todos las oficinas registradas en el sistema
     *
     * @return Lista de oficinas
     */
    public Collection<Oficina> getOficinas() {
        return fachadaOficina.listar();
    }

    /**
     * Retorna la fachada de las oficinas
     *
     * @return fachada de los oficinas
     */
    public FachadaOficina getFachadaOficina() {
        return fachadaOficina;
    }
    
    /**
     * Adiciona a la unidad de persistencia la oficina actual
     *
     * @return Nombre de la página despues de crear
     */
    public String crear() {
        fachadaOficina.adicionar(oficina);
        oficina = null;
        return "lista";
    }

    /**
     * Elimina la oficina especificada y retorna a la lista
     *
     * @param oficina Oficina a eliminar
     * @return Pagina a la que se devuelve
     */
    public String eliminar(Oficina oficina) {
        fachadaOficina.eliminar(oficina);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Oficina eliminada.", ""));
        return "lista";
    }


    /**
     * Asigna la oficina especificada como la oficina actual y llama a la
     * página de actualizar
     * @param oficina Oficina a actualizar
     * @return devuelve a la página de actualización
     */
    public String prepararActualizar(Oficina oficina) {
        //Asigna la oficina seleccionado desde la lista como la actual 
        this.oficina = oficina;
        return "actualizar";
    }

    /**
     * Actualiza los datos de la oficina actual
     *
     * @return devuelve a la lista
     */
    public String actualizar() {
        fachadaOficina.actualizar(oficina);
        oficina = null;
        return "lista";
    }
}
