
package com.ecommerce.OnlineShopping.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {
    // Ruta para mostrar el formulario de inicio de sesión
    @GetMapping("/login")
    public String showLogin() {
        return "login";
        // Devuelve la vista "login.html" ubicada en la carpeta `templates`.
        // Sirve como la página de inicio de sesión para los usuarios.
    }

    // Ruta para mostrar el formulario de registro
    @GetMapping("/register")
    public String showRegister() {
        return "register";
        // Devuelve la vista "register.html" ubicada en la carpeta `templates`.
        // Sirve como la página de registro para nuevos usuarios.
    }
    
    @GetMapping("/principal")
    public String showPrincipal() {
        return "principal";
        // Devuelve la vista "register.html" ubicada en la carpeta `templates`.
        // Sirve como la página de registro para nuevos usuarios.
    }
    
    @GetMapping("/catalogo")
    public String showProducts() {
        return "catalogo";
    }
}
