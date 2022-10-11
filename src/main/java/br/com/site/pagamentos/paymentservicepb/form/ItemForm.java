package br.com.site.pagamentos.paymentservicepb.form;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemForm {
	@NotNull (message = "item must not be null")
	private String item;
	
	@NotNull (message = "value must not be null")
	@Positive (message = "value must be positive")
	private BigDecimal value;
	
	@NotNull (message = "value must not be null")
	@Positive (message = "value must be positive")
	private int qty;
}