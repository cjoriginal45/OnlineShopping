package com.ecommerce.OnlineShopping.Controllers;

import com.ecommerce.OnlineShopping.Services.UserService;
import com.ecommerce.OnlineShopping.models.Cliente;
import java.net.URI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/users/cliente")
public class ClienteController {

    @Autowired
    private UserService userService;

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/register")
    public ResponseEntity<?> createClient(@RequestBody Cliente cliente) {
        try {
            if (userService.createClient(cliente)) {
                // Si el registro es exitoso, crea una URI con la ubicación del nuevo recurso.
                URI location = ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(cliente.getIdUsuario())
                        .toUri();

                return ResponseEntity.created(location).body(cliente); // Retorna estado 201 Created.
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se pudo registrar el cliente");
            // Si no se pudo registrar, retorna estado 400 Bad Request.
        } catch (DataIntegrityViolationException e) {
            // Captura excepciones relacionadas con violaciones de integridad de datos (por ejemplo, duplicados).
            String errorMessage = "Error al registrar el cliente";

            if (e.getMessage().contains("username")) {
                errorMessage = "El nombre de usuario ya está en uso";
            } else if (e.getMessage().contains("email")) {
                errorMessage = "El correo electrónico ya está en uso";
            }
            return ResponseEntity.status(HttpStatus.CONFLICT).body(errorMessage);
            // Retorna estado 409 Conflict si hay datos duplicados.
        } catch (Exception e) {
            // Captura cualquier otra excepción inesperada.
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error inesperado: " + e.getMessage());
        }
    }

}
