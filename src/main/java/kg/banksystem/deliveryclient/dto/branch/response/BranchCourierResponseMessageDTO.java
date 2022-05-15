package kg.banksystem.deliveryclient.dto.branch.response;

import lombok.Data;

import java.util.List;

@Data
public class BranchCourierResponseMessageDTO {
    private String message;
    private List<BranchCourierResponseDTO> data;
    private String status;
}