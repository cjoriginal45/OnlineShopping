
package com.ecommerce.OnlineShopping.models;

import java.util.HashMap;
import java.util.Map;


public class Configuration {
     // Mapa que almacena los parámetros de configuración
    private Map<String, String> parametros;
    
    // Instancia única de la clase Configuracion (Singleton)
    private static Configuration instancia;
    
    // Constructor privado para evitar instanciación externa
    private Configuration() {
        parametros = new HashMap<>();
    }
    
    // Método público para obtener la instancia única de Configuracion
    public static Configuration getInstancia() {
        if (instancia == null) {
            instancia = new Configuration();
        }
        return instancia;
    }
    
    // Método para obtener el valor de un parámetro dado su clave
    public String getParametro(String clave) {
        return parametros.get(clave);
    }
    
    // Método para establecer el valor de un parámetro dado su clave y valor
    public void setParametro(String clave, String valor) {
        parametros.put(clave, valor);
    }
}
