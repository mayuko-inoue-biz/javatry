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

/**
 * @author jflute
 */
public class TicketBooth {

    // ===================================================================================
    //                                                                          Definition
    //                                                                          ==========
    private static final int MAX_QUANTITY = 10;
    private static final int ONE_DAY_PRICE = 7400; // when 2019/06/15
    private static final int TWO_DAY_PRICE = 13200; // when 2019/06/15

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    private int oneDayPassportQuantity = MAX_QUANTITY;
    private int twoDayPassportQuantity = MAX_QUANTITY;
    private Integer salesProceeds; // null allowed: until first purchase

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public TicketBooth() {
    }

    // ===================================================================================
    //                                                                          Buy Ticket
    //                                                                          ==========
    // you can rewrite comments for your own language by jflute
    // e.g. Japanese
    // /**
    // * 1Dayパスポートを買う、パークゲスト用のメソッド。
    // * @param handedMoney パークゲストから手渡しされたお金(金額) (NotNull, NotMinus)
    // * @throws TicketSoldOutException ブース内のチケットが売り切れだったら
    // * @throws TicketShortMoneyException 買うのに金額が足りなかったら
    // */
    // TODO done mayukorin @return, せめて "購入された" というニュアンスがあるといいかも by jflute (2024/08/16)
    // TODO done mayukorin @return, 型の明示は必要ない、型はメソッド見ればわかるから by jflute (2024/08/16)
    // 引数は複数あるので特定しないといけない、戻り値は一つしかないから特定が必要ない
    // [ふぉろー] コンパイラーの気持ちになって仕様を推測していくこともできるよ話 (2024/08/16)
    // TODO done mayukorin @return, javatryのポリシーとして (NotNull) のマークを付けて欲しい by jflute (2024/08/16)
    // 呼び出し側にとってありがたい情報なのでぜひ。(DBFluteのjavadocの例も見てもらった)
    /**
     * Buy one-day passport, method for park guest.
     * @param handedMoney The money (amount) handed over from park guest. (NotNull, NotMinus)
     * @return 購入された oneDayPassport (NotNull)
     * @throws TicketSoldOutException When ticket in booth is sold out.
     * @throws TicketShortMoneyException When the specified money is short for purchase.
     */
    public Ticket buyOneDayPassport(Integer handedMoney) {
        if (oneDayPassportQuantity <= 0) {
            throw new TicketSoldOutException("Sold out");
        }

        Ticket oneDayPassport = sellTicket(handedMoney, ONE_DAY_PRICE).getTicket();
        --oneDayPassportQuantity;

        return oneDayPassport;
    }

    // TODO done mayukorin 列挙タイプの説明自体は良いけど、もうちょい濁したほうがよい。もし将来増えた場合... by jflute (2024/08/16)
    // "など" を使うとよい。"チケットとお釣りなどで構成される" とするだけで耐久性の強い説明になる。
    // 後は、e.g.で列挙して表現するという工夫もあり "e.g. チケット、お釣り"
    /**
     * TwoDayPassport を買うためのメソッド。ゲストが使う
     * @param handedMoney ゲストから渡された金額（NotNull, NotMinus）
     * @return TwoDayPassport とお釣りなどで構成される（NotNull）
     * @throws TicketSoldOutException チケットが売り切れている場合
     * @throws TicketShortMoneyException ゲストから渡された金額が、チケット料金よりも少ない場合
     */
    public TicketBuyResult buyTwoDayPassport(Integer handedMoney) {
        if (twoDayPassportQuantity <= 0) {
            throw new TicketSoldOutException("Sold out");
        }

        TicketBuyResult twoDayPassportBuyResult = sellTicket(handedMoney, TWO_DAY_PRICE);
        --twoDayPassportQuantity;

        return twoDayPassportBuyResult;
    }

    private TicketBuyResult sellTicket(Integer handedMoney, Integer ticketPrice) {
        checkIfNotLackOfMoney(handedMoney, ticketPrice);

        Ticket ticket = publishTicket(ticketPrice);
        updateSalesProceeds(ticketPrice);
        int change = handedMoney - ticketPrice;
        return new TicketBuyResult(ticket, change);
    }

    private void checkIfNotLackOfMoney(Integer handedMoney, Integer ticketPrice) {
        if (handedMoney < ticketPrice) {
            throw new TicketShortMoneyException("Short money: " + handedMoney);
        }
    }

    private Ticket publishTicket(Integer ticketPrice) {
        return new Ticket(ticketPrice);
    }

    // TODO done mayukorin [いいね] updateSalesProceeds()はとても良いメソッドです (粒度も名前も完璧) by jflute (2024/08/16)
    // TODO done mayukorin まず目標として、他にもupdateSalesProceeds()みたいなメソッド作れるはずなので... by jflute (2024/08/16)
    // 最低限そこまではやってみましょう。
    // TODO jflute 関数内で、引数によりアクセスする passportQuantity 変数を変えるにはどうしたら良いのでしょうか？
    // 関数の切り出しはある程度できたのですが、やはり PassportQuantity が切り出せずに残ってしまっています。
    // 私は以下の2種類の方法しか思いつかないのですが、他にヒントなどあったら教えていただきたいです。
    // ① passportQuantity の map をインスタンス変数として用意する
    // value に各 passport quantity が入っている map のインスタンス変数を用意する
    // 関数内では、map の key を引数にとって、key に対応する passport quantity にアクセスする
    // ② passportQuantity 分の Ticket のキューで構成される map をインスタンス変数として用意する
    // 各 quantity 分の Ticket インスタンスのキューで構成される map を用意する
    // TicketBooth のコンストラクタ内で map は初期化する
    // sellTicket 内では、キーを引数にとって対応する passport の キューにアクセスし、Ticket を取り出す
    // passportQuantity はインスタンス変数として持つ必要はなく、キューのサイズで分かる。
    private void updateSalesProceeds(Integer ticketPrice) {
        if (salesProceeds != null) {
            salesProceeds = salesProceeds + ticketPrice;
        } else {
            salesProceeds = ticketPrice;
        }
    }

    public static class TicketSoldOutException extends RuntimeException {

        private static final long serialVersionUID = 1L;

        public TicketSoldOutException(String msg) {
            super(msg);
        }
    }

    public static class TicketShortMoneyException extends RuntimeException {

        private static final long serialVersionUID = 1L;

        public TicketShortMoneyException(String msg) {
            super(msg);
        }
    }

    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    public int getOneDayPassportQuantity() {
        return oneDayPassportQuantity;
    }

    public int getTwoDayPassportQuantity() {
        return twoDayPassportQuantity;
    }

    public Integer getSalesProceeds() {
        return salesProceeds;
    }
}
