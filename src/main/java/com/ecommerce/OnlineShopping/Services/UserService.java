package com.ecommerce.OnlineShopping.Services;

import com.ecommerce.OnlineShopping.Repositories.UserRepository;
import com.ecommerce.OnlineShopping.models.Administrador;
import com.ecommerce.OnlineShopping.models.Cliente;
import com.ecommerce.OnlineShopping.models.RegisterRequest;
import com.ecommerce.OnlineShopping.models.Usuario;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private BCryptPasswordEncoder passwordEncoder;

    public UserService() {
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public boolean createUser(Usuario user) {

        if (userRepository.existsByNombre(user.getNombre())) {
            // Verifica si el nombre de usuario ya está en uso.
            throw new DataIntegrityViolationException("El nombre de usuario ya está en uso");
            // Lanza una excepción específica si el username ya existe.
        }

        if (userRepository.existsByEmail(user.getEmail())) {
            // Verifica si el correo electrónico ya está en uso.
            throw new DataIntegrityViolationException("El correo electrónico ya está en uso");
        }

        userRepository.save(user);
        return true;
    }

    public boolean authenticate(String nombre, String email, String password) {
        Optional<Usuario> user = userRepository.findByNombreAndEmail(nombre, email);

        if (user.isPresent()) {
            
            Usuario usuario = user.get();
            // Aquí podrías comparar la contraseña con la que está almacenada, utilizando BCrypt u otro método seguro.
            if (usuario.getPassword().equals(password)) {
                return true; // Contraseña correcta
            } else {
                return false; // Contraseña incorrecta
            }
        }
        return false; // Retorna `false` si no se encuentra el usuario o las contraseñas no coinciden.
    }

    public boolean createAdmin(Administrador admin, Usuario currentUser) {
        // Verifica si el usuario actual tiene el rol de ADMIN.
        if (!"ADMIN".equals(currentUser.getRol())) {
            throw new SecurityException("No tienes permisos para crear un administrador");
        }

        // Valida datos únicos como nombre y correo.
        if (userRepository.existsByNombre(admin.getNombre())) {
            throw new DataIntegrityViolationException("El nombre de usuario ya está en uso");
        }

        if (userRepository.existsByEmail(admin.getEmail())) {
            throw new DataIntegrityViolationException("El correo electrónico ya está en uso");
        }

        // Guarda el administrador en la base de datos.
        userRepository.save(admin);
        return true;
    }

    public boolean createClient(Cliente cliente) {
        // Valida si el nombre de usuario ya está en uso.
        if (userRepository.existsByNombre(cliente.getNombre())) {
            throw new DataIntegrityViolationException("El nombre de usuario ya está en uso");
        }

        // Valida si el correo electrónico ya está en uso.
        if (userRepository.existsByEmail(cliente.getEmail())) {
            throw new DataIntegrityViolationException("El correo electrónico ya está en uso");
        }

        // Valida campos adicionales específicos de Cliente.
        if (cliente.getDireccion() == null || cliente.getDireccion().isEmpty()) {
            throw new IllegalArgumentException("La dirección es obligatoria");
        }

        if (cliente.getCodigoPostal() == null || cliente.getCodigoPostal() <= 0) {
            throw new IllegalArgumentException("El código postal es obligatorio y debe ser válido");
        }

        // Guarda el cliente en el repositorio.
        userRepository.save(cliente);
        return true;
    }

    public List<Usuario> getUsers() {
        return userRepository.findAll();
    }

    /*
     public String hashPassword(Usuario usuario) {
        // Validar que la contraseña no sea nula
        if (usuario.getPassword() == null || usuario.getPassword().isEmpty()) {
            System.out.println("La contraseña es nula o vacía");
            throw new IllegalArgumentException("La contraseña no puede ser nula o vacía");
        }

        // Obtener la contraseña del usuario
        String password = usuario.getPassword();

        // Log para depuración (no expongas la contraseña completa)
        System.out.println("Contraseña recibida (parcial): " + password.substring(0, Math.min(password.length(), 3)) + "***");

        // Convertir la contraseña a bytes usando UTF-8
        byte[] passwordBytes = password.getBytes(StandardCharsets.UTF_8);
        System.out.println("Bytes generados: " + Arrays.toString(passwordBytes));

        // Crear un encriptador BCrypt
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        // Generar el hash de la contraseña
        String hashedPassword = encoder.encode(password);

        // Log para depuración (opcional)
        System.out.println("Contraseña hasheada: " + hashedPassword);

        // Devolver el hash generado
        return hashedPassword;
    }
     */

    public void registerUser(RegisterRequest registerRequest) throws Exception {

        System.out.println("Datos recibidos: " + registerRequest.toString());

        // Validar que los campos obligatorios no sean nulos o vacíos
        if (registerRequest.getUsername() == null || registerRequest.getUsername().isEmpty()) {
            throw new Exception("El nombre de usuario no puede estar vacío");
        }
        if (registerRequest.getEmail() == null || registerRequest.getEmail().isEmpty()) {
            throw new Exception("El correo electrónico no puede estar vacío");
        }
        if (registerRequest.getDireccion() == null || registerRequest.getDireccion().isEmpty()) {
            throw new Exception("La dirección no puede estar vacía");
        }
        if (registerRequest.getPassword() == null || registerRequest.getPassword().isEmpty()) {
            throw new Exception("La contraseña no puede estar vacía");
        }

        // Validar si el usuario ya existe
        if (userRepository.existsByNombre(registerRequest.getUsername())) {
            throw new Exception("El usuario ya existe");
        }

        // Crear el objeto Cliente
        Cliente client = new Cliente();
        client.setNombre(registerRequest.getUsername());
        client.setEmail(registerRequest.getEmail());
        client.setDireccion(registerRequest.getDireccion());
        client.setCodigoPostal(registerRequest.getPostalCode());
        client.setPassword(registerRequest.getPassword());

        // Guardar en la base de datos
        userRepository.save(client);
    }

}
