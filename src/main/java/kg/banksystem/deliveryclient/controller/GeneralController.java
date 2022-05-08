package kg.banksystem.deliveryclient.controller;

import kg.banksystem.deliveryclient.dto.branch.request.OrderRequestDTO;
import kg.banksystem.deliveryclient.dto.branch.request.OrderStoryRequestDTO;
import kg.banksystem.deliveryclient.dto.branch.response.*;
import kg.banksystem.deliveryclient.service.GeneralService;
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
            String token = Arrays.stream(request.getCookies())
                    .filter(cookie -> cookie.getName().equals(AuthenticationServiceImpl.COOKIE_NAME)).
                    findFirst().map(Cookie::getValue).orElse(null);

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
    @GetMapping("orders")
    public String getOrdersPage(@CookieValue(name = "token") String token, Model model,
                                @RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "", required = false) Long number,
                                @RequestParam(defaultValue = "", required = false) String branch) {
        try {
            if (token == null) {
                return "redirect:/error/401";
            } else {
                ListOrderResponseMessageDTO feedback = generalService.getAllActiveOrders(token, page, number, branch);
                if (feedback.getStatus().equals("ERROR")) {
                    model.addAttribute("orderError", true);
                    model.addAttribute("feedback", feedback.getMessage());
                } else {
                    model.addAttribute("orders", feedback.getData());
                }
                model.addAttribute("branch", branch);
                model.addAttribute("branches", generalService.getBranchNames(token).getData());
                model.addAttribute("currentPage", page);
                model.addAttribute("totalPages", feedback.getTotalPages() - 1);
                String role = generalService.getRoleByToken(token);
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
    @GetMapping("story")
    public String getOrdersStoryPage(@CookieValue(name = "token") String token, Model model,
                                     @RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "", required = false) Long number,
                                     @RequestParam(defaultValue = "", required = false) String branch,
                                     @RequestParam(defaultValue = "", required = false) Long courier) {
        try {
            if (token == null) {
                return "redirect:/error/401";
            } else {
                ListOrderStoryResponseMessageDTO feedback = generalService.getAllOrderStory(token, page, number, branch, courier);
                if (feedback.getStatus().equals("ERROR")) {
                    model.addAttribute("storyError", true);
                    model.addAttribute("feedback", feedback.getMessage());
                } else {
                    model.addAttribute("stories", feedback.getData());
                }
                model.addAttribute("currentPage", page);
                model.addAttribute("totalPages", feedback.getTotalPages() - 1);
                String role = generalService.getRoleByToken(token);
                if (role.equals("Администратор") || role.equals("Сотрудник банка")) {
                    model.addAttribute("courier", courier);
                    model.addAttribute("couriers", generalService.getCouriers(token).getData());
                    model.addAttribute("branch", branch);
                    model.addAttribute("branches", generalService.getBranchNames(token).getData());
                    return "admin/story/stories";
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
                                         @ModelAttribute("orderRequestDTO") OrderRequestDTO orderRequestDTO) {
        OrderResponseMessageDTO feedback = generalService.getOrderById(token, orderRequestDTO);
        return feedback.getData();
    }

    // DONE
    @GetMapping(value = {"story/detail", "story/story/detail"})
    @ResponseBody
    public OrderStoryResponseDTO getStoryById(@CookieValue(name = "token") String token,
                                              @ModelAttribute("orderStoryRequestDTO") OrderStoryRequestDTO orderStoryRequestDTO) {
        OrderStoryResponseMessageDTO feedback = generalService.getStoryById(token, orderStoryRequestDTO);
        return feedback.getData();
    }
}