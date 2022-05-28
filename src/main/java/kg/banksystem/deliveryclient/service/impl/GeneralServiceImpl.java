package kg.banksystem.deliveryclient.service.impl;

import kg.banksystem.deliveryclient.dto.account.response.RoleResponseMessageDTO;
import kg.banksystem.deliveryclient.dto.admin.response.BranchReportResponseMessageDTO;
import kg.banksystem.deliveryclient.dto.admin.response.ListBranchResponseMessageDTO;
import kg.banksystem.deliveryclient.dto.admin.response.ListRoleResponseMessageDTO;
import kg.banksystem.deliveryclient.dto.admin.response.ListUserResponseMessageDTO;
import kg.banksystem.deliveryclient.dto.baseresponse.SimpleResponseMessageDTO;
import kg.banksystem.deliveryclient.dto.branch.request.OrderRequestDTO;
import kg.banksystem.deliveryclient.dto.branch.request.OrderStoryRequestDTO;
import kg.banksystem.deliveryclient.dto.branch.response.OrderResponseMessageDTO;
import kg.banksystem.deliveryclient.dto.branch.response.OrderStoryResponseMessageDTO;
import kg.banksystem.deliveryclient.service.GeneralService;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Service
public class GeneralServiceImpl implements GeneralService {

    public static final String ADDRESS_ALL = "http://localhost:5000/api/all/";

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
    public OrderResponseMessageDTO getOrderById(String token, OrderRequestDTO orderRequestDTO) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Bearer " + token);
        HttpEntity<?> entity = new HttpEntity<>(orderRequestDTO, headers);
        ResponseEntity<OrderResponseMessageDTO> response = restTemplate.exchange(ADDRESS_ALL + "orders/detail", HttpMethod.POST, entity, OrderResponseMessageDTO.class);
        return response.getBody();
    }

    @Override
    public OrderStoryResponseMessageDTO getStoryById(String token, OrderStoryRequestDTO orderStoryRequestDTO) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Bearer " + token);
        HttpEntity<?> entity = new HttpEntity<>(orderStoryRequestDTO, headers);
        ResponseEntity<OrderStoryResponseMessageDTO> response = restTemplate.exchange(ADDRESS_ALL + "orders/detail", HttpMethod.POST, entity, OrderStoryResponseMessageDTO.class);
        return response.getBody();
    }

    @Override
    public ListBranchResponseMessageDTO getBranches(String token) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Bearer " + token);
        HttpEntity<?> entity = new HttpEntity<>(headers);
        ResponseEntity<ListBranchResponseMessageDTO> response = restTemplate.exchange(ADDRESS_ALL + "branches", HttpMethod.POST, entity, ListBranchResponseMessageDTO.class);
        return response.getBody();
    }

    @Override
    public ListRoleResponseMessageDTO getRoles(String token) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Bearer " + token);
        HttpEntity<?> entity = new HttpEntity<>(headers);
        ResponseEntity<ListRoleResponseMessageDTO> response = restTemplate.exchange(ADDRESS_ALL + "roles", HttpMethod.POST, entity, ListRoleResponseMessageDTO.class);
        return response.getBody();
    }

    @Override
    public ListUserResponseMessageDTO getCouriers(String token) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Bearer " + token);
        HttpEntity<?> entity = new HttpEntity<>(headers);
        ResponseEntity<ListUserResponseMessageDTO> response = restTemplate.exchange(ADDRESS_ALL + "couriers", HttpMethod.POST, entity, ListUserResponseMessageDTO.class);
        return response.getBody();
    }

    @Override
    public BranchReportResponseMessageDTO getReport(String token, String branch, String period) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Bearer " + token);
        HttpEntity<?> entity = new HttpEntity<>(headers);
        ResponseEntity<BranchReportResponseMessageDTO> response = restTemplate.exchange(ADDRESS_ALL + ("report?branch=" + branch + "&period=" + period), HttpMethod.POST, entity, BranchReportResponseMessageDTO.class);
        return response.getBody();
    }
}