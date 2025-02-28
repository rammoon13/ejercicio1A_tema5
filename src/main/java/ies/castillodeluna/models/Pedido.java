package ies.castillodeluna.models;

import jakarta.persistence.*;
import java.util.Date;

/**
 * Clase que representa un pedido realizado por un cliente en el sistema.
 */
@Entity
@Table(name = "Pedidos")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pedido")
    private int id;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date fecha;

    @Column(nullable = false)
    private double importeTotal;

    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;

    public Pedido() {}

    public Pedido(Date fecha, double importeTotal, Cliente cliente) {
        this.fecha = fecha;
        this.importeTotal = importeTotal;
        this.cliente = cliente;
    }

    public int getId() { return id; }
    public Date getFecha() { return fecha; }
    public double getImporteTotal() { return importeTotal; }
    public Cliente getCliente() { return cliente; }

    @Override
    public String toString() {
        return String.format("Pedido{id=%d, fecha=%s, importeTotal=%.2f, cliente='%s'}",
                id, fecha, importeTotal, cliente.getNombre());
    }
}
