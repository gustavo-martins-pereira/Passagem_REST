package br.com.etaure.entities.dto;

import br.com.etaure.entities.Passagem;

public class PassagemCriadaDTO {

	private Integer id;
	private String origem;
	private String destino;
	private Double valorDaPassagem;
	private Integer idPassageiro;
	
	public PassagemCriadaDTO() {
		
	}
	
	public Passagem retornarObjetoPassagem() {
		return new Passagem(null, this.origem, this.destino, this.valorDaPassagem, null);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOrigem() {
		return origem;
	}

	public void setOrigem(String origem) {
		this.origem = origem;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public Double getValorDaPassagem() {
		return valorDaPassagem;
	}

	public void setValorDaPassagem(Double valorDaPassagem) {
		this.valorDaPassagem = valorDaPassagem;
	}

	public Integer getIdPassageiro() {
		return idPassageiro;
	}

	public void setIdPassageiro(Integer idPassageiro) {
		this.idPassageiro = idPassageiro;
	}
	
}
