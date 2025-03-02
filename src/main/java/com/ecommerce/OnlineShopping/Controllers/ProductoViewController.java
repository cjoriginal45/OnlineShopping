
import org.springframework.ui.Model;
import com.ecommerce.OnlineShopping.Services.ProductService;
import com.ecommerce.OnlineShopping.models.Producto;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/product")
public class ProductoViewController {

    @Autowired
    private ProductService productService;
    
    @GetMapping("/{nombre}")
    public String getProductoHtml(@PathVariable String nombre, Model model) {
        // Buscar el producto por nombre
        Optional<Producto> producto = productService.obtenerPorNombre(nombre);
        
        if (producto.isPresent()) {
            model.addAttribute("producto", producto.get()); // Agrega el producto al modelo
            return "producto"; // Retorna la vista "producto.html"
        } else {
            return "error/404"; // Redirige a la página de error si no se encuentra el producto
        }
    }
}
