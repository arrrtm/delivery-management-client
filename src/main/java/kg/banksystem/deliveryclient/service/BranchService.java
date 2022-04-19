package kg.banksystem.deliveryclient.service;

import kg.banksystem.deliveryclient.dto.account.response.LogicalResponseMessageDTO;
import kg.banksystem.deliveryclient.dto.branch.request.OrderIdRequestDTO;
import kg.banksystem.deliveryclient.dto.branch.request.OrderStatusRequestDTO;
import kg.banksystem.deliveryclient.dto.branch.response.ListOrderResponseMessageDTO;

public interface BranchService {
    ListOrderResponseMessageDTO getOrdersForBranch(String token, OrderStatusRequestDTO orderStatusRequestDTO);

    LogicalResponseMessageDTO changeStatusReadyFromDelivery(String token, OrderIdRequestDTO orderIdRequestDTO);

    LogicalResponseMessageDTO changeStatusDestroyed(String token, OrderIdRequestDTO orderIdRequestDTO);
}