package br.com.site.pagamentos.paymentservicepb.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.site.pagamentos.paymentservicepb.constant.DocumentType;
import br.com.site.pagamentos.paymentservicepb.constant.SellerInformation;
import br.com.site.pagamentos.paymentservicepb.dto.requestbody.CustomerRequestDto;
import br.com.site.pagamentos.paymentservicepb.dto.requestbody.PaymentRequestDto;
import br.com.site.pagamentos.paymentservicepb.dto.response.PaymentResponseDto;
import br.com.site.pagamentos.paymentservicepb.dto.response.TokenResponseDto;
import br.com.site.pagamentos.paymentservicepb.form.ItemForm;
import br.com.site.pagamentos.paymentservicepb.form.OrderForm;
import br.com.site.pagamentos.paymentservicepb.util.MappersUtil;

@Service
public class OrderService {

	public PaymentResponseDto sendOrderPaymentRequest(String paymentRequestBodyJSON, HttpHeaders paymentRequestHeader) {
        RestTemplate rest = new RestTemplate();
        String url = "https://pb-getway-payment.herokuapp.com/v1/payments/credit-card";

        HttpEntity<String> httpEntity = new HttpEntity<String>(paymentRequestBodyJSON, paymentRequestHeader);
        ResponseEntity<PaymentResponseDto> response = rest.exchange(url, HttpMethod.POST, httpEntity,
                PaymentResponseDto.class);
        return response.getBody();
    }
	
	public String createPaymentRequestBody(OrderForm form) {
		SellerInformation sellerInformation = new SellerInformation();
        CustomerRequestDto costumerDto = new CustomerRequestDto(DocumentType.CPF, form.getCpf());        
        BigDecimal total = calculaTotalPedido(form);

        PaymentRequestDto paymentRequestBody = new PaymentRequestDto(sellerInformation.getSellerId(),
                costumerDto, form.getPaymentType(), form.getCurrencyType(), total, MappersUtil.convertCardFormToCardDto(form.getCard()));

        ObjectMapper mapper = new ObjectMapper();
        try {
        	String paymentRequestBodyToJSON = mapper.writeValueAsString(paymentRequestBody);
        	return paymentRequestBodyToJSON;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "";
        }
	}

	public BigDecimal calculaTotalPedido(OrderForm form) {
		List<ItemForm> itens = form.getItems();
		BigDecimal total = new BigDecimal(0);
		for (ItemForm item : itens) {
			total = total.add(item.getValue().setScale(2).multiply(new BigDecimal(item.getQty())));
		}
		total = total.subtract(form.getDiscount().setScale(2)).add(form.getShipping().setScale(2));
		return total.setScale(2, RoundingMode.HALF_UP);
	}
	
	public HttpHeaders createPaymentRequestHeader(TokenResponseDto token) {
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);
        header.add("Authorization", "Bearer " + token.getAccessToken());
        return header;
    }
}