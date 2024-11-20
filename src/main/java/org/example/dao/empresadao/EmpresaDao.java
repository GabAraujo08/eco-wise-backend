package org.example.dao.empresadao;

import org.example.entities.empresa.Empresa;
import org.example.entities.tarefa.Tarefa;
import org.example.exceptions.empresa.EmpresaNotFoundException;
import org.example.exceptions.empresa.EmpresaNotSavedException;
import org.example.exceptions.tarefa.TarefaNotFoundException;
import org.example.exceptions.tarefa.TarefaNotSavedException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface EmpresaDao {

    Empresa create(Empresa empresa, Connection connection) throws SQLException, EmpresaNotSavedException;

    List<Empresa> readAll() ;

    Empresa update(Empresa empresa, Connection connection) throws SQLException, EmpresaNotFoundException;

    void delete(Long id, Connection connection) throws EmpresaNotFoundException, SQLException;

}
