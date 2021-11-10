package br.com.alura.loja.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.alura.loja.dao.PassagemDAO;
import br.com.alura.loja.entities.Passagem;

@Path("passagens")
public class PassagemResource {

	@Path("{id}")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public String busca(@PathParam("id") Integer id) {
		Passagem passagem = PassagemDAO.findById(id);
		
		return passagem.toXML();
	}
	
}
