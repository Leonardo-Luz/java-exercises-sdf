package ifrs.edu.br.models;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "empregado")
public class Funcionario {

    @Id
    @Column(name = "codigo")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "horas_extras")
    private int horasExtras;

    @Column(name = "data_nascimento", unique = false, nullable = false)
    private LocalDate dataNascimento;

    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @Column(name = "end_email", unique = true, nullable = false, length = 150)
    private String email;

    public Funcionario(int id, int horasExtras, LocalDate dataNascimento, String nome, String email) {
        this.id = id;
        this.horasExtras = horasExtras;
        this.dataNascimento = dataNascimento;
        this.nome = nome;
        this.email = email;
    }

    public Funcionario(int horasExtras, LocalDate dataNascimento, String nome, String email) {
        this.horasExtras = horasExtras;
        this.dataNascimento = dataNascimento;
        this.nome = nome;
        this.email = email;
    }

    public Funcionario() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHorasExtras() {
        return horasExtras;
    }

    public void setHorasExtras(int horasExtras) {
        this.horasExtras = horasExtras;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Funcionário: \n" +
                "\tid: " + this.id + "\n" +
                "\thorasExtras: " + this.horasExtras + "\n" +
                "\tdataNascimento: " + this.dataNascimento.toString() + "\n" +
                "\tnome: " + this.nome.toString() + "\n" +
                "\temail: " + this.email.toString();
    }
}
