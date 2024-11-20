package org.example.dao.empresadao;



public class EmpresaDaoFactory {
    private EmpresaDaoFactory() {
    }

    public static EmpresaDao create(){
        return new EmpresaDaoImpl();
    }

}
