package ies.castillodeluna;

import ies.castillodeluna.ui.Menu;
import ies.castillodeluna.db.HibernateUtil;
import org.hibernate.SessionFactory;

/**
 * Clase principal que inicia la aplicación y gestiona la conexión con Hibernate.
 */
public class Main {
    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        try {
            Menu menu = new Menu();
            menu.mostrarMenu();
        } finally {
            HibernateUtil.shutdown();
        }
    }
}
