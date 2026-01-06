package com.fc.apibanco.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ApiKeyRequest {
    
    @NotBlank(message = "El consumidor es obligatorio")
    private String consumidor;
    
    private boolean lectura;
    private boolean escritura; 
    private boolean actualizacion;
    private boolean eliminacion;
}
