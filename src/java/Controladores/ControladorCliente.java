package Controladores;

import Entidades.Cliente;
import Modelo.FachadaCliente;
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
@ManagedBean(name = "controladorCliente")
@SessionScoped
public class ControladorCliente implements Serializable{
    //Cliente en creación o actualización
    private Cliente cliente;

    //Fachada de acceso a los datos
    @EJB
    private FachadaCliente fachadaCliente;

    /*
    * Constructor vacio
    */
    public ControladorCliente() {
    }

    /*
    * Devuelve la cliente en creación o edición   
    * @return cliente a crear o actualizar
    */
    public Cliente getCliente() {

        // Si la cliente esta vacio crear uno 
        if (cliente == null) {
            cliente = new Cliente();
        }

        // Retorna el cliente actual
        return cliente;
    }

    /**
     * Actualiza la cliente en edición o creación
     *
     * @param cliente cliente actual
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    /**
     * Devuelve todos las clientes registradas en el sistema
     *
     * @return Lista de clientes
     */
    public Collection<Cliente> getClientes() {
        return fachadaCliente.listar();
    }

    /**
     * Retorna la fachada de las clientes
     *
     * @return fachada de los clientes
     */
    public FachadaCliente getFachadaCliente() {
        return fachadaCliente;
    }
    
    /**
     * Adiciona a la unidad de persistencia la cliente actual
     *
     * @return Nombre de la página despues de crear
     */
    public String crear() {
        fachadaCliente.adicionar(cliente);
        cliente = null;
        return "lista";
    }

    /**
     * Elimina la cliente especificada y retorna a la lista
     *
     * @param cliente Cliente a eliminar
     * @return Pagina a la que se devuelve
     */
    public String eliminar(Cliente cliente) {
        fachadaCliente.eliminar(cliente);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Cliente eliminada.", ""));
        return "lista";
    }


    /**
     * Asigna la cliente especificada como la cliente actual y llama a la
     * página de actualizar
     * @param cliente Cliente a actualizar
     * @return devuelve a la página de actualización
     */
    public String prepararActualizar(Cliente cliente) {
        //Asigna la cliente seleccionado desde la lista como la actual 
        this.cliente = cliente;
        return "actualizar";
    }

    /**
     * Actualiza los datos de la cliente actual
     *
     * @return devuelve a la lista
     */
    public String actualizar() {
        fachadaCliente.actualizar(cliente);
        cliente = null;
        return "lista";
    }

}
