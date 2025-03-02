package com.ecommerce.OnlineShopping.Controllers;

import com.ecommerce.OnlineShopping.DTO.DetallesProductoDTO;
import com.ecommerce.OnlineShopping.Repositories.MarcaRepository;
import com.ecommerce.OnlineShopping.Services.ProductService;
import com.ecommerce.OnlineShopping.models.Producto;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;
    
    @Autowired
    private MarcaRepository marcaRepository;

    @GetMapping("/api/catalogo")
    public ResponseEntity<Page<Producto>> listarProductos(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "8") int size,
            @RequestParam(required = false) String categoria,
            @RequestParam(required = false) String busqueda) {

        Page<Producto> productos;
        Pageable pageable = PageRequest.of(page, size);

        if (categoria != null && !categoria.isEmpty()) {
            productos = productService.buscarPorCategoria(categoria, pageable);
        } else if (busqueda != null && !busqueda.isEmpty()) {
            productos = productService.buscarPorNombre(busqueda, pageable);
        } else {
            productos = productService.obtenerTodos(pageable);
        }

        return ResponseEntity.ok(productos);
    }

    @GetMapping("/api/product/{name}")
    public ResponseEntity<?> mostrarProducto(@PathVariable String name) {
        if (name == null) {
            return ResponseEntity.badRequest().body("Nombre inválido");
        }

        try {
            System.out.println("Buscando producto con nombre: " + name);
            Optional<Producto> producto = productService.obtenerPorNombre(name);

            System.out.println(producto.get().getMarca());
            
            if (producto.isEmpty()) {
                System.out.println("Producto no encontrado");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Producto no encontrado");
            }
            
            if (producto.isPresent()) {
                DetallesProductoDTO detallesDTO;
                detallesDTO = new DetallesProductoDTO(
                        producto.get().getNombre(),
                        producto.get().getPrecio(),
                        producto.get().getGarantia(),
                        producto.get().getMarca(),
                        producto.get().getModelo(),
                        producto.get().getColor(),
                        producto.get().getOrigen(),
                        producto.get().getCategoria().getNombre(),
                        producto.get().getStock(),
                        producto.get().getImagen(),
                        producto.get().getDescripcion());
                detallesDTO.obtenerImagenMarca(marcaRepository,producto.get().getMarca());
                return ResponseEntity.ok(detallesDTO);
            } else {
                return ResponseEntity.badRequest().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error en el servidor");
        }
    }

}
