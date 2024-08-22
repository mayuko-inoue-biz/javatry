/*
 * Copyright 2019-2022 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.docksidestage.bizfw.basic.buyticket;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * @author jflute
 */
public class Ticket {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    private final int displayPrice; // written on ticket, park guest can watch this
    private int remainAvailableDays; // チケットの残り使用可能日数
    private LocalDateTime lastUsedDay; // チケットを使用した最新日
    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public Ticket(int displayPrice, int consecutiveAvailableDays) {
        this.displayPrice = displayPrice;
        this.remainAvailableDays = consecutiveAvailableDays;
    }

    // ===================================================================================
    //                                                                             In Park
    //                                                                             =======
    public void doInPark(LocalDateTime currentDay) {
        if (remainAvailableDays == 0) {
            throw new IllegalStateException("This ticket is unavailable: displayedPrice=" + displayPrice);
        }
        if (lastUsedDay != null) {
            long daysSinceLastUsedDay = Duration.between(lastUsedDay, currentDay).toDays();
            if (daysSinceLastUsedDay == 0) {
                throw new IllegalStateException("Already in park by this ticket today: consecutive days you can use from tomorrow=" + remainAvailableDays);
            } else if (daysSinceLastUsedDay > 1) {
                remainAvailableDays = 0;
                throw new IllegalStateException("This ticket is no longer valid as you did not use the ticket on consecutive days: displayedPrice=" + displayPrice);
            } else if (daysSinceLastUsedDay < 0) {
                throw new IllegalStateException("currentDay must be a time later than lastUsedDay: specified currentDay=" + currentDay + ", lastUsedDay=" + lastUsedDay);
            }
        }
        remainAvailableDays--;
        lastUsedDay = currentDay;
    }

    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    public int getDisplayPrice() {
        return displayPrice;
    }

    public boolean unAvailable() {
        return remainAvailableDays == 0;
    }
}
