package kg.banksystem.deliveryclient.dto.account.request;

import lombok.Data;

@Data
public class EditAccountRequestDTO {
    private String password;

    private String userFullName;
    private String userPhoneNumber;
    private String email;

    private String status;
}