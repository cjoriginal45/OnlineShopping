package com.ecommerce.OnlineShopping.Controllers;

import com.ecommerce.OnlineShopping.DTO.CompraDTO;
import com.ecommerce.OnlineShopping.Services.ProductService;
import com.ecommerce.OnlineShopping.models.Producto;
import java.util.Map;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/buy")
public class CompraController {

    private final ProductService productService;

    public CompraController(ProductService productService) {
        this.productService = productService;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @PostMapping("/comprar")
    public ResponseEntity<?> comprarAhora(@RequestBody CompraDTO compraDTO) {

        if (compraDTO == null) {
            return ResponseEntity.badRequest().body("Error");
        }

        try {

            return validaciones(compraDTO); // Validar productoId

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                    "status", "error",
                    "message", "Error al realizar la compra: " + e.getMessage()
            ));
        }
    }

    @GetMapping("/producto/{id}")
    public ResponseEntity<?> obtenerProducto(@PathVariable Integer id) {
        Optional<Producto> producto = productService.obtenerPorId(id);
        if (producto.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Producto no encontrado");
        }
        return ResponseEntity.ok(producto.get());
    }

    @GetMapping("/producto/modelo/{modelo}")
    public ResponseEntity<?> obtenerProductoPorModeloYMarca(
            @PathVariable String modelo) {

        System.out.println("Modelo recibido: " + modelo);
        try {
            Optional<Producto> producto = productService.obtenerPorModelo(modelo);
            if (producto.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Producto no encontrado");
            }
            return ResponseEntity.ok(producto.get());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    private ResponseEntity<?> validaciones(CompraDTO compraDTO) {
        try {
            // Validar la fecha de vencimiento
            if (compraDTO.getFechaVencimiento() == null || !compraDTO.isExpirationDateValid()) {
                return ResponseEntity.badRequest().body("Fecha de vencimiento inválida o vencida");
            }

            // Validar el CVV
            if (!compraDTO.isCvvValid()) {
                return ResponseEntity.badRequest().body("CVV inválido");
            }

            // Validar campos obligatorios
            if (compraDTO.getDireccion() == null || compraDTO.getCodigoPostal() == null || compraDTO.getNumeroTarjeta() == null) {
                return ResponseEntity.badRequest().body("Faltan datos requeridos");
            }

            // Obtener el producto
            Producto product = productService.obtenerPorId(compraDTO.getProductoId())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

            // Validar el stock
            if (product.getStock() < 1) {
                return ResponseEntity.badRequest().body(Map.of(
                        "status", "error",
                        "message", "No hay suficiente stock para el producto"
                ));
            }

            // Reducir el stock
            product.setStock(product.getStock() - 1);
            productService.save(product);

            return ResponseEntity.ok(Map.of(
                    "status", "success",
                    "message", "Compra realizada con éxito"
            ));
        } catch (Exception e) {
            // Manejar la excepción y devolver una respuesta adecuada
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al realizar la compra: " + e.getMessage());
        }

    }
}
