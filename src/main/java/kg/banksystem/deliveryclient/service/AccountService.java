package kg.banksystem.deliveryclient.service;

import kg.banksystem.deliveryclient.dto.account.request.EditAccountRequestDTO;
import kg.banksystem.deliveryclient.dto.account.request.ResetPasswordRequestDTO;
import kg.banksystem.deliveryclient.dto.admin.response.UserResponseMessageDTO;
import kg.banksystem.deliveryclient.dto.baseresponse.LogicalResponseMessageDTO;

public interface AccountService {
    UserResponseMessageDTO viewPersonalArea(String token);

    LogicalResponseMessageDTO editPersonalAccountData(String token, EditAccountRequestDTO editAccountRequestDTO);

    LogicalResponseMessageDTO resetPasswordByEmail(ResetPasswordRequestDTO resetPasswordRequestDTO);
}