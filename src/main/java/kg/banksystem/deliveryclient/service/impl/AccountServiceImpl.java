package kg.banksystem.deliveryclient.service.impl;

import kg.banksystem.deliveryclient.dto.account.request.EditAccountRequestDTO;
import kg.banksystem.deliveryclient.dto.account.request.ResetPasswordRequestDTO;
import kg.banksystem.deliveryclient.dto.admin.response.UserResponseMessageDTO;
import kg.banksystem.deliveryclient.dto.baseresponse.LogicalResponseMessageDTO;
import kg.banksystem.deliveryclient.service.AccountService;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AccountServiceImpl implements AccountService {

    public static final String ADDRESS_ACCOUNT = "http://localhost:8888/api/account/";

    @Override
    public UserResponseMessageDTO viewPersonalArea(String token) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Bearer " + token);
        HttpEntity<?> entity = new HttpEntity<>(headers);
        ResponseEntity<UserResponseMessageDTO> response = restTemplate.exchange(ADDRESS_ACCOUNT + "view", HttpMethod.POST, entity, UserResponseMessageDTO.class);
        return response.getBody();
    }

    @Override
    public LogicalResponseMessageDTO editPersonalAccountData(String token, EditAccountRequestDTO editAccountRequestDTO) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Bearer " + token);
        HttpEntity<?> entity = new HttpEntity<>(editAccountRequestDTO, headers);
        ResponseEntity<LogicalResponseMessageDTO> response = restTemplate.exchange(ADDRESS_ACCOUNT + "edit", HttpMethod.POST, entity, LogicalResponseMessageDTO.class);
        return response.getBody();
    }

    @Override
    public LogicalResponseMessageDTO resetPasswordByEmail(ResetPasswordRequestDTO resetPasswordRequestDTO) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<?> entity = new HttpEntity<Object>(resetPasswordRequestDTO, headers);
        ResponseEntity<LogicalResponseMessageDTO> response = restTemplate.exchange(ADDRESS_ACCOUNT + "password/reset", HttpMethod.POST, entity, LogicalResponseMessageDTO.class);
        return response.getBody();
    }
}