package kg.banksystem.deliveryclient.dto.admin.response;

import lombok.Data;

import java.util.List;

@Data
public class ListUserResponseMessageDTO {
    private String message;
    private List<UserResponseDTO> data;
    private String status;
    private int totalPages;
}