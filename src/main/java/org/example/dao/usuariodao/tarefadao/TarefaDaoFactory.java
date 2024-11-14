package org.example.dao.usuariodao.tarefadao;

import org.example.dao.usuariodao.UsuarioDao;
import org.example.dao.usuariodao.UsuarioDaoImpl;

public class TarefaDaoFactory {
    private TarefaDaoFactory() {
    }

    public static TarefaDao create(){
        return new TarefaDaoImpl();
    }
}
