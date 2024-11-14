package org.example.config;

public class DatabaseConfig {


    private DatabaseConfig() {
       throw new UnsupportedOperationException();
    }

    static String getUrl(){
        return "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL";
    }

    static String getUser(){
        return "rm558802";
    }

    static String getPassword(){
        return "fiap24";
    }
}