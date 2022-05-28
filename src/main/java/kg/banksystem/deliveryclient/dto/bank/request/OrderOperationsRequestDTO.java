package kg.banksystem.deliveryclient.dto.bank.request;

import lombok.Data;

@Data
public class OrderOperationsRequestDTO {
    private Long id;
    private String addressDelivery;
    private String addressPickup;
    private Long branch;
    private Long card;
    private Long client;
}