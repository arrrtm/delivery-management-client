package kg.banksystem.deliveryclient.controller;

import kg.banksystem.deliveryclient.dto.baseresponse.LogicalResponseMessageDTO;
import kg.banksystem.deliveryclient.dto.branch.request.OrderRequestDTO;
import kg.banksystem.deliveryclient.dto.branch.request.OrderStatusRequestDTO;
import kg.banksystem.deliveryclient.dto.branch.response.ListOrderResponseMessageDTO;
import kg.banksystem.deliveryclient.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class BranchEmployeeController {

    private final BranchService branchService;

    @Autowired
    public BranchEmployeeController(BranchService branchService) {
        this.branchService = branchService;
    }

    // DONE
    @GetMapping("orders/new")
    public String getNewOrdersPageByBranch(@CookieValue(name = "token") String token, Model model,
                                           @RequestParam(defaultValue = "0") int page,
                                           @RequestParam(defaultValue = "", required = false) Long number) {
        try {
            if (token == null) {
                return "redirect:/error/401";
            } else {
                OrderStatusRequestDTO orderStatusRequestDTO = new OrderStatusRequestDTO();
                orderStatusRequestDTO.setRequestStatus("new");
                baseBranchPages(token, model, page, number, orderStatusRequestDTO);
                return "branch/order/newOrders";
            }
        } catch (HttpClientErrorException.Forbidden exf) {
            return "redirect:/error/403";
        } catch (HttpClientErrorException.Unauthorized exu) {
            return "redirect:/error/401";
        }
    }

    // DONE
    @GetMapping("orders/active")
    public String getActiveOrdersPageByBranch(@CookieValue(name = "token") String token, Model model,
                                              @RequestParam(defaultValue = "0") int page,
                                              @RequestParam(defaultValue = "", required = false) Long number) {
        try {
            if (token == null) {
                return "redirect:/error/401";
            } else {
                OrderStatusRequestDTO orderStatusRequestDTO = new OrderStatusRequestDTO();
                orderStatusRequestDTO.setRequestStatus("active");
                baseBranchPages(token, model, page, number, orderStatusRequestDTO);
                return "branch/order/activeOrders";
            }
        } catch (HttpClientErrorException.Forbidden exf) {
            return "redirect:/error/403";
        } catch (HttpClientErrorException.Unauthorized exu) {
            return "redirect:/error/401";
        }
    }

    // DONE
    @GetMapping("orders/destroyed")
    public String getDestroyedOrdersPageByBranch(@CookieValue(name = "token") String token, Model model,
                                                 @RequestParam(defaultValue = "0") int page,
                                                 @RequestParam(defaultValue = "", required = false) Long number) {
        try {
            if (token == null) {
                return "redirect:/error/401";
            } else {
                OrderStatusRequestDTO orderStatusRequestDTO = new OrderStatusRequestDTO();
                orderStatusRequestDTO.setRequestStatus("destroyed");
                baseBranchPages(token, model, page, number, orderStatusRequestDTO);
                return "branch/order/destroyedOrders";
            }
        } catch (HttpClientErrorException.Forbidden exf) {
            return "redirect:/error/403";
        } catch (HttpClientErrorException.Unauthorized exu) {
            return "redirect:/error/401";
        }
    }

    // DONE
    @PostMapping("change/ready-from-delivery")
    public String changeStatusReadyFromDelivery(@CookieValue(name = "token") String token, RedirectAttributes redirectAttributes,
                                                @ModelAttribute("idNO") Long id) {
        try {
            if (token == null) {
                return "redirect:/error/401";
            } else {
                OrderRequestDTO orderRequestDTO = new OrderRequestDTO();
                orderRequestDTO.setId(id);
                LogicalResponseMessageDTO feedback = branchService.changeStatusReadyFromDelivery(token, orderRequestDTO);
                if (feedback.getStatus().equals("ERROR")) {
                    redirectAttributes.addFlashAttribute("action", "ready-for-delivery-fail");
                } else {
                    redirectAttributes.addFlashAttribute("action", "ready-for-delivery-success");
                }
                redirectAttributes.addFlashAttribute("feedback", feedback.getMessage());
                return "redirect:/orders/new";
            }
        } catch (HttpClientErrorException.Forbidden exf) {
            return "redirect:/error/403";
        } catch (HttpClientErrorException.Unauthorized exu) {
            return "redirect:/error/401";
        }
    }

    // DONE
    @PostMapping("change/destroyed")
    public String changeStatusDestroyed(@CookieValue(name = "token") String token, RedirectAttributes redirectAttributes,
                                        @ModelAttribute("idAO") Long id) {
        try {
            if (token == null) {
                return "redirect:/error/401";
            } else {
                OrderRequestDTO orderRequestDTO = new OrderRequestDTO();
                orderRequestDTO.setId(id);
                LogicalResponseMessageDTO feedback = branchService.changeStatusDestroyed(token, orderRequestDTO);
                if (feedback.getStatus().equals("ERROR")) {
                    redirectAttributes.addFlashAttribute("action", "destroyed-fail");
                } else {
                    redirectAttributes.addFlashAttribute("action", "destroyed-success");
                }
                redirectAttributes.addFlashAttribute("feedback", feedback.getMessage());
                return "redirect:/orders/active";
            }
        } catch (HttpClientErrorException.Forbidden exf) {
            return "redirect:/error/403";
        } catch (HttpClientErrorException.Unauthorized exu) {
            return "redirect:/error/401";
        }
    }

    // IN PROGRESS
    @PostMapping("orders/qr")
    public String getQrForOrder(@CookieValue(name = "token") String token, Model model) {
        return null;
    }

    // DONE
    private void baseBranchPages(@CookieValue(name = "token") String token, Model model,
                                 @RequestParam(defaultValue = "0") int page,
                                 @RequestParam(defaultValue = "", required = false) Long number,
                                 OrderStatusRequestDTO orderStatusRequestDTO) {
        ListOrderResponseMessageDTO feedback = branchService.getOrdersForBranch(token, orderStatusRequestDTO, page, number);
        if (feedback.getStatus().equals("ERROR")) {
            model.addAttribute("orderError", true);
            model.addAttribute("feedback", feedback.getMessage());
        } else {
            model.addAttribute("orders", feedback.getData());
        }
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", feedback.getTotalPages() - 1);
    }
}