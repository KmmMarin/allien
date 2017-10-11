package Modelo;

import Entidades.Cliente;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Desarrollo
 */
@Stateless
public class FachadaCliente {
    @PersistenceContext(unitName = "PruebaPU")
    private EntityManager em;

    /**
     * Adiciona un cliente. Si la cliente ya existe lanza una excepcion
     *
     * @param cliente Cliente a adicionar
     */
    public void adicionar(Cliente cliente) {
        em.persist(cliente);
    }

    /**
     * Actualiza un cliente. Actualiza los datos del cliente
     *
     * @param cliente Cliente a adicionar
     */
    public void actualizar(Cliente cliente) {
        em.merge(cliente);
    }

    /**
     * Elimina la cliente .
     *
     * @param cliente Cliente a eliminar
     */
    public void eliminar(Cliente cliente) {
        em.remove(em.merge(cliente));
    }

    /**
     * Busca la cliente con el nombre dado. Retorna null si no existe
     *
     * @param nombre Nombre de la cliente
     * @return null si la cliente no existe o el objeto cliente
     */
    public Cliente buscar(String nombre) {
        return em.find(Cliente.class, nombre);
    }

    /**
     * Retorna TODAS las cliente que se han almacenado
     *
     * @return Lista con las clientes
     */
    public Collection<Cliente> listar() {
        Query query = em.createQuery("SELECT c FROM Cliente c ");
        return (Collection<Cliente>) query.getResultList();
    }
}
