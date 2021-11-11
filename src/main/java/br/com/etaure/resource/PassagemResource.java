package br.com.etaure.resource;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.etaure.dao.PassagemDAO;
import br.com.etaure.entities.Passagem;

@Path("passagens")
public class PassagemResource {

	// Lista todas as Passagens
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public String listAll() {
		List<Passagem> passagens = PassagemDAO.findAll();
		
		StringBuilder sb = new StringBuilder();
		
		for (Passagem passagem : passagens) {
			sb.append(passagem.toXML());
			sb.append("/n");
		}
		
		return sb.toString();
	}
	
	// Lista a Passagem com o id passado por parâmetro
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_XML)
	public String findById(@PathParam("id") Integer id) {
		Passagem passagem = PassagemDAO.findById(id);
		
		return passagem.toXML();
	} 
	
}
