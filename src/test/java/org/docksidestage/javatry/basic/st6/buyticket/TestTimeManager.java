package org.docksidestage.javatry.basic.st6.buyticket;

import java.time.LocalDateTime;

import org.docksidestage.bizfw.basic.time.TimeManager;

// done mayukorin src/main/javaは本番リリースするものだけにして、test用はsrc/test/javaに置きたい by jflute (2024/11/01)
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
    // TODO mayukorin TetTicketBoothではnullを突っ込んでるので(NotNull)ではなく(NullAllowed)になる by jflute (2024/11/07)
    // TODO mayukorin 一方で、現状はnull受け取りしか想定されていないので、そもそもコンストラクターで受け取らなくても良いのでは？ by jflute (2024/11/07)
    // デフォルトでは、specifiedは何も初期化せず、switchされて初めて値が入るようにして...
    // getのときspecifiedがなければ本物の現在日時を戻すようにするとか。(e.g. 本物のTimeManagerを保持して利用する)
    /**
     * @param specifiedLocalDateTime 指定したいLocalDateTime（NotNull）
     */
    protected TestTimeManager(LocalDateTime specifiedLocalDateTime) {
        this.specifiedLocalDateTime = specifiedLocalDateTime;
    }

    // ===================================================================================
    //                                                                            Switcher
    //                                                                            ========
    // done mayukorin "途中でまた変えられる" みたいなニュアンスが一言あるとわかりやすいかも by jflute (2024/11/01)
    // done mayukorin switchCurrentDateTime()とか、メソッド名にニュアンスを入れても良いかも by jflute (2024/11/01)
    // TODO mayukorin テスト専用のクラスでもあるので、もうpublicにしちゃっても別に良いかなってのはありますね by jflute (2024/11/06)
    // (protectedでpackageスコープアクセスだとわかりづらさが若干あるので、無理に隠蔽しなくてもと)
    /**
     * 指定したい LocalDateTime を変えるためのメソッド
     * @param specifiedLocalDateTime 指定したいLocalDateTime（NotNull）
     */
    protected void doSwitchCurrentDateTime(LocalDateTime specifiedLocalDateTime) {
        this.specifiedLocalDateTime = specifiedLocalDateTime;
    }

    // done mayukorin localDateTimeってタグコメントがザクっと過ぎるので...現在日時とかでも by jflute (2024/11/01)
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
