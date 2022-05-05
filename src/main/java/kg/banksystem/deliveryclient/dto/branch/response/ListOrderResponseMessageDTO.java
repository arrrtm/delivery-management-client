package kg.banksystem.deliveryclient.dto.branch.response;

import lombok.Data;

import java.util.List;

@Data
public class ListOrderResponseMessageDTO {
    private String message;
    private List<OrderResponseDTO> data;
    private String status;
    private int totalPages;
}