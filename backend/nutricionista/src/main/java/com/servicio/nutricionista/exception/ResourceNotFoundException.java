package com.servicio.nutricionista.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String resourceName, String fieldName, Long fieldValue) {
        super(resourceName + " no encontrado con " + fieldName + ": " + fieldValue);
    }
}
