package br.com.site.pagamentos.paymentservicepb.dto.response;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthorizationResponseDto {
	@JsonProperty("authorization_code")
	String authorizationCode;
	@JsonProperty("authorized_at")
	LocalDate authorizedAt;
	@JsonProperty("reason_code")
	int reasonCode;
	@JsonProperty("reason_message")
	String reasonMessage;
}
