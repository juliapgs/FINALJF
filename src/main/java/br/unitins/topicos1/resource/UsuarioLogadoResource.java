package br.unitins.topicos1.resource;

import java.util.List;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.jboss.logging.Logger;

import br.unitins.topicos1.service.PedidoService;
import br.unitins.topicos1.service.UsuarioService;
import br.unitins.topicos1.application.Error;
import br.unitins.topicos1.dto.pedido.PedidoResponseDTO;
import br.unitins.topicos1.dto.usuario.UpdateEmailDTO;
import br.unitins.topicos1.dto.usuario.UpdateNomeDTO;
import br.unitins.topicos1.dto.usuario.UpdateSenhaDTO;
import br.unitins.topicos1.dto.usuario.UpdateTelefoneDTO;
import br.unitins.topicos1.dto.usuario.UsuarioResponseDTO;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/usuariologado")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsuarioLogadoResource {

    @Inject
    JsonWebToken jwt;

    @Inject
    PedidoService pedidoService;

    @Inject
    UsuarioService usuarioService;

    private static final Logger LOG = Logger.getLogger(UsuarioLogadoResource.class);

    @GET
    @RolesAllowed({"Cliente", "Administrador"})
    public Long getIdUsuario() {

        String login = jwt.getSubject();

        UsuarioResponseDTO usuario = usuarioService.findByLogin(login);
        return usuario.id();
    }

    @GET
    @Path("/meusdados")
    @RolesAllowed({"Cliente", "Administrador"})
    public Response getUsuario() {
        
        String login = jwt.getSubject();

        LOG.info("Buscando perfis do usuário logado.");
        return Response.ok(usuarioService.findByLogin(login)).build();
    }

     @GET
    @Path("/meus-pedidos")
    @RolesAllowed({"Cliente", "Administrador"})
    public Response minhasCompras() {
        LOG.infof("Buscando compras");

        try {
            List<PedidoResponseDTO> response = pedidoService.findByUsuario(this.getIdUsuario());
            LOG.info("Pesquisa realizada com sucesso.");
            return Response.ok(response).build();
        } catch (ConstraintViolationException e) {
            LOG.error("Erro ao buscar compras.");
            LOG.debug(e.getMessage());
            Error error = new Error("constraint_violation", "Erro de validação. Verifique os dados fornecidos.");
            return Response.status(Status.BAD_REQUEST).entity(error).build();
        } catch (Exception e) {
            LOG.fatal("Erro sem identificacao: " + e.getMessage());
            Error error = new Error("internal_error", "Erro interno no servidor.");
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(error).build();
        }
    }

    @PATCH
    @Path("/alterar/nome")
    @RolesAllowed({ "Cliente", "Administrador" })
    public Response updateNome(@Valid UpdateNomeDTO dto){
        LOG.info("Iniciando a o Update de nome");
        String login = jwt.getSubject();

        UsuarioResponseDTO retorno = usuarioService.updateNome(dto, login);
        
      
        return Response.status(201).entity(retorno).build();
    }

    @PATCH
    @Path("/alterar/senha")
    @RolesAllowed({ "Cliente", "Administrador" })
    public Response updateSenha(@Valid UpdateSenhaDTO dto){
        LOG.info("Iniciando  o Update de senha");

        String login = jwt.getSubject();

        UsuarioResponseDTO retorno = usuarioService.updateSenha(dto, login);
        
        return Response.status(201).entity(retorno).build();
    }

    @PATCH
    @Path("/alterar/email")
    @RolesAllowed({ "Cliente", "Administrador" })
    public Response updateEmail(@Valid UpdateEmailDTO dto){
        LOG.info("Iniciando a o Update de email");
        String login = jwt.getSubject();

        UsuarioResponseDTO retorno = usuarioService.updateEmail(dto, login);
        LOG.info("Encerrando o update de email");        
        return Response.status(201).entity(retorno).build();
    }

    @PATCH
    @Path("/alterar/telefone")
    @RolesAllowed({ "Cliente", "Administrador" })
    public Response updateTelefone(@Valid UpdateTelefoneDTO dto){
        LOG.info("Iniciando a o Update de telefone");
        String login = jwt.getSubject();

        UsuarioResponseDTO retorno = usuarioService.updateTelefone(dto, login);

        return Response.status(201).entity(retorno).build();
    }
}