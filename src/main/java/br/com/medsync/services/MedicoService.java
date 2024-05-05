package br.com.medsync.services;

import br.com.medsync.models.Paciente;
import br.com.medsync.repositories.MedicoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class MedicoService {

    Logger log = LoggerFactory.getLogger(MedicoService.class);

    private MedicoRepository repository;

    @Autowired
    public MedicoService(MedicoRepository repository){
        this.repository = repository;
    }

    public Paciente buscarPaciente(String cpf){
        log.info("Buscando paciente.");
        return repository.buscarPaciente(cpf);
    }
}
