package com.ecommerce.OnlineShopping.Controllers;

import com.ecommerce.OnlineShopping.Services.UserService;
import com.ecommerce.OnlineShopping.models.LoginRequest;
import com.ecommerce.OnlineShopping.DTO.RegisterRequestDTO;
import com.ecommerce.OnlineShopping.models.Usuario;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<?> getUsers() {
        List<Usuario> users = userService.getUsers();
        return ResponseEntity.ok(users);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequestDTO registerRequest) {

        try {
            //hashing de la constraseña
            Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
            String hash = argon2.hash(1, 1024, 1, registerRequest.getPassword());
            registerRequest.setPassword(hash);
            
            userService.registerUser(registerRequest);
            return ResponseEntity.ok("Registro exitoso");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al registrar el usuario: " + e.getMessage());
        }
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest, HttpServletRequest httpRequest) {
        boolean isAuthenticated = userService.authenticate(loginRequest.getNombre(), loginRequest.getEmail());
        
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        boolean hash = argon2.verify(userService.verificarPassword(loginRequest.getEmail()), loginRequest.getPassword());
        
        if (isAuthenticated && hash) {
            HttpSession session = httpRequest.getSession();
            session.setAttribute("user", loginRequest.getNombre());
            return ResponseEntity.ok().body(Map.of("authenticated", true));
        } else {
            return ResponseEntity.status(401).body(Map.of("authenticated", false));
        }
    }

    // En UserController.java
    @GetMapping("/check")
    public ResponseEntity<?> checkAuth(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        boolean isAuthenticated = (session != null && session.getAttribute("user") != null);

        if(isAuthenticated){
        return ResponseEntity.ok(Map.of("authenticated", isAuthenticated));
        } 
        return ResponseEntity.status(401).body(Map.of("authenticated", false));
    }

}
