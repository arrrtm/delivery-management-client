package kg.banksystem.deliveryclient.controller;

import kg.banksystem.deliveryclient.dto.admin.response.ListUserResponseMessageDTO;
import kg.banksystem.deliveryclient.dto.bank.request.UsersWithRoleRequestDTO;
import kg.banksystem.deliveryclient.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.HttpClientErrorException;

@Controller
public class BankEmployeeController {

    private final BankService bankService;

    @Autowired
    public BankEmployeeController(BankService bankService) {
        this.bankService = bankService;
    }

    @GetMapping("users/couriers")
    public String getCouriersPage(@CookieValue(name = "token") String token, Model model) {
        try {
            UsersWithRoleRequestDTO users = new UsersWithRoleRequestDTO();
            users.setRequestRole("courier");
            if (token == null) {
                return "redirect:/error/401";
            } else {
                getPages(token, model, users);
            }
            return "bank/branchCouriersList";
        } catch (HttpClientErrorException.Forbidden exf) {
            return "redirect:/error/403";
        }
    }

    @GetMapping("users/employees")
    public String getBranchEmployeesPage(@CookieValue(name = "token") String token, Model model) {
        try {
            UsersWithRoleRequestDTO users = new UsersWithRoleRequestDTO();
            users.setRequestRole("branch_employee");
            if (token == null) {
                return "redirect:/error/401";
            } else {
                getPages(token, model, users);
            }
            return "bank/branchEmployeesList";
        } catch (HttpClientErrorException.Forbidden exf) {
            return "redirect:/error/403";
        }
    }

    @GetMapping("bank/orders")
    public String getOrdersPage(@CookieValue(name = "token") String token, Model model) {
        return null;
    }

    @GetMapping("clients")
    public String getClientsPage(@CookieValue(name = "token") String token, Model model) {
        return null;
    }

    public void getPages(String token, Model model, UsersWithRoleRequestDTO users) {
        ListUserResponseMessageDTO feedback = bankService.getAllUsers(token, users);
        if (feedback.getStatus().equals("ERROR")) {
            model.addAttribute("userError", true);
            model.addAttribute("feedback", feedback.getMessage());
        } else {
            model.addAttribute("userList", feedback.getData());
        }
    }
}