package kg.banksystem.deliveryclient.service.impl;

import kg.banksystem.deliveryclient.dto.admin.response.ListUserResponseMessageDTO;
import kg.banksystem.deliveryclient.dto.bank.request.UsersWithRoleRequestDTO;
import kg.banksystem.deliveryclient.service.BankService;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BankServiceImpl implements BankService {

    public static final String ADDRESS_BANK = "http://localhost:8888/api/bank/";

    @Override
    public ListUserResponseMessageDTO getAllUsers(String token, UsersWithRoleRequestDTO users) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Bearer " + token);
        HttpEntity<?> entity = new HttpEntity<>(users, headers);
        ResponseEntity<ListUserResponseMessageDTO> response = restTemplate.exchange(ADDRESS_BANK + "users", HttpMethod.POST, entity, ListUserResponseMessageDTO.class);
        return response.getBody();
    }
}