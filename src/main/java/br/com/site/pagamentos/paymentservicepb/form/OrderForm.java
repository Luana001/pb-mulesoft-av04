package br.com.site.pagamentos.paymentservicepb.form;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.site.pagamentos.paymentservicepb.config.StringAsEnumValid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderForm {
	
	@NotNull (message = "cpf must not be null")
	@NotEmpty @JsonAlias({"cpf", "cnpj"})
	private String cpf;
	
	@Valid @NotNull (message = "items must not be null")
	private List<ItemForm> items;
	
	@NotNull (message = "shipping must not be null")
	@Positive (message = "shipping must be positive")
	private BigDecimal shipping;
	
	@NotNull (message = "discount must not be null")
	@Positive (message = "discount must be positive")
	private BigDecimal discount;

	@NotNull (message = "payment_type must not be null")
	@StringAsEnumValid (message = "the value for payment_type is invalid")
	@JsonProperty("payment_type")
	private String paymentType;
		
	@NotNull (message = "currency_type must not be null")
	@StringAsEnumValid (message = "the value for currency_type is invalid")
	@JsonProperty("currency_type")
	private String currencyType;
	
	@Valid @NotNull (message = "payment must not be null") @JsonProperty("payment")
	private CardForm card;
}