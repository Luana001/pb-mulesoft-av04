package br.com.site.pagamentos.paymentservicepb.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import br.com.site.pagamentos.paymentservicepb.dto.response.TokenResponseDto;
import br.com.site.pagamentos.paymentservicepb.form.CardForm;
import br.com.site.pagamentos.paymentservicepb.form.ItemForm;
import br.com.site.pagamentos.paymentservicepb.form.OrderForm;

class OrderServiceTest {

	private OrderService service;

	@BeforeEach
	void inicialize() {
		this.service = new OrderService();
	}

	@Test
	void deveRetornarTotalDoPedido() {
		OrderForm orderForm = createForm();
		BigDecimal total = service.calculaTotalPedido(orderForm);
		BigDecimal valor = new BigDecimal("295.13");
		assertEquals(valor, total);
	}

	@Test
	void deveRetornarHttpHeadersComTokenParaAutenticacao() {
		TokenResponseDto token = createToken();
		HttpHeaders headers = service.createPaymentRequestHeader(token);

		assertEquals(MediaType.APPLICATION_JSON, headers.getContentType());
		assertTrue(headers.containsKey("Authorization"));
		assertTrue(headers.toString().contains("Bearer " + token.getAccessToken()));
	}

	@Test
	void deveRetornarBodyDaRequisicaoDePagamentoComoString() {
		OrderForm orderForm = createForm();

		String paymenRequestBodyJSON = service.createPaymentRequestBody(orderForm);

		assertTrue(paymenRequestBodyJSON.contains("\"seller_id\":\"7be8890e-f4da-40c2-975e-0b9a87c5ad69\""));
		assertTrue(paymenRequestBodyJSON.contains("\"document_type\":\"CPF\""));
		assertTrue(paymenRequestBodyJSON.contains("\"document_number\":\"44325592303\""));
		assertTrue(paymenRequestBodyJSON.contains("\"payment_type\":\"CREDIT_CARD\""));
		assertTrue(paymenRequestBodyJSON.contains("\"currency\":\"BRL\""));
		assertTrue(paymenRequestBodyJSON.contains("\"transaction_amount\":295.13"));
		assertTrue(paymenRequestBodyJSON.contains("\"cardholder_name\":\"CARLOS MONTE\""));
		assertTrue(paymenRequestBodyJSON.contains("\"security_code\":\"356\""));
		assertTrue(paymenRequestBodyJSON.contains("\"brand\":\"MASTERCARD\""));
		assertTrue(paymenRequestBodyJSON.contains("\"expiration_month\":1"));
		assertTrue(paymenRequestBodyJSON.contains("\"expiration_year\":2023"));
	}

	public OrderForm createForm() {
		CardForm cardForm = new CardForm("3723544712123", "CARLOS MONTE", "356", 1, 2023, "MASTERCARD");

		List<ItemForm> items = new ArrayList<>();
		items.add(new ItemForm("Item 2", new BigDecimal("44.6"), 4));
		items.add(new ItemForm("Item 2", new BigDecimal("55.6"), 2));

		OrderForm form = new OrderForm("44325592303", items, new BigDecimal("9.99"), new BigDecimal("4.46"),
				"CREDIT_CARD", "BRL", cardForm);

		return form;
	}

	private TokenResponseDto createToken() {
		TokenResponseDto token = new TokenResponseDto();
		token.setAccessToken("eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJHZXR3YXkgcGF");
		token.setTokenType("Bearer");
		token.setExpiresIn("180");
		return token;
	}

}
