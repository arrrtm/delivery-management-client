package kg.banksystem.deliveryclient.dto.admin.response;

import lombok.Data;

@Data
public class UserResponseMessageDTO {
    private String message;
    private UserBaseResponseDTO data;
    private String status;
}