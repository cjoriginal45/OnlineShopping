package com.ecommerce.OnlineShopping.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProducto;

    @Column(nullable = false, length = 45)
    private String nombre;
    @Column(nullable = false)
    private Double precio;
    @Column(nullable = false)
    private Integer stock;
    @Column(nullable = false, length = 80)
    private String descripcion;
    @Column
    private String imagen;
    @Column
    private String marca;
    @Column
    private int garantia;
    @Column
    private String modelo;
    @Column
    private String color;
    @Column
    private String origen;

    @ManyToOne
    @JoinColumn(name = "categoria_id", nullable = false)
    @JsonBackReference
    private Categoria categoria;

    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<DetallePedido> detalles;

    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ItemCarrito> items;

    public Producto() {
        this.detalles = new ArrayList<>();
        this.items = new ArrayList<>();
    }

    public Producto(Integer idProducto, String nombre, Double precio, Integer stock, String descripcion, Categoria categoria, List<DetallePedido> detalles, List<ItemCarrito> items) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.detalles = detalles;
        this.items = items;
    }

    public Producto(Integer idProducto, String nombre, Double precio, Integer stock, String descripcion, String imagen, String marca, int garantia, String modelo, String color, String origen, Categoria categoria) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.marca = marca;
        this.garantia = garantia;
        this.modelo = modelo;
        this.color = color;
        this.origen = origen;
        this.categoria = categoria;
        this.detalles = null;
        this.items = null;
    }

    

    public Producto(Integer idProducto, String nombre, Double precio, Integer stock, String descripcion, String imagen, Categoria categoria, List<DetallePedido> detalles, List<ItemCarrito> items) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.categoria = categoria;
        this.detalles = null;
        this.items = null;
    }

    public Producto(Integer idProducto, String nombre, Double precio, Integer stock, String descripcion, String imagen, String marca, int garantia, String modelo, String color, String origen, Categoria categoria, List<DetallePedido> detalles, List<ItemCarrito> items) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.marca = marca;
        this.garantia = garantia;
        this.modelo = modelo;
        this.color = color;
        this.origen = origen;
        this.categoria = categoria;
        this.detalles = null;
        this.items = null;
    }
    
    

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public List<DetallePedido> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetallePedido> detalles) {
        this.detalles = detalles;
    }

    public List<ItemCarrito> getItems() {
        return items;
    }

    public void setItems(List<ItemCarrito> items) {
        this.items = items;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getGarantia() {
        return garantia;
    }

    public void setGarantia(int garantia) {
        this.garantia = garantia;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }
     
    
}
