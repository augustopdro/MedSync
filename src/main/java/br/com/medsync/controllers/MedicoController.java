package br.com.medsync.controllers;

import br.com.medsync.models.Paciente;
import br.com.medsync.services.MedicoService;
import br.com.medsync.services.PacienteService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.parser.Entity;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/medico")
public class MedicoController {

    private MedicoService medicoService;

    @Autowired
    public MedicoController(MedicoService medicoService) {
        this.medicoService = medicoService;
    }
    Logger log = LoggerFactory.getLogger(MedicoController.class);

    @PostMapping("fichaMedica")
    public Paciente buscarPaciente(@Valid @RequestBody String cpf){
        log.info("Buscando paciente");

        return buscarPaciente(cpf);
    }


}
