
package com.ecommerce.OnlineShopping.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
class Categoria {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCategoria;
    
    @Column(nullable = false)
    private String nombre;
    @Column
    private String descripcion;
    
    @OneToMany(mappedBy = "categoria", cascade = CascadeType.PERSIST)
    private List<Producto> productos;

    public Categoria() {
        this.productos=new ArrayList<>();
    }

    public Categoria(Integer idCategoria, String nombre, String descripcion, List<Producto> productos) {
        this.idCategoria = idCategoria;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.productos = productos;
    }

    public Categoria(Integer idCategoria, String nombre, String descripcion) {
        this.idCategoria = idCategoria;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.productos = null;
    }
    
    public void agregarProducto(Producto producto){
        this.productos.add(producto);
    }
    
    public void eliminarProducto(Producto producto){
        this.productos.remove(producto);
    }

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }
   
    
}
