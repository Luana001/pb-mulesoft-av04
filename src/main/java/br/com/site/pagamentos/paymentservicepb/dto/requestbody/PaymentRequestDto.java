package br.com.site.pagamentos.paymentservicepb.dto.requestbody;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRequestDto {

	@JsonProperty("seller_id")
	String sellerId;
	
	CustomerRequestDto customer;
	
	@JsonProperty("payment_type")
	String paymentType;
	
	String currency;
	
	@JsonProperty("transaction_amount")
	BigDecimal trasactionAmount;
	
	CardRequestDto card;
}
