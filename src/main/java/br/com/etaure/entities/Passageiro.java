package br.com.etaure.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;

@Entity
public class Passageiro {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull(message = "O nome do passageiro não pode ser nulo")
	@Size(min = 2, max = 50, message = "O nome deve ter no mínimo 2 letras e no máximo 50")
	private String nome;
	
	@NotNull(message = "A nacionalidade do passageiro não pode ser nula")
	@Size(min = 2, max = 50, message = "A nacionalidade deve ter no mínimo 2 letras e no máximo 50")
	private String nacionalidade;
	
	public Passageiro() {
		
	}

	public Passageiro(Integer id, String nome, String nacionalidade) {
		this.id = id;
		this.nome = nome;
		this.nacionalidade = nacionalidade;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNacionalidade() {
		return nacionalidade;
	}

	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}

	@Override
	public String toString() {
		return "Passageiro [id=" + id + ", nome=" + nome + ", nacionalidade=" + nacionalidade + "]";
	}
	
	public String toXML() {
		return new XStream().toXML(this);
	}

	public String toJson() {
		return new Gson().toJson(this);
	}
	
}
