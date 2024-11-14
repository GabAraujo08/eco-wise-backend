package org.example.service.tarefa;

import org.example.entities.tarefa.Tarefa;
import org.example.exceptions.tarefa.TarefaNotFoundException;
import org.example.exceptions.tarefa.TarefaNotSavedException;


import java.sql.SQLException;
import java.util.List;

public interface TarefaService {

    Tarefa create(Tarefa tarefa) throws TarefaNotSavedException, SQLException;

    List<Tarefa> readAll() ;

    Tarefa update(Tarefa tarefa) throws SQLException, TarefaNotFoundException;

    void delete(Long id) throws SQLException, TarefaNotFoundException;
}
