package kg.banksystem.deliveryclient.dto.admin.request;

import lombok.Data;

@Data
public class BranchRequestDTO {
    private Long id;
    private String name;
    private String address;
}