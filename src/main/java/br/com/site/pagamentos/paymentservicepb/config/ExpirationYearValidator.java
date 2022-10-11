package br.com.site.pagamentos.paymentservicepb.config;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class ExpirationYearValidator implements ConstraintValidator<ExpirationYearValid, Integer> {

	@Override
	public boolean isValid(Integer value, ConstraintValidatorContext context) {
		if(value > 9 && value < 100 || value > 999 && value < 10000) {
			return true;
		}
			return false;
	}

}