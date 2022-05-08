package kg.banksystem.deliveryclient.dto.branch.response;

import lombok.Data;

@Data
public class OrderStoryResponseMessageDTO {
    private String message;
    private OrderStoryResponseDTO data;
    private String status;
}