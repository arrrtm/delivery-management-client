package kg.banksystem.deliveryclient.controller;

import kg.banksystem.deliveryclient.dto.admin.response.BranchReportResponseMessageDTO;
import kg.banksystem.deliveryclient.dto.admin.response.BranchStatisticResponseMessageDTO;
import kg.banksystem.deliveryclient.dto.baseresponse.LogicalResponseMessageDTO;
import kg.banksystem.deliveryclient.dto.baseresponse.SimpleResponseMessageDTO;
import kg.banksystem.deliveryclient.dto.branch.request.OrderRequestDTO;
import kg.banksystem.deliveryclient.dto.branch.request.OrderStatusRequestDTO;
import kg.banksystem.deliveryclient.dto.branch.response.BranchCourierResponseMessageDTO;
import kg.banksystem.deliveryclient.dto.branch.response.ListOrderResponseMessageDTO;
import kg.banksystem.deliveryclient.dto.branch.response.ListOrderStoryResponseMessageDTO;
import kg.banksystem.deliveryclient.service.BranchService;
import kg.banksystem.deliveryclient.service.GeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class BranchEmployeeController {

    private final BranchService branchService;
    private final GeneralService generalService;

    @Autowired
    public BranchEmployeeController(BranchService branchService, GeneralService generalService) {
        this.branchService = branchService;
        this.generalService = generalService;
    }

    // DONE
    static void reportModel(@CookieValue(name = "token") String token, Model model,
                            @RequestParam(defaultValue = "", required = false) String branch,
                            @RequestParam(defaultValue = "", required = false) String period,
                            BranchStatisticResponseMessageDTO statistics,
                            GeneralService generalService) {
        if (statistics.getStatus().equals("ERROR")) {
            model.addAttribute("statisticsError", true);
            model.addAttribute("feedback", statistics.getMessage());
        } else {
            model.addAttribute("statistics", statistics.getData());
            BranchReportResponseMessageDTO feedbackReport = generalService.getReport(token, branch, period);
            if (feedbackReport.getStatus().equals("SUCCESS")) {
                model.addAttribute("reportSuccess", true);
                model.addAttribute("feedbackReport", feedbackReport.getMessage());
                model.addAttribute("report", feedbackReport.getData());
            }
        }
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
    @GetMapping("story/branch")
    public String getAllOrdersStoryForBranchPage(@CookieValue(name = "token") String token, Model model,
                                                 @RequestParam(defaultValue = "0") int page,
                                                 @RequestParam(defaultValue = "", required = false) Long number,
                                                 @RequestParam(defaultValue = "", required = false) Long courier) {
        try {
            if (token == null) {
                return "redirect:/error/401";
            } else {
                ListOrderStoryResponseMessageDTO feedback = branchService.getOrderStoryForBranch(token, page, number, courier);
                if (feedback.getStatus().equals("ERROR")) {
                    model.addAttribute("storyError", true);
                    model.addAttribute("feedback", feedback.getMessage());
                } else {
                    model.addAttribute("stories", feedback.getData());
                }
                model.addAttribute("currentPage", page);
                model.addAttribute("totalPages", feedback.getTotalPages() - 1);
                model.addAttribute("courier", courier);
                model.addAttribute("couriers", branchService.getCouriersByBranch(token).getData());
                return "branch/story/stories";
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

    // DONE
    @GetMapping("orders/qr")
    public String getQrForOrder(@CookieValue(name = "token") String token, Model model,
                                @ModelAttribute("id") Long id) {
        try {
            if (token == null) {
                return "redirect:/error/401";
            } else {
                OrderRequestDTO orderRequestDTO = new OrderRequestDTO();
                orderRequestDTO.setId(id);
                SimpleResponseMessageDTO feedback = branchService.getQrForOrder(token, orderRequestDTO);
                if (feedback.getStatus().equals("ERROR")) {
                    model.addAttribute("qrError", true);
                    model.addAttribute("feedback", feedback.getMessage());
                } else {
                    model.addAttribute("qr", feedback.getData());
                    model.addAttribute("number", id);
                }
                return "branch/order/qr";
            }
        } catch (HttpClientErrorException.Forbidden exf) {
            return "redirect:/error/403";
        } catch (HttpClientErrorException.Unauthorized exu) {
            return "redirect:/error/401";
        }
    }

    // DONE
    @GetMapping("statistics/branch")
    public String getStatisticsForBranchPage(@CookieValue(name = "token") String token, Model model,
                                             @RequestParam(defaultValue = "", required = false) String period) {
        try {
            if (token == null) {
                return "redirect:/error/401";
            } else {
                SimpleResponseMessageDTO branchName = branchService.getBranchByToken(token);
                String branch = branchName.getData();
                reportModel(token, model, branch, period, branchService.getStatistics(token), generalService);
                return "branch/statistics/statistics";
            }
        } catch (HttpClientErrorException.Forbidden exf) {
            return "redirect:/error/403";
        } catch (HttpClientErrorException.Unauthorized exu) {
            return "redirect:/error/401";
        }
    }

    // DONE
    @GetMapping("statistics/branch/couriers")
    public String getStatisticsForBranchByCouriersPage(@CookieValue(name = "token") String token, Model model,
                                                       @RequestParam(defaultValue = "", required = false) String period) {
        try {
            if (token == null) {
                return "redirect:/error/401";
            } else {
                SimpleResponseMessageDTO branchName = branchService.getBranchByToken(token);
                String branch = branchName.getData();
                BranchCourierResponseMessageDTO statistics = branchService.getCourierStatistics(token);
                if (statistics.getStatus().equals("ERROR")) {
                    model.addAttribute("statisticsError", true);
                    model.addAttribute("feedback", statistics.getMessage());
                } else {
                    model.addAttribute("couriers", statistics.getData());
                    BranchReportResponseMessageDTO feedbackReport = generalService.getReport(token, branch, period);
                    if (feedbackReport.getStatus().equals("SUCCESS")) {
                        model.addAttribute("reportSuccess", true);
                        model.addAttribute("feedbackReport", feedbackReport.getMessage());
                        model.addAttribute("report", feedbackReport.getData());
                    }
                }
                return "branch/statistics/couriers";
            }
        } catch (HttpClientErrorException.Forbidden exf) {
            return "redirect:/error/403";
        } catch (HttpClientErrorException.Unauthorized exu) {
            return "redirect:/error/401";
        }
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