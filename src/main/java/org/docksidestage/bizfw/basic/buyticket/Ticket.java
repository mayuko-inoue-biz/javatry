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

// TODO mayukorin せっかくの作品なので自分の名前を by jflute (2024/08/23)
/**
 * @author jflute
 */
public class Ticket {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    private final int displayPrice; // written on ticket, park guest can watch this
    // TODO mayukorin remainが動詞感が強いので、もうちょい形容詞的な活用にしたい by jflute (2024/08/23)
    private int remainAvailableDays; // チケットの残り使用可能日数
    // TODO mayukorin 最新 "日" なので、LocalDate でいいかなと by jflute (2024/08/23)
    // TODO mayukorin Dayでも大きな間違いじゃないですが、Dayだと30とか31だけを持ってるイメージ、年月日なのでDateがよく使われる by jflute (2024/08/23)
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
        // TODO mayukorin [いいね] 例外throwするときに関連する変数の値も出しているの素晴らしい by jflute (2024/08/23)
        // TODO mayukorin [読み物課題] せっかくなのでこちらを by jflute (2024/08/23)
        // 例外メッセージ、敬語で満足でもロスロスパターン
        // https://jflute.hatenadiary.jp/entry/20170804/explossloss
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
