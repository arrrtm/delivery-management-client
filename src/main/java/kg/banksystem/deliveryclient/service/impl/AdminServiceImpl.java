package kg.banksystem.deliveryclient.service.impl;

import kg.banksystem.deliveryclient.dto.admin.request.BranchRequestDTO;
import kg.banksystem.deliveryclient.dto.admin.request.UserRequestDTO;
import kg.banksystem.deliveryclient.dto.admin.response.BranchResponseMessageDTO;
import kg.banksystem.deliveryclient.dto.admin.response.ListBranchResponseMessageDTO;
import kg.banksystem.deliveryclient.dto.admin.response.ListUserResponseMessageDTO;
import kg.banksystem.deliveryclient.dto.admin.response.UserResponseMessageDTO;
import kg.banksystem.deliveryclient.dto.baseresponse.SimpleResponseMessageDTO;
import kg.banksystem.deliveryclient.service.AdminService;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AdminServiceImpl implements AdminService {

    public static final String ADDRESS_ADMIN = "http://localhost:8888/api/admin/";

    @Override
    public ListUserResponseMessageDTO getAllUsers(String token, int page) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Bearer " + token);
        HttpEntity<?> entity = new HttpEntity<>(headers);
        ResponseEntity<ListUserResponseMessageDTO> response = restTemplate.exchange(ADDRESS_ADMIN + "users?page=" + page, HttpMethod.POST, entity, ListUserResponseMessageDTO.class);
        return response.getBody();
    }

    @Override
    public UserResponseMessageDTO getUserById(String token, UserRequestDTO userRequestDTO) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Bearer " + token);
        HttpEntity<?> entity = new HttpEntity<>(userRequestDTO, headers);
        ResponseEntity<UserResponseMessageDTO> response = restTemplate.exchange(ADDRESS_ADMIN + "users/detail", HttpMethod.POST, entity, UserResponseMessageDTO.class);
        return response.getBody();
    }

    // IN PROGRESS
    @Override
    public SimpleResponseMessageDTO registerUser(String token, UserRequestDTO userRequestDTO) {
        return null;
    }

    // IN PROGRESS
    @Override
    public SimpleResponseMessageDTO updateUser(String token, UserRequestDTO userRequestDTO) {
        return null;
    }

    // IN PROGRESS
    @Override
    public SimpleResponseMessageDTO removeUser(String token, UserRequestDTO userRequestDTO) {
        return null;
    }

    @Override
    public ListBranchResponseMessageDTO getAllBranches(String token, int page) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Bearer " + token);
        HttpEntity<?> entity = new HttpEntity<>(headers);
        ResponseEntity<ListBranchResponseMessageDTO> response = restTemplate.exchange(ADDRESS_ADMIN + "branches?page=" + page, HttpMethod.POST, entity, ListBranchResponseMessageDTO.class);
        return response.getBody();
    }

    @Override
    public BranchResponseMessageDTO getBranchById(String token, BranchRequestDTO branchRequestDTO) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Bearer " + token);
        HttpEntity<?> entity = new HttpEntity<>(branchRequestDTO, headers);
        ResponseEntity<BranchResponseMessageDTO> response = restTemplate.exchange(ADDRESS_ADMIN + "branches/detail", HttpMethod.POST, entity, BranchResponseMessageDTO.class);
        return response.getBody();
    }

    @Override
    public SimpleResponseMessageDTO addBranch(String token, BranchRequestDTO branchRequestDTO) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Bearer " + token);
        HttpEntity<?> entity = new HttpEntity<>(branchRequestDTO, headers);
        ResponseEntity<SimpleResponseMessageDTO> response = restTemplate.exchange(ADDRESS_ADMIN + "branches/add", HttpMethod.POST, entity, SimpleResponseMessageDTO.class);
        return response.getBody();
    }

    @Override
    public SimpleResponseMessageDTO editBranch(String token, BranchRequestDTO branchRequestDTO) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Bearer " + token);
        HttpEntity<?> entity = new HttpEntity<>(branchRequestDTO, headers);
        ResponseEntity<SimpleResponseMessageDTO> response = restTemplate.exchange(ADDRESS_ADMIN + "branches/edit", HttpMethod.POST, entity, SimpleResponseMessageDTO.class);
        return response.getBody();
    }

    @Override
    public SimpleResponseMessageDTO deleteBranch(String token, BranchRequestDTO branchRequestDTO) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Bearer " + token);
        HttpEntity<?> entity = new HttpEntity<>(branchRequestDTO, headers);
        ResponseEntity<SimpleResponseMessageDTO> response = restTemplate.exchange(ADDRESS_ADMIN + "branches/delete", HttpMethod.POST, entity, SimpleResponseMessageDTO.class);
        return response.getBody();
    }
}