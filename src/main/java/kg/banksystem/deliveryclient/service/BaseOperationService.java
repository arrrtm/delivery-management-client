package kg.banksystem.deliveryclient.service;

import kg.banksystem.deliveryclient.dto.baseresponse.SimpleListResponseMessageDTO;
import kg.banksystem.deliveryclient.dto.branch.request.OrderRequestDTO;
import kg.banksystem.deliveryclient.dto.branch.response.ListOrderResponseMessageDTO;
import kg.banksystem.deliveryclient.dto.branch.response.OrderResponseMessageDTO;

public interface BaseOperationService {
    String getRoleByToken(String token);

    String getNameByToken(String token);

    ListOrderResponseMessageDTO getAllActiveOrders(String token, int page, Long number, String branch);

    OrderResponseMessageDTO getOrderById(String token, OrderRequestDTO orderRequestDTO);

    SimpleListResponseMessageDTO getBranchNames(String token);
}