package kg.banksystem.deliveryclient.service.impl;

import kg.banksystem.deliveryclient.dto.account.response.LogicalResponseMessageDTO;
import kg.banksystem.deliveryclient.dto.branch.request.OrderIdRequestDTO;
import kg.banksystem.deliveryclient.dto.branch.request.OrderStatusRequestDTO;
import kg.banksystem.deliveryclient.dto.branch.response.ListOrderResponseMessageDTO;
import kg.banksystem.deliveryclient.service.BranchService;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BranchServiceImpl implements BranchService {

    public static final String ADDRESS_BRANCH = "http://localhost:8888/api/branch/";

    @Override
    public ListOrderResponseMessageDTO getOrdersForBranch(String token, OrderStatusRequestDTO orderStatusRequestDTO) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Bearer " + token);
        HttpEntity<?> entity = new HttpEntity<>(orderStatusRequestDTO, headers);
        ResponseEntity<ListOrderResponseMessageDTO> response = restTemplate.exchange(ADDRESS_BRANCH + "orders", HttpMethod.POST, entity, ListOrderResponseMessageDTO.class);
        return response.getBody();
    }

    @Override
    public LogicalResponseMessageDTO changeStatusReadyFromDelivery(String token, OrderIdRequestDTO orderIdRequestDTO) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Bearer " + token);
        HttpEntity<?> entity = new HttpEntity<>(orderIdRequestDTO, headers);
        ResponseEntity<LogicalResponseMessageDTO> response = restTemplate.exchange(ADDRESS_BRANCH + "orders/change/ready_from_delivery", HttpMethod.POST, entity, LogicalResponseMessageDTO.class);
        return response.getBody();
    }

    @Override
    public LogicalResponseMessageDTO changeStatusDestroyed(String token, OrderIdRequestDTO orderIdRequestDTO) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Bearer " + token);
        HttpEntity<?> entity = new HttpEntity<>(orderIdRequestDTO, headers);
        ResponseEntity<LogicalResponseMessageDTO> response = restTemplate.exchange(ADDRESS_BRANCH + "orders/change/destroyed", HttpMethod.POST, entity, LogicalResponseMessageDTO.class);
        return response.getBody();
    }
}