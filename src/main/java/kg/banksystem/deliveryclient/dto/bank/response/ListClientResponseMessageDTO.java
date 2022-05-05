package kg.banksystem.deliveryclient.dto.bank.response;

import lombok.Data;

import java.util.List;

@Data
public class ListClientResponseMessageDTO {
    private String message;
    private List<ClientResponseDTO> data;
    private String status;
    private int totalPages;
}