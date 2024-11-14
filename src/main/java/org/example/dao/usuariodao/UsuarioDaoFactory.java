package org.example.dao.usuariodao;

public class UsuarioDaoFactory {
    private UsuarioDaoFactory() {
    }

    public static UsuarioDao create(){
        return new UsuarioDaoImpl();
    }
}
