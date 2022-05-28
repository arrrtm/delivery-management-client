package kg.banksystem.deliveryclient.service;

import kg.banksystem.deliveryclient.dto.admin.request.BranchRequestDTO;
import kg.banksystem.deliveryclient.dto.admin.request.UserRequestDTO;
import kg.banksystem.deliveryclient.dto.admin.response.BranchResponseMessageDTO;
import kg.banksystem.deliveryclient.dto.admin.response.ListBranchResponseMessageDTO;
import kg.banksystem.deliveryclient.dto.admin.response.ListUserResponseMessageDTO;
import kg.banksystem.deliveryclient.dto.baseresponse.SimpleResponseMessageDTO;

public interface AdminService {
    ListUserResponseMessageDTO getAllUsers(String token, int page);

    SimpleResponseMessageDTO removeUser(String token, UserRequestDTO userRequestDTO);

    ListBranchResponseMessageDTO getAllBranches(String token, int page);

    BranchResponseMessageDTO getBranchById(String token, BranchRequestDTO branchRequestDTO);

    SimpleResponseMessageDTO addBranch(String token, BranchRequestDTO branchRequestDTO);

    SimpleResponseMessageDTO editBranch(String token, BranchRequestDTO branchRequestDTO);

    SimpleResponseMessageDTO deleteBranch(String token, BranchRequestDTO branchRequestDTO);
}