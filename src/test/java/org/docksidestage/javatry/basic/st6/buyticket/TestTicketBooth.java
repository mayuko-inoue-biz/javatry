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
    //                                                                          Definition
    //                                                                          ==========
    private static final TestTimeManager testTimeManager = new TestTimeManager();

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    // done mayukorin switchメソッドがpublicで、1テスト内で複数回switchさせられることが前提なのであれば... by jflute (2024/11/07)
    // コンストラクターでは受け取らず何もせず、switchしたい人は(最初の一回も)自分でswitchしてもらうようにした方がシンプルかなと。
    // 現状のTestTicketBoothが日付を差し替えること前提のテスト用TicketBoothになってるというのも汎用性が低いかなと。
    // done mayukorin localDateTimeだとニュアンスが何もないので、後で使っているspecifiedLocalDateTimeとか by jflute (2024/11/07)
    // あと、specifiedLocalDateTimeとspecifiedCurrentDateTimeでブレてる。
    // (あと、LocalDateTimeのLocalはかなりクラス構造の便宜上の名前なので、変数名にあまり表現しなくても良いかなと)
    public TestTicketBooth() {
        super();
    }

    // [ふぉろー] TestTicketに差し替えるのであればオーバーライドの粒度はこれでOK。
    // 一方で、現状ではTestTicket何にもしてないので、実質本物のTicketをテストでnewしちゃっても問題なし。
    // であれば、差し替えたいのはTimeManagerだけになるので、オーバーライドの粒度はもっと小さくても良い。
    // (一方で一方で、TestTicket現状では何もテスト処理入ってないけど、将来入る可能性もあるから、あっても良い)
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
        // done mayukorin [いいね] おおぉ、もう TestTimeManager を唯一のものにして保持するようにしたのですね。 by jflute (2024/11/06)
        // テストなのである程度の割り切りもアリなのでこれはこれで良いと思います。
        // 細かいところは1on1でフォローします。(マッチ棒の話とか)
        // [ふぉろー] createTicket()は、super()で呼ばれることになっているので、
        // サブクラスの方のインスタンス変数とかで渡そうと思っても、タイミング的に間に合わない。
        // なので、staticな唯一のTimeManagerインスタンスをあらかじめ用意しておいて、
        // そいつに日付をswitchさせていくっていうやり方しかなさそうなのでまゆこりんさんすごい！
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
        testTimeManager.switchCurrentDateTime(specifiedCurrentDateTime);
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
