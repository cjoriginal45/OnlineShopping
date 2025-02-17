package com.ecommerce.OnlineShopping.Controllers;

import com.ecommerce.OnlineShopping.Services.CategoriaService;
import com.ecommerce.OnlineShopping.Services.ProductService;
import com.ecommerce.OnlineShopping.models.Categoria;
import com.ecommerce.OnlineShopping.models.Producto;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;
    
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

     /*
    @GetMapping("/catalogo")
    public ResponseEntity<?> listarProductos(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "8") int size) {
        try {
            Categoria categoriaElectronicos = new Categoria(1, "Electrónicos", "producto electronico");
            Categoria categoriaHogar = new Categoria(2, "Hogar", "producto hogar");

            List<Producto> productosSimulados = Arrays.asList(
                    new Producto(1, "Laptop Gamer", 1200.99, 10, "Laptop potente para gaming", categoriaElectronicos),
                    new Producto(2, "Smartphone", 899.99, 15, "Smartphone de última generación", categoriaElectronicos),
                    new Producto(3, "Audífonos", 59.99, 20, "Audífonos con cancelación de ruido", categoriaElectronicos),
                    new Producto(4, "Monitor 27''", 300.50, 8, "Monitor Full HD para oficina", categoriaElectronicos),
                    new Producto(5, "Silla de Oficina", 250.00, 5, "Silla ergonómica de oficina", categoriaHogar),
                    new Producto(6, "Mesa de Madera", 150.00, 7, "Mesa de comedor en madera", categoriaHogar),
                    new Producto(7, "Refrigerador", 1100.00, 4, "Refrigerador con congelador grande", categoriaHogar),
                    new Producto(8, "Horno Eléctrico", 90.00, 10, "Horno eléctrico de 30 litros", categoriaHogar),
                    new Producto(9, "Ventilador", 80.00, 12, "Ventilador de torre silencioso", categoriaHogar),
                    new Producto(10, "Lámpara LED", 25.00, 30, "Lámpara LED de escritorio", categoriaHogar)
            );

            // Validaciones
            if (size <= 0 || page < 0) {
                return ResponseEntity.badRequest().body("Los valores de paginación no son válidos.");
            }

            int totalProductos = productosSimulados.size();
            int start = page * size;

            if (start >= totalProductos) {
                return ResponseEntity.ok(new PageImpl<>(Collections.emptyList(), PageRequest.of(page, size), totalProductos));
            }

            int end = Math.min(start + size, totalProductos);
            List<Producto> pageContent = productosSimulados.subList(start, end);

            Page<Producto> productosPage = new PageImpl<>(pageContent, PageRequest.of(page, size), totalProductos);
            return ResponseEntity.ok(productosPage);
        } catch (Exception e) {
            e.printStackTrace(); // Muestra el error en la consola
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor: " + e.getMessage());
        }
    }
    */
}
