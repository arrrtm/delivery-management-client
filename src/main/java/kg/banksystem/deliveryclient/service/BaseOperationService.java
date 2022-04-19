package kg.banksystem.deliveryclient.service;

import kg.banksystem.deliveryclient.dto.branch.request.OrderRequestDTO;
import kg.banksystem.deliveryclient.dto.branch.response.OrderResponseMessageDTO;

public interface BaseOperationService {
    String getRoleByToken(String token);

    String getNameByToken(String token);

    OrderResponseMessageDTO getOrderDetail(String token, OrderRequestDTO orderRequestDTO);
}