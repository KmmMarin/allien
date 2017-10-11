/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Entidades.Oficina;
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
public class FachadaOficina {
    @PersistenceContext(unitName = "PruebaPU")
    private EntityManager em;

    /**
     * Adiciona un oficina. Si la oficina ya existe lanza una excepcion
     *
     * @param oficina Oficina a adicionar
     */
    public void adicionar(Oficina oficina) {
        em.persist(oficina);
    }

    /**
     * Actualiza un oficina. Actualiza los datos del oficina
     *
     * @param oficina Oficina a adicionar
     */
    public void actualizar(Oficina oficina) {
        em.merge(oficina);
    }

    /**
     * Elimina la oficina .
     *
     * @param oficina Oficina a eliminar
     */
    public void eliminar(Oficina oficina) {
        em.remove(em.merge(oficina));
    }

    /**
     * Busca la oficina con el nombre dado. Retorna null si no existe
     *
     * @param nombre Nombre de la oficina
     * @return null si la oficina no existe o el objeto oficina
     */
    public Oficina buscar(String nombre) {
        return em.find(Oficina.class, nombre);
    }

    /**
     * Retorna TODAS las oficina que se han almacenado
     *
     * @return Lista con las oficinas
     */
    public Collection<Oficina> listar() {
        Query query = em.createQuery("SELECT p FROM Oficina p ");
        return (Collection<Oficina>) query.getResultList();
    }
    
    public Oficina buscarPorNombre(String nombre) {
        try {
            Query consulta = em.createQuery("Select o from Oficina o where o.nombre = ?1");
            consulta.setParameter(1, nombre);
            return (Oficina) consulta.getResultList().get(0);
        } catch (Exception ex) {
            return null;
        }
    }
}
