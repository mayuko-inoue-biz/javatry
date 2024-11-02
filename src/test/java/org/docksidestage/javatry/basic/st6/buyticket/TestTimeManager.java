package org.docksidestage.javatry.basic.st6.buyticket;

import java.time.LocalDateTime;

import org.docksidestage.bizfw.basic.time.TimeManager;

// TODO done mayukorin src/main/javaは本番リリースするものだけにして、test用はsrc/test/javaに置きたい by jflute (2024/11/01)
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
    protected TestTimeManager(LocalDateTime specifiedLocalDateTime) {
        this.specifiedLocalDateTime = specifiedLocalDateTime;
    }

    // ===================================================================================
    //                                                                            Switcher
    //                                                                            ========
    // TODO done mayukorin "途中でまた変えられる" みたいなニュアンスが一言あるとわかりやすいかも by jflute (2024/11/01)
    // TODO done mayukorin switchCurrentDateTime()とか、メソッド名にニュアンスを入れても良いかも by jflute (2024/11/01)
    /**
     * 指定したい LocalDateTime を変えるためのメソッド
     * @param specifiedLocalDateTime 指定したいLocalDateTime（NotNull）
     */
    protected void doSwitchCurrentDateTime(LocalDateTime specifiedLocalDateTime) {
        this.specifiedLocalDateTime = specifiedLocalDateTime;
    }

    // TODO done mayukorin localDateTimeってタグコメントがザクっと過ぎるので...現在日時とかでも by jflute (2024/11/01)
    // ===================================================================================
    //                                                                              現在日時
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
