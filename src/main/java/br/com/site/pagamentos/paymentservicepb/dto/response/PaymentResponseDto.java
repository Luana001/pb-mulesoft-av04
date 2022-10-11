package br.com.site.pagamentos.paymentservicepb.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.site.pagamentos.paymentservicepb.constant.CurrencyType;
import br.com.site.pagamentos.paymentservicepb.constant.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentResponseDto {
	@JsonProperty("payment_id")
	String paymentId;
	@JsonProperty("seller_id")
	String sellerId;
	@JsonProperty("transaction_amount")
	BigDecimal transactionAmount;
	CurrencyType currency;
	PaymentStatus status;
	@JsonProperty("received_at")
	LocalDate receivedAt;
	AuthorizationResponseDto authorization;
}
