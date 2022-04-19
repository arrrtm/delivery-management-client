package kg.banksystem.deliveryclient.controller;

import kg.banksystem.deliveryclient.service.BaseOperationService;
import kg.banksystem.deliveryclient.service.impl.AuthenticationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
                        return "home/home_admin";
                    case "Сотрудник банка":
                        return "home/home_employee_bank";
                    case "Сотрудник филиала":
                        return "home/home_employee_branch";
                    default:
                        return "redirect:/error/403";
                }
            }
        } catch (Exception ex) {
            return "redirect:/error/401";
        }
    }
}