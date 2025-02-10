
package com.ecommerce.OnlineShopping.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Pedido {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPedido;
    
    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;
    
    
    @Column(nullable = false)
    private LocalDate fecha;
    @Column(nullable = false)
    private Double total;
    
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    List<DetallePedido> detalles;

    public Pedido() {
        this.detalles=new ArrayList<>();
    }

    public Pedido(Integer idPedido, Cliente cliente, LocalDate fecha, double total) {
        this.idPedido = idPedido;
        this.cliente = cliente;
        this.fecha = fecha;
        this.total = total;
        this.detalles = null;
    }

    public Integer getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Integer idPedido) {
        this.idPedido = idPedido;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
    
    public void agregarDetalle(DetallePedido detalle){
        this.detalles.add(detalle);
    }

    public List<DetallePedido> getDetalles() {
        return detalles;
    }
    
    
}
