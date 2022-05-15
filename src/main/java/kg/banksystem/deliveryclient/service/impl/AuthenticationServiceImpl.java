package kg.banksystem.deliveryclient.service.impl;

import kg.banksystem.deliveryclient.dto.auth.request.AuthenticationRequestDTO;
import kg.banksystem.deliveryclient.dto.auth.response.TokenResponseMessageDTO;
import kg.banksystem.deliveryclient.service.AuthenticationService;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    public static final String COOKIE_NAME = "token";
    public static final String ADDRESS_AUTH = "http://localhost:5000/api/auth/";

    @Override
    public TokenResponseMessageDTO getAuthentication(AuthenticationRequestDTO authenticationRequestDTO) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<?> entity = new HttpEntity<Object>(authenticationRequestDTO, headers);
        ResponseEntity<TokenResponseMessageDTO> response = restTemplate.exchange(ADDRESS_AUTH + "login", HttpMethod.POST, entity, TokenResponseMessageDTO.class);
        return response.getBody();
    }
}