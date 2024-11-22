package org.example.entities.usuario;



public class Usuario {
    private Long id;
    private String nome;
    private String email;
    private String senha;
    private Integer pontos;
    private Long empresaId;


    public Usuario(Long id, String nome, String email, String senha, Integer pontos, Long empresaId) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        if (verificaSenha(senha)) {
            this.senha = senha;
        }else{
            throw new IllegalArgumentException("Senha inválida");
        }
        this.pontos = pontos;
        this.empresaId = empresaId;
    }

    public Long getEmpresaId() {
        return empresaId;
    }

    public void setEmpresaId(Long empresaId) {
        this.empresaId = empresaId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        if (verificaSenha(senha)) {
            this.senha = senha;
        }else {
            throw new IllegalArgumentException("Senha inválida");
        }
    }

    public Integer getPontos() {
        return pontos;
    }

    public void setPontos(Integer pontos) {
        this.pontos = pontos;
    }

    private Boolean verificaSenha(String senha){
        return this.senha.length() >= 8;
    }
}
