package kg.banksystem.deliveryclient.dto.branch.request;

import lombok.Data;

@Data
public class OrderStatusRequestDTO {
    private Long id;
    private String requestStatus;
}