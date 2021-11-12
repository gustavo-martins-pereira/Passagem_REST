package br.com.etaure.application;

import br.com.etaure.dao.PassageiroDAO;
import br.com.etaure.entities.Passageiro;

public class Program {

	public static void main(String[] args) {

		/* Passageiro passageiro = new Passageiro(null, "Gustavo", "Brasileiro");
		Passagem passagem = new Passagem(1, "ES", "SP", 57.45, new Passageiro(6, "Maria", "Americana"));

		System.out.println(passagem); */
		
		Passageiro passageiro = new Passageiro(null, "Maria", "Americana");
	
		System.out.println(PassageiroDAO.insert(passageiro));
		
	}

}
