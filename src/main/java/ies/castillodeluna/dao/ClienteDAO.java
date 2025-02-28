package ies.castillodeluna.dao;

import ies.castillodeluna.models.Cliente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;

/**
 * DAO para gestionar los clientes usando Hibernate.
 */
public class ClienteDAO {
    private EntityManagerFactory emf;

    public ClienteDAO() {
        this.emf = Persistence.createEntityManagerFactory("pedidosPU");
    }

    public void agregarCliente(Cliente cliente) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(cliente);
        em.getTransaction().commit();
        em.close();
    }

    public void borrarCliente(int idCliente) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Cliente cliente = em.find(Cliente.class, idCliente);
        if (cliente != null) {
            em.remove(cliente);
        }
        em.getTransaction().commit();
        em.close();
    }

    public List<Cliente> obtenerClientes() {
        EntityManager em = emf.createEntityManager();
        List<Cliente> clientes = em.createQuery("FROM Cliente", Cliente.class).getResultList();
        em.close();
        return clientes;
    }
}
