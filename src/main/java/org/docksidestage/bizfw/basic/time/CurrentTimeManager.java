package org.docksidestage.bizfw.basic.time;

import java.time.LocalDateTime;

import org.docksidestage.bizfw.basic.buyticket.TicketBooth;

/**
 * 現在の LocalDateTime を返すクラス
 * @author mayukorin
 */
public class CurrentTimeManager implements TimeManager {

    // ===================================================================================
    //                                                                       localDateTime
    //                                                                         ===========
    /**
     * 現在の LocalDateTime を取得するためのメソッド
     * @return 現在の LocalDateTime（NotNull）
     */
    @Override
    public LocalDateTime getLocalDateTime() {
        return LocalDateTime.now();
    }
}
