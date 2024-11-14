

package org.example.dao.usuariodao;

import org.example.entities.usuario.Usuario;
import org.example.exceptions.usuario.UsuarioNotFoundException;
import org.example.exceptions.usuario.UsuarioNotSavedException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface UsuarioDao {

    Usuario create(Usuario usuario, Connection connection) throws SQLException, UsuarioNotSavedException;

    List<Usuario> readAll() ;

    Usuario update(Usuario usuario, Connection connection) throws SQLException, UsuarioNotFoundException;

    void delete(Long id, Connection connection) throws UsuarioNotFoundException, SQLException;

    //Usuario findById(Long id, Connection connection) throws UsuarioNotFoundException, SQLException;
}