package br.unitins.topicos1.resource;

import java.io.IOException;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;
import org.jboss.logging.Logger;

import br.unitins.topicos1.form.UsuarioImageForm;
import br.unitins.topicos1.service.UsuarioFileService;
import br.unitins.topicos1.service.UsuarioService;
import br.unitins.topicos1.application.Error;
import br.unitins.topicos1.dto.usuario.UpdateEmailDTO;
import br.unitins.topicos1.dto.usuario.UpdateNomeDTO;
import br.unitins.topicos1.dto.usuario.UpdateSenhaDTO;
import br.unitins.topicos1.dto.usuario.UpdateTelefoneDTO;
import br.unitins.topicos1.dto.usuario.UsuarioResponseDTO;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.ResponseBuilder;
import jakarta.ws.rs.core.Response.Status;

@Path("/usuariologado")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsuarioLogadoResource {

    @Inject
    JsonWebToken jwt;

    @Inject
    UsuarioService usuarioService;

    @Inject
    UsuarioFileService fileService;

    private static final Logger LOG = Logger.getLogger(UsuarioLogadoResource.class);

    @GET
    @Path("/meusdados")
    @RolesAllowed({"Cliente", "Administrador"})
    public Response getUsuario() {

        String login = jwt.getSubject();

        return Response.ok(usuarioService.findByLogin(login)).build();

    }

    @PATCH
    @Path("/alterar/imagem")
    @RolesAllowed({"Cliente", "Administrador"})
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response salvarImagem(@MultipartForm UsuarioImageForm form){
        String nomeImagem;
        try {
            nomeImagem = fileService.salvar(form.getNomeImagem(), form.getImagem());
        } catch (IOException e) {
            e.printStackTrace();
            Error error = new Error("409", e.getMessage());
            return Response.status(Status.CONFLICT).entity(error).build();
        }

        String login = jwt.getSubject();
        UsuarioResponseDTO usuarioDTO = usuarioService.findByLogin(login);
        usuarioDTO = usuarioService.updateNomeImagem(usuarioDTO.id(), nomeImagem);

        return Response.ok(usuarioDTO).build();

    }

    @GET
    @Path("/download/imagem/{nomeImagem}")
    @RolesAllowed({"Cliente", "Administrador"})
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response download(@PathParam("nomeImagem") String nomeImagem) {
        ResponseBuilder response = Response.ok(fileService.obter(nomeImagem));
        response.header("Content-Disposition", "attachment;filename="+nomeImagem);
        return response.build();
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