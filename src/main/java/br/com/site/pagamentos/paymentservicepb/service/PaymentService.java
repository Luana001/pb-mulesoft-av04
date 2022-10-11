package br.com.site.pagamentos.paymentservicepb.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.site.pagamentos.paymentservicepb.dto.PaymentDto;
import br.com.site.pagamentos.paymentservicepb.dto.requestbody.PaymentRequestDto;
import br.com.site.pagamentos.paymentservicepb.dto.response.PaymentResponseDto;
import br.com.site.pagamentos.paymentservicepb.dto.response.TokenResponseDto;
import br.com.site.pagamentos.paymentservicepb.form.OrderForm;
import br.com.site.pagamentos.paymentservicepb.model.PaymentEntity;
import br.com.site.pagamentos.paymentservicepb.repository.PaymentRepository;
import br.com.site.pagamentos.paymentservicepb.util.MappersUtil;

@Service
public class PaymentService {
	private PaymentRepository paymentRepository;
	private OrderService orderService;
	private AuthenticationService authenticationService;
	
	@Autowired
	public PaymentService(PaymentRepository paymentRepository, OrderService orderService, AuthenticationService authenticationService) {
		this.paymentRepository = paymentRepository;
		this.orderService = orderService;
		this.authenticationService = authenticationService;
	}

	public PaymentEntity makeOrderPayment(OrderForm form) {
		LocalDateTime time =  LocalDateTime.now();
		TokenResponseDto token = new TokenResponseDto();
	
		token = authenticationService.authenticate(token, time);
		PaymentRequestDto paymentRequestBody = orderService.createPaymentRequestBody(form);
		HttpHeaders paymentRequestHeader = orderService.createPaymentRequestHeader(token);
		PaymentResponseDto paymentResponseDto = orderService.sendOrderPaymentRequest(paymentRequestBody, paymentRequestHeader);
		PaymentEntity payment = MappersUtil.convertPaymentResponseDtoToPayment(paymentResponseDto);
		paymentRepository.save(payment);		
		return payment;
	}

	public List<PaymentDto> getAllPayments(){
		List<PaymentEntity> payments = paymentRepository.findAll();
		List<PaymentDto> paymentsDto = new ArrayList<>();
		for(PaymentEntity payment: payments) {
			paymentsDto.add(MappersUtil.convertPaymentToPaymentDto(payment));
		}
		return paymentsDto;
	}
	
    public ResponseEntity<PaymentDto> findById(String id) {
        Optional<PaymentEntity> optional = paymentRepository.findById(id);
        if(optional.isPresent()) {
        	PaymentEntity payment = optional.get();
        	PaymentDto paymentDto = MappersUtil.convertPaymentToPaymentDto(payment);
        	return ResponseEntity.ok(paymentDto);
        }   
        return ResponseEntity.notFound().build();
    }
}
