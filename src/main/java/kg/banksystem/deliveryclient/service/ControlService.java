package kg.banksystem.deliveryclient.service;

import kg.banksystem.deliveryclient.dto.account.response.LogicalResponseMessageDTO;
import kg.banksystem.deliveryclient.dto.admin.request.UserRequestDTO;

public interface ControlService {
    LogicalResponseMessageDTO blockUser(String token, UserRequestDTO userRequestDTO);

    LogicalResponseMessageDTO unblockUser(String token, UserRequestDTO userRequestDTO);
}