package org.docksidestage.bizfw.basic.buyticket;

import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * @author mayukorin
 */
public class SpecificTimeTicket extends Ticket{
    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    private final LocalTime availableTime;


    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    // TODO 最優先 mayukorin 呼び出し側で SpecificTimeTicketType を意識して指定しなければいけないのは微妙なのか考える
    public SpecificTimeTicket(SpecificTimeTicketType ticketType) {
        super(ticketType);
        this.availableTime = ticketType.getAvailableTime();

    }

    // ===================================================================================
    //                                                                             In Park
    //                                                                             =======
    // TODO mayukorin コメント追加したい
    public void doInPark(LocalDateTime currentDateTime) {
        LocalTime currentTime = currentDateTime.toLocalTime();
        // TODO mayukorin  after だとぴったりのとき入らない？
        if (currentTime.isBefore(availableTime)) {
            throw new IllegalStateException("Cannot be used if earlier than availableTime: availableTime=" + availableTime + ", currentTime=" + currentTime);
        }
        super.doInPark(currentDateTime);
    }
}
