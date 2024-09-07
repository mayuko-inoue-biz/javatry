package org.docksidestage.bizfw.basic.buyticket;

/**
 * @author mayukorin
 */
public interface TicketType {
    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    int getInitialAvailableDays();
    int getPrice();
    int getInitialQuantity();
}
