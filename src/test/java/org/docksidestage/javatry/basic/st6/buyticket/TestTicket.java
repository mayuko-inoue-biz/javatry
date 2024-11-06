package org.docksidestage.javatry.basic.st6.buyticket;

import org.docksidestage.bizfw.basic.buyticket.Ticket;
import org.docksidestage.bizfw.basic.buyticket.TicketType;

// done mayukorin javadoc by jflute (2024/11/01)
/**
 * Ticketクラスのtest用に使うクラス
 * @author mayukorin
 */
public class TestTicket extends Ticket {

    // done mayukorin ConstructorでTestTimeManagerを受け取れるようにしたいところ by jflute (2024/11/01)
    public TestTicket(TicketType ticketType, TestTimeManager timeManager) {
        super(ticketType, timeManager);
    }

    // done mayukorin [いいね] こういう一時的なコメントもとても嬉しいものです by jflute (2024/10/29)
    // というのは、仮で入れて、そのまま修正するタイミングもなく放置されるとかよくあることで...
    // すると、この日付はちゃんとした決められた値なのか？仮の値なのか？後から読んだ人がわからなくなるのです。

//    [思い出]
//    public void setTimeManager(TestTimeManager timeManager) {
//        super.timeManager = timeManager;
//    }
}
