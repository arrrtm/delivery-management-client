package kg.banksystem.deliveryclient.service;

import kg.banksystem.deliveryclient.dto.baseresponse.LogicalResponseMessageDTO;
import kg.banksystem.deliveryclient.dto.branch.request.OrderRequestDTO;
import kg.banksystem.deliveryclient.dto.branch.request.OrderStatusRequestDTO;
import kg.banksystem.deliveryclient.dto.branch.response.ListOrderResponseMessageDTO;

public interface BranchService {
    ListOrderResponseMessageDTO getOrdersForBranch(String token, OrderStatusRequestDTO orderStatusRequestDTO, int page, Long number);

    LogicalResponseMessageDTO changeStatusReadyFromDelivery(String token, OrderRequestDTO orderRequestDTO);

    LogicalResponseMessageDTO changeStatusDestroyed(String token, OrderRequestDTO orderRequestDTO);
}