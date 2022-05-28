package kg.banksystem.deliveryclient.controller;

import kg.banksystem.deliveryclient.dto.auth.request.AuthenticationRequestDTO;
import kg.banksystem.deliveryclient.dto.auth.response.TokenResponseMessageDTO;
import kg.banksystem.deliveryclient.service.AuthenticationService;
import kg.banksystem.deliveryclient.service.impl.AuthenticationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @GetMapping("/")
    public String getLoginPage(Model model) {
        model.addAttribute("authenticationRequestDTO", new AuthenticationRequestDTO());
        return "login";
    }

    @PostMapping("login")
    public String login(@ModelAttribute("authenticationRequestDTO") AuthenticationRequestDTO authenticationRequestDTO, HttpServletResponse response, RedirectAttributes redirectAttributes) {
        TokenResponseMessageDTO authStatus = authenticationService.getAuthentication(authenticationRequestDTO);
        if (authStatus.getStatus().equals("ERROR")) {
            redirectAttributes.addFlashAttribute("feedback", authStatus.getMessage());
            redirectAttributes.addFlashAttribute("action", "login-fail");
            return "redirect:/";
        } else {
            Cookie cookie = new Cookie(AuthenticationServiceImpl.COOKIE_NAME, authStatus.getData().getToken());
            response.addCookie(cookie);
            redirectAttributes.addFlashAttribute("feedback", authStatus.getMessage());
            redirectAttributes.addFlashAttribute("action", "login-success");
            return "redirect:/home";
        }
    }
}