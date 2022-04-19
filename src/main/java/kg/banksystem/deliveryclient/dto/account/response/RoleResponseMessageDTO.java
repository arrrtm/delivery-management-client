package kg.banksystem.deliveryclient.dto.account.response;

import lombok.Data;

@Data
public class RoleResponseMessageDTO {
    private String message;
    private RoleResponseDTO data;
    private String status;
}