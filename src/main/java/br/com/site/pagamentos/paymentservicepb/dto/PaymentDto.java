package br.com.site.pagamentos.paymentservicepb.dto;

import java.math.BigDecimal;

import br.com.site.pagamentos.paymentservicepb.constant.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDto {
	Long orderId;
	BigDecimal total;
	String paymentId;
	PaymentStatus paymentStatus;
	String message;
}
