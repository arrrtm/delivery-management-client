package kg.banksystem.deliveryclient.controller;

import kg.banksystem.deliveryclient.dto.admin.request.BranchRequestDTO;
import kg.banksystem.deliveryclient.dto.admin.request.UserRequestDTO;
import kg.banksystem.deliveryclient.dto.admin.response.*;
import kg.banksystem.deliveryclient.dto.baseresponse.LogicalResponseMessageDTO;
import kg.banksystem.deliveryclient.dto.baseresponse.SimpleResponseMessageDTO;
import kg.banksystem.deliveryclient.service.AdminService;
import kg.banksystem.deliveryclient.service.ControlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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

    // DONE
    static void baseUserPages(Model model, @RequestParam(defaultValue = "0") int page, ListUserResponseMessageDTO feedback) {
        if (feedback.getStatus().equals("ERROR")) {
            model.addAttribute("userError", true);
            model.addAttribute("feedback", feedback.getMessage());
        } else {
            model.addAttribute("users", feedback.getData());
        }
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", feedback.getTotalPages() - 1);
    }

    // DONE
    @GetMapping("users")
    public String getUsersPage(@CookieValue(name = "token") String token, Model model,
                               @RequestParam(defaultValue = "0") int page) {
        try {
            if (token == null) {
                return "redirect:/error/401";
            } else {
                ListUserResponseMessageDTO feedback = adminService.getAllUsers(token, page);
                baseUserPages(model, page, feedback);
                return "admin/user/users";
            }
        } catch (HttpClientErrorException.Forbidden exf) {
            return "redirect:/error/403";
        } catch (HttpClientErrorException.Unauthorized exu) {
            return "redirect:/error/401";
        }
    }

    // IN PROGRESS
    @PostMapping("users/register")
    public String userRegister(@CookieValue(name = "token") String token, RedirectAttributes redirectAttributes,
                               @ModelAttribute("userRequestDTO") UserRequestDTO userRequestDTO) {
        return null;
    }

    // IN PROGRESS
    @PostMapping("users/update")
    public String userUpdate(@CookieValue(name = "token") String token, RedirectAttributes redirectAttributes,
                             @ModelAttribute("userRequestDTO") UserRequestDTO userRequestDTO) {
        return null;
    }

    // IN PROGRESS
    @PostMapping("users/remove")
    public String userRemove(@CookieValue(name = "token") String token, RedirectAttributes redirectAttributes,
                             @ModelAttribute("userRequestDTO") UserRequestDTO userRequestDTO) {
        return null;
    }

    // DONE
    @GetMapping("branches")
    public String getBranchesPage(@CookieValue(name = "token") String token, Model model,
                                  @RequestParam(defaultValue = "0") int page) {
        try {
            if (token == null) {
                return "redirect:/error/401";
            } else {
                ListBranchResponseMessageDTO feedback = adminService.getAllBranches(token, page);
                if (feedback.getStatus().equals("ERROR")) {
                    model.addAttribute("branchError", true);
                    model.addAttribute("feedback", feedback.getMessage());
                } else {
                    model.addAttribute("branches", feedback.getData());
                }
                model.addAttribute("currentPage", page);
                model.addAttribute("totalPages", feedback.getTotalPages() - 1);
                return "admin/branch/branches";
            }
        } catch (HttpClientErrorException.Forbidden exf) {
            return "redirect:/error/403";
        } catch (HttpClientErrorException.Unauthorized exu) {
            return "redirect:/error/401";
        }
    }

    // DONE
    @PostMapping("branches/add")
    public String branchAdd(@CookieValue(name = "token") String token, RedirectAttributes redirectAttributes,
                            @ModelAttribute("branchRequestDTO") BranchRequestDTO branchRequestDTO) {
        try {
            if (token == null) {
                return "redirect:/error/401";
            } else {
                SimpleResponseMessageDTO feedback = adminService.addBranch(token, branchRequestDTO);
                if (feedback.getStatus().equals("ERROR")) {
                    redirectAttributes.addFlashAttribute("action", "add-fail");
                } else {
                    redirectAttributes.addFlashAttribute("action", "add-success");
                }
                redirectAttributes.addFlashAttribute("feedback", feedback.getMessage());
                return "redirect:/branches";
            }
        } catch (HttpClientErrorException.Forbidden exf) {
            return "redirect:/error/403";
        } catch (HttpClientErrorException.Unauthorized exu) {
            return "redirect:/error/401";
        }
    }

    // DONE
    @PostMapping("branches/edit")
    public String branchEdit(@CookieValue(name = "token") String token, RedirectAttributes redirectAttributes,
                             @ModelAttribute("branchRequestDTO") BranchRequestDTO branchRequestDTO) {
        try {
            if (token == null) {
                return "redirect:/error/401";
            } else {
                SimpleResponseMessageDTO feedback = adminService.editBranch(token, branchRequestDTO);
                if (feedback.getStatus().equals("ERROR")) {
                    redirectAttributes.addFlashAttribute("action", "edit-fail");
                } else {
                    redirectAttributes.addFlashAttribute("action", "edit-success");
                }
                redirectAttributes.addFlashAttribute("feedback", feedback.getMessage());
                return "redirect:/branches";
            }
        } catch (HttpClientErrorException.Forbidden exf) {
            return "redirect:/error/403";
        } catch (HttpClientErrorException.Unauthorized exu) {
            return "redirect:/error/401";
        }
    }

    // DONE
    @PostMapping("/branches/delete")
    public String branchDelete(@CookieValue(name = "token") String token, RedirectAttributes redirectAttributes,
                               @ModelAttribute("branchRequestDTO") BranchRequestDTO branchRequestDTO) {
        try {
            if (token == null) {
                return "redirect:/error/401";
            } else {
                SimpleResponseMessageDTO feedback = adminService.deleteBranch(token, branchRequestDTO);
                if (feedback.getStatus().equals("ERROR")) {
                    redirectAttributes.addFlashAttribute("action", "delete-fail");
                } else {
                    redirectAttributes.addFlashAttribute("action", "delete-success");
                }
                redirectAttributes.addFlashAttribute("feedback", feedback.getMessage());
                return "redirect:/branches";
            }
        } catch (HttpClientErrorException.Forbidden exf) {
            return "redirect:/error/403";
        } catch (HttpClientErrorException.Unauthorized exu) {
            return "redirect:/error/401";
        }
    }

    // DONE
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
        } catch (HttpClientErrorException.Unauthorized exu) {
            return "redirect:/error/401";
        }
    }

    // DONE
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
        } catch (HttpClientErrorException.Unauthorized exu) {
            return "redirect:/error/401";
        }
    }

    // DONE
    @GetMapping(value = {"users/detail", "users/users/detail"})
    @ResponseBody
    public UserResponseDTO getUserById(@CookieValue(name = "token") String token,
                                       @ModelAttribute("userRequestDTO") UserRequestDTO userRequestDTO) {
        UserResponseMessageDTO feedback = adminService.getUserById(token, userRequestDTO);
        return feedback.getData().getUserData();
    }

    // DONE
    @GetMapping(value = {"branches/detail", "branches/branches/detail"})
    @ResponseBody
    public BranchResponseDTO getBranchById(@CookieValue(name = "token") String token,
                                           @ModelAttribute("branchRequestDTO") BranchRequestDTO branchRequestDTO) {
        BranchResponseMessageDTO feedback = adminService.getBranchById(token, branchRequestDTO);
        return feedback.getData();
    }

    // DONE
    protected Optional<String> getPreviousPageByRequest(HttpServletRequest request) {
        return Optional.ofNullable(request.getHeader("Referer")).map(requestUrl -> "redirect:" + requestUrl);
    }
}