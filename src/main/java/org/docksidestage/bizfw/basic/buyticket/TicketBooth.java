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

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

// done mayukorin せっかくの作品なので自分の名前を by jflute (2024/08/30)
// TODO done mayukorin [読み物課題] 行動経済学でした by jflute (2024/08/30)
// https://twitter.com/jflute/status/840176230414483460
// https://twitter.com/jflute/status/1426028421675511808
/**
 * @author jflute
 * @author mayukorin
 */
public class TicketBooth {

    // done mayukorin これは知らなくて当然ですが、Definitionはstatic finalなもので使う慣習があるので... by jflute (2024/08/30)
    // ticketStockはインスタンスに属するAttributeなので、salesProceedsと同じエリアのAttribute配下に移動しましょう。
    // ===================================================================================
    //                                                                          Definition
    //                                                                          ==========

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    // done mayukorin 利用する時は抽象的なインターフェースのMapで十分 by jflute (2024/08/30)
    // 利用する側は自分が必要な最低限の概念のインターフェースで扱いたい。詳しくはstep6にて。
    // done mayukorin 本気で書くならこういうコメントも by jflute (2024/08/30)
    //  e.g. /** チケット種別ごとのチケット在庫のストック、valueのリストが0ならその種別は売り切れ (NotNull) */
    // done mayukorin せっかくのコメントなので、JavaDoc形式で書いて ticketStock に関連付けてみましょう by jflute (2024/08/30)
    // /** */ 形式で書いたコメントはJavaDocとして認識され、直後の変数やメソッドに紐づくようになります。
    /** チケット種別(key)ごとのチケットの在庫(value)、valueのListのサイズが0になったらその種別は売り切れ (NotNull) */
    private final Map<TicketType, List<Ticket>> ticketStock;
    // Map の Key の指定間違いを防止するために、key に Enum を用いることにした。
    // Price や、Quantity、DAY も全て Enum に集約した方が管理しやすいと思ったのでそうした。
    // [ふぉろー] EnumMapのHashMapとのパフォーマンスの違いについて説明した by jflute (2024/08/30)

    // [ふぉろー] 本気で書くとこんな感じ:
    /** このチケットブーズにおける売上、購入ごとにすぐに計上される (NullAllowed: 最初の購入まではnull) */
    private Integer salesProceeds; // null allowed: until first purchase
    
    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public TicketBooth() {
        // done mayukorin ここはTicketBoothとしてのコアな仕組みを構築する準備ロジックなのでコメントあるといい by jflute (2024/08/30)
        // _/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
        // e.g. jfluteより
        //  チケット種別ごとにあらかじめチケットの在庫(インスタンス)を作っておく
        //  この種別ごとの在庫が購入される事に徐々に減っていく
        // _/_/_/_/_/_/_/_/_/_/_/_/
        // ↓でも、真似てもいいので自分でなんか書いてみて
        // チケット種別ごとにあらかじめ決められた数だけチケットインスタンスを作成して List に入れておく
        // チケットが購入されたら List 内のチケットインスタンスが減っていく
        ticketStock = new EnumMap<>(TicketType.class);

        for (TicketType ticketType : TicketType.values()) { // ordinalの順でループ
            List<Ticket> tickets = new ArrayList<>();
            for (int i = 0; i < ticketType.getInitialQuantity(); i++) {
                tickets.add(new Ticket(ticketType.getPrice(), ticketType.getInitialAvailableDays()));
            }

            ticketStock.put(ticketType, tickets);
        }
        // [ふぉろー] 厳密にはunmodifiableにしておくと、安全で可読性がさらに良くなる
        // (ただ、本来は読み取り専用Mapインターフェースみたいなのがあったら一番だけどJava標準にはない)
        //ticketStock = Collections.unmodifiableMap(workingStock);
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
    // done mayukorin @return, せめて "購入された" というニュアンスがあるといいかも by jflute (2024/08/16)
    // done mayukorin @return, 型の明示は必要ない、型はメソッド見ればわかるから by jflute (2024/08/16)
    // 引数は複数あるので特定しないといけない、戻り値は一つしかないから特定が必要ない
    // [ふぉろー] コンパイラーの気持ちになって仕様を推測していくこともできるよ話 (2024/08/16)
    // done mayukorin @return, javatryのポリシーとして (NotNull) のマークを付けて欲しい by jflute (2024/08/16)
    // 呼び出し側にとってありがたい情報なのでぜひ。(DBFluteのjavadocの例も見てもらった)
    /**
     * Buy one-day passport, method for park guest.
     * @param handedMoney The money (amount) handed over from park guest. (NotNull, NotMinus)
     * @return 購入された oneDayPassport (NotNull)
     * @throws TicketSoldOutException When ticket in booth is sold out.
     * @throws TicketShortMoneyException When the specified money is short for purchase.
     */
    public Ticket buyOneDayPassport(Integer handedMoney) {
        // done mayukorin 参照するだけの処理であれば、引数で指定して、中で共有化できる by jflute (2024/08/23)
        // done mayukorin [いいね] お釣りの計算とかResultの生成とか超微々たるコストなので気にせず実行して辻褄合わせるのもアリ by jflute (2024/08/23)
        // TODO mayukorin ここまで来たら直接returnしちゃってもいいかなと。ショートカットあるかな？探してみてください by jflute (2024/08/30)
        Ticket oneDayPassport = sellTicket(TicketType.ONE_DAY_PASSPORT, handedMoney).getTicket();
        return oneDayPassport;
    }

    // done mayukorin 列挙タイプの説明自体は良いけど、もうちょい濁したほうがよい。もし将来増えた場合... by jflute (2024/08/16)
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
        TicketBuyResult twoDayPassportBuyResult = sellTicket(TicketType.TWO_DAY_PASSPORT, handedMoney);
        return twoDayPassportBuyResult;
    }

    // done mayukorin [いいね] 買い手のpublic buyに対して、売り手のprivate sellという対比が素敵すぎる by jflute (2024/08/30)
    // そこまで考えていたか...す、すごい。
    // ちなみに、他のケースだと... doBuyTicket(), doBuyPassport() というように doを付けて実処理感を出す。
    // ほかだと、internalBuyTicket() とか buyTicketInternally() とかあるけど、個人的には直感的ではないかな!?
    private TicketBuyResult sellTicket(TicketType ticketType, Integer handedMoney) {
        assertEnoughTicketQuantity(ticketType);
        assertEnoughMoney(ticketType, handedMoney);

        Ticket ticket = takeOutTicket(ticketType);
        updateSalesProceeds(ticketType);
        int change = calcChange(ticketType, handedMoney);
        return new TicketBuyResult(ticket, change);
    }

    private void assertEnoughTicketQuantity(TicketType ticketType) {
        if (ticketStock.get(ticketType).isEmpty()) {
            throw new TicketSoldOutException("Sold out");
        }
    }

    // done mayukorin checkは、目的語が正しいもの来るのか？間違ったものが来るのか？両方ありえる by jflute (2024/08/23)
    // というかcheckだと、どっちで例外がthrowされるのかがパッとわからない。
    // 代表選手として、assertという言葉があって、これは目的語が必ず正しいことが来る e.g. assertEnoughMoney
    // (プログラミングの世界における世界的な慣習になっている)
    // done mayukorin 引数の順番、sellTicket()と逆になっているけど、意図してないのであればブレなので合わせたほうがいいかな by jflute (2024/08/30)
    private void assertEnoughMoney(TicketType ticketType, Integer handedMoney) {
        if (handedMoney < ticketType.getPrice()) {
            throw new TicketShortMoneyException("Short money: " + handedMoney);
        }
    }

    private Ticket takeOutTicket(TicketType ticketType) {
        // done mayukorin remove()がそのインスタンスを戻すので事前getが要らない by jflute (2024/08/30)
        // done mayukorin 問答無用なことの補足として横に "すでにassertされてること前提" とか書いておくといいかなと by jflute (2024/08/30)
        return ticketStock.get(ticketType).remove(0); // 0インデックスが存在することは既にassertされてる前提
    }

    // done mayukorin [いいね] updateSalesProceeds()はとても良いメソッドです (粒度も名前も完璧) by jflute (2024/08/16)
    // done mayukorin まず目標として、他にもupdateSalesProceeds()みたいなメソッド作れるはずなので... by jflute (2024/08/16)
    // 最低限そこまではやってみましょう。
    // done jflute 関数内で、引数によりアクセスする passportQuantity 変数を変えるにはどうしたら良いのでしょうか？
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
    // done mayukorin [いいね] 素晴らしい、特に2はとても良い発想です (実務で状況変わった時に適してるかは別ですが) by jflute (2024/08/23)
    // done mayukorin では2で実装してみてください by jflute (2024/08/23)
    // done jflute 3の紹介は2の実装が終わってから (2024/08/23)
    // [ふぉろー] Quantityクラスを作る話をした。値的オブジェクト、value的オブジェクト
    // とはいえ現場では、作るならちゃんと全体ポリシーがあった方が良い、どこまで作るか？などなど
    
    // done mayukorin [いいね] 質問/相談するときに、自分ここまで考えました、という内容を伝えるの素晴らしい by jflute (2024/08/23)
    // done mayukorin [読み物課題] 質問のコツその一: なんでその質問してるのか？も伝えよう by jflute (2024/08/23)
    // https://jflute.hatenadiary.jp/entry/20170611/askingway1
    
    private void updateSalesProceeds(TicketType ticketType) {
        if (salesProceeds != null) {
            salesProceeds = salesProceeds + ticketType.getPrice();
        } else {
            salesProceeds = ticketType.getPrice();
        }
    }

    // done mayukorin [いいね] 小さくてもれっきとした業務ロジックなのでお釣りという概念を明示するためにもprivateメソッド良い by jflute (2024/08/30)
    private Integer calcChange(TicketType ticketType, Integer handedMoney) {
        return handedMoney - ticketType.getPrice();
    }
    
    // done mayukorin メソッドに切り出すIntelliJのショートカットを調べてきてください by jflute (2024/08/30)
    // command + option + M でした！
    // done mayukorin [へんじ] おっ、そうなんだ。これEclipseと同じだね(^^ by jflute (2024/08/30)

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
        return ticketStock.get(TicketType.ONE_DAY_PASSPORT).size();
    }

    public int getTwoDayPassportQuantity() {
        return ticketStock.get(TicketType.TWO_DAY_PASSPORT).size();
    }

    public Integer getSalesProceeds() {
        return salesProceeds;
    }
}
