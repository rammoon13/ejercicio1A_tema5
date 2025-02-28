package ies.castillodeluna.dao;

import ies.castillodeluna.models.Pedido;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;

/**
 * DAO para gestionar los pedidos usando Hibernate.
 */
public class PedidoDAO {
    private EntityManagerFactory emf;

    public PedidoDAO() {
        this.emf = Persistence.createEntityManagerFactory("pedidosPU");
    }

    public void agregarPedido(Pedido pedido) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(pedido);
        em.getTransaction().commit();
        em.close();
    }

    public void borrarPedido(int idPedido) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Pedido pedido = em.find(Pedido.class, idPedido);
        if (pedido != null) {
            em.remove(pedido);
        }
        em.getTransaction().commit();
        em.close();
    }

    public List<Pedido> obtenerPedidosPorCliente(int idCliente) {
        EntityManager em = emf.createEntityManager();
        List<Pedido> pedidos = em.createQuery("FROM Pedido p WHERE p.cliente.id = :idCliente", Pedido.class)
                .setParameter("idCliente", idCliente)
                .getResultList();
        em.close();
        return pedidos;
    }
}
