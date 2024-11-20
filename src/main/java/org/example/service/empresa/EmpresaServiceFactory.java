package org.example.service.empresa;

public class EmpresaServiceFactory {
    private EmpresaServiceFactory() {
    }

    public static EmpresaService create(){
        return new EmpresaServiceImpl();
    }
}
