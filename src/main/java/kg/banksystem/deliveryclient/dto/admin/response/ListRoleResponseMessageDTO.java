package kg.banksystem.deliveryclient.dto.admin.response;

import kg.banksystem.deliveryclient.dto.account.response.RoleResponseDTO;
import lombok.Data;

import java.util.List;

@Data
public class ListRoleResponseMessageDTO {
    private String message;
    private List<RoleResponseDTO> data;
    private String status;
}