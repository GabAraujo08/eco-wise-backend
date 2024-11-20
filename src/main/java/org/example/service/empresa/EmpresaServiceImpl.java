package org.example.service.empresa;

import org.example.config.DatabaseConnectionFactory;
import org.example.dao.empresadao.EmpresaDao;
import org.example.dao.empresadao.EmpresaDaoFactory;
import org.example.dao.tarefadao.TarefaDao;
import org.example.dao.tarefadao.TarefaDaoFactory;
import org.example.entities.empresa.Empresa;
import org.example.exceptions.empresa.EmpresaNotFoundException;
import org.example.exceptions.empresa.EmpresaNotSavedException;
import org.example.exceptions.tarefa.TarefaNotSavedException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class EmpresaServiceImpl implements EmpresaService {

    private final EmpresaDao dao = EmpresaDaoFactory.create();
    @Override
    public Empresa create(Empresa empresa) throws EmpresaNotSavedException, SQLException {
        Connection connection = DatabaseConnectionFactory.create().get();
        try{
            empresa = this.dao.create(empresa, connection);
            connection.commit();
            return empresa;
        }catch (SQLException | EmpresaNotSavedException e){
            connection.rollback();
            throw e;
        }
    }

    @Override
    public List<Empresa> readAll() {
        return this.dao.readAll();
    }

    @Override
    public Empresa update(Empresa empresa) throws SQLException, EmpresaNotFoundException {
        Connection connection = DatabaseConnectionFactory.create().get();
        empresa = this.dao.update(empresa, connection);
        connection.commit();
        return empresa;
    }

    @Override
    public void delete(Long id) throws SQLException, EmpresaNotFoundException {
        Connection connection = DatabaseConnectionFactory.create().get();
        this.dao.delete(id, connection);
        connection.commit();
    }
}
