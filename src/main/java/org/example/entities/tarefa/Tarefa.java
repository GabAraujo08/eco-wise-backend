package org.example.entities.tarefa;

public class Tarefa {
    private Long id;
    private String nome;
    private String descricao;
    private Integer pontos;



    public Tarefa(Long id, String nome, String descricao, Integer pontos) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.pontos = pontos;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getPontos() {
        return pontos;
    }

    public void setPontos(Integer pontos) {
        this.pontos = pontos;
    }


}


