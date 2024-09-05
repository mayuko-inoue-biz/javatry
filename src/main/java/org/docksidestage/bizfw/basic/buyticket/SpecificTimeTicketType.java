package org.docksidestage.bizfw.basic.buyticket;

import java.time.LocalTime;

/**
 * @author mayukorin
 */
// TODO mayukorin 名前他に良いのがないか考える
public enum SpecificTimeTicketType implements TicketType {

    // ===================================================================================
    //                                                                           Identifier
    //                                                                           =========
    NIGHT_ONLY_TWO_DAY_PASSPORT(2, 7400, MAX_QUANTITY, LocalTime.of(17, 0, 0));

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    private final int initialAvailableDays;
    private final int price;
    private final int initialQuantity;
    private final LocalTime availableTime;

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    SpecificTimeTicketType(int initialAvailableDays, int price, int initialQuantity, LocalTime availableTime) {
        this.initialAvailableDays = initialAvailableDays;
        this.price = price;
        this.initialQuantity = initialQuantity;
        this.availableTime = availableTime;
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

    public LocalTime getAvailableTime() {
        return availableTime;
    }
}
