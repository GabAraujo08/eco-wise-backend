package org.example.service.usuario;

import org.example.config.DatabaseConnectionFactory;
import org.example.dao.usuariodao.UsuarioDao;
import org.example.dao.usuariodao.UsuarioDaoFactory;
import org.example.entities.usuario.Usuario;
import org.example.exceptions.usuario.UsuarioNotFoundException;
import org.example.exceptions.usuario.UsuarioNotSavedException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UsuarioServiceImpl implements UsuarioService {
    private final UsuarioDao dao = UsuarioDaoFactory.create();

    @Override
    public Usuario create(Usuario usuario) throws UsuarioNotSavedException, SQLException {
        Connection connection = DatabaseConnectionFactory.create().get();
        try{
            usuario = this.dao.create(usuario, connection);
            connection.commit();
            return usuario;
        }catch (SQLException | UsuarioNotSavedException e){
            connection.rollback();
            throw e;
        }
    }

    @Override
    public List<Usuario> readAll() {
        return this.dao.readAll();
    }

    @Override
    public Usuario update(Usuario usuario) throws SQLException, UsuarioNotFoundException {
        Connection connection = DatabaseConnectionFactory.create().get();
        usuario = this.dao.update(usuario, connection);
        connection.commit();
        return usuario;
    }

    @Override
    public void delete(Long id) throws SQLException, UsuarioNotFoundException {
        Connection connection = DatabaseConnectionFactory.create().get();
        this.dao.delete(id, connection);
        connection.commit();
    }

//    @Override
//    public Usuario findById(Long id) throws SQLException, UsuarioNotFoundException {
//        Connection connection = DatabaseConnectionFactory.create().get();
//        try {
//            Usuario usuario = this.dao.findById(id, connection);
//            connection.commit();
//            return usuario;
//        } catch (SQLException | UsuarioNotFoundException e) {
//            connection.rollback();
//            throw e;
//        }
//    }

}
