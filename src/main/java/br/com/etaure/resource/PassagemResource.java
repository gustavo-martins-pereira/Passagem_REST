package br.com.etaure.resource;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import com.google.gson.Gson;

import br.com.etaure.dao.PassagemDAO;
import br.com.etaure.entities.Passagem;

@Path("passagens")
public class PassagemResource {

	// Lista todas as Passagens
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response listAll() {
		List<Passagem> passagens = PassagemDAO.findAll();
		
		if(passagens.isEmpty()) {
			// Caso não tenha conteúdo retorna o código 204
			ResponseBuilder responseBuilder;
			responseBuilder = Response.status(Status.NO_CONTENT);
			
			return responseBuilder.build();
		} else {
			// Caso tenha ao menos 1 passagem, retorna todas as passagens registradas
			StringBuilder sb = new StringBuilder();
			
			for (Passagem passagem : passagens) {
				sb.append(passagem.toJson());
			}
			
			return Response.ok(sb.toString()).build();
		}
	}
	
	// Lista a Passagem com o id passado por parâmetro
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String findById(@PathParam("id") Integer id) {
		Passagem passagem = PassagemDAO.findById(id);
		
		return passagem.toJson();
		
		// Quando não encontrar, retornar um 404
	}
	
	// Insere uma nova Passagem para o banco de dados
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String add(String passagemJson) {
		Passagem passagem = new Gson().fromJson(passagemJson, Passagem.class);
		// Melhoria pra trazer sem id
		passagem.setId(null);
		
		PassagemDAO.insert(passagem);
		
		// retorna 201
		return passagem.toJson();
	}
	
	// Atualiza uma Passagem que já existe no banco de acordo com o "id" passado como parâmetro pela requisição HTTP
	@PUT
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String update(@PathParam("id") Integer id ,String passagemJson) {
		Passagem passagem = new Gson().fromJson(passagemJson, Passagem.class);
		
		PassagemDAO.updatePassagem(id, passagem);
		passagem = PassagemDAO.findById(id);
		
		//Not found 404
		
		return passagem.toJson();
	}
	
	// Deleta uma passagem do banco
	@DELETE
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public void delete(@PathParam("id") Integer id) {
		//Find
		PassagemDAO.deletePassagem(id);
		//Not found 404
	}
	
}
