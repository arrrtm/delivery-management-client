package kg.banksystem.deliveryclient.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ServerErrorException;

@Controller
@RequestMapping("/error/")
public class ExceptionHandlingController {

    // DONE
    @GetMapping("401")
    @ExceptionHandler(HttpClientErrorException.Unauthorized.class)
    public String unauthorized() {
        return "error/401";
    }

    // DONE
    @GetMapping("403")
    @ExceptionHandler(HttpClientErrorException.Forbidden.class)
    public String forbidden() {
        return "error/403";
    }

    // DONE
    @GetMapping("404")
    @ExceptionHandler(HttpClientErrorException.NotFound.class)
    public String not_found() {
        return "error/404";
    }

    // DONE
    @GetMapping("405")
    @ExceptionHandler(HttpClientErrorException.MethodNotAllowed.class)
    public String not_allowed() {
        return "error/405";
    }

    // DONE
    @GetMapping("505")
    @ExceptionHandler(ServerErrorException.class)
    public String server_error() {
        return "error/505";
    }
}