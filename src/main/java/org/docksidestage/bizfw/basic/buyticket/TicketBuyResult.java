package org.docksidestage.bizfw.basic.buyticket;

// done mayukorin class宣言直下(Attributeコメントの上)、他のクラスでは空行空いてるので形を合わせましょう by jflute (2024/08/30)
/**
 * @author mayukorin
 * TicketBoothで購入したTicketとお釣りなどで構成されている。
 */
public class TicketBuyResult {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    /** 購入したチケット（NotNull） */
    private final Ticket ticket;
    /** チケット購入時のお釣り（NotMinus） */
    private final int change;

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public TicketBuyResult(Ticket ticket, int change) {
        this.ticket = ticket;
        if (change < 0) {
            // _/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
            // done mayukorin 例外throwのところ良いコメントなのですが、横のスラスラコメントだと見づらいので... by jflute (2024/08/30)
            // せっかくなので見やすく改行を入れてもいいので、ぜひ独立行のコメントに直しましょう。
            // _/_/_/_/_/_/_/_/_/_/
            // change が0以上と保証されている buyTwoDayPassport 以外のところで TicketBuyResult が呼ばれることもありえる &&
            // change が負の場合 change の意味がなくなるので（guest 的には change受け取ったのにお金減ったんだけどということになる）
            // exception を出すようにした
            throw new ChangeMinusException("minus change: " + change);
            // done jflute [1on1にて] 横のスラスラコメントで書くか？独立行コメントで書くか？の境目は？ (2024/08/30)
            // 長くなったら独立させるってシンプルな感じ。
            // 個人的には横のスラスラはまあまあ好きなので、書けるまでは書く。
            // 横のスラスラの(個人的に思う)メリットなど色々と説明した。
        }
        this.change = change;
    }

    public static class ChangeMinusException extends RuntimeException {
        private static final long serialVersionUID = 1L; // done mayukorin serialVersionUID の役割を後で調べる
        // シリアライズを宣言したクラスのバージョン。
        // ID が同じ = 構造が同じことを保証している
        // シリアライズ：Javaのオブジェクトまたはデータを別のJavaシステムでも利用できるようにbyte形式（stream of bytes）に変換するフォーマット変換技術
        public ChangeMinusException(String msg) {
            super(msg);
        }
    }

    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    public Ticket getTicket() {
        return ticket;
    }

    public int getChange() {
        return change;
    }
}
