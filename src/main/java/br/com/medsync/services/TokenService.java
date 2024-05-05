package br.com.medsync.services;

import br.com.medsync.dtos.LoginDTO;
import br.com.medsync.dtos.TokenDTO;
import br.com.medsync.models.Paciente;
import br.com.medsync.repositories.PacienteRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
public class TokenService {

    @Autowired
    PacienteRepository pacienteRepository;

    @Value("${jwt.secret}")
    String secret;

    public TokenDTO generateToken(LoginDTO credencial) {
        Algorithm alg = Algorithm.HMAC256(secret);
        var token = JWT.create()
                .withSubject(credencial.cpf())
                .withExpiresAt(Instant.now().plus(1, ChronoUnit.HOURS))
                .withIssuer("MedSync")
                .sign(alg);

        return new TokenDTO(token, "JWT", "Bearer");
    }

    public Paciente valideAndGetUserBy(String token) {
        Algorithm alg = Algorithm.HMAC256(secret);
        var cpf =  JWT.require(alg)
                .withIssuer("MedSync")
                .build()
                .verify(token)
                .getSubject()
                ;

        return pacienteRepository.buscarCpf(cpf)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }
}
