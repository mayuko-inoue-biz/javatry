package org.docksidestage.bizfw.basic.time;

import java.time.LocalDateTime;

// TODO mayukorin src/main/javaは本番リリースするものだけにして、test用はsrc/test/javaに置きたい by jflute (2024/11/01)
// basic/buyticketパッケージを作ってもいいし...st6/buyticketパッケージを作ってもいいし...
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
    // TODO mayukorin "途中でまた変えられる" みたいなニュアンスが一言あるとわかりやすいかも by jflute (2024/11/01)
    // TODO mayukorin switchCurrentDateTime()とか、メソッド名にニュアンスを入れても良いかも by jflute (2024/11/01)
    /**
     * 指定したい LocalDateTime を変えるためのメソッド
     * @param specifiedLocalDateTime 指定したいLocalDateTime（NotNull）
     */
    public void setSpecifiedLocalDateTime(LocalDateTime specifiedLocalDateTime) {
        this.specifiedLocalDateTime = specifiedLocalDateTime;
    }

    // TODO mayukorin localDateTimeってタグコメントがザクっと過ぎるので...現在日時とかでも by jflute (2024/11/01)
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
