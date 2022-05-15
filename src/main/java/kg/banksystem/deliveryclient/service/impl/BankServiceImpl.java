package kg.banksystem.deliveryclient.service.impl;

import kg.banksystem.deliveryclient.dto.admin.response.ListUserResponseMessageDTO;
import kg.banksystem.deliveryclient.dto.bank.request.ClientRequestDTO;
import kg.banksystem.deliveryclient.dto.bank.request.UsersWithRoleRequestDTO;
import kg.banksystem.deliveryclient.dto.bank.response.ClientResponseMessageDTO;
import kg.banksystem.deliveryclient.dto.bank.response.ListClientResponseMessageDTO;
import kg.banksystem.deliveryclient.dto.baseresponse.LogicalResponseMessageDTO;
import kg.banksystem.deliveryclient.dto.baseresponse.SimpleResponseMessageDTO;
import kg.banksystem.deliveryclient.dto.branch.request.OrderRequestDTO;
import kg.banksystem.deliveryclient.service.BankService;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BankServiceImpl implements BankService {

    public static final String ADDRESS_BANK = "http://localhost:5000/api/bank/";

    @Override
    public ListUserResponseMessageDTO getAllUsers(String token, UsersWithRoleRequestDTO users, int page) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Bearer " + token);
        HttpEntity<?> entity = new HttpEntity<>(users, headers);
        ResponseEntity<ListUserResponseMessageDTO> response = restTemplate.exchange(ADDRESS_BANK + "users?page=" + page, HttpMethod.POST, entity, ListUserResponseMessageDTO.class);
        return response.getBody();
    }

    @Override
    public ListClientResponseMessageDTO getAllClients(String token, int page) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Bearer " + token);
        HttpEntity<?> entity = new HttpEntity<>(headers);
        ResponseEntity<ListClientResponseMessageDTO> response = restTemplate.exchange(ADDRESS_BANK + "clients?page=" + page, HttpMethod.POST, entity, ListClientResponseMessageDTO.class);
        return response.getBody();
    }

    @Override
    public ClientResponseMessageDTO getClientById(String token, ClientRequestDTO clientRequestDTO) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Bearer " + token);
        HttpEntity<?> entity = new HttpEntity<>(clientRequestDTO, headers);
        ResponseEntity<ClientResponseMessageDTO> response = restTemplate.exchange(ADDRESS_BANK + "clients/detail", HttpMethod.POST, entity, ClientResponseMessageDTO.class);
        return response.getBody();
    }

    @Override
    public SimpleResponseMessageDTO addClient(String token, ClientRequestDTO clientRequestDTO) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Bearer " + token);
        HttpEntity<?> entity = new HttpEntity<>(clientRequestDTO, headers);
        ResponseEntity<SimpleResponseMessageDTO> response = restTemplate.exchange(ADDRESS_BANK + "clients/add", HttpMethod.POST, entity, SimpleResponseMessageDTO.class);
        return response.getBody();
    }

    @Override
    public SimpleResponseMessageDTO editClient(String token, ClientRequestDTO clientRequestDTO) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Bearer " + token);
        HttpEntity<?> entity = new HttpEntity<>(clientRequestDTO, headers);
        ResponseEntity<SimpleResponseMessageDTO> response = restTemplate.exchange(ADDRESS_BANK + "clients/edit", HttpMethod.POST, entity, SimpleResponseMessageDTO.class);
        return response.getBody();
    }

    @Override
    public SimpleResponseMessageDTO deleteClient(String token, ClientRequestDTO clientRequestDTO) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Bearer " + token);
        HttpEntity<?> entity = new HttpEntity<>(clientRequestDTO, headers);
        ResponseEntity<SimpleResponseMessageDTO> response = restTemplate.exchange(ADDRESS_BANK + "clients/delete", HttpMethod.POST, entity, SimpleResponseMessageDTO.class);
        return response.getBody();
    }

    // IN PROGRESS
    @Override
    public SimpleResponseMessageDTO addOrder(String token, OrderRequestDTO orderRequestDTO) {
        return null;
    }

    // IN PROGRESS
    @Override
    public SimpleResponseMessageDTO editOrder(String token, OrderRequestDTO orderRequestDTO) {
        return null;
    }

    // IN PROGRESS
    @Override
    public SimpleResponseMessageDTO deleteOrder(String token, OrderRequestDTO orderRequestDTO) {
        return null;
    }

    @Override
    public LogicalResponseMessageDTO changeStatusSentToBranch(String token, OrderRequestDTO orderRequestDTO) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Bearer " + token);
        HttpEntity<?> entity = new HttpEntity<>(orderRequestDTO, headers);
        ResponseEntity<LogicalResponseMessageDTO> response = restTemplate.exchange(ADDRESS_BANK + "orders/change/sent-to-branch", HttpMethod.POST, entity, LogicalResponseMessageDTO.class);
        return response.getBody();
    }
}