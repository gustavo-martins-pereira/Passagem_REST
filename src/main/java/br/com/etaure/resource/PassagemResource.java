package br.com.etaure.resource;

import java.net.URI;
import java.net.URISyntaxException;
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
			return Response.status(Status.NO_CONTENT).build();
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
	public Response findById(@PathParam("id") Integer id) {
		Passagem passagem = PassagemDAO.findById(id);
		
		if(passagem == null) {
			return Response.status(Status.NOT_FOUND).build();
		} else {
			return Response.ok(passagem.toJson()).build();
		}
	}
	
	// Insere uma nova Passagem para o banco de dados
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response add(String passagemJson) throws URISyntaxException {
		Passagem passagem = new Gson().fromJson(passagemJson, Passagem.class);
		// TODO Melhoria pra receber o Json sem o "id"		
		passagem.setId(null);
		// TODO Verifica se os campos são válidos
		
		PassagemDAO.insert(passagem);
		
		// Prepara a URI para redirecionar o cliente para a nova passagem
		URI uri = new URI("passagens/" + passagem.getId());
		
		return Response.created(uri).build();
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
