package kg.banksystem.deliveryclient.controller;

import kg.banksystem.deliveryclient.dto.admin.response.ListUserResponseMessageDTO;
import kg.banksystem.deliveryclient.dto.bank.request.ClientRequestDTO;
import kg.banksystem.deliveryclient.dto.bank.request.OrderOperationsRequestDTO;
import kg.banksystem.deliveryclient.dto.bank.request.UsersWithRoleRequestDTO;
import kg.banksystem.deliveryclient.dto.bank.response.ClientResponseDTO;
import kg.banksystem.deliveryclient.dto.bank.response.ClientResponseMessageDTO;
import kg.banksystem.deliveryclient.dto.bank.response.ListClientResponseMessageDTO;
import kg.banksystem.deliveryclient.dto.baseresponse.LogicalResponseMessageDTO;
import kg.banksystem.deliveryclient.dto.baseresponse.SimpleResponseMessageDTO;
import kg.banksystem.deliveryclient.dto.branch.request.OrderRequestDTO;
import kg.banksystem.deliveryclient.service.BankService;
import kg.banksystem.deliveryclient.service.GeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.stream.Collectors;

@Controller
public class BankEmployeeController {

    private final BankService bankService;
    private final GeneralService generalService;

    @Autowired
    public BankEmployeeController(BankService bankService, GeneralService generalService) {
        this.bankService = bankService;
        this.generalService = generalService;
    }

    @GetMapping("users/couriers")
    public String getCouriersPage(@CookieValue(name = "token") String token, Model model, @RequestParam(defaultValue = "0") int page) {
        try {
            UsersWithRoleRequestDTO users = new UsersWithRoleRequestDTO();
            users.setRequestRole("courier");
            if (token == null) {
                return "redirect:/error/401";
            } else {
                model.addAttribute("branches", generalService.getBranches(token).getData());
                model.addAttribute("roles", generalService.getRoles(token).getData().stream().filter(role -> role.getName().equals("Курьер")).collect(Collectors.toList()));
                getPages(token, model, users, page);
            }
            return "bank/user/branchCouriers";
        } catch (HttpClientErrorException.Forbidden exf) {
            return "redirect:/error/403";
        } catch (HttpClientErrorException.Unauthorized exu) {
            return "redirect:/error/401";
        }
    }

    @GetMapping("users/employees")
    public String getBranchEmployeesPage(@CookieValue(name = "token") String token, Model model, @RequestParam(defaultValue = "0") int page) {
        try {
            UsersWithRoleRequestDTO users = new UsersWithRoleRequestDTO();
            users.setRequestRole("branch_employee");
            if (token == null) {
                return "redirect:/error/401";
            } else {
                model.addAttribute("branches", generalService.getBranches(token).getData());
                model.addAttribute("roles", generalService.getRoles(token).getData().stream().filter(role -> role.getName().equals("Сотрудник филиала")).collect(Collectors.toList()));
                getPages(token, model, users, page);
            }
            return "bank/user/branchEmployees";
        } catch (HttpClientErrorException.Forbidden exf) {
            return "redirect:/error/403";
        } catch (HttpClientErrorException.Unauthorized exu) {
            return "redirect:/error/401";
        }
    }

    @GetMapping("clients")
    public String getClientsPage(@CookieValue(name = "token") String token, Model model, @RequestParam(defaultValue = "0") int page) {
        try {
            if (token == null) {
                return "redirect:/error/401";
            } else {
                ListClientResponseMessageDTO feedback = bankService.getAllClients(token, page);
                if (feedback.getStatus().equals("ERROR")) {
                    model.addAttribute("clientError", true);
                    model.addAttribute("feedback", feedback.getMessage());
                } else {
                    model.addAttribute("clients", feedback.getData());
                }
                model.addAttribute("currentPage", page);
                model.addAttribute("totalPages", feedback.getTotalPages() - 1);
                return "bank/client/clients";
            }
        } catch (HttpClientErrorException.Forbidden exf) {
            return "redirect:/error/403";
        } catch (HttpClientErrorException.Unauthorized exu) {
            return "redirect:/error/401";
        }
    }

    @PostMapping("clients/add")
    public String clientAdd(@CookieValue(name = "token") String token, RedirectAttributes redirectAttributes, @ModelAttribute("clientRequestDTO") ClientRequestDTO clientRequestDTO) {
        try {
            if (token == null) {
                return "redirect:/error/401";
            } else {
                SimpleResponseMessageDTO feedback = bankService.addClient(token, clientRequestDTO);
                if (feedback.getStatus().equals("ERROR")) {
                    redirectAttributes.addFlashAttribute("action", "add-fail");
                } else {
                    redirectAttributes.addFlashAttribute("action", "add-success");
                }
                redirectAttributes.addFlashAttribute("feedback", feedback.getMessage());
                return "redirect:/clients";
            }
        } catch (HttpClientErrorException.Forbidden exf) {
            return "redirect:/error/403";
        } catch (HttpClientErrorException.Unauthorized exu) {
            return "redirect:/error/401";
        }
    }

    @PostMapping("clients/edit")
    public String clientEdit(@CookieValue(name = "token") String token, RedirectAttributes redirectAttributes, @ModelAttribute("clientRequestDTO") ClientRequestDTO clientRequestDTO) {
        try {
            if (token == null) {
                return "redirect:/error/401";
            } else {
                SimpleResponseMessageDTO feedback = bankService.editClient(token, clientRequestDTO);
                if (feedback.getStatus().equals("ERROR")) {
                    redirectAttributes.addFlashAttribute("action", "edit-fail");
                } else {
                    redirectAttributes.addFlashAttribute("action", "edit-success");
                }
                redirectAttributes.addFlashAttribute("feedback", feedback.getMessage());
                return "redirect:/clients";
            }
        } catch (HttpClientErrorException.Forbidden exf) {
            return "redirect:/error/403";
        } catch (HttpClientErrorException.Unauthorized exu) {
            return "redirect:/error/401";
        }
    }

    @PostMapping("clients/delete")
    public String clientDelete(@CookieValue(name = "token") String token, RedirectAttributes redirectAttributes, @ModelAttribute("clientRequestDTO") ClientRequestDTO clientRequestDTO) {
        try {
            if (token == null) {
                return "redirect:/error/401";
            } else {
                SimpleResponseMessageDTO feedback = bankService.deleteClient(token, clientRequestDTO);
                if (feedback.getStatus().equals("ERROR")) {
                    redirectAttributes.addFlashAttribute("action", "delete-fail");
                } else {
                    redirectAttributes.addFlashAttribute("action", "delete-success");
                }
                redirectAttributes.addFlashAttribute("feedback", feedback.getMessage());
                return "redirect:/clients";
            }
        } catch (HttpClientErrorException.Forbidden exf) {
            return "redirect:/error/403";
        } catch (HttpClientErrorException.Unauthorized exu) {
            return "redirect:/error/401";
        }
    }

    @PostMapping("orders/add")
    public String orderAdd(@CookieValue(name = "token") String token, RedirectAttributes redirectAttributes, @ModelAttribute("orderOperationsRequestDTO") OrderOperationsRequestDTO orderOperationsRequestDTO) {
        try {
            if (token == null) {
                return "redirect:/error/401";
            } else {
                SimpleResponseMessageDTO feedback = bankService.addOrder(token, orderOperationsRequestDTO);
                if (feedback.getStatus().equals("ERROR")) {
                    redirectAttributes.addFlashAttribute("action", "add-fail");
                } else {
                    redirectAttributes.addFlashAttribute("action", "add-success");
                }
                redirectAttributes.addFlashAttribute("feedback", feedback.getMessage());
                return "redirect:/orders";
            }
        } catch (HttpClientErrorException.Forbidden exf) {
            return "redirect:/error/403";
        } catch (HttpClientErrorException.Unauthorized exu) {
            return "redirect:/error/401";
        }
    }

    @PostMapping("orders/edit")
    public String orderEdit(@CookieValue(name = "token") String token, RedirectAttributes redirectAttributes, @ModelAttribute("orderOperationsRequestDTO") OrderOperationsRequestDTO orderOperationsRequestDTO) {
        try {
            if (token == null) {
                return "redirect:/error/401";
            } else {
                SimpleResponseMessageDTO feedback = bankService.editOrder(token, orderOperationsRequestDTO);
                if (feedback.getStatus().equals("ERROR")) {
                    redirectAttributes.addFlashAttribute("action", "edit-fail");
                } else {
                    redirectAttributes.addFlashAttribute("action", "edit-success");
                }
                redirectAttributes.addFlashAttribute("feedback", feedback.getMessage());
                return "redirect:/orders";
            }
        } catch (HttpClientErrorException.Forbidden exf) {
            return "redirect:/error/403";
        } catch (HttpClientErrorException.Unauthorized exu) {
            return "redirect:/error/401";
        }
    }

    @PostMapping("orders/delete")
    public String orderDelete(@CookieValue(name = "token") String token, RedirectAttributes redirectAttributes, @ModelAttribute("orderOperationsRequestDTO") OrderOperationsRequestDTO orderOperationsRequestDTO) {
        try {
            if (token == null) {
                return "redirect:/error/401";
            } else {
                SimpleResponseMessageDTO feedback = bankService.deleteOrder(token, orderOperationsRequestDTO);
                if (feedback.getStatus().equals("ERROR")) {
                    redirectAttributes.addFlashAttribute("action", "delete-fail");
                } else {
                    redirectAttributes.addFlashAttribute("action", "delete-success");
                }
                redirectAttributes.addFlashAttribute("feedback", feedback.getMessage());
                return "redirect:/orders";
            }
        } catch (HttpClientErrorException.Forbidden exf) {
            return "redirect:/error/403";
        } catch (HttpClientErrorException.Unauthorized exu) {
            return "redirect:/error/401";
        }
    }

    @PostMapping("change/sent-to-branch")
    public String changeStatusSentToBranch(@CookieValue(name = "token") String token, RedirectAttributes redirectAttributes, @ModelAttribute("id") Long id) {
        try {
            if (token == null) {
                return "redirect:/error/401";
            } else {
                OrderRequestDTO orderRequestDTO = new OrderRequestDTO();
                orderRequestDTO.setId(id);
                LogicalResponseMessageDTO feedback = bankService.changeStatusSentToBranch(token, orderRequestDTO);
                if (feedback.getStatus().equals("ERROR")) {
                    redirectAttributes.addFlashAttribute("action", "sent-to-branch-fail");
                } else {
                    redirectAttributes.addFlashAttribute("action", "sent-to-branch-success");
                }
                redirectAttributes.addFlashAttribute("feedback", feedback.getMessage());
                return "redirect:/orders";
            }
        } catch (HttpClientErrorException.Forbidden exf) {
            return "redirect:/error/403";
        } catch (HttpClientErrorException.Unauthorized exu) {
            return "redirect:/error/401";
        }
    }

    @GetMapping(value = {"clients/detail", "clients/clients/detail"})
    @ResponseBody
    public ClientResponseDTO getClientById(@CookieValue(name = "token") String token, @ModelAttribute("clientRequestDTO") ClientRequestDTO clientRequestDTO) {
        ClientResponseMessageDTO feedback = bankService.getClientById(token, clientRequestDTO);
        return feedback.getData();
    }

    public void getPages(String token, Model model, UsersWithRoleRequestDTO users, int page) {
        ListUserResponseMessageDTO feedback = bankService.getAllUsers(token, users, page);
        BaseAdminController.baseUserPages(model, page, feedback);
    }
}