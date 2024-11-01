package org.docksidestage.bizfw.basic.buyticket.test;

import org.docksidestage.bizfw.basic.buyticket.Ticket;
import org.docksidestage.bizfw.basic.buyticket.TicketBooth;
import org.docksidestage.bizfw.basic.buyticket.TicketType;
import org.docksidestage.bizfw.basic.time.TestTimeManager;

// TODO mayukorin javadoc by jflute (2024/11/01)
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
        // TODO mayukorin [いいね] コメントはとても素晴らしい by jflute (2024/11/01)
        // TODO mayukorin 後でsetterではなく、もうここでTestTimeManagerが入れられたら世話ないのに by jflute (2024/11/01)
        // hint: もはやマッチ棒パズル (このクラス内の修正だけでいける!?)
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
