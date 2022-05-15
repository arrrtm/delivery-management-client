package kg.banksystem.deliveryclient.dto.branch.response;

import lombok.Data;

@Data
public class BranchCourierResponseDTO {
    private String courierFullName;
    private String courierPhoneNumber;
    private Long countOfCompletedOrdersPerDay;
    private Long countOfCompletedOrdersPerWeek;
    private Long countOfCompletedOrdersPerMonth;
    private String lastOrderDate;
}