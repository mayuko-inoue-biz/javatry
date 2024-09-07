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
import java.time.LocalDateTime;

// done mayukorin せっかくの作品なので自分の名前を by jflute (2024/08/23)
// done mayukorin lastUsedDateの変数宣言の直下、つまりConstructorタグコメントの直上に空行を by jflute (2024/08/30)
// 他のクラスやタグコメントの区切れでは、空行空けてるので統一性を。
/**
 * @author jflute
 */
public interface Ticket {

    // ===================================================================================
    //                                                                             In Park
    //                                                                             =======
    void doInPark(LocalDateTime currentDateTime);

    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    TicketType getTicketType();
    int getDisplayPrice();
    // done mayukorin メソッドに関しては、isとかboolean表現の動詞が欲しいところですね by jflute (2024/08/30)
    // 例えば、素直にisUnavailable()とか。でも否定の判定よりも、素直にisAvailable()にして、
    // 否定が欲しい利用側は自分で反転させるでもいいのかなと。
    // 否定の判定を欲しい利用箇所が圧倒的に多い特定のものとかであればいいのですが、できるだけ判定は素直にしたいところ。
    // もしくは、「使い切った」というニュアンスの単語を使うとか。isUsedUp(), isExhausted() とか、
    // 単語自体が逆を表現する用語を使って、反対の判定の方を素直にさせてしまうというのもアリです。
    // (要は、isNotUsedUp()とかisNotExhausted()みたいなことは避けたいということです)
    boolean isUsedUp();
    int getRemainingAvailableDays();
    LocalDate getLastUsedDate();
}
