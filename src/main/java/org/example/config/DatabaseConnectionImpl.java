package org.example.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

public class DatabaseConnectionImpl implements DatabaseConnection {

    private static DatabaseConnectionImpl dbConnection;
    private static Connection connection;
    private final Logger logger = Logger.getLogger(this.getClass().getName());


    private DatabaseConnectionImpl() throws SQLException {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            connection = DriverManager.getConnection(
                    DatabaseConfig.getUrl(),
                    DatabaseConfig.getUser(),
                    DatabaseConfig.getPassword()
            );
        } catch (ClassNotFoundException e) {
            logger.severe("A classe Driver do Oracle não foi localizada.");
        }

    }

    public static synchronized DatabaseConnectionImpl getInstance() throws SQLException {
        if(dbConnection == null || connection.isClosed()){
            dbConnection = new DatabaseConnectionImpl();
        }
        return dbConnection;
    }

    @Override
    public Connection get() throws SQLException {
        connection.setAutoCommit(false);
        return connection;
    }
}
