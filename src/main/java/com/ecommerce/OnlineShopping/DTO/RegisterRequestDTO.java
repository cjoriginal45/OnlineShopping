
package com.ecommerce.OnlineShopping.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;


public class RegisterRequestDTO {
    @JsonProperty("username")
    private String username;
    @JsonProperty("email")
    private String email;
    @JsonProperty("password")
    private String password;
    @JsonProperty("direccion")
    private String direccion;
    @JsonProperty("postalCode")
    private int postalCode;

    public RegisterRequestDTO(String username, String email, String password, String direccion, int postalCode) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.direccion = direccion;
        this.postalCode = postalCode;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

    @Override
    public String toString() {
        return "RegisterRequest{" + "username=" + username + ", email=" + email + ", password=" + password + ", direccion=" + direccion + ", postalCode=" + postalCode + '}';
    }
    
    
    
}
