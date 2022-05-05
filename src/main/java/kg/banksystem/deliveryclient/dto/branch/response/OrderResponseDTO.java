package kg.banksystem.deliveryclient.dto.branch.response;

import kg.banksystem.deliveryclient.dto.admin.response.BranchResponseDTO;
import kg.banksystem.deliveryclient.dto.admin.response.UserResponseDTO;
import kg.banksystem.deliveryclient.dto.bank.response.CardResponseDTO;
import kg.banksystem.deliveryclient.dto.bank.response.ClientResponseDTO;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
public class OrderResponseDTO {
    private Long id;
    private String addressPickup;
    private String addressDelivery;
    private CardResponseDTO card;
    private BranchResponseDTO branch;
    private ClientResponseDTO client;
    private String status;
    private String typeDelivery;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private Set<UserResponseDTO> users;
}