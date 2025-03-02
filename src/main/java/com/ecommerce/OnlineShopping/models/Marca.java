
package com.ecommerce.OnlineShopping.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Marca {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer marcaID;
    
    private String nombre;
    private String imagen;

    public Marca() {
    }

    public Marca(String nombre, String imagen) {
        this.nombre = nombre;
        this.imagen = imagen;
    }

    public Marca(Integer marcaID, String nombre, String imagen) {
        this.marcaID = marcaID;
        this.nombre = nombre;
        this.imagen = imagen;
    }

    public Integer getMarcaID() {
        return marcaID;
    }

    public void setMarcaID(Integer marcaID) {
        this.marcaID = marcaID;
    }

    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
    
    
    
}
