package kg.banksystem.deliveryclient.dto.admin.response;

import lombok.Data;

@Data
public class BranchStatisticResponseDTO {
    private String branchName;
    private String branchAddress;
    private Long totalCountOfOrdersActive;
    private Long totalCountOfOrdersComplete;
    private Long totalCountOfCouriersActive;
    private Long totalCountOfCouriersInactive;
    private Long countOfCompletedOrdersPerWeek;
    private Long countOfCompletedOrdersPerMonth;
    private Long countOfCompletedOrdersPerYear;
}