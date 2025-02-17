
package com.ecommerce.OnlineShopping.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HeaderController {
     @GetMapping("/header")
    public String getHeader() {
        return "header";  // Spring buscará header.html en templates/
    }
}
