package kg.banksystem.deliveryclient.dto.baseresponse;

import lombok.Data;

@Data
public class SimpleResponseMessageDTO {
    private String message;
    private String data;
    private String status;
}