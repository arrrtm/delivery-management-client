package kg.banksystem.deliveryclient.dto.admin.request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserRequestDTO {
    private Long id;
    private String username;
    private String password;
    private String userFullName;
    private String userPhoneNumber;
    private String email;
    private String status;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private String role;
    private String branches;
}