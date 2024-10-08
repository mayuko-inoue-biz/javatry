package org.docksidestage.bizfw.basic.buyticket;

import java.time.LocalTime;

/**
 * Ticket種別ごとの値段や使用可能日時、 1チケットブースあたりの発行数などが設定されている。
 * @author mayukorin
 */
public enum TicketType {

    // ===================================================================================
    //                                                                           Identifier
    //                                                                           =========
    ONE_DAY_PASSPORT(1, 7400, 10, LocalTime.of(9, 0), LocalTime.of(21, 0)),
    TWO_DAY_PASSPORT(2, 13200, 10, LocalTime.of(9, 0), LocalTime.of(21, 0)),// MAX_QUANTITY を入れられなくなってしまった...
    FOUR_DAY_PASSPORT(4, 22400, 10, LocalTime.of(9, 0), LocalTime.of(21, 0)),
    NIGHT_ONLY_TWO_DAY_PASSPORT(2, 7400, 10, LocalTime.of(18, 0), LocalTime.of(21, 0));

    // [ふぉろー] 確かに、こうやってもENUMの定義のところで利用することができない by jflute
    //public static final int MAX_QUANTITY = 10;
    // initialQuantityをIntegerにして、nullだったらデフォルトのMAXにするとかもアリ
    // (but nullに意味をもたせることになる::とはいえスコープ短ければ別に悪くはない、一言nullはデフォルトと書いてあれば)

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    // done mayukorin canInParkTime, mustOutParkTimeはnot nullって一言どこかに欲しいですね by jflute (2024/09/09)
    // なぜかというと、後からチケット種別を追加する人が、時間って指定しないといけないの？とか迷うかもしれないので。
    // (実際、nullにするとTicketのdoInPark()が呼ばれたときにNullPointerExceptionとちょと遠いので)
    // done mayukorin mustOutParkTimeは、その時間自体はInなのかOutなのかの説明があると安心する by jflute (2024/09/09)
    // 一方で、inParkBeginTime, inParkEndTime とか begin/end で簡易な名前つけるパターンもよくある。(参考までに)
    /** チケットの使用可能日数 */
    private final int initialAvailableDays;

    /** 値段 */
    private final int price;

    /** 1チケットブースあたりのチケット発行数 */
    private final int initialQuantity;

    /** イン可能時間 (NotNull) */
    private final LocalTime inParkBeginTime;

    /** アウトしていなければいけない時間、この時間にはアウト状態になっているべき、inParkBeginTime より遅い時刻を指定すること(NotNull) */
    private final LocalTime inParkEndTime;

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    TicketType(int initialAvailableDays, int price, Integer initialQuantity, LocalTime inParkBeginTime, LocalTime inParkEndTime) {
        this.initialAvailableDays = initialAvailableDays;
        this.price = price;
        this.initialQuantity = initialQuantity;
        this.inParkBeginTime = inParkBeginTime;
        this.inParkEndTime = inParkEndTime;
    }

    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    public int getInitialAvailableDays() {
        return initialAvailableDays;
    }

    public int getPrice() {
        return price;
    }

    public int getInitialQuantity() { return initialQuantity; }

    public LocalTime getInParkBeginTime() {
        return inParkBeginTime;
    }

    public LocalTime getInParkEndTime() {
        return inParkEndTime;
    }
}
