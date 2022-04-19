package kg.banksystem.deliveryclient.controller;

import kg.banksystem.deliveryclient.dto.account.response.LogicalResponseMessageDTO;
import kg.banksystem.deliveryclient.dto.branch.request.OrderIdRequestDTO;
import kg.banksystem.deliveryclient.dto.branch.request.OrderRequestDTO;
import kg.banksystem.deliveryclient.dto.branch.request.OrderStatusRequestDTO;
import kg.banksystem.deliveryclient.dto.branch.response.ListOrderResponseMessageDTO;
import kg.banksystem.deliveryclient.dto.branch.response.OrderResponseMessageDTO;
import kg.banksystem.deliveryclient.service.BaseOperationService;
import kg.banksystem.deliveryclient.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class BranchEmployeeController {

    private final BranchService branchService;
    private final BaseOperationService baseOperationService;

    @Autowired
    public BranchEmployeeController(BranchService branchService, BaseOperationService baseOperationService) {
        this.branchService = branchService;
        this.baseOperationService = baseOperationService;
    }

    @GetMapping("branch/orders/new")
    public String getNewOrdersPageByBranch(@CookieValue(name = "token") String token, Model model) {
        try {
            if (token == null) {
                return "redirect:/error/401";
            } else {
                OrderStatusRequestDTO orderStatusRequestDTO = new OrderStatusRequestDTO();
                orderStatusRequestDTO.setRequestStatus("new");
                ListOrderResponseMessageDTO feedback = branchService.getOrdersForBranch(token, orderStatusRequestDTO);
                if (feedback.getStatus().equals("ERROR")) {
                    model.addAttribute("orderError", true);
                    model.addAttribute("feedback", feedback.getMessage());
                } else {
                    model.addAttribute("orderList", feedback.getData());
                }
                return "branch/newOrdersList";
            }
        } catch (HttpClientErrorException.Forbidden exf) {
            return "redirect:/error/403";
        }
    }

    @GetMapping("branch/orders/active")
    public String getActiveOrdersPageByBranch(@CookieValue(name = "token") String token, Model model) {
        try {
            if (token == null) {
                return "redirect:/error/401";
            } else {
                OrderStatusRequestDTO orderStatusRequestDTO = new OrderStatusRequestDTO();
                orderStatusRequestDTO.setRequestStatus("active");
                ListOrderResponseMessageDTO feedback = branchService.getOrdersForBranch(token, orderStatusRequestDTO);
                if (feedback.getStatus().equals("ERROR")) {
                    model.addAttribute("orderError", true);
                    model.addAttribute("feedback", feedback.getMessage());
                } else {
                    model.addAttribute("orderList", feedback.getData());
                }
                return "branch/activeOrdersList";
            }
        } catch (HttpClientErrorException.Forbidden exf) {
            return "redirect:/error/403";
        }
    }

    @GetMapping("branch/orders/destroyed")
    public String getDestroyedOrdersPageByBranch(@CookieValue(name = "token") String token, Model model) {
        try {
            if (token == null) {
                return "redirect:/error/401";
            } else {
                OrderStatusRequestDTO orderStatusRequestDTO = new OrderStatusRequestDTO();
                orderStatusRequestDTO.setRequestStatus("destroyed");
                ListOrderResponseMessageDTO feedback = branchService.getOrdersForBranch(token, orderStatusRequestDTO);
                if (feedback.getStatus().equals("ERROR")) {
                    model.addAttribute("orderError", true);
                    model.addAttribute("feedback", feedback.getMessage());
                } else {
                    model.addAttribute("orderList", feedback.getData());
                }
                return "branch/destroyedOrdersList";
            }
        } catch (HttpClientErrorException.Forbidden exf) {
            return "redirect:/error/403";
        }
    }

    @GetMapping("orders/{id}/processing")
    public String orderProcessing(@PathVariable("id") Long id, Model model,
                                  @CookieValue(name = "token") String token) {
        try {
            if (token == null) {
                return "redirect:/error/401";
            } else {
                OrderRequestDTO orderRequestDTO = new OrderRequestDTO();
                orderRequestDTO.setId(id);
                orderRequestDTO.setRequestStatus("new_accepted");
                OrderResponseMessageDTO feedback = baseOperationService.getOrderDetail(token, orderRequestDTO);
                model.addAttribute("orderDetail", feedback.getData());
                if (feedback.getData().getStatus().equals("Отправлен в филиал")) {
                    return "branch/detailNewOrder";
                } else {
                    return "branch/detailActiveOrder";
                }
            }
        } catch (HttpClientErrorException.Forbidden exf) {
            return "redirect:/error/403";
        }
    }

    @PostMapping("change/{id}/ready_from_delivery")
    public String changeStatusReadyFromDelivery(@CookieValue(name = "token") String token, @PathVariable("id") Long id,
                                                RedirectAttributes redirectAttributes) {
        try {
            if (token == null) {
                return "redirect:/error/401";
            } else {
                OrderIdRequestDTO orderIdRequestDTO = new OrderIdRequestDTO();
                orderIdRequestDTO.setId(id);
                LogicalResponseMessageDTO feedback = branchService.changeStatusReadyFromDelivery(token, orderIdRequestDTO);
                if (feedback.getStatus().equals("ERROR")) {
                    redirectAttributes.addFlashAttribute("action", "ready-for-delivery-fail");
                } else {
                    redirectAttributes.addFlashAttribute("action", "ready-for-delivery-success");
                }
                redirectAttributes.addFlashAttribute("feedback", feedback.getMessage());
                return "redirect:/branch/orders/new";
            }
        } catch (HttpClientErrorException.Forbidden exf) {
            return "redirect:/error/403";
        }
    }

    @PostMapping("change/{id}/destroyed")
    public String changeStatusDestroyed(@CookieValue(name = "token") String token, @PathVariable("id") Long id,
                                        RedirectAttributes redirectAttributes) {
        try {
            if (token == null) {
                return "redirect:/error/401";
            } else {
                OrderIdRequestDTO orderIdRequestDTO = new OrderIdRequestDTO();
                orderIdRequestDTO.setId(id);
                LogicalResponseMessageDTO feedback = branchService.changeStatusDestroyed(token, orderIdRequestDTO);
                if (feedback.getStatus().equals("ERROR")) {
                    redirectAttributes.addFlashAttribute("action", "destroyed-fail");
                } else {
                    redirectAttributes.addFlashAttribute("action", "destroyed-success");
                }
                redirectAttributes.addFlashAttribute("feedback", feedback.getMessage());
                return "redirect:/branch/orders/active";
            }
        } catch (HttpClientErrorException.Forbidden exf) {
            return "redirect:/error/403";
        }
    }
}