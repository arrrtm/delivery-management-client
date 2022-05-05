package kg.banksystem.deliveryclient.controller;

import kg.banksystem.deliveryclient.dto.baseresponse.SimpleListResponseMessageDTO;
import kg.banksystem.deliveryclient.dto.branch.request.OrderRequestDTO;
import kg.banksystem.deliveryclient.dto.branch.response.ListOrderResponseMessageDTO;
import kg.banksystem.deliveryclient.dto.branch.response.OrderResponseDTO;
import kg.banksystem.deliveryclient.dto.branch.response.OrderResponseMessageDTO;
import kg.banksystem.deliveryclient.service.BaseOperationService;
import kg.banksystem.deliveryclient.service.impl.AuthenticationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Controller
public class HomeController {

    private final BaseOperationService baseOperationService;

    @Autowired
    public HomeController(BaseOperationService baseOperationService) {
        this.baseOperationService = baseOperationService;
    }

    // DONE
    @GetMapping("home")
    public String getHomePage(HttpServletRequest request, Model model) {
        try {
            String token = Arrays.stream(request.getCookies())
                    .filter(cookie -> cookie.getName().equals(AuthenticationServiceImpl.COOKIE_NAME)).
                    findFirst().map(Cookie::getValue).orElse(null);

            if (token == null) {
                return "redirect:/error/401";
            } else {
                String role = baseOperationService.getRoleByToken(token);
                String user_role = "Вы авторизовались как " + role;
                model.addAttribute("user_role", user_role);
                model.addAttribute("user_name", baseOperationService.getNameByToken(token));

                switch (role) {
                    case "Администратор":
                        return "home/homeAdmin";
                    case "Сотрудник банка":
                        return "home/homeBankEmployee";
                    case "Сотрудник филиала":
                        return "home/homeBranchEmployee";
                    default:
                        return "redirect:/error/403";
                }
            }
        } catch (Exception ex) {
            return "redirect:/error/401";
        }
    }

    // DONE
    @GetMapping("orders")
    public String getOrdersPage(@CookieValue(name = "token") String token, Model model,
                                @RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "", required = false) Long number,
                                @RequestParam(defaultValue = "", required = false) String branch) {
        try {
            if (token == null) {
                return "redirect:/error/401";
            } else {
                ListOrderResponseMessageDTO feedback = baseOperationService.getAllActiveOrders(token, page, number, branch);
                if (feedback.getStatus().equals("ERROR")) {
                    model.addAttribute("orderError", true);
                    model.addAttribute("feedback", feedback.getMessage());
                } else {
                    model.addAttribute("orders", feedback.getData());
                }
                model.addAttribute("branch", branch);
                SimpleListResponseMessageDTO branches = baseOperationService.getBranchNames(token);
                model.addAttribute("branches", branches.getData());
                model.addAttribute("currentPage", page);
                model.addAttribute("totalPages", feedback.getTotalPages() - 1);
                String role = baseOperationService.getRoleByToken(token);
                if (role.equals("Администратор")) {
                    return "admin/order/orders";
                } else if (role.equals("Сотрудник банка")) {
                    return "bank/order/orders";
                } else {
                    return "redirect:/error/403";
                }
            }
        } catch (HttpClientErrorException.Forbidden exf) {
            return "redirect:/error/403";
        } catch (HttpClientErrorException.Unauthorized exu) {
            return "redirect:/error/401";
        }
    }

    // DONE
    @GetMapping(value = {"orders/detail", "orders/orders/detail"})
    @ResponseBody
    public OrderResponseDTO getOrderById(@CookieValue(name = "token") String token,
                                         @ModelAttribute("branchRequestDTO") OrderRequestDTO orderRequestDTO) {
        OrderResponseMessageDTO feedback = baseOperationService.getOrderById(token, orderRequestDTO);
        return feedback.getData();
    }
}