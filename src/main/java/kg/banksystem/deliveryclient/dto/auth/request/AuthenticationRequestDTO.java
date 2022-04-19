package kg.banksystem.deliveryclient.dto.auth.request;

import lombok.Data;

@Data
public class AuthenticationRequestDTO {
    private String username;
    private String password;
}