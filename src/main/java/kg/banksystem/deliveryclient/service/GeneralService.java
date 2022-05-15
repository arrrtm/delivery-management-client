package kg.banksystem.deliveryclient.service;

import kg.banksystem.deliveryclient.dto.admin.response.BranchReportResponseMessageDTO;
import kg.banksystem.deliveryclient.dto.admin.response.ListUserResponseMessageDTO;
import kg.banksystem.deliveryclient.dto.baseresponse.SimpleListResponseMessageDTO;
import kg.banksystem.deliveryclient.dto.branch.request.OrderRequestDTO;
import kg.banksystem.deliveryclient.dto.branch.request.OrderStoryRequestDTO;
import kg.banksystem.deliveryclient.dto.branch.response.OrderResponseMessageDTO;
import kg.banksystem.deliveryclient.dto.branch.response.OrderStoryResponseMessageDTO;

public interface GeneralService {
    String getRoleByToken(String token);

    String getNameByToken(String token);

    OrderResponseMessageDTO getOrderById(String token, OrderRequestDTO orderRequestDTO);

    OrderStoryResponseMessageDTO getStoryById(String token, OrderStoryRequestDTO orderStoryRequestDTO);

    SimpleListResponseMessageDTO getBranchNames(String token);

    ListUserResponseMessageDTO getCouriers(String token);

    BranchReportResponseMessageDTO getReport(String token, String branch, String period);
}