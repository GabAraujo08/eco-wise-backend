package org.example.entities.empresa;

public class Empresa {

    private Long id;
    private String nome;
    private String cnpj;
    private String senha;


    public Empresa(Long id, String cnpj, String nome, String senha) {
        this.id = id;
        this.cnpj = cnpj;
        this.nome = nome;
        this.senha = senha;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
