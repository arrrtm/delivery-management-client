package kg.banksystem.deliveryclient.dto.baseresponse;

import lombok.Data;

@Data
public class LogicalResponseMessageDTO {
    private String message;
    private Boolean data;
    private String status;
}