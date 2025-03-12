package ifrs.edu.com.models;

import java.time.LocalDate;

public abstract class Publicacao {
    private String titulo;
    private LocalDate data;

    Publicacao(String titulo, LocalDate data) {
        this.titulo = titulo;
        this.data = data;
    }

    public LocalDate getData() {
        return data;
    }

    public String getTitulo() {
        return titulo;
    }
}
