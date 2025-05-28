package ifrs.edu.br.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

/**
 * Projeto
 */
@Entity
public class Projeto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 100, nullable = false, unique = true)
    private String nome;

    @Column(name = "data_criacao", nullable = false)
    private LocalDate dataCriacao;

    @Column(name = "data_fim", nullable = true)
    private LocalDate dataFim;

    @ManyToMany
    @JoinTable(name = "Projeto_Empregado", joinColumns = @JoinColumn(name = "projeto_id"), inverseJoinColumns = @JoinColumn(name = "empregado_id"))
    private List<Funcionario> participantes;

    public Projeto(int id, String nome, LocalDate dataCriacao, LocalDate dataFim) {
        this.id = id;
        this.nome = nome;
        this.dataCriacao = dataCriacao;
        this.dataFim = dataFim;
        this.participantes = new ArrayList<>();
    }

    public Projeto(String nome, LocalDate dataCriacao) {
        this.nome = nome;
        this.dataCriacao = dataCriacao;
        this.participantes = new ArrayList<>();
    }

    public Projeto() {
    }

    public void addParticipante(Funcionario funcionario) {
        this.participantes.add(funcionario);
    }

    public void complete() {
        this.dataFim = LocalDate.now();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    public List<Funcionario> getParticipantes() {
        return participantes;
    }

    public void setParticipantes(List<Funcionario> participantes) {
        this.participantes = participantes;
    }

    @Override
    public String toString() {
        String participantesString = this.participantes.stream()
                .map((funcionario) -> "\t\tfuncionario: " + funcionario.getNome() + "\n")
                .collect(Collectors.joining(""));

        return "Projeto: \n" +
                "\tid: " + this.id + "\n" +
                "\tnome: " + this.nome + "\n" +
                "\tdataCriacao: " + this.dataCriacao.toString() + "\n" +
                "\tdataFim: " + ( this.dataFim != null ? this.dataFim : "Projeto Não finalizado" ) + "\n" +
                ((this.participantes != null && this.participantes.size() > 0)
                        ? "\n\tparticipantes: \n" + participantesString
                        : "");
    }
}
