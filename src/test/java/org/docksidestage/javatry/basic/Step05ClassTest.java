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
package org.docksidestage.javatry.basic;

import org.docksidestage.bizfw.basic.buyticket.TicketBooth;
import org.docksidestage.bizfw.basic.buyticket.TicketBooth.TicketShortMoneyException;
import org.docksidestage.bizfw.basic.buyticket.Ticket;
import org.docksidestage.bizfw.basic.buyticket.TicketBuyResult;
import org.docksidestage.unit.PlainTestCase;

/**
 * The test of class. <br>
 * Operate exercise as javadoc. If it's question style, write your answer before test execution. <br>
 * (javadocの通りにエクササイズを実施。質問形式の場合はテストを実行する前に考えて答えを書いてみましょう) <br>
 * 
 * If ambiguous requirement exists, you can determine specification that seems appropriate. <br>
 * (要件が曖昧なところがあれば、適切だと思われる仕様を決めても良いです)
 * 
 * @author jflute
 * @author mayukorin
 */
public class Step05ClassTest extends PlainTestCase {

    // ===================================================================================
    //                                                                          How to Use
    //                                                                          ==========
    /**
     * What string is sea variable at the method end? <br>
     * (メソッド終了時の変数 sea の中身は？)
     */
    public void test_class_howToUse_basic() {
        TicketBooth booth = new TicketBooth();
        booth.buyOneDayPassport(7400);
        int sea = booth.getOneDayPassportQuantity();
        log(sea); // your answer? => 9
        // buyOneDayPassportで、インスタンス変数 quantity がMAX_QUANTITYから-1に更新されるため
        // 答え：9
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_class_howToUse_overpay() {
        TicketBooth booth = new TicketBooth();
        booth.buyOneDayPassport(10000);
        Integer sea = booth.getSalesProceeds();
        log(sea); // your answer? => 10000
        // done mayukorin "handedMoney の初期値は..." -> "salesProceeds の初期値は..." by jflute (2024/08/09)
        // salesProceeds の初期値は null なため、buyOneDayPassport 内で salesProceeds = handedMoney（=10000）になるため
        // 答え：10000
        // 「② 受け取ったお金の分だけ売上が増えていく問題」を修正後は、one-day price 価格（7400）になる
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_class_howToUse_nosales() {
        TicketBooth booth = new TicketBooth();
        Integer sea = booth.getSalesProceeds();
        log(sea); // your answer? => null
        // salesProceeds の初期値は null & Integer 型の sea に代入してもエラーにはならないため。
        // 答え：null
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_class_howToUse_wrongQuantity() {
        Integer sea = doTest_class_ticket_wrongQuantity();
        log(sea); // your answer? => 10
        // handedMony が 7400 未満なので buyOneDayPassport 内で TicketShortMoneyException が投げられる。
        // doTest 関数内では、TicketShortMoneyException がキャッチされ、return booth.getQuantity() まで実行される
        // quantity は 10-1 = 9 なため、sea = 9 となる。
        // 答え：9
        // 「① お金不足でもチケットが減る問題」を修正後は、TicketShortMoneyException が投げられた後に quantity を減らすようにするため、10 のまま
    }

    private Integer doTest_class_ticket_wrongQuantity() {
        TicketBooth booth = new TicketBooth();
        int handedMoney = 7399;
        try {
            booth.buyOneDayPassport(handedMoney);
            fail("always exception but none");
        } catch (TicketShortMoneyException continued) {
            log("Failed to buy one-day passport: money=" + handedMoney, continued);
        }
        return booth.getOneDayPassportQuantity();
    }

    // [お知らせ] JavaDocのみならず、コメントはチャレンジとして積極的に書いていってみてください。
    // コメントは書かないと上手にならないのでby jflute
    // ===================================================================================
    //                                                                           Let's fix
    //                                                                           =========
    /**
     * Fix the problem of ticket quantity reduction when short money. (Don't forget to fix also previous exercise answers) <br>
     * (お金不足でもチケットが減る問題をクラスを修正して解決しましょう (以前のエクササイズのanswerの修正を忘れずに))
     */
    public void test_class_letsFix_ticketQuantityReduction() {
        Integer sea = doTest_class_ticket_wrongQuantity();
        log(sea); // should be max quantity, visual check here
        // 10 になった。
    }

    /**
     * Fix the problem of sales proceeds increased by handed money. (Don't forget to fix also previous exercise answers) <br>
     * (受け取ったお金の分だけ売上が増えていく問題をクラスを修正して解決しましょう (以前のエクササイズのanswerの修正を忘れずに))
     */
    public void test_class_letsFix_salesProceedsIncrease() {
        TicketBooth booth = new TicketBooth();
        booth.buyOneDayPassport(10000);
        Integer sea = booth.getSalesProceeds();
        log(sea); // should be same as one-day price, visual check here
        // 7400 になった。ついでにお釣りを返すように修正
    }

    /**
     * Make method for buying two-day passport (price is 13200). (which can return change as method return value)
     * (TwoDayPassport (金額は13200) も買うメソッドを作りましょう (戻り値でお釣りをちゃんと返すように))
     */
    public void test_class_letsFix_makeMethod_twoday() {
        // uncomment after making the method
        TicketBooth booth = new TicketBooth();
        int money = 14000;
        // TODO mayukorin "(booth.buyTwoDayPassport(money))" の括弧、気持ちはわかるけど、やってる人いないので... by jflute (2024/08/16)
        // 一方で、文法上消してもいい括弧でも、人間の誤解を防ぐために明示的に括弧を付けることはよくなるので、それはそれで良い
        int change = (booth.buyTwoDayPassport(money)).getChange();
        Integer sea = booth.getSalesProceeds() + change;
        log(sea); // should be same as money
        // 14000 になった。

        // and show two-day passport quantity here
        int quantity = booth.getTwoDayPassportQuantity();
        log(quantity); // 9 になった。
        
        // [ふぉろー] コピペ作業をできるだけ避けるにせよ、どうしてもしないといけなくなった場合の工夫 by jflute
        // o ハイライト使って修正漏れを発見する習慣
        // o 別のテキストファイルにパッと入れて検索で発見する習慣
        // o そもそも別のテキストファイルで一括置換する技
    }
    
    /**
     * Recycle duplicate logics between one-day and two-day by e.g. private method in class. (And confirm result of both before and after) <br>
     * (OneDayとTwoDayで冗長なロジックがあったら、クラス内のprivateメソッドなどで再利用しましょう (修正前と修正後の実行結果を確認))
     */
    public void test_class_letsFix_refactor_recycle() {
        TicketBooth booth = new TicketBooth();
        booth.buyOneDayPassport(10000);
        log(booth.getOneDayPassportQuantity(), booth.getSalesProceeds()); // should be same as before-fix
        // 修正前：9, 7400
        // 修正後：9, 7400
        // salesProceedsを更新するところだけ、privateメソッドに切り出した
        // quantity を更新するところも切り出そうかなと思ったが、結局切り出し先で、条件分岐で --twoDayPassportQuantity や --oneDayPassportQuantity をしなければならず、
        // 書く量は切り出す前後で変わらず、切り出すメリットがあまりないかなと思ったのでやめた
        // Map に passport の情報をそれぞれ入れて key でアクセスした先の quantity を -1 すれば、
        // --twoDayPassportQuantity や --oneDayPassportQuantity などせずに書く量を少なくできると思ったが、
        // 変更量が多くなってしまうのでやめた。

    }

    // ===================================================================================
    //                                                                           Challenge
    //                                                                           =========
    /**
     * Now you cannot get ticket if you buy one-day passport, so return Ticket class and do in-park. <br>
     * (OneDayPassportを買ってもチケットをもらえませんでした。戻り値でTicketクラスを戻すようにしてインしましょう)
     */
    public void test_class_moreFix_return_ticket() {
        // uncomment out after modifying the method
        TicketBooth booth = new TicketBooth();
        Ticket oneDayPassport = booth.buyOneDayPassport(10000);
        log(oneDayPassport.getDisplayPrice()); // should be same as one-day price
        log(oneDayPassport.isAlreadyIn()); // should be false
        oneDayPassport.doInPark();
        log(oneDayPassport.isAlreadyIn()); // should be true
        // false, true になった
    }

    /**
     * Now also you cannot get ticket if two-day passport, so return class that has ticket and change. <br>
     * (TwoDayPassportもチケットをもらえませんでした。チケットとお釣りを戻すクラスを作って戻すようにしましょう)
     */
    public void test_class_moreFix_return_whole() {
        // uncomment after modifying the method
        TicketBooth booth = new TicketBooth();
        int handedMoney = 20000;
        TicketBuyResult buyResult = booth.buyTwoDayPassport(handedMoney);
        Ticket twoDayPassport = buyResult.getTicket();
        int change = buyResult.getChange();
        log(twoDayPassport.getDisplayPrice() + change); // should be same as money
        // same money になった
        // TODO done jflute Ticket を new する処理を、TicketBuyResult のコンストラクタとbuyTwoDayPassport のどちらにに入れるべきか、ご意見をお聞きしたいです。
        // 私の考え
        // TicketBuyResult の目的： ticket と change をひとまとめに扱いやすくするためのもの（将来、例えば guest クラスができたとき、TicketBuyResult があれば Ticket と change をセットで使える）」という理解をしてます。
        // その目的からすると Ticket を new する処理は TicketBuyResult のコンストラクタに入れるべきではないのかなと思い、buyTwoDayPassport に入れました。
        // [いいね] 素晴らしい。
        // TicketBuyResultはいわゆる「入れ物クラス」言われるもので、あまりこういうクラスには処理を入れないのが慣習。
        // オブジェクト指向としてはデータに振る舞いをってのはあるけど...ResultがTicketを管理してるか？って言ったらそうでもない気がする。
        // Resultは受け渡しで、一時的にチケットを相手に渡すだけのオブジェクトであって、管理してるのはBoothなんじゃないか？って考えもできる。
        // なので、まゆこりんさんの考えを全面的に支持します。
    }

    /**
     * Now you can use only one in spite of two-day passport, so fix Ticket to be able to handle plural days. <br>
     * (TwoDayPassportなのに一回しか利用できません。複数日数に対応できるようにTicketを修正しましょう)
     */
    public void test_class_moreFix_usePluralDays() {
        // your confirmation code here
    }

    /**
     * Accurately determine whether type of bought ticket is two-day passport or not by if-statemet. (fix Ticket classes if needed) <br>
     * (買ったチケットの種別がTwoDayPassportなのかどうかをif文で正確に判定してみましょう。(必要ならTicketクラスたちを修正))
     */
    public void test_class_moreFix_whetherTicketType() {
        // uncomment when you implement this exercise
        //TicketBooth booth = new TicketBooth();
        //Ticket oneDayPassport = booth.buyOneDayPassport(10000);
        //showTicketIfNeeds(oneDayPassport);
        //TicketBuyResult buyResult = booth.buyTwoDayPassport(10000);
        //Ticket twoDayPassport = buyResult.getTicket();
        //showTicketIfNeeds(twoDayPassport);
    }

    // uncomment when you implement this exercise
    //private void showTicketIfNeeds(Ticket ticket) {
    //    if (xxxxxxxxxxxxxxxxxx) { // write determination for two-day passport
    //        log("two-day passport");
    //    } else {
    //        log("other");
    //    }
    //}

    // ===================================================================================
    //                                                                           Good Luck
    //                                                                           =========
    /**
     * Fix it to be able to buy four-day passport (price is 22400). <br>
     * (FourDayPassport (金額は22400) のチケットも買えるようにしましょう)
     */
    public void test_class_moreFix_wonder_four() {
        // your confirmation code here
    }

    /**
     * Fix it to be able to buy night-only two-day passport (price is 7400), which can be used at only night. <br>
     * (NightOnlyTwoDayPassport (金額は7400) のチケットも買えるようにしましょう。夜しか使えないようにしましょう)
     */
    public void test_class_moreFix_wonder_night() {
        // your confirmation code here
    }

    /**
     * Refactor if you want to fix (e.g. is it well-balanced name of method and variable?). <br>
     * (その他、気になるところがあったらリファクタリングしてみましょう (例えば、バランスの良いメソッド名や変数名になっていますか？))
     */
    public void test_class_moreFix_yourRefactoring() {
        // your confirmation code here
    }

    /**
     * Write intelligent comments on source code to the main code in buyticket package. <br>
     * (buyticketパッケージのクラスに、気の利いたコメントを追加してみましょう)
     */
    public void test_class_moreFix_yourSuperComments() {
        // your confirmation code here
    }
}
