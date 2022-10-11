package br.com.site.pagamentos.paymentservicepb.config;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.com.site.pagamentos.paymentservicepb.constant.Brand;
import br.com.site.pagamentos.paymentservicepb.constant.CurrencyType;
import br.com.site.pagamentos.paymentservicepb.constant.PaymentType;

public class StringAsEnumValidator implements ConstraintValidator<StringAsEnumValid, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		
		for (Brand brand: Brand.values()) {
			if (brand.toString().equals(value)) {
				return true;
			} 
		}
		
		for (PaymentType paymentType: PaymentType.values()) {
			if(paymentType.toString().equals(value)) {
				return true;
			}
		}
		
		for(CurrencyType currencyType: CurrencyType.values()) {
			if(currencyType.toString().equals(value)) {
				return true;
			}
		}
		
		return false;
	}
}