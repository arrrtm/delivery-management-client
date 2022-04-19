package kg.banksystem.deliveryclient.dto.admin.response;

import lombok.Data;

import java.util.List;

@Data
public class ListBranchResponseMessageDTO {
    private String message;
    private List<BranchResponseDTO> data;
    private String status;
}