package org.docksidestage.javatry.basic.st6.buyticket;

import java.time.LocalDateTime;

import org.docksidestage.bizfw.basic.time.CurrentTimeManager;
import org.docksidestage.bizfw.basic.time.TimeManager;

// done mayukorin src/main/javaは本番リリースするものだけにして、test用はsrc/test/javaに置きたい by jflute (2024/11/01)
// basic/buyticketパッケージを作ってもいいし...st6/buyticketパッケージを作ってもいいし...
/**
 * 指定した CurrentDateTime を返すクラス
 * @author mayukorin
 */
public class TestTimeManager implements TimeManager {

    // ===================================================================================
    //                                                                          Definition
    //                                                                          ==========
    private static final CurrentTimeManager currentTimeManager = new CurrentTimeManager();

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    /** 指定した CurrentDateTime (NullAllowed: switchCurrentDateTime()が呼ばれて初めて値が入る) */
    private LocalDateTime specifiedCurrentDateTime;

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    // done mayukorin TetTicketBoothではnullを突っ込んでるので(NotNull)ではなく(NullAllowed)になる by jflute (2024/11/07)
    // done mayukorin 一方で、現状はnull受け取りしか想定されていないので、そもそもコンストラクターで受け取らなくても良いのでは？ by jflute (2024/11/07)
    // デフォルトでは、specifiedは何も初期化せず、switchされて初めて値が入るようにして...
    // getのときspecifiedがなければ本物の現在日時を戻すようにするとか。(e.g. 本物のTimeManagerを保持して利用する)
    protected TestTimeManager() {}

    // ===================================================================================
    //                                                                            Switcher
    //                                                                            ========
    // done mayukorin "途中でまた変えられる" みたいなニュアンスが一言あるとわかりやすいかも by jflute (2024/11/01)
    // done mayukorin switchCurrentDateTime()とか、メソッド名にニュアンスを入れても良いかも by jflute (2024/11/01)
    // done mayukorin テスト専用のクラスでもあるので、もうpublicにしちゃっても別に良いかなってのはありますね by jflute (2024/11/06)
    // (protectedでpackageスコープアクセスだとわかりづらさが若干あるので、無理に隠蔽しなくてもと)
    // TODO done mayukorin ここはもう do じゃなくて単なる switch でいいかなと (クラスが分かれてるし) by jflute (2024/11/11)
    /**
     * 指定したい CurrentDateTime を変えるためのメソッド
     * @param specifiedCurrentDateTime 指定したいCurrentDateTime（NotNull）
     */
    public void switchCurrentDateTime(LocalDateTime specifiedCurrentDateTime) {
        this.specifiedCurrentDateTime = specifiedCurrentDateTime;
    }

    // done mayukorin localDateTimeってタグコメントがザクっと過ぎるので...現在日時とかでも by jflute (2024/11/01)
    // ===================================================================================
    //                                                                              現在日時
    //                                                                         ===========
    /**
     * このメソッド呼び出し前にswitchCurrentDateTime()でCurrentDateTimeを指定していたらそのCurrentDateTimeを返す<br>
     * 指定していなければ、現在日時を返す
     */
    @Override
    public LocalDateTime getCurrentDateTime() {
        if (specifiedCurrentDateTime == null) {
            return currentTimeManager.getCurrentDateTime();
        }
        return specifiedCurrentDateTime;
    }
}
