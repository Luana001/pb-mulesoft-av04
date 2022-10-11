package br.com.site.pagamentos.paymentservicepb.dto.requestbody;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.site.pagamentos.paymentservicepb.constant.DocumentType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRequestDto {
	@JsonProperty("document_type")
	DocumentType documentType;
	@JsonProperty("document_number")
	String documentNumber;
}
