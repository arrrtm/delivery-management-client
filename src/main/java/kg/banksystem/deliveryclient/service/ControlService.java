package kg.banksystem.deliveryclient.service;

import kg.banksystem.deliveryclient.dto.admin.request.UserRequestDTO;
import kg.banksystem.deliveryclient.dto.admin.response.BranchStatisticResponseMessageDTO;
import kg.banksystem.deliveryclient.dto.baseresponse.LogicalResponseMessageDTO;
import kg.banksystem.deliveryclient.dto.branch.response.ListOrderResponseMessageDTO;
import kg.banksystem.deliveryclient.dto.branch.response.ListOrderStoryResponseMessageDTO;

public interface ControlService {
    LogicalResponseMessageDTO blockUser(String token, UserRequestDTO userRequestDTO);

    LogicalResponseMessageDTO unblockUser(String token, UserRequestDTO userRequestDTO);

    ListOrderResponseMessageDTO getAllActiveOrders(String token, int page, Long number, String branch);

    ListOrderStoryResponseMessageDTO getAllOrderStory(String token, int page, Long number, String branch, Long courier);

    BranchStatisticResponseMessageDTO getStatistics(String token);
}