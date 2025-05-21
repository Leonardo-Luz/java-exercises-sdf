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

    public Dependente(String cpf, LocalDate dataNascimento, String nome, String profissao, Funcionario funcionario) {
        this.setCpf(cpf);
        this.dataNascimento = dataNascimento;
        this.nome = nome;
        this.profissao = profissao;
        this.funcionario = funcionario;
    }

    public Dependente() {
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        if (!cpfIsValid(cpf))
            throw new RuntimeException("Cpf inválido");

        this.cpf = cpf;
    }

    private boolean cpfIsValid(String cpf) {
        int first = 0;
        for (int i = 8; i >= 0; i--)
            first += Character.getNumericValue(cpf.charAt(i)) * ((8 - i) + 2);

        if (first % 11 < 2)
            first = 0;
        else
            first = 11 - (first % 11);

        if (first != Character.getNumericValue(cpf.charAt(9)))
            return false;

        int second = 0;
        for (int i = 9; i >= 0; i--)
            second += Character.getNumericValue(cpf.charAt(i)) * ((9 - i) + 2);

        if (second % 11 < 2)
            second = 0;
        else
            second = 11 - (second % 11);

        if (second != Character.getNumericValue(cpf.charAt(10)))
            return false;

        return true;
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

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
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
