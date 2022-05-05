package kg.banksystem.deliveryclient.dto.baseresponse;

import lombok.Data;

import java.util.List;

@Data
public class SimpleListResponseMessageDTO {
    private String message;
    private List<String> data;
    private String status;
}