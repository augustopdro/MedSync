package br.com.medsync.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Prontuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false)
    private String queixa;

    @NotNull
    @Column(nullable = false)
    private String duracao;

    @NotNull
    @Column(nullable = false)
    private String descricao;

    @NotNull
    @Column(nullable = false)
    private String examefisico;

    @NotNull
    @Column(nullable = false)
    private String conduta;

    @ManyToOne
    private Paciente paciente;
}
