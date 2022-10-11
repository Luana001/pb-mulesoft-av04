package br.com.site.pagamentos.paymentservicepb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.site.pagamentos.paymentservicepb.model.PaymentEntity;

public interface PaymentRepository extends JpaRepository<PaymentEntity, String>{
}
