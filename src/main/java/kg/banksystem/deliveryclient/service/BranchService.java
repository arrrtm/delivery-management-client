package kg.banksystem.deliveryclient.service;

import kg.banksystem.deliveryclient.dto.admin.response.BranchStatisticResponseMessageDTO;
import kg.banksystem.deliveryclient.dto.admin.response.ListUserResponseMessageDTO;
import kg.banksystem.deliveryclient.dto.baseresponse.LogicalResponseMessageDTO;
import kg.banksystem.deliveryclient.dto.baseresponse.SimpleResponseMessageDTO;
import kg.banksystem.deliveryclient.dto.branch.request.OrderRequestDTO;
import kg.banksystem.deliveryclient.dto.branch.request.OrderStatusRequestDTO;
import kg.banksystem.deliveryclient.dto.branch.response.BranchCourierResponseMessageDTO;
import kg.banksystem.deliveryclient.dto.branch.response.ListOrderResponseMessageDTO;
import kg.banksystem.deliveryclient.dto.branch.response.ListOrderStoryResponseMessageDTO;

public interface BranchService {
    ListOrderResponseMessageDTO getOrdersForBranch(String token, OrderStatusRequestDTO orderStatusRequestDTO, int page, Long number);

    LogicalResponseMessageDTO changeStatusReadyFromDelivery(String token, OrderRequestDTO orderRequestDTO);

    LogicalResponseMessageDTO changeStatusDestroyed(String token, OrderRequestDTO orderRequestDTO);

    ListOrderStoryResponseMessageDTO getOrderStoryForBranch(String token, int page, Long number, Long courier);

    SimpleResponseMessageDTO getQrForOrder(String token, OrderRequestDTO orderRequestDTO);

    ListUserResponseMessageDTO getCouriersByBranch(String token);

    BranchStatisticResponseMessageDTO getStatistics(String token);

    SimpleResponseMessageDTO getBranchByToken(String token);

    BranchCourierResponseMessageDTO getCourierStatistics(String token);
}