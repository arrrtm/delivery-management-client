package kg.banksystem.deliveryclient.dto.bank.response;

import lombok.Data;

import java.util.List;

@Data
public class ListCardResponseMessageDTO {
    private String message;
    private List<CardResponseDTO> data;
    private String status;
}