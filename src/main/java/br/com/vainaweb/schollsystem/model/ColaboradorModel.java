package br.com.vainaweb.schollsystem.model;

import br.com.vainaweb.schollsystem.dto.DadosAtualizados;
import br.com.vainaweb.schollsystem.dto.EnderecoDTO;
import br.com.vainaweb.schollsystem.enums.Cargo;
import jakarta.validation.Valid;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.br.CPF;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity// Trata a classe como uma entidade
@Table(name = "tb_colaboradores") // Diz que essa entidade é uma tabela

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ColaboradorModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Email // Define a coluna como um email
    @Column(unique = true) // Define a coluna como unica
    private String email;

    @CPF // Define a coluna como um CPF
    @Column(unique = true) // Define a coluna como unica
    private String cpf;

    private Cargo cargo;

    @Embedded // Diz que esse atributo é incorporavel
    private Endereco endereco;

    public ColaboradorModel(String nome, String email, String cpf, Cargo cargo, @Valid EnderecoDTO endereco) {
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.cargo = cargo;
        this.endereco = new Endereco(endereco.cep(), endereco.logradouro(), endereco.bairro(), endereco.cidade(),
                endereco.complemento(), endereco.uf(), endereco.numero());
    }

    public void atualizarInfo(DadosAtualizados dados) {
        this.nome = dados.nome() != null ? dados.nome() : this.nome;
        this.email = dados.email();
    }
}