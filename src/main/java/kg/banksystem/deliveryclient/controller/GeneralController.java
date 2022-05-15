package kg.banksystem.deliveryclient.controller;

import kg.banksystem.deliveryclient.dto.branch.request.OrderRequestDTO;
import kg.banksystem.deliveryclient.dto.branch.request.OrderStoryRequestDTO;
import kg.banksystem.deliveryclient.dto.branch.response.OrderResponseDTO;
import kg.banksystem.deliveryclient.dto.branch.response.OrderResponseMessageDTO;
import kg.banksystem.deliveryclient.dto.branch.response.OrderStoryResponseDTO;
import kg.banksystem.deliveryclient.dto.branch.response.OrderStoryResponseMessageDTO;
import kg.banksystem.deliveryclient.service.GeneralService;
import kg.banksystem.deliveryclient.service.impl.AuthenticationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Controller
public class GeneralController {

    private final GeneralService generalService;

    @Autowired
    public GeneralController(GeneralService generalService) {
        this.generalService = generalService;
    }

    // DONE
    @GetMapping("home")
    public String getHomePage(HttpServletRequest request, Model model) {
        try {
            String token = Arrays.stream(request.getCookies()).filter(cookie -> cookie.getName().equals(AuthenticationServiceImpl.COOKIE_NAME)).findFirst().map(Cookie::getValue).orElse(null);

            if (token == null) {
                return "redirect:/error/401";
            } else {
                String role = generalService.getRoleByToken(token);
                String user_role = "Вы авторизовались как " + role;
                model.addAttribute("user_role", user_role);
                model.addAttribute("user_name", generalService.getNameByToken(token));

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
    @GetMapping(value = {"orders/detail", "orders/orders/detail"})
    @ResponseBody
    public OrderResponseDTO getOrderById(@CookieValue(name = "token") String token, @ModelAttribute("orderRequestDTO") OrderRequestDTO orderRequestDTO) {
        OrderResponseMessageDTO feedback = generalService.getOrderById(token, orderRequestDTO);
        return feedback.getData();
    }

    // DONE
    @GetMapping(value = {"story/detail", "story/story/detail"})
    @ResponseBody
    public OrderStoryResponseDTO getStoryById(@CookieValue(name = "token") String token, @ModelAttribute("orderStoryRequestDTO") OrderStoryRequestDTO orderStoryRequestDTO) {
        OrderStoryResponseMessageDTO feedback = generalService.getStoryById(token, orderStoryRequestDTO);
        return feedback.getData();
    }
}