package kg.banksystem.deliveryclient.service.impl;

import kg.banksystem.deliveryclient.dto.admin.request.UserRequestDTO;
import kg.banksystem.deliveryclient.dto.admin.response.BranchStatisticResponseMessageDTO;
import kg.banksystem.deliveryclient.dto.admin.response.UserDetailResponse;
import kg.banksystem.deliveryclient.dto.bank.response.ListCardResponseMessageDTO;
import kg.banksystem.deliveryclient.dto.bank.response.ListClientResponseMessageDTO;
import kg.banksystem.deliveryclient.dto.baseresponse.LogicalResponseMessageDTO;
import kg.banksystem.deliveryclient.dto.baseresponse.SimpleResponseMessageDTO;
import kg.banksystem.deliveryclient.dto.branch.response.ListOrderResponseMessageDTO;
import kg.banksystem.deliveryclient.dto.branch.response.ListOrderStoryResponseMessageDTO;
import kg.banksystem.deliveryclient.service.ControlService;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ControlServiceImpl implements ControlService {

    public static final String ADDRESS_CONTROL = "http://localhost:5000/api/control/";

    @Override
    public LogicalResponseMessageDTO blockUser(String token, UserRequestDTO userRequestDTO) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Bearer " + token);
        HttpEntity<?> entity = new HttpEntity<>(userRequestDTO, headers);
        ResponseEntity<LogicalResponseMessageDTO> response = restTemplate.exchange(ADDRESS_CONTROL + "user/block", HttpMethod.POST, entity, LogicalResponseMessageDTO.class);
        return response.getBody();
    }

    @Override
    public LogicalResponseMessageDTO unblockUser(String token, UserRequestDTO userRequestDTO) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Bearer " + token);
        HttpEntity<?> entity = new HttpEntity<>(userRequestDTO, headers);
        ResponseEntity<LogicalResponseMessageDTO> response = restTemplate.exchange(ADDRESS_CONTROL + "user/unblock", HttpMethod.POST, entity, LogicalResponseMessageDTO.class);
        return response.getBody();
    }

    @Override
    public UserDetailResponse getUserById(String token, UserRequestDTO userRequestDTO) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Bearer " + token);
        HttpEntity<?> entity = new HttpEntity<>(userRequestDTO, headers);
        ResponseEntity<UserDetailResponse> response = restTemplate.exchange(ADDRESS_CONTROL + "users/detail", HttpMethod.POST, entity, UserDetailResponse.class);
        return response.getBody();
    }

    @Override
    public SimpleResponseMessageDTO registerUser(String token, UserRequestDTO userRequestDTO) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Bearer " + token);
        HttpEntity<?> entity = new HttpEntity<>(userRequestDTO, headers);
        ResponseEntity<SimpleResponseMessageDTO> response = restTemplate.exchange(ADDRESS_CONTROL + "users/register", HttpMethod.POST, entity, SimpleResponseMessageDTO.class);
        return response.getBody();
    }

    @Override
    public SimpleResponseMessageDTO updateUser(String token, UserRequestDTO userRequestDTO) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Bearer " + token);
        HttpEntity<?> entity = new HttpEntity<>(userRequestDTO, headers);
        ResponseEntity<SimpleResponseMessageDTO> response = restTemplate.exchange(ADDRESS_CONTROL + "users/update", HttpMethod.POST, entity, SimpleResponseMessageDTO.class);
        return response.getBody();
    }

    @Override
    public ListOrderResponseMessageDTO getAllActiveOrders(String token, int page, Long number, Long branch) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Bearer " + token);
        HttpEntity<?> entity = new HttpEntity<>(headers);
        ResponseEntity<ListOrderResponseMessageDTO> response;
        if (number != null) {
            response = restTemplate.exchange(ADDRESS_CONTROL + "orders?page=0&order=" + number, HttpMethod.POST, entity, ListOrderResponseMessageDTO.class);
        } else if (branch != null) {
            response = restTemplate.exchange(ADDRESS_CONTROL + "orders?page=" + page + "&branch=" + branch, HttpMethod.POST, entity, ListOrderResponseMessageDTO.class);
        } else {
            response = restTemplate.exchange(ADDRESS_CONTROL + "orders?page=" + page, HttpMethod.POST, entity, ListOrderResponseMessageDTO.class);
        }
        return response.getBody();
    }

    @Override
    public ListOrderStoryResponseMessageDTO getAllOrderStory(String token, int page, Long number, Long branch, Long courier) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Bearer " + token);
        HttpEntity<?> entity = new HttpEntity<>(headers);
        ResponseEntity<ListOrderStoryResponseMessageDTO> response;
        if (number != null) {
            response = restTemplate.exchange(ADDRESS_CONTROL + "story?page=0&order=" + number, HttpMethod.POST, entity, ListOrderStoryResponseMessageDTO.class);
        } else if (courier != null) {
            response = restTemplate.exchange(ADDRESS_CONTROL + "story?page=" + page + "&courier=" + courier, HttpMethod.POST, entity, ListOrderStoryResponseMessageDTO.class);
        } else if (branch != null) {
            response = restTemplate.exchange(ADDRESS_CONTROL + "story?page=" + page + "&branch=" + branch, HttpMethod.POST, entity, ListOrderStoryResponseMessageDTO.class);
        } else {
            response = restTemplate.exchange(ADDRESS_CONTROL + "story?page=" + page, HttpMethod.POST, entity, ListOrderStoryResponseMessageDTO.class);
        }
        return response.getBody();
    }

    @Override
    public BranchStatisticResponseMessageDTO getStatistics(String token) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Bearer " + token);
        HttpEntity<?> entity = new HttpEntity<>(headers);
        ResponseEntity<BranchStatisticResponseMessageDTO> response = restTemplate.exchange(ADDRESS_CONTROL + "statistic", HttpMethod.POST, entity, BranchStatisticResponseMessageDTO.class);
        return response.getBody();
    }

    @Override
    public ListCardResponseMessageDTO getCards(String token) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Bearer " + token);
        HttpEntity<?> entity = new HttpEntity<>(headers);
        ResponseEntity<ListCardResponseMessageDTO> response = restTemplate.exchange(ADDRESS_CONTROL + "cards", HttpMethod.POST, entity, ListCardResponseMessageDTO.class);
        return response.getBody();
    }

    @Override
    public ListClientResponseMessageDTO getClients(String token) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Bearer " + token);
        HttpEntity<?> entity = new HttpEntity<>(headers);
        ResponseEntity<ListClientResponseMessageDTO> response = restTemplate.exchange(ADDRESS_CONTROL + "bank-clients", HttpMethod.POST, entity, ListClientResponseMessageDTO.class);
        return response.getBody();
    }
}