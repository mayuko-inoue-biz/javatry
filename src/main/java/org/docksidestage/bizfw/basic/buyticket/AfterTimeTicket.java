package org.docksidestage.bizfw.basic.buyticket;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

/**
 * @author mayukorin
 */
public class AfterTimeTicket implements Ticket {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    /** チケット種別 (NotNull) */
    private final TicketType ticketType;
    private final int displayPrice; // written on ticket, park guest can watch this
    private int remainingAvailableDays; // チケットの残り使用可能日数
    /** チケット最新使用日 (NullAllowed：チケットを使ってInParkするまでnull) */
    private LocalDate lastUsedDate;
    /** チケットが利用可能になる時刻 (NotNull) */
    private final LocalTime availableStartTime;

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public AfterTimeTicket(AfterTimeTicketType ticketType) {
        this.ticketType = ticketType;
        this.displayPrice = ticketType.getPrice();
        this.remainingAvailableDays = ticketType.getInitialAvailableDays();
        this.availableStartTime = ticketType.getAvailableStartTime();
    }

    // ===================================================================================
    //                                                                             In Park
    //                                                                             =======
    public void doInPark(LocalDateTime currentDateTime) {
        // 現在時刻からチケットの使用可否をチェックしている
        LocalTime currentTime = currentDateTime.toLocalTime();
        if (currentTime.isBefore(availableStartTime)) { // チケットを利用できる時刻より前にインしようとする
            throw new IllegalStateException("Too early to in park: currentTime=" + currentTime+ ", availableStartTime=" + availableStartTime);
        }

        // TODO m.inoue AllDayTicketとの処理を共通化したい
        // 使用回数からチケットの使用可否をチェックしている
        if (remainingAvailableDays == 0) { // チケットを使い切った
            throw new IllegalStateException("This ticket is unavailable: displayedPrice=" + displayPrice);
        }
        // ↓以下、多少jfluteがコメント補佐した。でもドラえもんは...
        // 前回使用日から(2回目以降のインで)判断できるチケットの使用可否をチェックしている
        // チケットが利用できる条件: チケット最新使用日と今日の間の日数(daysSinceLastUsedDay)を計算し、日数が1日差、つまり今日がチケット最新使用日の次の日だったらチケットを利用できる。
        LocalDate currentDate = currentDateTime.toLocalDate();
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
        remainingAvailableDays--;
        lastUsedDate = currentDate;
    }

    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    @Override
    public TicketType getTicketType() {
        return ticketType;
    }

    @Override
    public int getDisplayPrice() {
        return displayPrice;
    }

    @Override
    public boolean isUsedUp() {
        return remainingAvailableDays == 0;
    }

    @Override
    public int getRemainingAvailableDays() {
        return remainingAvailableDays;
    }

    @Override
    public LocalDate getLastUsedDate() {
        return lastUsedDate;
    }
}
