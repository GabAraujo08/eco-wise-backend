package org.example.service.tarefa;

import org.example.config.DatabaseConnectionFactory;
import org.example.dao.usuariodao.UsuarioDao;
import org.example.dao.usuariodao.UsuarioDaoFactory;
import org.example.dao.usuariodao.tarefadao.TarefaDao;
import org.example.dao.usuariodao.tarefadao.TarefaDaoFactory;
import org.example.entities.tarefa.Tarefa;
import org.example.exceptions.tarefa.TarefaNotFoundException;
import org.example.exceptions.tarefa.TarefaNotSavedException;
import org.example.exceptions.usuario.UsuarioNotSavedException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class TarefaServiceImpl implements TarefaService {
    private final TarefaDao dao = TarefaDaoFactory.create();

    @Override
    public Tarefa create(Tarefa tarefa) throws TarefaNotSavedException, SQLException {
        Connection connection = DatabaseConnectionFactory.create().get();
        try{
            tarefa = this.dao.create(tarefa, connection);
            connection.commit();
            return tarefa;
        }catch (SQLException | TarefaNotSavedException e){
            connection.rollback();
            throw e;
        }
    }

    @Override
    public List<Tarefa> readAll() {
        return this.dao.readAll();
    }

    @Override
    public Tarefa update(Tarefa tarefa) throws SQLException, TarefaNotFoundException {
        return null;
    }

    @Override
    public void delete(Long id) throws SQLException, TarefaNotFoundException {

    }
}
