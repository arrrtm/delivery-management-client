package kg.banksystem.deliveryclient.dto.bank.response;

import lombok.Data;

@Data
public class ClientResponseDTO {
    private Long id;
    private String clientPin;
    private String clientFullName;
    private String clientPhoneNumber;
}