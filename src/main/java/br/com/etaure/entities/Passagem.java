package br.com.etaure.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;

@Entity
public class Passagem implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull(message = "A origem da passagem não pode ser nulo")
	@NotBlank(message = "O campo não pode estar vazio")
	@Size(min = 2, max = 50, message = "A origem deve ter no mínimo 2 letras e no máximo 50")
	private String origem;
	
	@NotNull(message = "O destino da passagem não pode ser nulo")
	@NotBlank(message = "O campo não pode estar vazio")
	@Size(min = 2, max = 50, message = "O destino deve ter no mínimo 2 letras e no máximo 50")
	private String destino;
	
	@Column(name = "valor_passagem")
	@NotNull(message = "O valor da passagem não pode ser nulo")
	@Digits(integer = 3, fraction = 2, message = "O número deve conter somente 3 dígitos e 2 casas decimais")
	private Double valorDaPassagem;
	
	@ManyToOne
	@JoinColumn(name = "id_passageiro")
	private Passageiro passageiro;
	
	public Passagem() {
		
	}

	public Passagem(Integer id, String origem, String destino, Double valorDaPassagem, Passageiro passageiro) {
		this.id = id;
		this.origem = origem;
		this.destino = destino;
		this.valorDaPassagem = valorDaPassagem;
		this.passageiro = passageiro;
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

	public Passageiro getPassageiro() {
		return passageiro;
	}

	public void setPassageiro(Passageiro passageiro) {
		this.passageiro = passageiro;
	}

	@Override
	public String toString() {
		return "Passagem [id=" + id + ", origem=" + origem + ", destino=" + destino + ", valorDaPassagem="
				+ valorDaPassagem + ", passageiro=" + passageiro + "]";
	}

	public String toXML() {
		return new XStream().toXML(this);
	}

	public String toJson() {
		return new Gson().toJson(this);
	}
	
}
