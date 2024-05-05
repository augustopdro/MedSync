package br.com.medsync.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    private String crm;

    @NotNull
    @Pattern(regexp = "^(?=.*[a-zA-Z])[a-zA-Z\s]{2,}$", message = "Nome deve conter no mínimo 3 caracteres e nenhum pode ser numérico")
    @Column(nullable = false)
    private String nome;

    @NotNull
    @NotBlank
    @Size(min = 8, max = 60, message = "Senha deve ter tamanho entre 8 e 60 caracteres")
    @Column(nullable = false)
    private String senha;

    @ManyToMany(mappedBy = "medicos")
    private List<Paciente> pacientes;

    @ManyToMany(mappedBy = "medicos")
    private List<Instituicao> instituicoes;

}
