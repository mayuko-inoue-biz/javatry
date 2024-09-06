package org.docksidestage.bizfw.basic.buyticket;

/**
 * @author mayukorin
 */
public enum TicketType {

    // ===================================================================================
    //                                                                           Identifier
    //                                                                           =========
    ONE_DAY_PASSPORT(1, 7400, 10),
    TWO_DAY_PASSPORT(2, 13200, 10),// MAX_QUANTITY を入れられなくなってしまった...
    FOUR_DAY_PASSPORT(4, 22400, 10);

    // [ふぉろー] 確かに、こうやってもENUMの定義のところで利用することができない by jflute
    //public static final int MAX_QUANTITY = 10;
    // initialQuantityをIntegerにして、nullだったらデフォルトのMAXにするとかもアリ
    // (but nullに意味をもたせることになる::とはいえスコープ短ければ別に悪くはない、一言nullはデフォルトと書いてあれば)
    
    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    private final int initialAvailableDays;
    private final int price;
    private final int initialQuantity;

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    TicketType(int initialAvailableDays, int price, Integer initialQuantity) {
        this.initialAvailableDays = initialAvailableDays;
        this.price = price;
        this.initialQuantity = initialQuantity;
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
}
