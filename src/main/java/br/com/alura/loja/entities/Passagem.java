package br.com.alura.loja.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.thoughtworks.xstream.XStream;

@Entity
public class Passagem implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "nome_passageiro")
	private String nomeDoPassageiro;
	private String origem;
	private String destino;
	
	@Column(name = "valor_passagem")
	private Double valorDaPassagem;
	
	public Passagem() {
		
	}

	public Passagem(Integer id, String nomeDoPassageiro, String origem, String destino, Double valorDaPassagem) {
		this.id = id;
		this.nomeDoPassageiro = nomeDoPassageiro;
		this.origem = origem;
		this.destino = destino;
		this.valorDaPassagem = valorDaPassagem;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNomeDoPassageiro() {
		return nomeDoPassageiro;
	}

	public void setNomeDoPassageiro(String nomeDoPassageiro) {
		this.nomeDoPassageiro = nomeDoPassageiro;
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
		return "Passagem [id=" + id + ", nomeDoPassageiro=" + nomeDoPassageiro + ", origem=" + origem + ", destino="
				+ destino + ", valorDaPassagem=" + valorDaPassagem + "]";
	}

	public String toXML() {
		return new XStream().toXML(this);
	}
	
}
