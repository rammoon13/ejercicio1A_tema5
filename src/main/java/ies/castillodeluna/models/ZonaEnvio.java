package ies.castillodeluna.models;

import jakarta.persistence.*;
import java.util.List;

/**
 * Clase que representa una zona de envío dentro del sistema.
 */
@Entity
@Table(name = "Zonas_Envio")
public class ZonaEnvio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_zona")
    private int id;

    @Column(name = "nombre_zona", nullable = false, unique = true)
    private String nombre;

    @Column(nullable = false)
    private double tarifaEnvio;

    @OneToMany(mappedBy = "zonaEnvio", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Cliente> clientes;

    public ZonaEnvio() {}

    public ZonaEnvio(String nombre, double tarifaEnvio) {
        this.nombre = nombre;
        this.tarifaEnvio = tarifaEnvio;
    }

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public double getTarifaEnvio() { return tarifaEnvio; }
    public List<Cliente> getClientes() { return clientes; }

    @Override
    public String toString() {
        return String.format("ZonaEnvio{id=%d, nombre='%s', tarifaEnvio=%.2f}", id, nombre, tarifaEnvio);
    }
}
