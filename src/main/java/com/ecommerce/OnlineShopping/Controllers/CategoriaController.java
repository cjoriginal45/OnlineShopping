
package com.ecommerce.OnlineShopping.Controllers;

import com.ecommerce.OnlineShopping.Services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {
    
    @Autowired
    private CategoriaService categoriaService;

    @GetMapping("/verificar")
    public boolean verificarCategoria(@RequestParam String nombre) {
        return categoriaService.validarCategoriaExistente(nombre);
    }
}
