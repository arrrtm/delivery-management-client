package kg.banksystem.deliveryclient.dto.admin.response;

import lombok.Data;

@Data
public class UserDetailResponse {
    private String message;
    private UserResponseDTO data;
    private String status;
}