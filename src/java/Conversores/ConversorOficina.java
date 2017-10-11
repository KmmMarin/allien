/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conversores;

import Controladores.ControladorOficina;
import Entidades.Oficina;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Desarrollo
 */
@FacesConverter("conversorOficina")
public class ConversorOficina implements Converter{

    /**
     * Devuelve el cliente como objeto cuando se envia el nombre en texto
     * @param context
     * @param component
     * @param value
     * @return La cliente que en la base de datos tiene ese nombre
     */
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {

        // Cargar el controlador del cliente
        ControladorCliente controlador = (ControladorCliente) context.getApplication().getELResolver().
                getValue(context.getELContext(), null, "controladorCliente");

        // Obtener la fachada y buscarla
        return controlador.getFachadaCliente().buscarPorNombre(value);
    }

    /**
     * Retorna el nombre del cliente si el objeto enviado es un
     * cliente, si no devuelve la cadena desconocido
     * @param context
     * @param component
     * @param value
     * @return El nombre del cliente
     */
    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        // Verifica si es del tipo cliente y le saca el nombre 
        if (value instanceof Cliente) {
            Cliente cliente = (Cliente) value;
            return cliente.getNombre();
        }

        return "Desconocido";
    }
}
