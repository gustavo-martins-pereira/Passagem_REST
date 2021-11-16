package br.com.etaure.entities.dto;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import br.com.etaure.entities.Passagem;

public class PassageiroComPassagensDTO {

	private Integer id;
	private String nome;
	private String nacionalidade;
	
	private List<PassagemSimplificadaDTO> passagens = new ArrayList<PassagemSimplificadaDTO>();
	
	public PassageiroComPassagensDTO() {
		
	}

	public PassageiroComPassagensDTO(Integer id, String nome, String nacionalidade,
			List<Passagem> passagens) {
		this.id = id;
		this.nome = nome;
		this.nacionalidade = nacionalidade;
		
		transformaParaPassagemSimplificada(passagens);
	}

	private void transformaParaPassagemSimplificada(List<Passagem> passagensOriginais) {
		for (Passagem passagem : passagensOriginais) {
			passagens.add(new PassagemSimplificadaDTO(passagem));
		}
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

	public List<PassagemSimplificadaDTO> getPassagens() {
		return passagens;
	}

	public void setPassagens(List<PassagemSimplificadaDTO> passagens) {
		this.passagens = passagens;
	}

	@Override
	public String toString() {
		return "PassageiroComPassagensDTO [id=" + id + ", nome=" + nome + ", nacionalidade=" + nacionalidade
				+ ", passagens=" + passagens + "]";
	}

	public String toJson() {
		return new Gson().toJson(this);
	}
	
}
