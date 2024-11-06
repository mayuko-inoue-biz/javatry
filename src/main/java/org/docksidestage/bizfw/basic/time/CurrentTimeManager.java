package org.docksidestage.bizfw.basic.time;

import java.time.LocalDateTime;

// done mayukorin import文 by jflute (2024/11/01)
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
    public LocalDateTime getCurrentDateTime() {
        return LocalDateTime.now();
    }
}
