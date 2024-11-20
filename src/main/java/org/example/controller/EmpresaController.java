package org.example.controller;


import org.example.dao.empresadao.EmpresaDao;
import org.example.dao.empresadao.EmpresaDaoFactory;
import org.example.dao.usuariodao.UsuarioDao;
import org.example.dao.usuariodao.UsuarioDaoFactory;
import org.example.dtos.EmpresaDto;
import org.example.dtos.UsuarioDto;
import org.example.entities.empresa.Empresa;
import org.example.entities.usuario.Usuario;
import org.example.exceptions.empresa.EmpresaNotFoundException;
import org.example.exceptions.empresa.EmpresaNotSavedException;
import org.example.exceptions.usuario.UsuarioNotFoundException;
import org.example.exceptions.usuario.UsuarioNotSavedException;
import org.example.service.empresa.EmpresaService;
import org.example.service.empresa.EmpresaServiceFactory;
import org.example.service.usuario.UsuarioService;
import org.example.service.usuario.UsuarioServiceFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Path("/empresa")
public class EmpresaController {

    private final EmpresaService empresaService = EmpresaServiceFactory.create();




    @GET
    @Path("/hello")
    @Produces(MediaType.APPLICATION_JSON)
    public Response hello() {
        try {
            return Response.status(Response.Status.OK)
                    .entity(Map.of("mensagem", "Hello World!")).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Ocorreu um erro inesperado: " + e.getMessage()).build();
        }
    }



    @POST
    @Path("")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response add(EmpresaDto input) {
        if (input.getId() == null) {
            try {
                // Criando empresa com os dados de EmpresaDto
                Empresa empresa = new Empresa(null, input.getNome(), input.getCnpj(), input.getSenha());

                // Chamando o service para salvar empresa
                Empresa empresaSalva = this.empresaService.create(empresa);

                return Response
                        .status(Response.Status.CREATED)
                        .entity(empresaSalva) // Retorna a empresa criado
                        .build();
            } catch (EmpresaNotSavedException e) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity(Map.of("mensagem", "Falha ao salvar a empresa no banco. Verifique os dados e tente novamente."))
                        .build();
            } catch (SQLException e) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity(Map.of("mensagem", "Erro inesperado ao tentar inserir empresa. Detalhes técnicos: " + e.getMessage()))
                        .build();
            }
        } else {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(Map.of("mensagem", "Este método permite apenas a criação de novas empresas, sem ID especificado."))
                    .build();
        }
    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        EmpresaDao empresaDao = EmpresaDaoFactory.create();
        List<Empresa> empresas = empresaDao.readAll();

        if (empresas.isEmpty()) {
            return Response.status(Response.Status.NO_CONTENT).build(); // Retorna 204 se não houver empresas
        }

        return Response.status(Response.Status.OK)
                .entity(this.empresaService.readAll()).build();
    }

    @PUT
    @Path("/update/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") Long id, EmpresaDto input) {
        try {
            Empresa empresa = this.empresaService.update(new Empresa(id, input.getNome(), input.getCnpj(), input.getSenha()));
            return Response.status(Response.Status.OK).entity(empresa).build();
        } catch (EmpresaNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(Map.of("mensagem", "Empresa não encontrada."))
                    .build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Map.of("mensagem", "Erro inesperado ao tentar atualizar empresa. Detalhes técnicos: " + e.getMessage()))
                    .build();
        }


    }

    @DELETE
    @Path("/delete/{id}")
    public Response delete(@PathParam("id") Long id) {
        try {
            this.empresaService.delete(id);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (EmpresaNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(Map.of("mensagem", "Empresa não encontrado"))
                    .build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Map.of("mensagem", "Erro inesperado ao tentar deletar empresa. Detalhes técnicos: " + e.getMessage()))
                    .build();
        }
    }
}
