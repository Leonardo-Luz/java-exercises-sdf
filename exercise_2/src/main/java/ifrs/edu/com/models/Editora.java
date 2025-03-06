package ifrs.edu.com.models;

import java.util.List;

public class Editora {
    private int id;
    private String nome;
    private String endereco;
    private List<Livro> livros;

    public Editora(int id, String nome, String endereco, List<Livro> livros) {
        setId(id);
        setNome(nome);
        setEndereco(endereco);
    }

    public String getNome() {
        return nome;
    }

    public int getId() {
        return id;
    }

    public String getEndereco() {
        return endereco;
    }

    public List<Livro> getLivros() {
        return livros;
    }

    public void setNome(String nome) {
        // TODO: VALIDATION

        this.nome = nome;
    }

    public void setId(int id) {
        // TODO: VALIDATION

        this.id = id;
    }

    public void setEndereco(String endereco) {
        // TODO: VALIDATION

        this.endereco = endereco;
    }

    public void setLivros(List<Livro> livros) {
        // TODO: VALIDATION

        this.livros = livros;
    }
}
