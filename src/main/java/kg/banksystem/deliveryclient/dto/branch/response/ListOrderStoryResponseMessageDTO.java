package kg.banksystem.deliveryclient.dto.branch.response;

import lombok.Data;

import java.util.List;

@Data
public class ListOrderStoryResponseMessageDTO {
    private String message;
    private List<OrderStoryResponseDTO> data;
    private String status;
    private int totalPages;
}