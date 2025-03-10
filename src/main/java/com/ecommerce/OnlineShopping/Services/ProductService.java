package com.ecommerce.OnlineShopping.Services;

import com.ecommerce.OnlineShopping.Repositories.ProductRepository;
import com.ecommerce.OnlineShopping.models.Producto;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

   @Autowired
    private ProductRepository productRepository;

    public Page<Producto> obtenerTodos(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    public Page<Producto> buscarPorCategoria(String categoria, Pageable pageable) {
        return productRepository.findByCategoriaNombre(categoria, pageable);
    }

    public Page<Producto> buscarPorNombre(String nombre, Pageable pageable) {
        return productRepository.findByNombreContainingIgnoreCase(nombre, pageable);
    }
    
    public Optional<Producto> obtenerPorId(Integer id){
        return productRepository.findById(id);
    }

    public Optional<Producto> obtenerPorNombre(String name) {
        return productRepository.findByName(name);
    }

    public Producto save(Producto product) {
        return productRepository.save(product);
    }

}
