package br.com.etaure.resource;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
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
	
	private ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	private Validator validator = factory.getValidator();
	
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
	
	// Lista a Passagem com o id passado por par�metro
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
		// TODO Melhoria pra receber o Json sem o "id"		
		Passagem passagem = new Gson().fromJson(passagemJson, Passagem.class);

		passagem.setId(null);

		// Verifica se os campos s�o v�lidos
		Set<ConstraintViolation<Passagem>> violations = validator.validate(passagem);

		if(violations.isEmpty()) {
			PassagemDAO.insert(passagem);
			
			// Prepara a URI para redirecionar o cliente para a nova passagem
			URI uri = new URI("passagens/" + passagem.getId());
			
			return Response.created(uri).status(Status.CREATED).build();
		} else {
			for (ConstraintViolation<Passagem> violation : violations) {
				System.out.println(violation.getMessage());
			}
			
			return Response.status(Status.NOT_ACCEPTABLE).build();
		}
	}
	
	// Atualiza uma Passagem que j� existe no banco de acordo com o "id" passado como par�metro pela requisi��o HTTP
	@PUT
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response update(@PathParam("id") Integer id ,String passagemJson) throws URISyntaxException {
		Passagem newPassagem = new Gson().fromJson(passagemJson, Passagem.class);
		
		Passagem oldPassagem = PassagemDAO.findById(id);
		
		if(oldPassagem == null) {
			return Response.status(Status.NOT_FOUND).build();
		} else {
			PassagemDAO.updatePassagem(id, newPassagem);
			
			return Response.ok().build();
		}
	}
	
	// Deleta uma passagem do banco
	@DELETE
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response delete(@PathParam("id") Integer id) {
		Passagem passagem = PassagemDAO.findById(id);
		
		if(passagem == null) {
			return Response.status(Status.NOT_FOUND).build();
		} else {
			PassagemDAO.deletePassagem(id);
			
			return Response.ok().build();
		}
	}
	
}
