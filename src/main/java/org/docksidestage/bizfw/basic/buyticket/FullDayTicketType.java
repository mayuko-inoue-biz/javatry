package org.docksidestage.bizfw.basic.buyticket;

/**
 * @author mayukorin
 */
public enum FullDayTicketType implements TicketType {

    // ===================================================================================
    //                                                                           Identifier
    //                                                                           =========
    ONE_DAY_PASSPORT(1, 7400, MAX_QUANTITY),
    TWO_DAY_PASSPORT(2, 13200, MAX_QUANTITY),
    FOUR_DAY_PASSPORT(4, 22400, MAX_QUANTITY);

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    private final int initialAvailableDays;
    private final int price;
    private final int initialQuantity;

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    FullDayTicketType(int initialAvailableDays, int price, int initialQuantity) {
        this.initialAvailableDays = initialAvailableDays;
        this.price = price;
        this.initialQuantity = initialQuantity;
    }

    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    @Override
    public int getInitialAvailableDays() {
        return initialAvailableDays;
    }

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public int getInitialQuantity() {
        return initialQuantity;
    }
}
