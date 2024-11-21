package com.nusrat.onlineBanking.service.customerpart;

import com.nusrat.onlineBanking.entities.customerPart.SupportTicket;
import com.nusrat.onlineBanking.repository.customerpart.SupportTicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SupportTicketService {

    @Autowired
    private SupportTicketRepository supportTicketRepository;

    public List<SupportTicket> getAllTickets() {
        return supportTicketRepository.findAll();
    }

    public List<SupportTicket> getTicketsByCustomerId(Long customerId) {
        return supportTicketRepository.findByCustomerId(customerId);
    }

    public SupportTicket createSupportTicket(SupportTicket ticket) {
        ticket.setStatus("Open");
        ticket.setCreatedAt(LocalDateTime.now());
        return supportTicketRepository.save(ticket);
    }

    public SupportTicket updateTicketStatus(Long ticketId, String status) {
        SupportTicket ticket = supportTicketRepository.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));
        ticket.setStatus(status);
        return supportTicketRepository.save(ticket);
    }
}
