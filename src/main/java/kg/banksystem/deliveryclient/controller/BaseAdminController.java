package kg.banksystem.deliveryclient.controller;

import kg.banksystem.deliveryclient.dto.account.response.LogicalResponseMessageDTO;
import kg.banksystem.deliveryclient.dto.admin.request.UserRequestDTO;
import kg.banksystem.deliveryclient.dto.admin.response.ListBranchResponseMessageDTO;
import kg.banksystem.deliveryclient.dto.admin.response.ListUserResponseMessageDTO;
import kg.banksystem.deliveryclient.dto.branch.response.ListOrderResponseMessageDTO;
import kg.banksystem.deliveryclient.service.AdminService;
import kg.banksystem.deliveryclient.service.ControlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Controller
public class BaseAdminController {

    private final AdminService adminService;
    private final ControlService controlService;

    @Autowired
    public BaseAdminController(AdminService adminService, ControlService controlService) {
        this.adminService = adminService;
        this.controlService = controlService;
    }

    @GetMapping("users")
    public String getUsersPage(@CookieValue(name = "token") String token, Model model) {
        try {
            if (token == null) {
                return "redirect:/error/401";
            } else {
                ListUserResponseMessageDTO feedback = adminService.getAllUsers(token);
                if (feedback.getStatus().equals("ERROR")) {
                    model.addAttribute("userError", true);
                    model.addAttribute("feedback", feedback.getMessage());
                } else {
                    model.addAttribute("userList", feedback.getData());
                }
                return "admin/usersList";
            }
        } catch (HttpClientErrorException.Forbidden exf) {
            return "redirect:/error/403";
        }
    }

    @GetMapping("users/add")
    public String userAddPage(@CookieValue(name = "token") String token, Model model) {
        return null;
    }

    @GetMapping("branches")
    public String getBranchesPage(@CookieValue(name = "token") String token, Model model) {
        try {
            if (token == null) {
                return "redirect:/error/401";
            } else {
                ListBranchResponseMessageDTO feedback = adminService.getAllBranches(token);
                if (feedback.getStatus().equals("ERROR")) {
                    model.addAttribute("branchError", true);
                    model.addAttribute("feedback", feedback.getMessage());
                } else {
                    model.addAttribute("branchList", feedback.getData());
                }
                return "admin/branchesList";
            }
        } catch (HttpClientErrorException.Forbidden exf) {
            return "redirect:/error/403";
        }
    }

    @GetMapping("branches/add")
    public String branchAddPage(@CookieValue(name = "token") String token, Model model) {
        return null;
    }

    @GetMapping("orders")
    public String getOrdersPage(@CookieValue(name = "token") String token, Model model) {
        try {
            if (token == null) {
                return "redirect:/error/401";
            } else {
                ListOrderResponseMessageDTO feedback = adminService.getAllActiveOrders(token);
                if (feedback.getStatus().equals("ERROR")) {
                    model.addAttribute("orderError", true);
                    model.addAttribute("feedback", feedback.getMessage());
                } else {
                    model.addAttribute("orderList", feedback.getData());
                }
                return "admin/ordersList";
            }
        } catch (HttpClientErrorException.Forbidden exf) {
            return "redirect:/error/403";
        }
    }

    @PostMapping("users/{id}/block")
    public String blockUser(@CookieValue(name = "token") String token, @ModelAttribute("id") UserRequestDTO userRequestDTO,
                            RedirectAttributes redirectAttributes, HttpServletRequest request) {
        try {
            if (token == null) {
                return "redirect:/error/401";
            } else {
                LogicalResponseMessageDTO feedback = controlService.blockUser(token, userRequestDTO);
                if (feedback.getStatus().equals("ERROR")) {
                    redirectAttributes.addFlashAttribute("action", "block-fail");
                } else {
                    redirectAttributes.addFlashAttribute("action", "block-success");
                }
                redirectAttributes.addFlashAttribute("feedback", feedback.getMessage());
                return getPreviousPageByRequest(request).orElse("/");
            }
        } catch (HttpClientErrorException.Forbidden exf) {
            return "redirect:/error/403";
        }
    }

    @PostMapping("users/{id}/unblock")
    public String unblockUser(@CookieValue(name = "token") String token, @ModelAttribute("id") UserRequestDTO userRequestDTO,
                              RedirectAttributes redirectAttributes, HttpServletRequest request) {
        try {
            if (token == null) {
                return "redirect:/error/401";
            } else {
                LogicalResponseMessageDTO feedback = controlService.unblockUser(token, userRequestDTO);
                if (feedback.getStatus().equals("ERROR")) {
                    redirectAttributes.addFlashAttribute("action", "unblock-fail");
                } else {
                    redirectAttributes.addFlashAttribute("action", "unblock-success");
                }
                redirectAttributes.addFlashAttribute("feedback", feedback.getMessage());
                return getPreviousPageByRequest(request).orElse("/");
            }
        } catch (HttpClientErrorException.Forbidden exf) {
            return "redirect:/error/403";
        }
    }

    protected Optional<String> getPreviousPageByRequest(HttpServletRequest request) {
        return Optional.ofNullable(request.getHeader("Referer")).map(requestUrl -> "redirect:" + requestUrl);
    }
}