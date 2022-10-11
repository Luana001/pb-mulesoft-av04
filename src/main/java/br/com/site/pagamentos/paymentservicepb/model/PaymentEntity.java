package br.com.site.pagamentos.paymentservicepb.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.site.pagamentos.paymentservicepb.constant.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "payments")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentEntity {
	
	@Id @Column(name = "payment_id")
	String paymentId;
	
	@Column(name = "order_id")
	Long orderId;
	
	BigDecimal total;
	
	@Column(name = "payment_status")
	@Enumerated(EnumType.STRING)
	PaymentStatus paymentStatus;

	String message;
}
