package org.docksidestage.bizfw.basic.buyticket;

import java.time.LocalTime;

/**
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
    // TODO mayukorin canInParkTime, mustOutParkTimeはnot nullって一言どこかに欲しいですね by jflute (2024/09/09)
    // なぜかというと、後からチケット種別を追加する人が、時間って指定しないといけないの？とか迷うかもしれないので。
    // (実際、nullにするとTicketのdoInPark()が呼ばれたときにNullPointerExceptionとちょと遠いので)
    private final int initialAvailableDays;
    private final int price;
    private final int initialQuantity;
    private final LocalTime canInParkTime;
    private final LocalTime mustOutParkTime;


    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    TicketType(int initialAvailableDays, int price, Integer initialQuantity, LocalTime canInParkTime, LocalTime mustOutParkTime) {
        this.initialAvailableDays = initialAvailableDays;
        this.price = price;
        this.initialQuantity = initialQuantity;
        this.canInParkTime = canInParkTime;
        this.mustOutParkTime = mustOutParkTime;
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

    public int getInitialQuantity() {
        return initialQuantity;
    }

    public LocalTime getCanInParkTime() {
        return canInParkTime;
    }

    public LocalTime getMustOutParkTime() {
        return mustOutParkTime;
    }
}
