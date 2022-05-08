package kg.banksystem.deliveryclient.dto.branch.request;

import kg.banksystem.deliveryclient.dto.admin.response.BranchResponseDTO;
import kg.banksystem.deliveryclient.dto.admin.response.UserResponseDTO;
import kg.banksystem.deliveryclient.dto.bank.response.CardResponseDTO;
import kg.banksystem.deliveryclient.dto.bank.response.ClientResponseDTO;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderStoryRequestDTO {
    private Long id;
    private String addressPickup;
    private String addressDelivery;
    private String status;
    private String comment;
    private Long orderNumber;
    private CardResponseDTO card;
    private ClientResponseDTO client;
    private BranchResponseDTO branch;
    private UserResponseDTO user;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private String requestStatus;
}