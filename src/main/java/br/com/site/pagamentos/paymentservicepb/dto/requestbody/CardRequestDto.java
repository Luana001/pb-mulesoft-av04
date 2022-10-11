package br.com.site.pagamentos.paymentservicepb.dto.requestbody;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class CardRequestDto {

	@NotNull @JsonProperty("number_token")
	private String cardNumber;
	
	@NotNull @JsonProperty("cardholder_name")
	private String cardHolderName;
	
	@NotNull @JsonProperty("security_code")
	private String securityCode;
	
	@NotNull @JsonProperty("expiration_month")
	private int expirationMonth;
	
	@NotNull @JsonProperty("expiration_year")
	private int expirationYear;
	
	@NotBlank @JsonProperty("brand")
	private String brand;
}
