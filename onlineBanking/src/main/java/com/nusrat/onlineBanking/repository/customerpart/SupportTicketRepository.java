package com.nusrat.onlineBanking.repository.customerpart;

import com.nusrat.onlineBanking.entities.customerPart.SupportTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SupportTicketRepository extends JpaRepository<SupportTicket, Long> {

    List<SupportTicket> findByCustomerId(long customerId);

//    Optional<SupportTicket> findByCustomerIdAndTicketId(int customerId, int ticketId);
}
