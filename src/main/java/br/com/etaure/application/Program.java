package br.com.etaure.application;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import br.com.etaure.entities.Passagem;

public class Program {

	public static void main(String[] args) {

		Passagem passagem = new Passagem(null, "Gustavo", "ES", "SP", 1000.0);

		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();

		Set<ConstraintViolation<Passagem>> violations = validator.validate(passagem);

		for (ConstraintViolation<Passagem> violation : violations) {
			System.out.println(violation.getMessage());
		}

	}

}
