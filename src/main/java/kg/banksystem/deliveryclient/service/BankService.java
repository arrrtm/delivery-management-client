package kg.banksystem.deliveryclient.service;

import kg.banksystem.deliveryclient.dto.admin.response.ListUserResponseMessageDTO;
import kg.banksystem.deliveryclient.dto.bank.request.UsersWithRoleRequestDTO;

public interface BankService {
    ListUserResponseMessageDTO getAllUsers(String token, UsersWithRoleRequestDTO users);
}