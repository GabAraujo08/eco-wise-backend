package org.example.dao.usuariodao.tarefadao;

import org.example.entities.tarefa.Tarefa;
import org.example.entities.usuario.Usuario;
import org.example.exceptions.tarefa.TarefaNotFoundException;
import org.example.exceptions.tarefa.TarefaNotSavedException;
import org.example.exceptions.usuario.UsuarioNotFoundException;
import org.example.exceptions.usuario.UsuarioNotSavedException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface TarefaDao {

    Tarefa create(Tarefa tarefa, Connection connection) throws SQLException, TarefaNotSavedException;

    List<Tarefa> readAll() ;

    Tarefa update(Tarefa tarefa, Connection connection) throws SQLException, TarefaNotFoundException;

    void delete(Long id, Connection connection) throws TarefaNotFoundException, SQLException;

}
