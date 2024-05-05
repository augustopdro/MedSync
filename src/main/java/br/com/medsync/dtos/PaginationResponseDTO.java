package br.com.medsync.dtos;

import br.com.medsync.models.Prontuario;

import java.util.List;

public record PaginationResponseDTO(
        List<Prontuario> content,
        int number,
        long totalElements,
        int totalPages,
        boolean first,
        boolean last
) {}
