package br.com.thiaguten.spring.model;

import br.com.thiaguten.spring.model.base.AbstractModel;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "USUARIO")
public class Usuario extends AbstractModel {

    private static final long serialVersionUID = -5467564907381165L;

    @Transient
//	@Version
//	@Column(name = "VERSION")
    private long version;

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "{NotBlank.usuario.nome}")
    @Column(name = "NOME", nullable = false, length = 100)
    private String nome;

    @NotNull(message = "{NotNull.usuario.idade}")
    @Digits(integer = 3, fraction = 0, message = "{Digits.usuario.idade}")
    @Column(name = "IDADE", nullable = false)
    private Integer idade;

    @NotBlank(message = "{NotBlank.usuario.email}")
    @Email(message = "{Email.usuario.email}")
    @Column(name = "EMAIL", nullable = false, unique = true, length = 50)
    private String email;

    public Usuario() {

    }

    public Usuario(String nome, Integer idade, String email) {
        super();
        this.nome = nome;
        this.idade = idade;
        this.email = email;
    }

    @Override
    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Usuario [version=" + version + ", id=" + id + ", nome=" + nome + ", idade=" + idade + ", email=" + email + "]";
    }

}
