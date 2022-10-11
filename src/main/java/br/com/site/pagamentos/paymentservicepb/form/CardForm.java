package br.com.site.pagamentos.paymentservicepb.form;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.site.pagamentos.paymentservicepb.config.ExpirationYearValid;
import br.com.site.pagamentos.paymentservicepb.config.StringAsEnumValid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class CardForm {
	
	@NotNull (message = "card_number must not be null")
	@JsonProperty("card_number")
	private String cardNumber;
	
	@NotNull (message = "cardholder_name must not be null")
	@JsonProperty("cardholder_name")
	private String cardHolderName;
	
	@Size(min = 3, max = 3, message = "security_code must have 3 numbers")
	@JsonProperty("security_code") 
	private String securityCode;
	
	@Min(value = 1, message = "expiration_month must be greater than or equal to 1") 
	@Max(value = 12, message = "expiration_month must be less than or equal to 12")
	@JsonProperty("expiration_month") 
	private int expirationMonth;
	
	@ExpirationYearValid
	@JsonProperty("expiration_year")
	private int expirationYear;
	
	@NotNull (message = "brand must not be null")
	@StringAsEnumValid (message = "the value for brand is invalid")
	@JsonProperty("brand")
	private String brand;
}
