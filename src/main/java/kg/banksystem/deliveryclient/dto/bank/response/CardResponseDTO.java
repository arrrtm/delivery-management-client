package kg.banksystem.deliveryclient.dto.bank.response;

import lombok.Data;

import java.util.Set;

@Data
public class CardResponseDTO {
    private Long id;
    private String typeCard;
    private String Description;
    private Set<CurrencyResponseDTO> currency;
}