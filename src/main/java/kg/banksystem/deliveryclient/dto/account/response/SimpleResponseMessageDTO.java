package kg.banksystem.deliveryclient.dto.account.response;

import lombok.Data;

@Data
public class SimpleResponseMessageDTO {
    private String message;
    private String data;
    private String status;
}