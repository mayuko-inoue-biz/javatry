package org.docksidestage.bizfw.basic.buyticket.test;

import org.docksidestage.bizfw.basic.buyticket.Ticket;
import org.docksidestage.bizfw.basic.buyticket.TicketBooth;
import org.docksidestage.bizfw.basic.buyticket.TicketType;

public class TestTicketBooth extends TicketBooth {

    @Override
    protected Ticket createTicket(TicketType ticketType) {
        return new TestTicket(ticketType);
    }
}
