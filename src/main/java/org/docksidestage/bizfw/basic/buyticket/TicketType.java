package org.docksidestage.bizfw.basic.buyticket;

/**
 * @author mayukorin
 */
public interface TicketType {
    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    // TODO mayukorin MAX_QUANTITY 外から変更できてしまうのか確認
    int MAX_QUANTITY = 10;

    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    int getInitialAvailableDays();
    int getPrice();
    int getInitialQuantity();
}
