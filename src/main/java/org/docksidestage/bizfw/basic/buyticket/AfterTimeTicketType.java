package org.docksidestage.bizfw.basic.buyticket;

import java.time.LocalTime;

/**
 * @author mayukorin
 */
public enum AfterTimeTicketType implements TicketType {

    // ===================================================================================
    //                                                                           Identifier
    //                                                                           =========
    NIGHT_ONLY_TWO_DAY_PASSPORT(2, 7400, 10, LocalTime.of(17, 0));

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    private final int initialAvailableDays;
    private final int price;
    private final int initialQuantity;
    /** チケットが利用可能になる時刻 (NotNull) */
    private final LocalTime availableStartTime;

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    AfterTimeTicketType(int initialAvailableDays, int price, Integer initialQuantity, LocalTime availableStartTime) {
        this.initialAvailableDays = initialAvailableDays;
        this.price = price;
        this.initialQuantity = initialQuantity;
        this.availableStartTime = availableStartTime;
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

    public LocalTime getAvailableStartTime() {
        return availableStartTime;
    }
}
