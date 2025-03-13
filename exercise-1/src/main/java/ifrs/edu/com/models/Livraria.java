package ifrs.edu.com.models;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Livraria {
    private String nome;
    private ArrayList<Impressao> estoque;

    Livraria(String nome) {
        this.nome = nome;
        this.estoque = new ArrayList<>();
    }

    public int getExemplares(Publicacao publicacao) {
        return (int) estoque.stream()
                .filter(impressao -> impressao.getPublicacao().equals(publicacao))
                .count();
    }

    public String getNome() {
        return nome;
    }

    public Impressao vende(Publicacao publicacao) {
        Optional<Impressao> optionalImpressao = estoque.stream()
                .filter(impressao -> impressao.getPublicacao().equals(publicacao))
                .findFirst();

        if (optionalImpressao.isPresent())
            estoque.remove(optionalImpressao.get());

        return optionalImpressao.isPresent() ? optionalImpressao.get() : null;
    }

    public void addEstoque(Publicacao publicacao) {
        estoque.add(new Impressao(publicacao));
    }

    public void addEstoque(Publicacao publicacao, int qty) {
        estoque.addAll(IntStream.range(0, qty)
                .mapToObj(i -> new Impressao(publicacao))
                .collect(Collectors.toList()));
    }
}
