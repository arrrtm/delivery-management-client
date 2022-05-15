package kg.banksystem.deliveryclient.dto.admin.response;

import lombok.Data;

@Data
public class BranchReportResponseDTO {
    private String orderNumber;
    private String addressPickup;
    private String addressDelivery;
    private String typeDelivery;
    private String orderStatus;
    private String orderCreated;
    private String cardType;
    private String cardDescription;
    private String clientPin;
    private String clientFullName;
    private String clientPhoneNumber;
    private String completedStoryNumber;
    private String userFullName;
    private String userPhoneNumber;
    private String userEmail;
    private String orderCompleted;
    private String storyStatus;
    private String storyComment;
    private String branchName;
    private String branchAddress;
}