package ifrs.edu.com.models;

import java.time.LocalDate;

public class Livro extends Publicacao {
    private int paginas;
    private String[] autores;

    Livro(String titulo, LocalDate data, int paginas, String... autores) {
        super(titulo, data);

        this.paginas = paginas;
        this.autores = autores;
    }

    public String[] getAutores() {
        return autores;
    }

    public int getPaginas() {
        return paginas;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj)
                && obj instanceof Livro
                && ((Livro) obj).getPaginas() == this.getPaginas()
                && ((Livro) obj).getAutores().equals(this.getAutores());
    }

    @Override
    public String toString() {
        return String.format("Livro: %s (%d) - %d paginas - Autores: %s",
                getTitulo(), getData().getYear(),
                getPaginas(), getAutores().toString());
    }
}
