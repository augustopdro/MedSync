package br.com.medsync.services;

import br.com.medsync.dtos.LoginDTO;
import br.com.medsync.dtos.TokenDTO;
import br.com.medsync.exceptions.RestNotFoundException;
import br.com.medsync.models.Paciente;
import br.com.medsync.repositories.PacienteRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PacienteService {
    Logger log = LoggerFactory.getLogger(PacienteService.class);

    private PacienteRepository repository;
    private AuthenticationManager manager;
    private PasswordEncoder encoder;
    private TokenService tokenService;

    @Autowired
    public PacienteService(PacienteRepository repository, AuthenticationManager manager, PasswordEncoder encoder, TokenService tokenService) {
        this.repository = repository;
        this.manager = manager;
        this.encoder = encoder;
        this.tokenService = tokenService;
    }

    public Paciente cadastrar(Paciente usuario)
    {
        log.info("Cadastrando paciente.");
        usuario.setSenha(encoder.encode(usuario.getSenha()));
        repository.save(usuario);

        return repository.save(usuario);
    }


    public Paciente recuperar(long id) {
        log.info("Recuperando cadastro de paciente pelo id: " + id);

        Paciente paciente = repository
                .findById(id)
                .orElseThrow(() -> new RestNotFoundException("Usuario não encontrado"));

        return paciente;
    }

    public TokenDTO logar(LoginDTO credenciais) {
        manager.authenticate(credenciais.toAuthentication());

        return tokenService.generateToken(credenciais);
    }
}
