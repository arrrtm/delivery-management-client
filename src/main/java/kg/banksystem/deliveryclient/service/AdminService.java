package kg.banksystem.deliveryclient.service;

import kg.banksystem.deliveryclient.dto.admin.response.ListBranchResponseMessageDTO;
import kg.banksystem.deliveryclient.dto.admin.response.ListUserResponseMessageDTO;
import kg.banksystem.deliveryclient.dto.branch.response.ListOrderResponseMessageDTO;

public interface AdminService {
    ListUserResponseMessageDTO getAllUsers(String token);

    ListBranchResponseMessageDTO getAllBranches(String token);

    ListOrderResponseMessageDTO getAllActiveOrders(String token);
}