package kg.banksystem.deliveryclient.dto.branch.response;

import lombok.Data;

@Data
public class OrderResponseMessageDTO {
    private String message;
    private OrderResponseDTO data;
    private String status;
}