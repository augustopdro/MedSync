package br.com.medsync.services;

import br.com.medsync.dtos.PaginationResponseDTO;
import br.com.medsync.repositories.ProntuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class HistoricoService {

    Logger log = LoggerFactory.getLogger(HistoricoService.class);
    private ProntuarioRepository prontuarioRepository;

    @Autowired
    public HistoricoService(ProntuarioRepository listaRepository) {
        this.prontuarioRepository = listaRepository;
    }

    public PaginationResponseDTO recuperarHistorico(long userId, Pageable pageable) {
        log.info("Buscando historico de listas do usuário: " + userId);

        var listas = prontuarioRepository.getAllRegisters(userId, pageable);

        return new PaginationResponseDTO(
                listas.getContent(),
                listas.getNumber(),
                listas.getTotalElements(),
                listas.getTotalPages(),
                listas.isFirst(),
                listas.isLast()
        );
    }
}
