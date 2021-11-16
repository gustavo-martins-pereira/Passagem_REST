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

import br.com.etaure.dao.PassageiroDAO;
import br.com.etaure.dao.PassagemDAO;
import br.com.etaure.entities.Passageiro;
import br.com.etaure.entities.Passagem;
import br.com.etaure.entities.dto.PassageiroComPassagensDTO;

@Path("passageiros")
public class PassageiroResource {

	private ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	private Validator validator = factory.getValidator();
	
	// Lista todos os Passageiros
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response listAll() {
		List<Passageiro> passageiros = PassageiroDAO.findAll();

		if (passageiros.isEmpty()) {
			return Response.status(Status.NO_CONTENT).build();
		} else {
			// Caso tenha ao menos 1 passageiro, retorna todos as passageiros registrados
			StringBuilder sb = new StringBuilder();

			for (Passageiro passageiro : passageiros) {
				sb.append(passageiro.toJson());
			}

			return Response.ok(sb.toString()).build();
		}
	}

	// Lista o Passageiro com o id passado por parâmetro
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findById(@PathParam("id") Integer id) {
		Passageiro passageiro = PassageiroDAO.findById(id);

		if (passageiro == null) {
			return Response.status(Status.NOT_FOUND).build();
		} else {
			// Retorna as passagens
			List<Passagem> passagens = PassagemDAO.findPassagensByIdPassageiro(id);
			
			PassageiroComPassagensDTO passageiroComPassagensDTO = new PassageiroComPassagensDTO(id, passageiro.getNome(), passageiro.getNacionalidade(), passagens);
			
			return Response.ok(passageiroComPassagensDTO.toJson()).build();
		}
	}

	// Insere um novo Passageiro para o banco de dados
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response add(String passageiroJson) throws URISyntaxException {
		// TODO Melhoria pra receber o Json sem o "id"
		Passageiro passageiro = new Gson().fromJson(passageiroJson, Passageiro.class);

		passageiro.setId(null);

		// Verifica se os campos são válidos
		Set<ConstraintViolation<Passageiro>> violations = validator.validate(passageiro);

		if (violations.isEmpty()) {
			PassageiroDAO.insert(passageiro);

			// Prepara a URI para redirecionar o cliente para o novo passageiro
			URI uri = new URI("passageiros/" + passageiro.getId());

			return Response.created(uri).status(Status.CREATED).build();
		} else {
			StringBuilder sb = new StringBuilder();
			for (ConstraintViolation<Passageiro> violation : violations) {
				sb.append(violation.getMessage());
			}

			return Response.ok(sb.toString()).status(Status.NOT_FOUND).build();
		}
	}

	// Atualiza uma Passageiro que já existe no banco de acordo com o "id" passado
	// como parâmetro pela requisição HTTP
	@PUT
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response update(@PathParam("id") Integer id, String passageiroJson) throws URISyntaxException {
		Passageiro newPassageiro = new Gson().fromJson(passageiroJson, Passageiro.class);
		
		Passageiro oldPassageiro = PassageiroDAO.findById(id);
		
		if (oldPassageiro == null) {
			return Response.status(Status.NOT_FOUND).build();
		} else {
			// Verifica se os campos são válidos
			Set<ConstraintViolation<Passageiro>> violations = validator.validate(newPassageiro);

			if(violations.isEmpty()) {
				PassageiroDAO.update(id, newPassageiro);

				return Response.ok().build();
			} else {
				StringBuilder sb = new StringBuilder();
				for (ConstraintViolation<Passageiro> violation : violations) {
					sb.append(violation.getMessage());
				}

				return Response.ok(sb.toString()).status(Status.NOT_FOUND).build();
			}
		
		}
	}

	// Deleta uma passageiro do banco
	@DELETE
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response delete(@PathParam("id") Integer id) {
		Passageiro passageiro = PassageiroDAO.findById(id);

		if (passageiro == null) {
			return Response.status(Status.NOT_FOUND).build();
		} else {
			PassageiroDAO.delete(id);

			return Response.ok().build();
		}
	}

}
