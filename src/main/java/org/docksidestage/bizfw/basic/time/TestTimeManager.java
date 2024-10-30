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
    /** 指定した LocalDateTime (NotNull) */
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
    //                                                                            Accessor
    //                                                                            ========
    /**
     * 指定したい LocalDateTime を変えるためのメソッド
     * @param specifiedLocalDateTime 指定したいLocalDateTime（NotNull）
     */
    public void setSpecifiedLocalDateTime(LocalDateTime specifiedLocalDateTime) {
        this.specifiedLocalDateTime = specifiedLocalDateTime;
    }

    // ===================================================================================
    //                                                                       localDateTime
    //                                                                         ===========
    /**
     * 指定した LocalDateTime を取得するためのメソッド
     * @return LocalDateTime（NotNull）
     */
    @Override
    public LocalDateTime getCurrentDateTime() {
        return specifiedLocalDateTime;
    }
}
