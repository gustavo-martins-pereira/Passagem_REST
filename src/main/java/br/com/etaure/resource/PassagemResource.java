package br.com.etaure.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;

import br.com.etaure.dao.PassagemDAO;
import br.com.etaure.entities.Passagem;

@Path("passagens")
public class PassagemResource {

	// Lista todas as Passagens
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String listAll() {
		List<Passagem> passagens = PassagemDAO.findAll();
		
		StringBuilder sb = new StringBuilder();
		
		for (Passagem passagem : passagens) {
			sb.append(passagem.toJson());
		}
		
		return sb.toString();
	}
	
	// Lista a Passagem com o id passado por parâmetro
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String findById(@PathParam("id") Integer id) {
		Passagem passagem = PassagemDAO.findById(id);
		
		return passagem.toJson();
	}
	
	// Insere uma nova Passagem para o banco de dados
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String add(String passagemJson) {
		Passagem passagem = new Gson().fromJson(passagemJson, Passagem.class);
		passagem.setId(null);
		
		PassagemDAO.insert(passagem);
		
		return passagem.toJson();
	}
	
	// Atualiza uma Passagem que já existe no banco de acordo com o "id" passado como parâmetro pela requisição HTTP
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	public String update(String passagemJson) {
		Passagem passagem = new Gson().fromJson(passagemJson, Passagem.class);
		
		System.out.println(passagem);
		
		PassagemDAO.updatePassagem(passagem);
		passagem = PassagemDAO.findById(passagem.getId());
		
		return passagem.toJson();
	}
	
}
