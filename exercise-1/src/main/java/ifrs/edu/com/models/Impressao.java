package ifrs.edu.com.models;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Impressao {
    private LocalDate data;
    private Publicacao publicacao;
    private String cod;

    private static int qtyImpressa = 0;

    Impressao(Publicacao publicacao) {
        this.data = LocalDate.now();
        this.publicacao = publicacao;

        this.cod = String.format("%s%d", publicacao instanceof Livro ? "L" : "R", Impressao.qtyImpressa);
    }

    public LocalDate getData() {
        return data;
    }

    public String getCod() {
        return cod;
    }

    public Publicacao getPublicacao() {
        return publicacao;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Impressao && ((Impressao) obj).getCod() == this.getCod();
    }

    @Override
    public String toString() {
        return String.format("exemplar: %s| Data impressao: %s\n%s",
                getCod(),
                getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                getPublicacao().toString());
    }
}
