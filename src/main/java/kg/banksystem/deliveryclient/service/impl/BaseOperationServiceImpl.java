package kg.banksystem.deliveryclient.service.impl;

import kg.banksystem.deliveryclient.dto.account.response.RoleResponseMessageDTO;
import kg.banksystem.deliveryclient.dto.account.response.SimpleResponseMessageDTO;
import kg.banksystem.deliveryclient.dto.branch.request.OrderRequestDTO;
import kg.banksystem.deliveryclient.dto.branch.response.OrderResponseMessageDTO;
import kg.banksystem.deliveryclient.service.BaseOperationService;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Service
public class BaseOperationServiceImpl implements BaseOperationService {

    public static final String ADDRESS_ALL = "http://localhost:8888/api/all/";

    @Override
    public String getRoleByToken(String token) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Bearer " + token);
        HttpEntity<?> entity = new HttpEntity<>(headers);
        ResponseEntity<RoleResponseMessageDTO> response = restTemplate.exchange(ADDRESS_ALL + "get/role", HttpMethod.POST, entity, RoleResponseMessageDTO.class);
        return Objects.requireNonNull(response.getBody()).getData().getName();
    }

    @Override
    public String getNameByToken(String token) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Bearer " + token);
        HttpEntity<?> entity = new HttpEntity<>(headers);
        ResponseEntity<SimpleResponseMessageDTO> response = restTemplate.exchange(ADDRESS_ALL + "get/name", HttpMethod.POST, entity, SimpleResponseMessageDTO.class);
        return Objects.requireNonNull(response.getBody()).getData();
    }

    @Override
    public OrderResponseMessageDTO getOrderDetail(String token, OrderRequestDTO orderRequestDTO) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Bearer " + token);
        HttpEntity<?> entity = new HttpEntity<>(orderRequestDTO, headers);
        ResponseEntity<OrderResponseMessageDTO> response = restTemplate.exchange(ADDRESS_ALL + "orders/detail", HttpMethod.POST, entity, OrderResponseMessageDTO.class);
        return response.getBody();
    }
}