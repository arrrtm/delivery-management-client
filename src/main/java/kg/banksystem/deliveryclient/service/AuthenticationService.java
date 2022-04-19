package kg.banksystem.deliveryclient.service;

import kg.banksystem.deliveryclient.dto.auth.request.AuthenticationRequestDTO;
import kg.banksystem.deliveryclient.dto.auth.response.TokenResponseMessageDTO;

public interface AuthenticationService {
    TokenResponseMessageDTO getAuthentication(AuthenticationRequestDTO authenticationRequestDTO);
}