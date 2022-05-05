package kg.banksystem.deliveryclient.dto.bank.response;

import lombok.Data;

@Data
public class ClientResponseMessageDTO {
    private String message;
    private ClientResponseDTO data;
    private String status;
}