package org.docksidestage.bizfw.basic.buyticket.test;

import org.docksidestage.bizfw.basic.buyticket.Ticket;
import org.docksidestage.bizfw.basic.buyticket.TicketBooth;
import org.docksidestage.bizfw.basic.buyticket.TicketType;
import org.docksidestage.bizfw.basic.time.TestTimeManager;

public class TestTicketBooth extends TicketBooth {

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public TestTicketBooth(TestTimeManager timeManager) {
        super();
        setTestTimeManagerToTestTicketsInStock(timeManager); // stock中のTicketのtimeManagerフィールドに、引数のtimeManagerを代入する
    }

    @Override
    protected Ticket createTicket(TicketType ticketType) {
        return new TestTicket(ticketType, null); // 後ほどsetTestTimeManagerToTestTicketsInStockでtimeManagerを代入するのでここでは一旦nullを入れる
    }

    private void setTestTimeManagerToTestTicketsInStock(TestTimeManager testTimeManager) {
        for (TicketType ticketType : TicketType.values()) { // ordinalの順でループ
            for (int i = 0; i < ticketType.getInitialQuantity(); i++) {
                // TestTicketにキャストできることは保証されている。createTicketを使って在庫生成するときに、ticketStock内にはTestTicketクラスが入るため
                // ZombieBarkingProcessのようにTestTicketの在庫をTestTicketBoothに保持しても良い気はするが
                // 在庫生成の処理を別で書かなきゃいけないのはめんどくさそうなので今回はキャストで行きたい
                ((TestTicket)this.ticketStock.get(ticketType).get(i)).setTimeManager(testTimeManager);
            }
        }
    }
}
