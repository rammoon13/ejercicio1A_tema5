package ies.castillodeluna.ui;

import ies.castillodeluna.dao.ClienteDAO;
import ies.castillodeluna.dao.PedidoDAO;
import ies.castillodeluna.dao.ZonaEnvioDAO;
import ies.castillodeluna.models.Cliente;
import ies.castillodeluna.models.Pedido;
import ies.castillodeluna.models.ZonaEnvio;

import java.util.List;
import java.util.Scanner;

/**
 * Clase encargada de gestionar las operaciones del menú relacionadas con la base de datos usando Hibernate.
 */
public class MenuHandler {
    private final ClienteDAO clienteDAO;
    private final PedidoDAO pedidoDAO;
    private final ZonaEnvioDAO zonaEnvioDAO;

    public MenuHandler() {
        this.clienteDAO = new ClienteDAO();
        this.pedidoDAO = new PedidoDAO();
        this.zonaEnvioDAO = new ZonaEnvioDAO();
    }

    public void mostrarZonasEnvio() {
        List<ZonaEnvio> zonas = zonaEnvioDAO.obtenerZonasEnvio();
        zonas.forEach(System.out::println);
    }

    public void mostrarClientes() {
        List<Cliente> clientes = clienteDAO.obtenerClientes();
        clientes.forEach(System.out::println);
    }

    public void mostrarPedidosCliente(Scanner scanner) {
        System.out.print("Ingrese el ID del Cliente: ");
        int idCliente = scanner.nextInt();
        List<Pedido> pedidos = pedidoDAO.obtenerPedidosPorCliente(idCliente);
        pedidos.forEach(System.out::println);
    }

    public void agregarCliente(Scanner scanner) {
        System.out.print("Nombre del cliente: ");
        String nombre = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Teléfono: ");
        String telefono = scanner.nextLine();
        System.out.print("ID de Zona de Envío: ");
        int idZona = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer
        ZonaEnvio zona = new ZonaEnvioDAO().obtenerZonaPorId(idZona);
        if (zona == null) {
            System.out.println("Error: No existe una zona con ID " + idZona);
            return;
        }
        Cliente cliente = new Cliente(nombre, email, telefono, zona);
        clienteDAO.agregarCliente(cliente);
        System.out.println("Cliente agregado exitosamente.");
        }

    public void borrarCliente(Scanner scanner) {
        System.out.print("Ingrese el ID del Cliente a eliminar: ");
        int idCliente = scanner.nextInt();
        clienteDAO.borrarCliente(idCliente);
        System.out.println("Cliente eliminado exitosamente.");
    }

    public void agregarPedido(Scanner scanner) {
        System.out.print("Ingrese el ID del Cliente: ");
        int idCliente = scanner.nextInt();
        System.out.print("Ingrese el importe total: ");
        double importe = scanner.nextDouble();
        scanner.nextLine(); // Limpiar buffer
        Pedido pedido = new Pedido(new java.util.Date(), importe, clienteDAO.obtenerClientes().stream().filter(c -> c.getId() == idCliente).findFirst().orElse(null));
        pedidoDAO.agregarPedido(pedido);
        System.out.println("Pedido agregado exitosamente.");
    }

    public void borrarPedido(Scanner scanner) {
        System.out.print("Ingrese el ID del Pedido a eliminar: ");
        int idPedido = scanner.nextInt();
        pedidoDAO.borrarPedido(idPedido);
        System.out.println("Pedido eliminado exitosamente.");
    }
}
