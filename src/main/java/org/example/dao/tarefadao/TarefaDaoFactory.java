package org.example.dao.tarefadao;

public class TarefaDaoFactory {
    private TarefaDaoFactory() {
    }

    public static TarefaDao create(){
        return new TarefaDaoImpl();
    }
}
