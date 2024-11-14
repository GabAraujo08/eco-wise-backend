package org.example.service.usuario;

import org.example.entities.usuario.Usuario;
import org.example.exceptions.usuario.UsuarioNotFoundException;
import org.example.exceptions.usuario.UsuarioNotSavedException;

import java.sql.SQLException;
import java.util.List;

public interface UsuarioService {
    Usuario create(Usuario usuario) throws UsuarioNotSavedException, SQLException;

    List<Usuario> readAll() ;

    Usuario update(Usuario usuario) throws SQLException, UsuarioNotFoundException;

    void delete(Long id) throws SQLException, UsuarioNotFoundException;

    //Usuario findById(Long id) throws SQLException, UsuarioNotFoundException;
}
