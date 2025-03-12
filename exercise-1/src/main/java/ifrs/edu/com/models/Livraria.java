package ifrs.edu.com.models;

import java.util.ArrayList;

public class Livraria {
    private String nome;
    private ArrayList<Impressao> estoque;

    Livraria(String nome) {
        this.nome = nome;
        this.estoque = new ArrayList<>();
    }
}
