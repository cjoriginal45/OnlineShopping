
package com.ecommerce.OnlineShopping.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Cliente extends Usuario{
    
    @Column(nullable = false, length = 100)
    private String direccion;
    
    @Column(nullable = false)
    private Integer codigoPostal;
    
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pedido> pedidos;
    
    @OneToOne(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private Carrito carrito;

    public Cliente() {
        this.pedidos=new ArrayList<>();
        this.carrito=null;
        this.setRol(Rol.CLIENTE);
    }

    public Cliente(String direccion, Integer codigoPostal) {
        this.direccion = direccion;
        this.codigoPostal = codigoPostal;
        this.pedidos=null;
        this.carrito=null;
    }

    public Cliente(String direccion, Integer codigoPostal, Integer idUsuario, String nombre, String email, String password) {
        super(idUsuario, nombre, email, password);
        this.direccion = direccion;
        this.codigoPostal = codigoPostal;
        this.pedidos=null;
        this.carrito=null;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Integer getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(Integer codigoPostal) {
        this.codigoPostal = codigoPostal;
    }
    
    public void realizarPedido(Pedido pedido){
        this.pedidos.add(pedido);
    }
    

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public Carrito getCarrito() {
        return carrito;
    }

    public void setCarrito(Carrito carrito) {
        this.carrito = carrito;
    }

    
    
    
    
}
