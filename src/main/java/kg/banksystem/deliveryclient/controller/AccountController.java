package kg.banksystem.deliveryclient.controller;

import kg.banksystem.deliveryclient.dto.account.request.EditAccountRequestDTO;
import kg.banksystem.deliveryclient.dto.account.request.ResetPasswordRequestDTO;
import kg.banksystem.deliveryclient.dto.admin.response.UserResponseMessageDTO;
import kg.banksystem.deliveryclient.dto.baseresponse.LogicalResponseMessageDTO;
import kg.banksystem.deliveryclient.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    // DONE
    @GetMapping("view")
    public String getPersonalAreaPage(@CookieValue(name = "token") String token, Model model,
                                      RedirectAttributes redirectAttributes) {
        if (token == null) {
            return "redirect:/error/401";
        } else {
            UserResponseMessageDTO feedback = accountService.viewPersonalArea(token);
            if (feedback.getStatus().equals("ERROR")) {
                redirectAttributes.addFlashAttribute("action", "account-fail");
                redirectAttributes.addFlashAttribute("feedback", feedback.getMessage());
                return "redirect:/view";
            } else {
                model.addAttribute("userResponseDTO", feedback.getData().getUserData());
                return "account/personalArea";
            }
        }
    }

    // DONE
    @GetMapping("reset")
    public String getPasswordResetPage(Model model) {
        model.addAttribute("resetPasswordRequestDTO", new ResetPasswordRequestDTO());
        return "account/reset";
    }

    // DONE
    @PostMapping("reset")
    public String resetPasswordByEmail(@ModelAttribute("resetPasswordRequestDTO") ResetPasswordRequestDTO resetPasswordRequestDTO,
                                       RedirectAttributes redirectAttributes) {
        LogicalResponseMessageDTO feedback = accountService.resetPasswordByEmail(resetPasswordRequestDTO);
        if (feedback.getStatus().equals("ERROR")) {
            redirectAttributes.addFlashAttribute("action", "reset-fail");
        } else {
            redirectAttributes.addFlashAttribute("action", "reset-success");
        }
        redirectAttributes.addFlashAttribute("feedback", feedback.getMessage());
        return "redirect:/reset";
    }

    // DONE
    @GetMapping("edit/personal")
    public String getEditPersonalDataPage(@CookieValue(name = "token") String token, Model model) {
        EditAccountRequestDTO editAccountRequestDTO = new EditAccountRequestDTO();
        UserResponseMessageDTO user = accountService.viewPersonalArea(token);
        editAccountRequestDTO.setUserFullName(user.getData().getUserData().getUserFullName());
        editAccountRequestDTO.setUserPhoneNumber(user.getData().getUserData().getUserPhoneNumber());
        editAccountRequestDTO.setEmail(user.getData().getUserData().getEmail());
        editAccountRequestDTO.setStatus("personal_data");
        model.addAttribute("editAccountRequestDTO", editAccountRequestDTO);
        return "account/changePersonalData";
    }

    // DONE
    @GetMapping("edit/password")
    public String getEditPasswordPage(Model model) {
        EditAccountRequestDTO editAccountRequestDTO = new EditAccountRequestDTO();
        editAccountRequestDTO.setStatus("personal_password");
        model.addAttribute("editAccountRequestDTO", editAccountRequestDTO);
        return "account/changePassword";
    }

    // DONE
    @PostMapping(value = {"edit/password", "edit/personal"})
    public String editPersonalPassword(@CookieValue(name = "token") String token, RedirectAttributes redirectAttributes,
                                       @ModelAttribute("editAccountRequestDTO") EditAccountRequestDTO editAccountRequestDTO) {
        if (token == null) {
            return "redirect:/error/401";
        } else if (editAccountRequestDTO.getStatus().equals("personal_password") ||
                editAccountRequestDTO.getStatus().equals("personal_data")) {
            LogicalResponseMessageDTO feedback = accountService.editPersonalAccountData(token, editAccountRequestDTO);
            if (feedback.getStatus().equals("ERROR")) {
                redirectAttributes.addFlashAttribute("action", "edit-fail");
            } else {
                redirectAttributes.addFlashAttribute("action", "edit-success");
            }
            redirectAttributes.addFlashAttribute("feedback", feedback.getMessage());
            return "redirect:/view";
        } else {
            return "redirect:/error/403";
        }
    }
}