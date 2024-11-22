package org.example.dao.tarefadao;

import oracle.jdbc.internal.OracleTypes;
import org.example.config.DatabaseConnectionFactory;
import org.example.entities.tarefa.Tarefa;
import org.example.exceptions.tarefa.TarefaNotFoundException;
import org.example.exceptions.tarefa.TarefaNotSavedException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class TarefaDaoImpl implements TarefaDao {

    Logger logger = Logger.getLogger(this.getClass().getName());

    @Override
    public Tarefa create(Tarefa tarefa, Connection connection) throws SQLException, TarefaNotSavedException {
        final String sql = "BEGIN "
                + "INSERT INTO TAREFA (nome_tarefa, descricao, pontos) "
                + "VALUES (?, ?, ?) "
                + "RETURNING id_tarefa INTO ?; "
                + "END;";

        // Preparando o CallableStatement
        CallableStatement call = connection.prepareCall(sql);

        try {
            // Definindo os parâmetros da consulta
            call.setString(1, tarefa.getNome());
            call.setString(2, tarefa.getDescricao());
            call.setInt(3, tarefa.getPontos());

            // Registrando o parâmetro de saída (id_tarefa gerado)
            call.registerOutParameter(4, OracleTypes.NUMBER);

            // Executando a consulta
            int linhasAlteradas = call.executeUpdate();
            long id = call.getLong(4); // Recupera o id gerado

            // Verificando se a inserção foi bem-sucedida
            if (linhasAlteradas == 0 || id == 0) {
                throw new TarefaNotSavedException();
            }

            // Atribuindo o id gerado à tarefa e retornando
            tarefa.setId(id);
            return tarefa;

        } catch (SQLException e) {
            // Lançando exceção personalizada, caso ocorra algum erro durante a inserção
            logger.severe(e.getMessage());
            throw new SQLException("Erro ao salvar a tarefa no banco de dados", e);

        } finally {
            // Fechando o CallableStatement
            call.close();
        }
    }




    @Override
    public List<Tarefa> readAll() {
        final List<Tarefa> all = new ArrayList<>();
        final String sql = "SELECT * FROM TAREFA";

        try (Connection conn = DatabaseConnectionFactory.create().get()) {
            System.out.println("Conexão estabelecida com sucesso.");

            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet resultSet = stmt.executeQuery();

            int count = 0;  // Contador de tarefas encontradas
            while (resultSet.next()) {
                Long id = resultSet.getLong("id_tarefa");
                String nome = resultSet.getString("nome_tarefa");
                String descricao = resultSet.getString("descricao");
                Integer pontos = resultSet.getInt("pontos");

                Tarefa tarefa = new Tarefa(id, nome, descricao, pontos);
                all.add(tarefa);

                // Log para verificar as tarefas
                System.out.println("Tarefa #" + (++count) + ": " + tarefa.getNome());
            }

            System.out.println("Total de tarefas encontradas: " + count);
        } catch (SQLException e) {
            logger.warning("Erro ao buscar tarefas: " + e.getMessage());
        }

        return all;
    }




    @Override
    public Tarefa update(Tarefa tarefa, Connection connection) throws SQLException, TarefaNotFoundException {
        return null;
    }

    @Override
    public void delete(Long id, Connection connection) throws TarefaNotFoundException, SQLException {

    }
}
