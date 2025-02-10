
package com.ecommerce.OnlineShopping.models;

import jakarta.persistence.Entity;

@Entity
public class Administrador extends Usuario{

    public Administrador() {
    }

    public Administrador(Integer idUsuario, String nombre, String email, String password) {
        super(idUsuario, nombre, email, password);
    }
    
    
}
