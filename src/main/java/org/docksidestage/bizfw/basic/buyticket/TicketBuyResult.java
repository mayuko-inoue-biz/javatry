package org.docksidestage.bizfw.basic.buyticket;

/**
 * @author mayukorin
 */
public class TicketBuyResult {
    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    private final Ticket ticket; // 購入したチケット（NotNull）
    private final int change; // チケット購入時のお釣り（NotMinus）

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public TicketBuyResult(Ticket ticket, int change) {
        this.ticket = ticket;
        if (change < 0) {
            throw new ChangeMinusException("minus change: " + change); // change が0以上と保証されている buyTwoDayPassport 以外のところで TicketBuyResult が呼ばれることもありえる && change が負の場合 change の意味がなくなるので（guest 的には change受け取ったのにお金減ったんだけどということになる）、 exception を出すようにした
        }
        this.change = change;
    }

    public static class ChangeMinusException extends RuntimeException {
        private static final long serialVersionUID = 1L; // TODO mayukorin serialVersionUID の役割を後で調べる
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
