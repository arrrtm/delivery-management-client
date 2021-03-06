package kg.banksystem.deliveryclient.dto.admin.response;

import kg.banksystem.deliveryclient.dto.account.response.RoleResponseDTO;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class UserResponseDTO {
    private Long id;
    private String username;
    private String userFullName;
    private String userPhoneNumber;
    private String email;
    private String status;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private RoleResponseDTO role;
    private List<BranchResponseDTO> branches;
}