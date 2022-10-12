package br.com.site.pagamentos.paymentservicepb.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.site.pagamentos.paymentservicepb.constant.PaymentStatus;
import br.com.site.pagamentos.paymentservicepb.dto.PaymentDto;
import br.com.site.pagamentos.paymentservicepb.form.CardForm;
import br.com.site.pagamentos.paymentservicepb.form.ItemForm;
import br.com.site.pagamentos.paymentservicepb.form.OrderForm;
import br.com.site.pagamentos.paymentservicepb.model.PaymentEntity;
import br.com.site.pagamentos.paymentservicepb.repository.PaymentRepository;

@ExtendWith(MockitoExtension.class)
class PaymentServiceTest {

	@Mock
	private PaymentRepository paymentRepository;

	@Mock
	private PaymentService paymentService;

	@Mock
	private OrderService orderService;

	@Mock
	private AuthenticationService authenticationService;
	
	@BeforeEach
	void inicialize() {
		this.paymentService = new PaymentService(this.paymentRepository, this.orderService, this.authenticationService);
	}

	@Test
	void deveRetornarListaComPagamentosConvertidosParaDto() {
		List<PaymentEntity> payments = createPaymentsList();
		List<PaymentDto> paymentsDto = createPaymentsDtoList();

		Mockito.when(paymentRepository.findAll()).thenReturn(payments);
		List<PaymentDto> newpaymentsDto = paymentService.getAllPayments();

		Mockito.verify(paymentRepository).findAll();
		assertEquals(paymentsDto.get(0).getPaymentId(), newpaymentsDto.get(0).getPaymentId());
		assertEquals(paymentsDto.get(1).getPaymentId(), newpaymentsDto.get(1).getPaymentId());
	}

	@Test
	void deveRetornarSomenteUmPagamentoBuscadoPeloId() {
		Optional<PaymentEntity> paymentOptional = Optional.of(createPaymentsList().get(0));

		Mockito.when(paymentRepository.findById(Mockito.any())).thenReturn(paymentOptional);
		ResponseEntity<PaymentDto> responseEntity = paymentService.findById(Mockito.anyString());
		Mockito.verify(paymentRepository).findById(Mockito.any());

		assertEquals(new ResponseEntity<>(HttpStatus.OK).getStatusCode(), responseEntity.getStatusCode());
	}

	@Test
	void naoDeveriaRetornarItemConvertidoDePagamentoEnityParaDtoSeOMesmoNaoForEncontrado() {
		ResponseEntity<PaymentDto> paymentDto = paymentService.findById("dfgh");

		assertEquals(new ResponseEntity<>(HttpStatus.NOT_FOUND).getStatusCodeValue(),
				paymentDto.getStatusCodeValue());
	}

	private List<PaymentEntity> createPaymentsList() { 
		List<PaymentEntity> payments = new ArrayList<>();
		payments.add(new PaymentEntity("1c3a1fec-9c4b-4b86-a2a2-ff537d29d3c7", 194526320L, new BigDecimal("282.49"),
				PaymentStatus.APPROVED, "transaction approved"));

		payments.add(new PaymentEntity("1c3a1fec-4b86-a2a2-ff537d-9c4b-29d3c7", 194526321L, new BigDecimal("623.25"),
				PaymentStatus.REPROVED, "transaction reproved"));
		return payments;
	}

	private List<PaymentDto> createPaymentsDtoList() {
		List<PaymentDto> paymentsDto = new ArrayList<>();
		paymentsDto.add(new PaymentDto(194526320L, new BigDecimal("282.49"), "1c3a1fec-9c4b-4b86-a2a2-ff537d29d3c7",
				PaymentStatus.APPROVED, "transaction approved"));

		paymentsDto.add(new PaymentDto(194526321L, new BigDecimal("623.25"), "1c3a1fec-4b86-a2a2-ff537d-9c4b-29d3c7",
				PaymentStatus.REPROVED, "transaction reproved"));
		return paymentsDto;
	}
	
	public OrderForm createForm() {
		CardForm cardForm = new CardForm("3723544712123", 
				"CARLOS MONTE", "356", 1, 2023, "MASTERCARD");

		List<ItemForm> items = new ArrayList<>();
		items.add(new ItemForm("Item 2",new BigDecimal("44.6"), 4));
		items.add(new ItemForm("Item 2",new BigDecimal("55.6"), 2));

        OrderForm form = new OrderForm("44325592303",
                items, new BigDecimal("9.99"), new BigDecimal("4.46"), "CREDIT_CARD", 
                "BRL", cardForm);

		return form;
	}
}

