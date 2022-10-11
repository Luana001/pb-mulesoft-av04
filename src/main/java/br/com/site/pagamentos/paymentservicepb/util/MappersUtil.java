package br.com.site.pagamentos.paymentservicepb.util;

import org.springframework.stereotype.Service;

import br.com.site.pagamentos.paymentservicepb.dto.PaymentDto;
import br.com.site.pagamentos.paymentservicepb.dto.requestbody.CardRequestDto;
import br.com.site.pagamentos.paymentservicepb.dto.response.PaymentResponseDto;
import br.com.site.pagamentos.paymentservicepb.form.CardForm;
import br.com.site.pagamentos.paymentservicepb.model.PaymentEntity;

@Service
public class MappersUtil {

	static long id = 194526320;
	
	public static PaymentEntity convertPaymentResponseDtoToPayment(PaymentResponseDto paymentResponseDto) {
		id++;
		PaymentEntity payment = new PaymentEntity();
        payment.setTotal(paymentResponseDto.getTransactionAmount());
        payment.setPaymentId(paymentResponseDto.getPaymentId());
        payment.setPaymentStatus(paymentResponseDto.getStatus());
        payment.setMessage(paymentResponseDto.getAuthorization().getReasonMessage());
        payment.setOrderId(id);
        return payment;
	}

	public static PaymentDto convertPaymentToPaymentDto(PaymentEntity payment) {
        PaymentDto paymentDto = new PaymentDto(
                payment.getOrderId(),
                payment.getTotal(),
                payment.getPaymentId(),
                payment.getPaymentStatus(),
                payment.getMessage());
        return paymentDto;
    }
	
	public static CardRequestDto convertCardFormToCardDto(CardForm card) {
        CardRequestDto cardDto = new CardRequestDto(
                card.getCardNumber(),
                card.getCardHolderName(),
                card.getSecurityCode(),
                card.getExpirationMonth(),
                card.getExpirationYear(),
                card.getBrand());
        return cardDto;
    }	
}