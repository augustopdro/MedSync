package br.com.medsync.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Paciente implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$", message = "Email só pode conter caracteres alfanuméricos e os especiais: _ . - @")
    @Column(nullable = false, unique = true)
    private String email;

    @NotNull
    @NotBlank
    @Size(min = 8, max = 60, message = "Senha deve ter tamanho entre 8 e 60 caracteres")
    @Column(nullable = false)
    private String senha;

    @NotNull
    @Pattern(regexp = "^(?=.*[a-zA-Z])[a-zA-Z\s]{2,}$", message = "Nome deve conter no mínimo 2 caracteres e nenhum pode ser numérico")
    @Column(nullable = false)
    private String nome;

    @NotNull
    @Column(nullable = false)
    private String dataDeNascimento;

    @NotNull
    @Column(nullable = false)
    private String alergias;

    @NotNull
    @Column(nullable = false)
    private String medicacoes;

    @NotNull
    @Column(nullable = false)
    private String comorbidades;

    @NotNull
    @Column(nullable = false, unique = true)
    private String cpf;

    @ManyToMany
    @JoinTable(
            name = "medico_paciente",
            joinColumns = @JoinColumn(name = "paciente_id"),
            inverseJoinColumns = @JoinColumn(name = "medico_id")
    )
    private List<Medico> medicos;

    @OneToMany
    private List<Prontuario> prontuarios;


    public Paciente(String cpf, String senha) {
        if(!setEmail(email))
            throw new IllegalArgumentException("Cpf inválido");

        if(!setSenha(senha))
            throw new IllegalArgumentException("Senha inválida");
    }

    public boolean setEmail(String novoEmail) {
        if(validarEmail(novoEmail))
        {
            email = novoEmail;
            return true;
        }

        return false;
    }

    public boolean setSenha(String novaSenha) {
        if(validarSenha(novaSenha))
        {
            senha = novaSenha;
            return true;
        }

        return false;
    }

    private boolean validarSenha(String senha)
    {
        return senha.length() >= 8;
    }

    private boolean validarEmail(String email)
    {
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        java.util.regex.Pattern padrao = java.util.regex.Pattern.compile(regex);

        return padrao.matcher(email).matches();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USUARIO"));
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
