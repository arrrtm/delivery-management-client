package kg.banksystem.deliveryclient.dto.admin.response;

import lombok.Data;

import java.util.List;

@Data
public class BranchReportResponseMessageDTO {
    private String message;
    private List<BranchReportResponseDTO> data;
    private String status;
}