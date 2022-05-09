package kg.banksystem.deliveryclient.dto.admin.response;

import lombok.Data;

import java.util.List;

@Data
public class BranchStatisticResponseMessageDTO {
    private String message;
    private List<BranchStatisticResponseDTO> data;
    private String status;
}