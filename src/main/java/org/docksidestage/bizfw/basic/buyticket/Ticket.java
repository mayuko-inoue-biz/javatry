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
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

// done mayukorin せっかくの作品なので自分の名前を by jflute (2024/08/23)
// done mayukorin lastUsedDateの変数宣言の直下、つまりConstructorタグコメントの直上に空行を by jflute (2024/08/30)
// 他のクラスやタグコメントの区切れでは、空行空けてるので統一性を。
// TODO done mayukorin javadoc内の説明文、authorよりも上が良いですね (Step5クラスのjavadocとか見てみましょう) by jflute (2024/09/20)
// e.g.
// /**
//  * Ticketを使ってパークにインできる
//  * @author jflute
//  * @author m.inoue
//  */
/**
 * Ticketを使ってパークにインできる
 * @author jflute
 * @author m.inoue
 */
public class Ticket {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    // done mayukorin javadoc入れたら、改行入れて見やすくしちゃってもいいかなと by jflute (2024/09/09)
    // TODO done mayukorin 定義順、Immutable,Mutableで分けてもいいかなと by jflute (2024/09/20)
    // (常にImmutable,Mutableで分けるわけでもなく、他に業務的にしっくりくる分けがあったらそっちで分けたりもする)
    /** チケット種別 (NotNull) */
    private final TicketType ticketType;

    /** チケット価格 (NotNull) */
    private final int displayPrice; // written on ticket, park guest can watch this

    /** イン可能時間 (NotNull) */
    private final LocalTime inParkBeginTime;

    /** アウトしていなければいけない時間、この時間にはアウト状態になっているべき (NotNull) */
    private final LocalTime inParkEndTime;

    /** チケットの残り使用可能日数 (NotNull) */
    private int remainingAvailableDays;

    /** チケット最新使用日 (NullAllowed：チケットを使ってInParkするまでnull) */
    private LocalDate lastUsedDate;

    // [インスタンス変数周りの思い出]
    // done m.inoue Ticket が TicketType の initialQuantity, Price, initialAvailableDays にアクセスできる必要はない気がする (2024/09/06)
    // [ふぉろー] 確かにその通りだけど、見えちゃっても特に問題ない程度ではある。内部構造ってほどのものでもないし。
    // そもそもTicketTypeがpublicなので、誰からもinitialQuantityとか見えちゃうものでもある。
    // 一方で、仕様変更によってはinitialQuantityが必要になったりする可能性もあるし。
    // 一方で一方で、enumにinterfaceをimplementsさせて隠す方法はある。
    // done mayukorin remainが動詞感が強いので、もうちょい形容詞的な活用にしたい by jflute (2024/08/23)
    // done mayukorin 最新 "日" なので、LocalDate でいいかなと by jflute (2024/08/23)
    // done mayukorin Dayでも大きな間違いじゃないですが、Dayだと30とか31だけを持ってるイメージ、年月日なのでDateがよく使われる by jflute (2024/08/23)
    // done mayukorin こここそ、(NullAllowed) が欲しいですね。最初使うまでnullってのが明示されて欲しいところ by jflute (2024/08/30)

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public Ticket(TicketType ticketType) {
        // Ticket の使用可能日数、値段は
        // 初期化時のticketType.getInitialAvailableDays()、ticketType.getPrice()から更新される可能性がある.
        // Ticketを使えば、Ticketの使用可能日数はticketType.getInitialAvailableDays()から減っていく
        // Ticketの値段も、セールなどでticketType.getInitialAvailableDays()から変わる可能性がある
        // そのため、Ticketの使用可能日数、値段は、ticketType.getPrice(), ticketType.getInitialAvailableDays() ではなく
        // this.displayPrice、this.remainingAvailableDays のインスタンス変数として定義している
        this.ticketType = ticketType;
        this.displayPrice = ticketType.getPrice();
        this.inParkBeginTime = ticketType.getInParkBeginTime();
        this.inParkEndTime = ticketType.getInParkEndTime();
        this.remainingAvailableDays = ticketType.getInitialAvailableDays();
    }

    // ===================================================================================
    //                                                                             In Park
    //                                                                             =======
    // TODO mayukorin 修行++: 利用する側が現在日時を指定できるとなると、変な時間を渡すこともできてしまうので... by jflute (2024/09/09)
    // できれば、現在日時の取得は内部で解決したいところですね。でも、テストで日時を指定したいから難しいところですね。
    // ということで、今のところこれでもいいけどいつかどうにかしたい、というところで。(1on1でフォローします)
    // 説明はしたので、step6をやってから着手するか？勢いに乗ってやるか？どちらでもOK。
    // [ふぉろー] 現場での現在日時インターフェースの話もした。
    // 題材として、LastaFluteのTimeManagerとUnitTestのswitchCurrentDate()の連携の話も少し。(究極の形)
    // switchCurrentDate(() -> {
    //     return LocalDateTime.of(2000, 1, 1, 1, 1);
    // });
    public void doInPark(LocalDateTime currentDateTime) {
        // TODO done mayukorin せっかくなので、IntelliJのショートカット使って、privateメソッド化いくつかやってみましょう by jflute (2024/09/20)
        assertCanInParkTime(currentDateTime.toLocalTime());
        assertNotUsedUpTicket();
        assertDailyInPark(currentDateTime.toLocalDate());
        useTicketForOneDay(currentDateTime.toLocalDate());
    }

    private void assertCanInParkTime(LocalTime currentTime) {
        // done mayukorin 21時ぴったりはインできて21時01分はインできない、という挙動は想定通りですか？ by jflute (2024/09/09)
        // 21時ぴったりにはインしてほしくないのでそのように直しました by mayukorin
        if (currentTime.isBefore(inParkBeginTime) || !currentTime.isBefore(inParkEndTime)) { // 今がインできない時間（なお、inParkEndTimeもインできない時間ピッタリなので該当する)
            throw new IllegalStateException("This time cannot be in park by ticket: currentTime=" + currentTime + ". inParkBeginTime=" + inParkBeginTime+ ", inParkEndTime=" + inParkEndTime);
        }
    }

    private void assertNotUsedUpTicket() {
        if (remainingAvailableDays == 0) { // チケットを使い切った
            throw new IllegalStateException("This ticket is unavailable: displayedPrice=" + displayPrice);
        }
    }

    private void assertDailyInPark(LocalDate currentDate) {
        // done mayukorin [いいね] 例外throwするときに関連する変数の値も出しているの素晴らしい by jflute (2024/08/23)
        // done mayukorin [読み物課題] せっかくなのでこちらを by jflute (2024/08/23)
        // 例外メッセージ、敬語で満足でもロスロスパターン
        // https://jflute.hatenadiary.jp/entry/20170804/explossloss
        // done mayukorin 変数名やif条件をしっかり読めばわかるのですが、込み入ってるのでコメントでの補足が欲しいところですね by jflute (2024/08/30)
        // done jflute 1on1にてコメント補足予定 (2024/09/02)
        // ↓以下、多少jfluteがコメント補佐した。でもドラえもんは...
        // 前回使用日から(2回目以降のインで)判断できるチケットの使用可否をチェックしている
        // チケットが利用できる条件: チケット最新使用日と今日の間の日数(daysSinceLastUsedDay)を計算し、日数が1日差、つまり今日がチケット最新使用日の次の日だったらチケットを利用できる。
        if (lastUsedDate != null) { // つまり2回目以降のイン
            long daysSinceLastUsedDay = ChronoUnit.DAYS.between(lastUsedDate, currentDate);
            if (daysSinceLastUsedDay == 0) { // つまり同じ日にインしてる
                throw new IllegalStateException("Already in park by this ticket today: consecutive days you can use from tomorrow=" + remainingAvailableDays);
            } else if (daysSinceLastUsedDay > 1) { // 前回から間1日空いちゃってのイン (仕様上許されてない)
                remainingAvailableDays = 0;
                throw new IllegalStateException("This ticket is no longer valid as you did not use the ticket on consecutive days: displayedPrice=" + displayPrice);
            } else if (daysSinceLastUsedDay < 0) { // ドラえもんが来てタイムマシーンで過去に戻った (これも許されてない)
                throw new IllegalStateException("currentDate must be a time later than lastUsedDate: specified currentDate=" + currentDate + ", lastUsedDate=" + lastUsedDate);
            }
        }
    }

    private void useTicketForOneDay(LocalDate currentDate) {
        remainingAvailableDays--;
        lastUsedDate = currentDate;
    }

    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    public TicketType getTicketType() {
        return ticketType;
    }

    public int getDisplayPrice() {
        return displayPrice;
    }

    // done mayukorin メソッドに関しては、isとかboolean表現の動詞が欲しいところですね by jflute (2024/08/30)
    // 例えば、素直にisUnavailable()とか。でも否定の判定よりも、素直にisAvailable()にして、
    // 否定が欲しい利用側は自分で反転させるでもいいのかなと。
    // 否定の判定を欲しい利用箇所が圧倒的に多い特定のものとかであればいいのですが、できるだけ判定は素直にしたいところ。
    // もしくは、「使い切った」というニュアンスの単語を使うとか。isUsedUp(), isExhausted() とか、
    // 単語自体が逆を表現する用語を使って、反対の判定の方を素直にさせてしまうというのもアリです。
    // (要は、isNotUsedUp()とかisNotExhausted()みたいなことは避けたいということです)
    public boolean isUsedUp() {
        return remainingAvailableDays == 0;
    }

    public int getRemainingAvailableDays() {
        return remainingAvailableDays;
    }

    public LocalDate getLastUsedDate() {
        return lastUsedDate;
    }
}
