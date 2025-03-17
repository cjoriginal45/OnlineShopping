package com.ecommerce.OnlineShopping.Services;

import com.ecommerce.OnlineShopping.Repositories.ProductRepository;
import com.ecommerce.OnlineShopping.models.Producto;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    

    public Page<Producto> obtenerTodos(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    public Page<Producto> buscarPorCategoria(String categoria, Pageable pageable) {
        return productRepository.findByCategoriaNombre(categoria, pageable);
    }

    public Page<Producto> buscarPorNombre(String nombre, Pageable pageable) {
        return productRepository.findByNombreContainingIgnoreCase(nombre, pageable);
    }

    public Optional<Producto> obtenerPorId(Integer id) {
        return productRepository.findById(id);
    }

    public Optional<Producto> obtenerPorNombre(String name) {
        String nombreNormalizado = normalizarNombre(name);
        System.out.println("Buscando producto con nombre normalizado: " + nombreNormalizado);
        return productRepository.findByName(nombreNormalizado);
    }

    public Producto save(Producto product) {
        return productRepository.save(product);
    }

    private String normalizarNombre(String nombre) {
        return nombre.replaceAll("[\"'\\-/]", "").toLowerCase().trim();
    }


    public Optional<Producto> obtenerPorModelo(String modelo) {
         if (modelo == null || modelo.trim().isEmpty()) {
            throw new IllegalArgumentException("El modelo no puede ser nulo");
        }

        String modeloNormalizado = normalizarNombre(modelo);

        return productRepository.findByModelo(modeloNormalizado);

    }

}
