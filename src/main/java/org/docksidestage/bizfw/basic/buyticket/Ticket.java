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

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

// done mayukorin せっかくの作品なので自分の名前を by jflute (2024/08/23)
// TODO mayukorin lastUsedDateの変数宣言の直下、つまりConstructorタグコメントの直上に空行を by jflute (2024/08/30)
// 他のクラスやタグコメントの区切れでは、空行空けてるので統一性を。
/**
 * @author jflute
 * @author mayukorin
 */
public class Ticket {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    private final int displayPrice; // written on ticket, park guest can watch this
    // done mayukorin remainが動詞感が強いので、もうちょい形容詞的な活用にしたい by jflute (2024/08/23)
    private int remainingAvailableDays; // チケットの残り使用可能日数
    // done mayukorin 最新 "日" なので、LocalDate でいいかなと by jflute (2024/08/23)
    // done mayukorin Dayでも大きな間違いじゃないですが、Dayだと30とか31だけを持ってるイメージ、年月日なのでDateがよく使われる by jflute (2024/08/23)
    // TODO mayukorin こここそ、(NullAllowed) が欲しいですね。最初使うまでnullってのが明示されて欲しいところ by jflute (2024/08/30)
    private LocalDate lastUsedDate; // チケットを使用した最新日
    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public Ticket(int displayPrice, int consecutiveAvailableDays) {
        this.displayPrice = displayPrice;
        this.remainingAvailableDays = consecutiveAvailableDays;
    }

    // ===================================================================================
    //                                                                             In Park
    //                                                                             =======
    public void doInPark(LocalDate currentDate) {
        if (remainingAvailableDays == 0) {
            throw new IllegalStateException("This ticket is unavailable: displayedPrice=" + displayPrice);
        }
        // done mayukorin [いいね] 例外throwするときに関連する変数の値も出しているの素晴らしい by jflute (2024/08/23)
        // done mayukorin [読み物課題] せっかくなのでこちらを by jflute (2024/08/23)
        // 例外メッセージ、敬語で満足でもロスロスパターン
        // https://jflute.hatenadiary.jp/entry/20170804/explossloss
        // TODO mayukorin 変数名やif条件をしっかり読めばわかるのですが、込み入ってるのでコメントでの補足が欲しいところですね by jflute (2024/08/30)
        if (lastUsedDate != null) {
            long daysSinceLastUsedDay = ChronoUnit.DAYS.between(lastUsedDate, currentDate);
            if (daysSinceLastUsedDay == 0) {
                throw new IllegalStateException("Already in park by this ticket today: consecutive days you can use from tomorrow=" + remainingAvailableDays);
            } else if (daysSinceLastUsedDay > 1) {
                remainingAvailableDays = 0;
                throw new IllegalStateException("This ticket is no longer valid as you did not use the ticket on consecutive days: displayedPrice=" + displayPrice);
            } else if (daysSinceLastUsedDay < 0) {
                throw new IllegalStateException("currentDate must be a time later than lastUsedDate: specified currentDate=" + currentDate + ", lastUsedDate=" + lastUsedDate);
            }
        }
        remainingAvailableDays--;
        lastUsedDate = currentDate;
    }

    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    public int getDisplayPrice() {
        return displayPrice;
    }

    // TODO mayukorin メソッドに関しては、isとかboolean表現の動詞が欲しいところですね by jflute (2024/08/30)
    // 例えば、素直にisUnavailable()とか。でも否定の判定よりも、素直にisAvailable()にして、
    // 否定が欲しい利用側は自分で反転させるでもいいのかなと。
    // 否定の判定を欲しい利用箇所が圧倒的に多い特定のものとかであればいいのですが、できるだけ判定は素直にしたいところ。
    // もしくは、「使い切った」というニュアンスの単語を使うとか。isUsedUp(), isExhausted() とか、
    // 単語自体が逆を表現する用語を使って、反対の判定の方を素直にさせてしまうというのもアリです。
    // (要は、isNotUsedUp()とかisNotExhausted()みたいなことは避けたいということです)
    public boolean unAvailable() {
        return remainingAvailableDays == 0;
    }
}
