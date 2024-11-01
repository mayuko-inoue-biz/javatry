package org.docksidestage.bizfw.basic.buyticket.test;

import org.docksidestage.bizfw.basic.buyticket.Ticket;
import org.docksidestage.bizfw.basic.buyticket.TicketType;
import org.docksidestage.bizfw.basic.time.TestTimeManager;
import org.docksidestage.bizfw.basic.time.TimeManager;

// TODO mayukorin javadoc by jflute (2024/11/01)
public class TestTicket extends Ticket {

    // TODO mayukorin ConstructorでTestTimeManagerを受け取れるようにしたいところ by jflute (2024/11/01)
    public TestTicket(TicketType ticketType, TimeManager timeManager) {
        super(ticketType, timeManager);
    }

    // done mayukorin [いいね] こういう一時的なコメントもとても嬉しいものです by jflute (2024/10/29)
    // というのは、仮で入れて、そのまま修正するタイミングもなく放置されるとかよくあることで...
    // すると、この日付はちゃんとした決められた値なのか？仮の値なのか？後から読んだ人がわからなくなるのです。

    public void setTimeManager(TestTimeManager timeManager) {
        super.timeManager = timeManager;
    }
}
