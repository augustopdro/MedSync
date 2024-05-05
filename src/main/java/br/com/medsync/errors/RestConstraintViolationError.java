package br.com.medsync.errors;

public record RestConstraintViolationError(
        int code,
        Object field,
        String message
) {}
