package org.example.controller;

import org.example.dao.empresadao.EmpresaDao;
import org.example.dao.empresadao.EmpresaDaoFactory;
import org.example.dao.tarefadao.TarefaDao;
import org.example.dao.tarefadao.TarefaDaoFactory;
import org.example.dtos.TarefaDto;
import org.example.dtos.UsuarioDto;
import org.example.entities.empresa.Empresa;
import org.example.entities.tarefa.Tarefa;
import org.example.entities.usuario.Usuario;
import org.example.exceptions.tarefa.TarefaNotSavedException;
import org.example.exceptions.usuario.UsuarioNotSavedException;
import org.example.service.tarefa.TarefaService;
import org.example.service.tarefa.TarefaServiceFactory;
import org.example.service.usuario.UsuarioService;
import org.example.service.usuario.UsuarioServiceFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Path("/tarefa")
public class TarefaController {

    private final TarefaService tarefaService = TarefaServiceFactory.create();

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
    public Response add(TarefaDto input) {
        if (input.getId() == null) {
            try {
                // Criando tarefa com os dados do TarefaDto
                Tarefa tarefa = new Tarefa(null, input.getNome(), input.getDescricao(), input.getPontos());

                // Chamando o service para salvar a tarefa
                Tarefa tarefaSalva = this.tarefaService.create(tarefa);

                return Response
                        .status(Response.Status.CREATED)
                        .entity(tarefaSalva) // Retorna a tarefa criada
                        .build();
            } catch (TarefaNotSavedException e) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity(Map.of("mensagem", "Falha ao salvar a tarefa no banco. Verifique os dados e tente novamente."))
                        .build();
            } catch (SQLException e) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity(Map.of("mensagem", "Erro inesperado ao tentar inserir tarefa. Detalhes técnicos: " + e.getMessage()))
                        .build();
            }
        } else {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(Map.of("mensagem", "Este método permite apenas a criação de novas tarefas, sem ID especificado."))
                    .build();
        }
    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        TarefaDao tarefaDao = TarefaDaoFactory.create();
        List<Tarefa> tarefas = tarefaDao.readAll();

        if (tarefas.isEmpty()) {
            return Response.status(Response.Status.NO_CONTENT).build(); // Retorna 204 se não houver tarefas
        }

        return Response.status(Response.Status.OK)
                .entity(tarefas) // Modificado para retornar a lista de tarefas obtida
                .build();
    }


}
