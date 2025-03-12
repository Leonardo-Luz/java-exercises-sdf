package ifrs.edu.com.models;

import java.time.LocalDate;

public class Revista extends Publicacao {
    private int edicao;
    private String editora;

    Revista(String titulo, LocalDate data, int edicao, String editora) {
        super(titulo, data);

        this.edicao = edicao;
        this.editora = editora;
    }

    public int getEdicao() {
        return edicao;
    }

    public String getEditora() {
        return editora;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return String.format("Revista: %s (%d/%d) - editora %s - edicao n:%d", getTitulo(), getData().getMonthValue(),
                getData().getYear(), getEditora(), getEdicao());
    }
}
