package br.com.medsync.errors;

public record RestValidationError(
        Integer code,
        String field,
        String message
) {}
