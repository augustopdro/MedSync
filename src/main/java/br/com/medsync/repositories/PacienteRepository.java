package br.com.medsync.repositories;

import br.com.medsync.models.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    @Query("SELECT u FROM Paciente u WHERE u.email = ?1 AND u.senha = ?2")
    Optional<Paciente> buscarCredenciais(String email, String senha);

    @Query("SELECT u FROM Paciente u WHERE u.email = ?1")
    Optional<Paciente> buscarEmail(String email);
}
