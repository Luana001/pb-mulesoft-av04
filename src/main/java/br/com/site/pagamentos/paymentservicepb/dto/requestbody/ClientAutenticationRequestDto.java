package br.com.site.pagamentos.paymentservicepb.dto.requestbody;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientAutenticationRequestDto {
	@JsonProperty("client_id")
	String ClientId;
	@JsonProperty("api_key")
	String ApiKey;
}
