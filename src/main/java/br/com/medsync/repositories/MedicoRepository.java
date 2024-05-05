package br.com.medsync.repositories;


import br.com.medsync.models.Medico;
import br.com.medsync.models.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long> {

    @Query("SELECT u FROM Paciente u WHERE u.cpf = ?1")
    Paciente buscarPaciente(String cpf);
}
