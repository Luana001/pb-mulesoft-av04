package br.com.site.pagamentos.paymentservicepb.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.site.pagamentos.paymentservicepb.dto.PaymentDto;
import br.com.site.pagamentos.paymentservicepb.form.OrderForm;
import br.com.site.pagamentos.paymentservicepb.model.PaymentEntity;
import br.com.site.pagamentos.paymentservicepb.service.PaymentService;
import br.com.site.pagamentos.paymentservicepb.util.MappersUtil;

@RestController
@RequestMapping("/api/v1/order/payment")
public class PaymentController {

	 @Autowired
     private PaymentService paymentService;
	 
     @GetMapping
     public List<PaymentDto> listarTodos() {
         return paymentService.getAllPayments();
     }

     @GetMapping("/{orderId}")
     public ResponseEntity<PaymentDto> findById(@PathVariable @NotNull String orderId) {
    	 return paymentService.findById(orderId);
     }

     @PostMapping
     public ResponseEntity<PaymentDto> aprovePayment(@RequestBody @Valid OrderForm form, UriComponentsBuilder uriBuilder) {
    	 PaymentEntity payment = paymentService.makeOrderPayment(form);
         URI endereco = uriBuilder.path("/api/v1/order/payment/{orderId}").buildAndExpand(payment.getPaymentId()).toUri();
         PaymentDto paymentDto = MappersUtil.convertPaymentToPaymentDto(payment);
         return ResponseEntity.created(endereco).body(paymentDto);
     }
}
