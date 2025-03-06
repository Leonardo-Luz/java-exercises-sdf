package ifrs.edu.com.models;

import java.sql.Date;

public class Autor {
    private String cpf;
    private String nome;
    private Date dataNasc;

    public Autor(String cpf, String nome, Date dataNasc) {
        setCpf(cpf);
        setNome(nome);
        setDataNasc(dataNasc);
    }

    public String getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public Date getDataNasc() {
        return dataNasc;
    }

    public void setCpf(String cpf) {
        // TODO: VALIDATION

        this.cpf = cpf;
    }

    public void setNome(String nome) {
        // TODO: VALIDATION

        this.nome = nome;
    }

    public void setDataNasc(Date dataNasc) {
        // TODO: VALIDATION

        this.dataNasc = dataNasc;
    }
}
