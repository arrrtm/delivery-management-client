package kg.banksystem.deliveryclient.service;

import kg.banksystem.deliveryclient.dto.admin.request.UserRequestDTO;
import kg.banksystem.deliveryclient.dto.admin.response.BranchStatisticResponseMessageDTO;
import kg.banksystem.deliveryclient.dto.admin.response.UserDetailResponse;
import kg.banksystem.deliveryclient.dto.bank.response.ListCardResponseMessageDTO;
import kg.banksystem.deliveryclient.dto.bank.response.ListClientResponseMessageDTO;
import kg.banksystem.deliveryclient.dto.baseresponse.LogicalResponseMessageDTO;
import kg.banksystem.deliveryclient.dto.baseresponse.SimpleResponseMessageDTO;
import kg.banksystem.deliveryclient.dto.branch.response.ListOrderResponseMessageDTO;
import kg.banksystem.deliveryclient.dto.branch.response.ListOrderStoryResponseMessageDTO;

public interface ControlService {
    LogicalResponseMessageDTO blockUser(String token, UserRequestDTO userRequestDTO);

    LogicalResponseMessageDTO unblockUser(String token, UserRequestDTO userRequestDTO);

    UserDetailResponse getUserById(String token, UserRequestDTO userRequestDTO);

    SimpleResponseMessageDTO registerUser(String token, UserRequestDTO userRequestDTO);

    SimpleResponseMessageDTO updateUser(String token, UserRequestDTO userRequestDTO);

    ListOrderResponseMessageDTO getAllActiveOrders(String token, int page, Long number, Long branch);

    ListOrderStoryResponseMessageDTO getAllOrderStory(String token, int page, Long number, Long branch, Long courier);

    BranchStatisticResponseMessageDTO getStatistics(String token);

    ListCardResponseMessageDTO getCards(String token);

    ListClientResponseMessageDTO getClients(String token);
}