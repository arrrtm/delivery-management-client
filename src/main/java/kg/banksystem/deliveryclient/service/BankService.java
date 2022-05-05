package kg.banksystem.deliveryclient.service;

import kg.banksystem.deliveryclient.dto.admin.response.ListUserResponseMessageDTO;
import kg.banksystem.deliveryclient.dto.bank.request.ClientRequestDTO;
import kg.banksystem.deliveryclient.dto.bank.request.UsersWithRoleRequestDTO;
import kg.banksystem.deliveryclient.dto.bank.response.ClientResponseMessageDTO;
import kg.banksystem.deliveryclient.dto.bank.response.ListClientResponseMessageDTO;
import kg.banksystem.deliveryclient.dto.baseresponse.LogicalResponseMessageDTO;
import kg.banksystem.deliveryclient.dto.baseresponse.SimpleResponseMessageDTO;
import kg.banksystem.deliveryclient.dto.branch.request.OrderRequestDTO;

public interface BankService {
    ListUserResponseMessageDTO getAllUsers(String token, UsersWithRoleRequestDTO users, int page);

    ListClientResponseMessageDTO getAllClients(String token, int page);

    ClientResponseMessageDTO getClientById(String token, ClientRequestDTO clientRequestDTO);

    SimpleResponseMessageDTO addClient(String token, ClientRequestDTO clientRequestDTO);

    SimpleResponseMessageDTO editClient(String token, ClientRequestDTO clientRequestDTO);

    SimpleResponseMessageDTO deleteClient(String token, ClientRequestDTO clientRequestDTO);

    // IN PROGRESS
    SimpleResponseMessageDTO addOrder(String token, OrderRequestDTO orderRequestDTO);

    // IN PROGRESS
    SimpleResponseMessageDTO editOrder(String token, OrderRequestDTO orderRequestDTO);

    // IN PROGRESS
    SimpleResponseMessageDTO deleteOrder(String token, OrderRequestDTO orderRequestDTO);

    LogicalResponseMessageDTO changeStatusSentToBranch(String token, OrderRequestDTO orderRequestDTO);
}