package kg.banksystem.deliveryclient.service.impl;

import kg.banksystem.deliveryclient.dto.admin.response.ListBranchResponseMessageDTO;
import kg.banksystem.deliveryclient.dto.admin.response.ListUserResponseMessageDTO;
import kg.banksystem.deliveryclient.dto.branch.response.ListOrderResponseMessageDTO;
import kg.banksystem.deliveryclient.service.AdminService;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AdminServiceImpl implements AdminService {

    public static final String ADDRESS_ADMIN = "http://localhost:8888/api/admin/";

    @Override
    public ListUserResponseMessageDTO getAllUsers(String token) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Bearer " + token);
        HttpEntity<?> entity = new HttpEntity<>(headers);
        ResponseEntity<ListUserResponseMessageDTO> response = restTemplate.exchange(ADDRESS_ADMIN + "users", HttpMethod.POST, entity, ListUserResponseMessageDTO.class);
        return response.getBody();
    }

    @Override
    public ListBranchResponseMessageDTO getAllBranches(String token) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Bearer " + token);
        HttpEntity<?> entity = new HttpEntity<>(headers);
        ResponseEntity<ListBranchResponseMessageDTO> response = restTemplate.exchange(ADDRESS_ADMIN + "branches", HttpMethod.POST, entity, ListBranchResponseMessageDTO.class);
        return response.getBody();
    }

    @Override
    public ListOrderResponseMessageDTO getAllActiveOrders(String token) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Bearer " + token);
        HttpEntity<?> entity = new HttpEntity<>(headers);
        ResponseEntity<ListOrderResponseMessageDTO> response = restTemplate.exchange(ADDRESS_ADMIN + "orders", HttpMethod.POST, entity, ListOrderResponseMessageDTO.class);
        return response.getBody();
    }
}