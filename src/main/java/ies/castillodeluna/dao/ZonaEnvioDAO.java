package ies.castillodeluna.dao;

import ies.castillodeluna.models.ZonaEnvio;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;

/**
 * DAO para gestionar las zonas de envío usando Hibernate.
 */
public class ZonaEnvioDAO {
    private EntityManagerFactory emf;

    public ZonaEnvioDAO() {
        this.emf = Persistence.createEntityManagerFactory("pedidosPU");
    }

    public List<ZonaEnvio> obtenerZonasEnvio() {
        EntityManager em = emf.createEntityManager();
        List<ZonaEnvio> zonas = em.createQuery("FROM ZonaEnvio", ZonaEnvio.class).getResultList();
        em.close();
        return zonas;
    }

    public ZonaEnvio obtenerZonaPorId(int id) {
        EntityManager em = emf.createEntityManager();
        ZonaEnvio zona = em.find(ZonaEnvio.class, id);
        em.close();
        return zona;
    }
    
}
