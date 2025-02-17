
package com.ecommerce.OnlineShopping.Services;
import com.ecommerce.OnlineShopping.Repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {
    
    @Autowired
    private CategoriaRepository categoriaRepository;

    public boolean validarCategoriaExistente(String nombre) {
        return categoriaRepository.existeCategoria(nombre);
    }
}
