package ies.castillodeluna.models;

import jakarta.persistence.*;
import java.util.List;

/**
 * Clase que representa a un cliente en el sistema.
 */
@Entity
@Table(name = "Clientes")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private int id;

    @Column(nullable = false)
    private String nombre;
    
    @Column(nullable = false, unique = true)
    private String email;
    
    @Column(nullable = false)
    private String telefono;
    
    @ManyToOne
    @JoinColumn(name = "id_zona", nullable = false)
    private ZonaEnvio zonaEnvio;
    
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pedido> pedidos;

    public Cliente() {}

    public Cliente(String nombre, String email, String telefono, ZonaEnvio zonaEnvio) {
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
        this.zonaEnvio = zonaEnvio;
    }

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public String getEmail() { return email; }
    public String getTelefono() { return telefono; }
    public ZonaEnvio getZonaEnvio() { return zonaEnvio; }
    public List<Pedido> getPedidos() { return pedidos; }

    @Override
    public String toString() {
        return String.format("Cliente{id=%d, nombre='%s', email='%s', telefono='%s', zonaEnvio='%s'}",
                id, nombre, email, telefono, zonaEnvio.getNombre());
    }
}
