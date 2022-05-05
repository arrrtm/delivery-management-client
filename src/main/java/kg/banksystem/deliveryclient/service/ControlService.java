package kg.banksystem.deliveryclient.service;

import kg.banksystem.deliveryclient.dto.admin.request.UserRequestDTO;
import kg.banksystem.deliveryclient.dto.baseresponse.LogicalResponseMessageDTO;

public interface ControlService {
    LogicalResponseMessageDTO blockUser(String token, UserRequestDTO userRequestDTO);

    LogicalResponseMessageDTO unblockUser(String token, UserRequestDTO userRequestDTO);
}