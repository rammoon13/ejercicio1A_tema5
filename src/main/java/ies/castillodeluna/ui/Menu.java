package ies.castillodeluna.ui;

import ies.castillodeluna.dao.ZonaEnvioDAO;
import ies.castillodeluna.dao.ClienteDAO;
import ies.castillodeluna.models.ZonaEnvio;
import ies.castillodeluna.models.Cliente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.Scanner;

/**
 * Clase que representa el menú de gestión de pedidos con Hibernate.
 */
public class Menu {
    private final MenuHandler menuHandler;
    private final Scanner scanner;

    /**
     * Constructor que inicializa el menú y precarga datos en la base de datos.
     */
    public Menu() {
        this.menuHandler = new MenuHandler();
        this.scanner = new Scanner(System.in);
        precargarDatos();
    }

    /**
     * Precarga datos iniciales en la base de datos si no existen.
     */
    private void precargarDatos() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pedidosPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        // Verificar si hay zonas de envío
        if (em.createQuery("SELECT COUNT(z) FROM ZonaEnvio z", Long.class).getSingleResult() == 0) {
            ZonaEnvio zona1 = new ZonaEnvio("Norte", 5.00);
            ZonaEnvio zona2 = new ZonaEnvio("Sur", 7.50);
            em.persist(zona1);
            em.persist(zona2);

            Cliente cliente1 = new Cliente("Ana López", "ana.lopez@example.com", "600123456", zona1);
            Cliente cliente2 = new Cliente("Carlos Pérez", "carlos.perez@example.com", "610654321", zona2);
            Cliente cliente3 = new Cliente("Beatriz Gómez", "beatriz.gomez@example.com", "620987654", zona1);
            Cliente cliente4 = new Cliente("David Martín", "david.martin@example.com", "630321789", zona2);
            em.persist(cliente1);
            em.persist(cliente2);
            em.persist(cliente3);
            em.persist(cliente4);
        }

        em.getTransaction().commit();
        em.close();
    }

    /**
     * Muestra el menú y gestiona las opciones seleccionadas por el usuario.
     */
    public void mostrarMenu() {
        int opcion;
        do {
            System.out.println("\n--- Menú de Gestión de Pedidos ---");
            System.out.println("1. Ver Zonas de Envío");
            System.out.println("2. Ver Clientes");
            System.out.println("3. Ver Pedidos de un Cliente");
            System.out.println("4. Agregar Cliente");
            System.out.println("5. Borrar Cliente");
            System.out.println("6. Agregar Pedido");
            System.out.println("7. Borrar Pedido");
            System.out.println("8. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpia el buffer del scanner

            switch (opcion) {
                case 1 -> menuHandler.mostrarZonasEnvio();
                case 2 -> menuHandler.mostrarClientes();
                case 3 -> menuHandler.mostrarPedidosCliente(scanner);
                case 4 -> menuHandler.agregarCliente(scanner);
                case 5 -> menuHandler.borrarCliente(scanner);
                case 6 -> menuHandler.agregarPedido(scanner);
                case 7 -> menuHandler.borrarPedido(scanner);
                case 8 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción inválida. Intente nuevamente.");
            }
        } while (opcion != 8);
    }
}