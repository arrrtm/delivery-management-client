package kg.banksystem.deliveryclient.dto.admin.response;

import lombok.Data;

@Data
public class BranchResponseMessageDTO {
    private String message;
    private BranchResponseDTO data;
    private String status;
}