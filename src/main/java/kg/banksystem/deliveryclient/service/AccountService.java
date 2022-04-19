package kg.banksystem.deliveryclient.service;

import kg.banksystem.deliveryclient.dto.account.request.EditAccountRequestDTO;
import kg.banksystem.deliveryclient.dto.account.request.ResetPasswordRequestDTO;
import kg.banksystem.deliveryclient.dto.account.response.LogicalResponseMessageDTO;
import kg.banksystem.deliveryclient.dto.admin.response.UserResponseMessageDTO;

public interface AccountService {
    UserResponseMessageDTO viewPersonalArea(String token);

    LogicalResponseMessageDTO editPersonalAccountData(String token, EditAccountRequestDTO editAccountRequestDTO);

    LogicalResponseMessageDTO resetPasswordByEmail(ResetPasswordRequestDTO resetPasswordRequestDTO);
}