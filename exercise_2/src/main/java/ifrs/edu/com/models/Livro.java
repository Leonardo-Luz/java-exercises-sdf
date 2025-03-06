package ifrs.edu.com.models;

import java.util.List;

public class Livro {
    private int id;
    private String titulo;
    private int anoLancamento;
    private Editora editora;
    private List<Autor> autores;

    public Livro(int id, String titulo, int anoLancamento, Editora editora, List<Autor> autores) {
        setAnoLancamento(anoLancamento);
        setEditora(editora);
        setTitulo(titulo);
        setId(id);
        setAutores(autores);
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public Editora getEditora() {
        return editora;
    }

    public int getAnoLancamento() {
        return anoLancamento;
    }

    public List<Autor> getAutores() {
        return autores;
    }

    public void setId(int id) {
        // TODO: VALIDATION

        this.id = id;
    }

    public void setTitulo(String titulo) {
        // TODO: VALIDATION

        this.titulo = titulo;
    }

    public void setEditora(Editora editora) {
        // TODO: VALIDATION

        this.editora = editora;
    }

    public void setAnoLancamento(int anoLancamento) {
        // TODO: VALIDATION

        this.anoLancamento = anoLancamento;
    }

    public void setAutores(List<Autor> autores) {
        // TODO: VALIDATION

        this.autores = autores;
    }
}
