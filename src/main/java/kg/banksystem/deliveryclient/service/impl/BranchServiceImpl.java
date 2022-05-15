package kg.banksystem.deliveryclient.service.impl;

import kg.banksystem.deliveryclient.dto.admin.response.BranchStatisticResponseMessageDTO;
import kg.banksystem.deliveryclient.dto.admin.response.ListUserResponseMessageDTO;
import kg.banksystem.deliveryclient.dto.baseresponse.LogicalResponseMessageDTO;
import kg.banksystem.deliveryclient.dto.baseresponse.SimpleResponseMessageDTO;
import kg.banksystem.deliveryclient.dto.branch.request.OrderRequestDTO;
import kg.banksystem.deliveryclient.dto.branch.request.OrderStatusRequestDTO;
import kg.banksystem.deliveryclient.dto.branch.response.BranchCourierResponseMessageDTO;
import kg.banksystem.deliveryclient.dto.branch.response.ListOrderResponseMessageDTO;
import kg.banksystem.deliveryclient.dto.branch.response.ListOrderStoryResponseMessageDTO;
import kg.banksystem.deliveryclient.service.BranchService;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Service
public class BranchServiceImpl implements BranchService {

    public static final String ADDRESS_BRANCH = "http://localhost:5000/api/branch/";

    @Override
    public ListOrderResponseMessageDTO getOrdersForBranch(String token, OrderStatusRequestDTO orderStatusRequestDTO, int page, Long number) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Bearer " + token);
        HttpEntity<?> entity = new HttpEntity<>(orderStatusRequestDTO, headers);
        ResponseEntity<ListOrderResponseMessageDTO> response;
        if (number != null) {
            response = restTemplate.exchange(ADDRESS_BRANCH + "orders?page=0&orderNumber=" + number, HttpMethod.POST, entity, ListOrderResponseMessageDTO.class);
        } else {
            response = restTemplate.exchange(ADDRESS_BRANCH + "orders?page=" + page, HttpMethod.POST, entity, ListOrderResponseMessageDTO.class);
        }
        return response.getBody();
    }

    @Override
    public LogicalResponseMessageDTO changeStatusReadyFromDelivery(String token, OrderRequestDTO orderRequestDTO) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Bearer " + token);
        HttpEntity<?> entity = new HttpEntity<>(orderRequestDTO, headers);
        ResponseEntity<LogicalResponseMessageDTO> response = restTemplate.exchange(ADDRESS_BRANCH + "orders/change/ready-from-delivery", HttpMethod.POST, entity, LogicalResponseMessageDTO.class);
        return response.getBody();
    }

    @Override
    public LogicalResponseMessageDTO changeStatusDestroyed(String token, OrderRequestDTO orderRequestDTO) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Bearer " + token);
        HttpEntity<?> entity = new HttpEntity<>(orderRequestDTO, headers);
        ResponseEntity<LogicalResponseMessageDTO> response = restTemplate.exchange(ADDRESS_BRANCH + "orders/change/destroyed", HttpMethod.POST, entity, LogicalResponseMessageDTO.class);
        return response.getBody();
    }

    @Override
    public ListOrderStoryResponseMessageDTO getOrderStoryForBranch(String token, int page, Long number, Long courier) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Bearer " + token);
        HttpEntity<?> entity = new HttpEntity<>(headers);
        ResponseEntity<ListOrderStoryResponseMessageDTO> response;
        if (number != null) {
            response = restTemplate.exchange(ADDRESS_BRANCH + "story?page=0&orderNumber=" + number, HttpMethod.POST, entity, ListOrderStoryResponseMessageDTO.class);
        } else if (courier != null) {
            response = restTemplate.exchange(ADDRESS_BRANCH + "story?page=" + page + "&courierId=" + courier, HttpMethod.POST, entity, ListOrderStoryResponseMessageDTO.class);
        } else {
            response = restTemplate.exchange(ADDRESS_BRANCH + "story?page=" + page, HttpMethod.POST, entity, ListOrderStoryResponseMessageDTO.class);
        }
        return response.getBody();
    }

    @Override
    public SimpleResponseMessageDTO getQrForOrder(String token, OrderRequestDTO orderRequestDTO) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Bearer " + token);
        HttpEntity<?> entity = new HttpEntity<>(orderRequestDTO, headers);
        ResponseEntity<SimpleResponseMessageDTO> response = restTemplate.exchange(ADDRESS_BRANCH + "orders/qr", HttpMethod.POST, entity, SimpleResponseMessageDTO.class);
        return response.getBody();
    }

    @Override
    public ListUserResponseMessageDTO getCouriersByBranch(String token) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Bearer " + token);
        HttpEntity<?> entity = new HttpEntity<>(headers);
        ResponseEntity<ListUserResponseMessageDTO> response = restTemplate.exchange(ADDRESS_BRANCH + "couriers", HttpMethod.POST, entity, ListUserResponseMessageDTO.class);
        return response.getBody();
    }

    @Override
    public BranchStatisticResponseMessageDTO getStatistics(String token) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Bearer " + token);
        HttpEntity<?> entity = new HttpEntity<>(headers);
        ResponseEntity<BranchStatisticResponseMessageDTO> response = restTemplate.exchange(ADDRESS_BRANCH + "statistic", HttpMethod.POST, entity, BranchStatisticResponseMessageDTO.class);
        return response.getBody();
    }

    @Override
    public SimpleResponseMessageDTO getBranchByToken(String token) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Bearer " + token);
        HttpEntity<?> entity = new HttpEntity<>(headers);
        ResponseEntity<SimpleResponseMessageDTO> response = restTemplate.exchange(ADDRESS_BRANCH + "get/branch", HttpMethod.POST, entity, SimpleResponseMessageDTO.class);
        return Objects.requireNonNull(response.getBody());
    }

    @Override
    public BranchCourierResponseMessageDTO getCourierStatistics(String token) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Bearer " + token);
        HttpEntity<?> entity = new HttpEntity<>(headers);
        ResponseEntity<BranchCourierResponseMessageDTO> response = restTemplate.exchange(ADDRESS_BRANCH + "/statistics/couriers", HttpMethod.POST, entity, BranchCourierResponseMessageDTO.class);
        return response.getBody();
    }
}