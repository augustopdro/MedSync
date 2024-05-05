package br.com.medsync.repositories;

import br.com.medsync.models.Prontuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProntuarioRepository extends JpaRepository<Prontuario, Long> {

    @Query("SELECT p FROM Prontuario p WHERE p.id = :pacienteId")
    Page<Prontuario> getAllRegisters(long pacienteId , Pageable pageable);
}
