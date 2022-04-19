package kg.banksystem.deliveryclient.dto.admin.response;

import lombok.Data;

@Data
public class UserBaseResponseDTO {
    private Long countCompleteDelivery;
    private UserResponseDTO userData;
}