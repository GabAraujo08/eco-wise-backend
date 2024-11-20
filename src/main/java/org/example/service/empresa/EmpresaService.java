package org.example.service.empresa;

import org.example.entities.empresa.Empresa;
import org.example.entities.tarefa.Tarefa;
import org.example.exceptions.empresa.EmpresaNotFoundException;
import org.example.exceptions.empresa.EmpresaNotSavedException;
import org.example.exceptions.tarefa.TarefaNotFoundException;
import org.example.exceptions.tarefa.TarefaNotSavedException;


import java.sql.SQLException;
import java.util.List;

public interface EmpresaService {

    Empresa create(Empresa empresa) throws EmpresaNotSavedException, SQLException;

    List<Empresa> readAll() ;

    Empresa update(Empresa empresa) throws SQLException, EmpresaNotFoundException;

    void delete(Long id) throws SQLException, EmpresaNotFoundException;
}
