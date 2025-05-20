package ifrs.edu.br.models;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Dependente {

    @Id
    private String cpf;

    @Column(name = "data_nascimento", nullable = false)
    private LocalDate dataNascimento;

    @Column(nullable = false, length = 100)
    private String nome;

    private String profissao;

    @ManyToOne
    @JoinColumn(name = "id_funcionario")
    private Funcionario funcionario;

    public Dependente(String cpf, LocalDate dataNascimento, String nome, String profissao) {
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.nome = nome;
        this.profissao = profissao;
    }

    public Dependente() {
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getProfissao() {
        return profissao;
    }

    public void setProfissao(String profissao) {
        this.profissao = profissao;
    }

    @Override
    public String toString() {
        return "Dependente: \n" +
                "\tcpf: " + this.cpf + "\n" +
                "\tdataNascimento: " + this.dataNascimento.toString() + "\n" +
                "\tnome: " + this.nome + "\n" +
                "\tprofissao: " + this.profissao;
    }
}
