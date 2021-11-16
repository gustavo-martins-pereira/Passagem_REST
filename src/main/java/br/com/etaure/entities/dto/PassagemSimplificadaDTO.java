package br.com.etaure.entities.dto;

import br.com.etaure.entities.Passagem;

public class PassagemSimplificadaDTO {

	private String origem;
	private String destino;
	private Double valorDaPassagem;
	
	public PassagemSimplificadaDTO() {
		
	}

	public PassagemSimplificadaDTO(String origem, String destino, Double valorDaPassagem) {
		this.origem = origem;
		this.destino = destino;
		this.valorDaPassagem = valorDaPassagem;
	}
	
	public PassagemSimplificadaDTO(Passagem passagem) {
		this.origem = passagem.getOrigem();
		this.destino = passagem.getDestino();
		this.valorDaPassagem = passagem.getValorDaPassagem();
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

	@Override
	public String toString() {
		return "PassagemSimplificadaDTO [origem=" + origem + ", destino=" + destino + ", valorDaPassagem="
				+ valorDaPassagem + "]";
	}
	
}
