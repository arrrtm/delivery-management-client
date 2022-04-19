package kg.banksystem.deliveryclient.dto.account.response;

import lombok.Data;

@Data
public class LogicalResponseMessageDTO {
    private String message;
    private Boolean data;
    private String status;
}