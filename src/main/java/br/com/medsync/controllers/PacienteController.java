package br.com.medsync.controllers;


import br.com.medsync.dtos.LoginDTO;
import br.com.medsync.dtos.TokenDTO;
import br.com.medsync.dtos.UsuarioResponseDTO;
import br.com.medsync.models.Paciente;
import br.com.medsync.services.PacienteService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/paciente")
public class PacienteController {
    private PacienteService pacienteService;


    @Autowired
    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }
    Logger log = LoggerFactory.getLogger(PacienteController.class);

    @PostMapping("cadastrar")
    public ResponseEntity<EntityModel<UsuarioResponseDTO>> cadastrar(@Valid @RequestBody Paciente paciente)
    {
        var usuarioCadastrado = pacienteService.cadastrar(paciente);

        var responseDTO = new UsuarioResponseDTO(usuarioCadastrado.getId(), usuarioCadastrado.getEmail(), usuarioCadastrado.getSenha());

        var entityModel = EntityModel.of(
                responseDTO,
                linkTo(methodOn(PacienteController.class).cadastrar(paciente)).withSelfRel(),
                linkTo(methodOn(PacienteController.class).logar(new LoginDTO(usuarioCadastrado.getEmail(), usuarioCadastrado.getSenha()))).withRel("logar")
        );

        return ResponseEntity.created(linkTo(methodOn(PacienteController.class).cadastrar(paciente)).toUri())
                .body(entityModel);
    }


    @PostMapping("login")
    public EntityModel<TokenDTO> logar(@Valid @RequestBody LoginDTO credenciais)
    {
        log.info("solicitando validação das credenciais informadas");

        TokenDTO responseService = pacienteService.logar(credenciais);

        return EntityModel.of(
                responseService,
                linkTo(methodOn(PacienteController.class).logar(credenciais)).withSelfRel(),
                linkTo(methodOn(PacienteController.class).cadastrar(new Paciente())).withRel("cadastrar")
        );
    }

}
