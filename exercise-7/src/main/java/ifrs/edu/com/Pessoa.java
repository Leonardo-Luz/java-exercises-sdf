package ifrs.edu.com;

import java.time.LocalDate;

public class Pessoa {
    private String nome;
    private int id;
    private LocalDate dataNasc;

    public Pessoa(String nome, LocalDate dataNasc) {
        this.nome = nome;
        this.dataNasc = dataNasc;
    }

    public Pessoa(String nome, int id, LocalDate dataNasc) {
        this.nome = nome;
        this.id = id;
        this.dataNasc = dataNasc;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(LocalDate dataNasc) {
        this.dataNasc = dataNasc;
    }

    @Override
    public int hashCode() {
        int result = nome.hashCode();
        result = 31 * result + id;
        result = 31 * result + dataNasc.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Pessoa pessoa = (Pessoa) o;

        if (id != pessoa.id)
            return false;
        if (!nome.equals(pessoa.nome))
            return false;
        return dataNasc.equals(pessoa.dataNasc);
    }

    @Override
    public String toString() {
        return "Pessoa{" +
                "nome='" + nome + '\'' +
                ", id=" + id +
                ", dataNasc=" + dataNasc +
                '}';
    }
}
