
package com.ecommerce.OnlineShopping.Services;
import com.ecommerce.OnlineShopping.Repositories.CategoriaRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }


    public boolean validarCategoriaExistente(String nombre) {
        return categoriaRepository.existeCategoria(nombre);
    }
}
