package org.example.service.tarefa;

public class TarefaServiceFactory {
    private TarefaServiceFactory() {
    }

    public static TarefaService create(){
        return new TarefaServiceImpl();
    }
}
