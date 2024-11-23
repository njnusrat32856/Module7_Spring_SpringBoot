package com.nusrat.onlineBanking.controller.customerpart;

import com.nusrat.onlineBanking.entities.customerPart.SupportTicket;
import com.nusrat.onlineBanking.service.customerPart.SupportTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/supporttickets")
public class SupportTicketController {

    @Autowired
    private SupportTicketService supportTicketService;

    @GetMapping("/")
    public List<SupportTicket> getAllTickets() {
        return supportTicketService.getAllTickets();
    }

    @GetMapping("/customer/{customerId}")
    public List<SupportTicket> getTicketsByCustomerId(
            @PathVariable Long customerId
    ) {
        return supportTicketService.getTicketsByCustomerId(customerId);
    }

    @PostMapping("/create")
    public SupportTicket createTicket(@RequestBody SupportTicket ticket) {
        return supportTicketService.createSupportTicket(ticket);
    }

    @PutMapping("/{id}")
    public SupportTicket updateTicketStatus(@PathVariable Long id, @RequestParam String status) {
        return supportTicketService.updateTicketStatus(id, status);
    }
}
