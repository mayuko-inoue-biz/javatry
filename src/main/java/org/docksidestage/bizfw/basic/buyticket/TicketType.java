package org.docksidestage.bizfw.basic.buyticket;

/**
 * @author mayukorin
 */
public enum TicketType {

    // ===================================================================================
    //                                                                           Identifier
    //                                                                           =========
    ONE_DAY_PASSPORT(1, 7400, 10),
    TWO_DAY_PASSPORT(2, 13200, 10); // MAX_QUANTITY を入れられなくなってしまった...

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    private final int initialAvailableDays;
    private final int price;
    private final int initialQuantity;

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    TicketType(int initialAvailableDays, int price, int initialQuantity) {
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
