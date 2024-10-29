package org.docksidestage.bizfw.basic.buyticket.test;

import java.time.LocalDateTime;

import org.docksidestage.bizfw.basic.buyticket.Ticket;
import org.docksidestage.bizfw.basic.buyticket.TicketType;
import org.docksidestage.bizfw.basic.time.TestTimeManager;
import org.docksidestage.bizfw.basic.time.TimeManager;

public class TestTicket extends Ticket {

    public TestTicket(TicketType ticketType) {
        super(ticketType);
    }

    @Override
    protected TimeManager createTimeManager() {
        return new TestTimeManager(LocalDateTime.of(2017, 11, 17, 18, 0)); // 一旦仮で入れておく
    }
}
