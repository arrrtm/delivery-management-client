package kg.banksystem.deliveryclient.service.impl;

import kg.banksystem.deliveryclient.dto.account.response.RoleResponseMessageDTO;
import kg.banksystem.deliveryclient.dto.admin.response.ListUserResponseMessageDTO;
import kg.banksystem.deliveryclient.dto.baseresponse.SimpleListResponseMessageDTO;
import kg.banksystem.deliveryclient.dto.baseresponse.SimpleResponseMessageDTO;
import kg.banksystem.deliveryclient.dto.branch.request.OrderRequestDTO;
import kg.banksystem.deliveryclient.dto.branch.request.OrderStoryRequestDTO;
import kg.banksystem.deliveryclient.dto.branch.response.ListOrderResponseMessageDTO;
import kg.banksystem.deliveryclient.dto.branch.response.ListOrderStoryResponseMessageDTO;
import kg.banksystem.deliveryclient.dto.branch.response.OrderResponseMessageDTO;
import kg.banksystem.deliveryclient.dto.branch.response.OrderStoryResponseMessageDTO;
import kg.banksystem.deliveryclient.service.GeneralService;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Service
public class GeneralServiceImpl implements GeneralService {

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
    public ListOrderResponseMessageDTO getAllActiveOrders(String token, int page, Long number, String branch) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Bearer " + token);
        HttpEntity<?> entity = new HttpEntity<>(headers);
        ResponseEntity<ListOrderResponseMessageDTO> response;
        if (number != null) {
            response = restTemplate.exchange(ADDRESS_ALL + "orders?page=0&orderNumber=" + number, HttpMethod.POST, entity, ListOrderResponseMessageDTO.class);
        } else if (!branch.isEmpty()) {
            response = restTemplate.exchange(ADDRESS_ALL + "orders?page=" + page + "&branchName=" + branch, HttpMethod.POST, entity, ListOrderResponseMessageDTO.class);
        } else {
            response = restTemplate.exchange(ADDRESS_ALL + "orders?page=" + page, HttpMethod.POST, entity, ListOrderResponseMessageDTO.class);
        }
        return response.getBody();
    }

    @Override
    public ListOrderStoryResponseMessageDTO getAllOrderStory(String token, int page, Long number, String branch, Long courier) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Bearer " + token);
        HttpEntity<?> entity = new HttpEntity<>(headers);
        ResponseEntity<ListOrderStoryResponseMessageDTO> response;
        if (number != null) {
            response = restTemplate.exchange(ADDRESS_ALL + "story?page=0&orderNumber=" + number, HttpMethod.POST, entity, ListOrderStoryResponseMessageDTO.class);
        } else if (courier != null) {
            response = restTemplate.exchange(ADDRESS_ALL + "story?page=" + page + "&courierId=" + courier, HttpMethod.POST, entity, ListOrderStoryResponseMessageDTO.class);
        } else if (!branch.isEmpty()) {
            response = restTemplate.exchange(ADDRESS_ALL + "story?page=" + page + "&branchName=" + branch, HttpMethod.POST, entity, ListOrderStoryResponseMessageDTO.class);
        } else {
            response = restTemplate.exchange(ADDRESS_ALL + "story?page=" + page, HttpMethod.POST, entity, ListOrderStoryResponseMessageDTO.class);
        }
        return response.getBody();
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
    public SimpleListResponseMessageDTO getBranchNames(String token) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Bearer " + token);
        HttpEntity<?> entity = new HttpEntity<>(headers);
        ResponseEntity<SimpleListResponseMessageDTO> response = restTemplate.exchange(ADDRESS_ALL + "branches", HttpMethod.POST, entity, SimpleListResponseMessageDTO.class);
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
}