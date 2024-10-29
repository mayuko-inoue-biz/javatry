package org.docksidestage.bizfw.basic.time;

import java.time.LocalDateTime;

/**
 * 指定した LocalDateTime を返すクラス
 * @author mayukorin
 */
public class TestTimeManager implements TimeManager {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    private LocalDateTime specifiedLocalDateTime;

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    /**
     * @param specifiedLocalDateTime 指定したいLocalDateTime（NotNull）
     */
    public TestTimeManager(LocalDateTime specifiedLocalDateTime) {
        this.specifiedLocalDateTime = specifiedLocalDateTime;
    }

    // ===================================================================================
    //                                                                       localDateTime
    //                                                                         ===========
    /**
     * 指定した LocalDateTime を取得するためのメソッド
     * @return コンストラクタで指定した LocalDateTime（NotNull）
     */
    @Override
    public LocalDateTime getLocalDateTime() {
        return specifiedLocalDateTime;
    }
}
