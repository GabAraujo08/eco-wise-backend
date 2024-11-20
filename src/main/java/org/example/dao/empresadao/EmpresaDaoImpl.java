package org.example.dao.empresadao;

import oracle.jdbc.internal.OracleTypes;
import org.example.config.DatabaseConnectionFactory;
import org.example.entities.empresa.Empresa;
import org.example.exceptions.empresa.EmpresaNotFoundException;
import org.example.exceptions.empresa.EmpresaNotSavedException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class EmpresaDaoImpl implements EmpresaDao {

    Logger logger = Logger.getLogger(this.getClass().getName());
    @Override
    public Empresa create(Empresa empresa, Connection connection) throws SQLException, EmpresaNotSavedException {



        final String sql = "BEGIN "
                + "INSERT INTO EMPRESA (nm_empresa, cnpj, senha_empresa) "
                + "VALUES (?, ?, ?) "
                + "RETURNING id_empresa INTO ?; "
                + "END;";

        // Preparando o CallableStatement
        CallableStatement call = connection.prepareCall(sql);

        try {
            // Definindo os parâmetros da consulta
            call.setString(1, empresa.getNome());
            call.setString(2, empresa.getCnpj());
            call.setString(3, empresa.getSenha());

            // Registrando o parâmetro de saída (id_empresa gerado)
            call.registerOutParameter(4, OracleTypes.NUMBER);

            // Executando a consulta
            int linhasAlteradas = call.executeUpdate();
            long id = call.getLong(4); // Recupera o id gerado

            // Verificando se a inserção foi bem-sucedida
            if (linhasAlteradas == 0 || id == 0) {
                throw new EmpresaNotSavedException();
            }

            // Atribuindo o id gerado à empresa e retornando
            empresa.setId(id);
            return empresa;

        } catch (SQLException e) {
            // Lançando exceção personalizada, caso ocorra algum erro durante a inserção
            throw new SQLException("Erro ao salvar a empresa no banco de dados", e);

        } finally {
            // Fechando o CallableStatement
            call.close();
        }
    }


    @Override
    public List<Empresa> readAll() {
        final List<Empresa> all = new ArrayList<>();
        final String sql = "SELECT * FROM EMPRESA";

        try (Connection conn = DatabaseConnectionFactory.create().get()) {
            // Preparando a consulta SQL
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet resultSet = stmt.executeQuery();

            // Iterando pelos resultados
            while (resultSet.next()) {
                // Obtém os valores da tabela para criar a empresa
                long id = resultSet.getLong("id_empresa");
                String nome = resultSet.getString("nm_empresa");
                String cnpj = resultSet.getString("cnpj");
                String senha = resultSet.getString("senha_empresa");

                // Cria o objeto Empresa com os dados recuperados
                Empresa empresa = new Empresa(id, nome, cnpj,  senha);


                // Adiciona a empresa à lista
                all.add(empresa);
            }
        } catch (SQLException e) {
            logger.warning("Não foi possível localizar nenhum registro de empresa: " + e.getMessage());
        }

        return all;
    }


    @Override
    public Empresa update(Empresa empresa, Connection connection) throws EmpresaNotFoundException, SQLException {
        final String sqlUpdate = "UPDATE EMPRESA SET nm_empresa = ?, cnpj = ?, senha_empresa = ? WHERE id_empresa = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sqlUpdate)) {
            // Define os parâmetros da consulta
            pstmt.setString(1, empresa.getNome());
            pstmt.setString(2, empresa.getCnpj());
            pstmt.setString(3, empresa.getSenha());
            pstmt.setLong(4, empresa.getId());

            // Executa a atualização
            int linhasAlteradas = pstmt.executeUpdate();

            // Verifica se a empresa foi encontrada e atualizada
            if (linhasAlteradas == 0) {
                throw new EmpresaNotFoundException();
            }

            // Retorna a empresa atualizada
            return empresa;
        } catch (SQLException e) {
            logger.severe("Erro ao atualizar a empresa: " + e.getMessage());
            throw e; // Lança novamente a exceção para ser tratada pelo chamador
        }
    }


    @Override
    public void delete(Long id, Connection connection) throws EmpresaNotFoundException, SQLException {
        final String sql = "DELETE FROM EMPRESA WHERE id_empresa = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            // Define o parâmetro para o ID da empresa
            pstmt.setLong(1, id);

            // Executa a exclusão
            int linhasAlteradas = pstmt.executeUpdate();

            // Verifica se a empresa foi encontrada e deletada
            if (linhasAlteradas == 0) {
                throw new EmpresaNotFoundException();
            }

            //System.out.println("Empresa com ID " + id + " deletada com sucesso.");
        } catch (SQLException e) {
            logger.severe("Erro ao deletar a empresa com ID " + id + ": " + e.getMessage());
            throw e; // Lança novamente a exceção para ser tratada pelo chamador
        }
    }

}
