
package com.ecommerce.OnlineShopping.DTO;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


public class CompraDTO {
    private Integer productoId;
    private String direccion;
    private String codigoPostal;
    private String numeroTarjeta;
    private String cvv;
    private String  fechaVencimiento;

    public CompraDTO() {
    }

    public CompraDTO(Integer productoId, String direccion, String codigoPostal, String numeroTarjeta, String cvv, String fechaVencimiento) {
        this.productoId = productoId;
        this.direccion = direccion;
        this.codigoPostal = codigoPostal;
        this.numeroTarjeta = numeroTarjeta;
        this.cvv = cvv;
        this.fechaVencimiento = fechaVencimiento;
    }

    public CompraDTO(String direccion, String codigoPostal, String numeroTarjeta, String cvv, String fechaVencimiento) {
        this.direccion = direccion;
        this.codigoPostal = codigoPostal;
        this.numeroTarjeta = numeroTarjeta;
        this.cvv = cvv;
        this.fechaVencimiento = fechaVencimiento;
    }


    public Integer getProductoId() {
        return productoId;
    }

    public void setProductoId(Integer productpId) {
        this.productoId = productpId;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public void setNumeroTarjeta(String numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(String fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }
    
    // Validación de la fecha de vencimiento
    public boolean isExpirationDateValid() {
       if (fechaVencimiento == null || fechaVencimiento.trim().isEmpty()) {
            return false;
        }

        try {
            // Parsear la fecha de vencimiento (formato MM/YY)
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yy");
            YearMonth expiration = YearMonth.parse(fechaVencimiento, formatter);

            // Verificar si la fecha de vencimiento es posterior al mes actual
            YearMonth current = YearMonth.now();
            return !expiration.isBefore(current);
        } catch (DateTimeParseException e) {
            // Si hay un error al parsear, la fecha es inválida
            return false;
        }

    }

    // Validación del CVV
    public boolean isCvvValid() {
        return this.cvv != null && (this.cvv.length() == 3 || this.cvv.length() == 4);
    }
    
    public boolean isValidCard() {
        // Verificar que la longitud sea 16 dígitos
        if (numeroTarjeta == null || numeroTarjeta.length() != 16) {
            return false;
        }

        // Aplicar el algoritmo de Luhn
        int sum = 0;
        boolean alternate = false;
        for (int i = numeroTarjeta.length() - 1; i >= 0; i--) {
            int digit = Character.getNumericValue(numeroTarjeta.charAt(i));
            if (alternate) {
                digit *= 2;
                if (digit > 9) {
                    digit = (digit % 10) + 1;
                }
            }
            sum += digit;
            alternate = !alternate;
        }

        // La tarjeta es válida si la suma es múltiplo de 10
        return (sum % 10 == 0);
    }
    
    
    
    


    
}
