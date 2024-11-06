package org.docksidestage.javatry.basic.st6.buyticket;

import java.time.LocalDateTime;

import org.docksidestage.bizfw.basic.buyticket.Ticket;
import org.docksidestage.bizfw.basic.buyticket.TicketBooth;
import org.docksidestage.bizfw.basic.buyticket.TicketType;

// done mayukorin javadoc by jflute (2024/11/01)
/**
 * TicketBoothクラスのtest用に使うクラス.<br>
 * テスト時に使う現在日時を途中で変えるには、switchCurrentDateTime を呼び出すこと.
 * @author mayukorin
 */
public class TestTicketBooth extends TicketBooth {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    private static final TestTimeManager testTimeManager = new TestTimeManager(null);

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public TestTicketBooth(LocalDateTime localDateTime) {
        super();
        switchCurrentDateTime(localDateTime);
    }

    @Override
    protected Ticket createTicket(TicketType ticketType) {
        // [思い出]
        // 後ほどsetTestTimeManagerToTestTicketsInStockでtimeManagerを代入するのでここでは一旦nullを入れる
        // done mayukorin [いいね] コメントはとても素晴らしい by jflute (2024/11/01)
        // done mayukorin 後でsetterではなく、もうここでTestTimeManagerが入れられたら世話ないのに by jflute (2024/11/01)
        // hint: もはやマッチ棒パズル (このクラス内の修正だけでいける!?)
        // なんとかここで TestTimeManager は入れられるようになったけど、後から外で指定された CurrentDateTime を入れ替えているという意味ではやってることがあまり変わってないかも
        // でも、これ以上あまり思いつかないかも...
        // createTicket() で、コンストラクタの外から引数で指定されたtestTimeManagerを代入するには
        // createTicket()実行時点で外から指定されたtestTimeManagerにアクセスできてなきゃいけないけど
        // そのためには、createTicket()が実行されるまでに TestTicketBoothのフィールドに外指定のtestTimeManagerを代入して、 createTicket()ではそのフィールドにアクセスするか、
        // 外指定のtestTimeManagerをTestTicketBoothのコンストラクタから引数としてcreateTicket()に渡していく方法しか思いつかない
        // しかし、コンストラクタでは、super()より前は何も実行できないので、前者は難しそう。
        // 後者も、引数として渡すにはTicketBooth のコンストラクタも大幅に変えなきゃいけないので、やりたくない
        // TODO mayukorin [いいね] おおぉ、もう TestTimeManager を唯一のものにして保持するようにしたのですね。 by jflute (2024/11/06)
        // テストなのである程度の割り切りもアリなのでこれはこれで良いと思います。
        // 細かいところは1on1でフォローします。(マッチ棒の話とか)
        return new TestTicket(ticketType, testTimeManager);
    }

    // ===================================================================================
    //                                                               Switch CurrentDateTime
    //                                                                         ===========
    /**
     * テスト時に、指定したい CurrentDateTime を変えるためのメソッド
     * @param specifiedCurrentDateTime 指定したい CurrentDateTime（NotNull）
     */
    public void switchCurrentDateTime(LocalDateTime specifiedCurrentDateTime) {
        testTimeManager.doSwitchCurrentDateTime(specifiedCurrentDateTime);
    }

//    // [思い出]
//    private void setTestTimeManagerToTestTicketsInStock(TestTimeManager testTimeManager) {
//        for (TicketType ticketType : TicketType.values()) { // ordinalの順でループ
//            for (int i = 0; i < ticketType.getInitialQuantity(); i++) {
//                // TestTicketにキャストできることは保証されている。createTicketを使って在庫生成するときに、ticketStock内にはTestTicketクラスが入るため
//                // ZombieBarkingProcessのようにTestTicketの在庫をTestTicketBoothに保持しても良い気はするが
//                // 在庫生成の処理を別で書かなきゃいけないのはめんどくさそうなので今回はキャストで行きたい
//                ((TestTicket)this.ticketStock.get(ticketType).get(i)).setTimeManager(testTimeManager);
//            }
//        }
//    }
}
