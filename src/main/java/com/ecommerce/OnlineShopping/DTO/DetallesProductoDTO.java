package com.ecommerce.OnlineShopping.DTO;

import com.ecommerce.OnlineShopping.Repositories.MarcaRepository;
import com.ecommerce.OnlineShopping.models.Marca;

public final class DetallesProductoDTO {

    private String nombre;
    private double precio;
    private int garantia;
    private String marca;
    private String modelo;
    private String color;
    private String origen;
    private String categoria;
    private Integer stock;
    private String imagen;
    private String imagenMarca;
    private String descripcion;

    public DetallesProductoDTO(String nombre, double precio, int garantia, String marca, String modelo, String color, String origen, String categoria, Integer stock, String imagen, String descripcion) {
        this.nombre = nombre;
        this.precio = precio;
        this.garantia = garantia;
        this.marca = marca;
        this.modelo = modelo;
        this.color = color;
        this.origen = origen;
        this.categoria = categoria;
        this.stock = stock;
        this.imagen = imagen;
        this.descripcion = descripcion;
    }

    public DetallesProductoDTO(double precio, int garantia, String marca, String modelo, String color, String origen, String categoria, Integer stock, String imagenMarca, String descripcion) {
        this.precio = precio;
        this.garantia = garantia;
        this.marca = marca;
        this.modelo = modelo;
        this.color = color;
        this.origen = origen;
        this.categoria = categoria;
        this.stock = stock;
        this.imagenMarca = imagenMarca;
        this.descripcion = descripcion;
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
    
        

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getGarantia() {
        return garantia;
    }

    public void setGarantia(int garantia) {
        this.garantia = garantia;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
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

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getImagenMarca() {
        return imagenMarca;
    }

    public void setImagenMarca(String imagenMarca) {
        this.imagenMarca = imagenMarca;
    }

    public void obtenerImagenMarca(MarcaRepository marcaRepository, String marca) {
        Marca m = marcaRepository.getMarca(marca);
        if (m != null) {
            this.imagenMarca = m.getImagen();
        }

    }

}
