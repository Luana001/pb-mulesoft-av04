package br.com.site.pagamentos.paymentservicepb.service;

import java.time.LocalDateTime;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.site.pagamentos.paymentservicepb.constant.SellerInformation;
import br.com.site.pagamentos.paymentservicepb.dto.requestbody.ClientAutenticationRequestDto;
import br.com.site.pagamentos.paymentservicepb.dto.response.TokenResponseDto;

@Service
public class AuthenticationService {
	public TokenResponseDto authenticate(TokenResponseDto token, LocalDateTime time) {
		if(token.getAccessToken() == null || time.compareTo(LocalDateTime.now()) < 0) {   
	        TokenResponseDto newToken = getToken();
	        newToken.setTokenExpirationTime(LocalDateTime.now().plusMinutes(3));
	        System.out.println(token.getAccessToken());
	        System.out.println(newToken.getAccessToken());
	        return newToken;
		}
		System.out.println(token.getAccessToken());
		return token;
    }
	
	public TokenResponseDto getToken(){
		String url = "https://pb-getway-payment.herokuapp.com/v1/auth";
        RestTemplate restTemplate = new RestTemplate();
        SellerInformation sellerInformation = new SellerInformation();

        ClientAutenticationRequestDto clientAutenticationDto = new ClientAutenticationRequestDto(
        		sellerInformation.getSellerClientId(),
        		sellerInformation.getSellerApiKey());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<ClientAutenticationRequestDto> entity = new HttpEntity<ClientAutenticationRequestDto>(clientAutenticationDto,headers);
        return restTemplate.exchange(url, HttpMethod.POST, entity, TokenResponseDto.class).getBody();
	}
}
