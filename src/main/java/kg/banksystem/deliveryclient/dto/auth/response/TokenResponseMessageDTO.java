package kg.banksystem.deliveryclient.dto.auth.response;

import lombok.Data;

@Data
public class TokenResponseMessageDTO {
    private String message;
    private AuthResponseMessageDTO data;
    private String status;
}