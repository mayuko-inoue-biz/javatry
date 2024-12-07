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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.docksidestage.javatry.basic.st8.St8DbFacade;
import org.docksidestage.javatry.basic.st8.St8Member;
import org.docksidestage.javatry.basic.st8.St8Withdrawal;
import org.docksidestage.unit.PlainTestCase;

/**
 * The test of Java8 functions. <br>
 * Operate as javadoc. If it's question style, write your answer before test execution. <br>
 * (javadocの通りに実施。質問形式の場合はテストを実行する前に考えて答えを書いてみましょう)
 * @author jflute
 * @author mayukorin
 */
public class Step08Java8FunctionTest extends PlainTestCase {

    // ===================================================================================
    //                                                                              Lambda
    //                                                                              ======
    // -----------------------------------------------------
    //                                              Callback
    //                                              --------
    /**
     * Are all the strings by log() methods in callback processes same? (yes or no) <br>
     * (コールバック処理の中で出力しているログの文字列はすべて同じでしょうか？ (yes or no))
     */
    public void test_java8_lambda_callback_basic() {
        String title = "over";

        log("...Executing named class callback(!?)");
        helpCallbackConsumer(new St8BasicConsumer(title));

        log("...Executing anonymous class callback");
        helpCallbackConsumer(new Consumer<String>() {
            public void accept(String stage) {
                log(stage + ": " + title);
            }
        });

        log("...Executing lambda block style callback");
        helpCallbackConsumer(stage -> {
            log(stage + ": " + title);
        });

        log("...Executing lambda expression style callback");
        helpCallbackConsumer(stage -> log(stage + ": " + title));

        // your answer? => 同じな気がする。
        // 合ってた

        // cannot reassign because it is used at callback process
        //title = "wave";
    }

    // done jflute 勉強のため、コールバック処理（引数として関数を渡せる）があると何が嬉しいのか知りたいです。by m.inoue (2024/11/27)
    // 自分の予想：処理の流れは同じで1箇所だけ処理の中身を変えたい場合、コールバック処理があればその処理の中身だけ書くだけで良く、面倒さがないのが嬉しい。
    // 仮にコールバック処理がなかったら、その1箇所だけのためにインターフェースを自作してその処理をオーバーライドするクラスを作成する必要がある。
    // [1on1でのフォロー] yesという感じ。(1on1では色々と説明)
    // (構造なしで)1個だけちゃちゃっと差し替える差分プログラミングに最適。

    /**
     * What is order of strings by log(). (write answer as comma-separated) <br>
     * (ログに出力される文字列の順番は？ (カンマ区切りで書き出しましょう))
     */
    public void test_java8_lambda_callback_order() {
        log("harbor");
        helpCallbackConsumer(stage -> {
            log(stage);
        });
        log("lost river");
        // your answer? => harbor, broadway, dockside, hangar, lost river
        // 合ってた
    }

    private class St8BasicConsumer implements Consumer<String> {

        private final String title;

        public St8BasicConsumer(String title) {
            this.title = title;
        }

        @Override
        public void accept(String stage) {
            log(stage + ": " + title);
        }
    }

    private void helpCallbackConsumer(Consumer<String> oneArgLambda) {
        log("broadway");
        oneArgLambda.accept("dockside");
        log("hangar");
    }

    /**
     * What string is sea variable at the method end? <br>
     * (メソッド終了時の変数 sea の中身は？)
     */
    public void test_java8_lambda_callback_valueRoad() {
        String label = "number";
        String sea = helpCallbackFunction(number -> {
            return label + ": " + number;
        });
        log(sea); // your answer? => number: 7
        // 合ってた
    }

    private String helpCallbackFunction(Function<Integer, String> oneArgLambda) {
        return oneArgLambda.apply(7);
    }

    // -----------------------------------------------------
    //                                         Convert Style
    //                                         -------------
    /**
     * Change callback style like this:
     * <pre>
     * o sea: to lambda block style
     * o land: to lambda expression style
     * o piari: to lambda block style
     * </pre>
     * (このようにコールバックスタイルを変えてみましょう:)
     * <pre>
     * o sea: BlockのLambda式に
     * o land: ExpressionのLambda式に
     * o piari: BlockのLambda式に
     * </pre>
     */
    public void test_java8_lambda_convertStyle_basic() {
        helpCallbackSupplier(() -> {
            return "broadway";
        });

        helpCallbackSupplier(() -> "dockside");

        helpCallbackSupplier(() -> {
            return "lost river";
        });
        // [1on1でのフォロー] ソースコードの見やすさを調整する一つの道具になる話
        // TODO mayukorin 宿題: IntelliJでblock/expressionを切り替えるショートカットは？ by jflute (2024/12/02)
        // 調査中です！なさそう？
    }

    private void helpCallbackSupplier(Supplier<String> oneArgLambda) {
        String supplied = oneArgLambda.get();
        log(supplied);
    }

    // ===================================================================================
    //                                                                            Optional
    //                                                                            ========
    /**
     * Are the strings by two log() methods same? (yes or no) <br>
     * (二つのlog()によって出力される文字列は同じでしょうか？ (yes or no))
     */
    public void test_java8_optional_concept() {
        St8Member oldmember = new St8DbFacade().oldselectMember(1);
        if (oldmember != null) {
            log(oldmember.getMemberId(), oldmember.getMemberName());
        }
        Optional<St8Member> optMember = new St8DbFacade().selectMember(1);
        if (optMember.isPresent()) {
            St8Member member = optMember.get();
            log(member.getMemberId(), member.getMemberName());
        }
        // your answer? => yes
        // 合ってた
        // 上と下で呼び出してるSt8DbFacadeメソッドは同じで、違いはメソッドの返り値にOptional.ofNullableを適用してるかどうか
        // Optional.ofNullableは、中身がnullだったらOptionalを返してnullじゃなかったらその値を返すらしい
        // 今回中身はnull
    }

    /**
     * Are the strings by two log() methods same? (yes or no) <br>
     * (二つのlog()によって出力される文字列は同じでしょうか？ (yes or no))
     */
    public void test_java8_optional_ifPresent() {
        Optional<St8Member> optMember = new St8DbFacade().selectMember(1);
        if (optMember.isPresent()) {
            St8Member member = optMember.get();
            log(member.getMemberId(), member.getMemberName());
        }
        optMember.ifPresent(member -> {
            log(member.getMemberId(), member.getMemberName());
        });
        // your answer? => yes
        // 合ってた
        // 下は、callbackで書いている
        // callbackの引数は、Optionalのvalue=optMember
    }

    /**
     * What string is sea, land, piari, bonvo, dstore, amba variables at the method end? <br>
     * (メソッド終了時の変数 sea, land, piari, bonvo, dstore, amba の中身は？)
     */
    public void test_java8_optional_map_flatMap() {
        St8DbFacade facade = new St8DbFacade();

        // traditional style
        St8Member oldmemberFirst = facade.oldselectMember(1);
        String sea;
        if (oldmemberFirst != null) {
            St8Withdrawal withdrawal = oldmemberFirst.oldgetWithdrawal();
            if (withdrawal != null) {
                sea = withdrawal.oldgetPrimaryReason();
                if (sea == null) {
                    sea = "*no reason1: the PrimaryReason was null";
                }
            } else {
                sea = "*no reason2: the Withdrawal was null";
            }
        } else {
            sea = "*no reason3: the selected Member was null";
        }

        Optional<St8Member> optMemberFirst = facade.selectMember(1);

        // map style
        String land = optMemberFirst.map(mb -> mb.oldgetWithdrawal())
                .map(wdl -> wdl.oldgetPrimaryReason())
                .orElse("*no reason: someone was not present");

        // flatMap style
        String piari = optMemberFirst.flatMap(mb -> mb.getWithdrawal())
                .flatMap(wdl -> wdl.getPrimaryReason())
                .orElse("*no reason: someone was not present");

        // flatMap and map style
        String bonvo = optMemberFirst.flatMap(mb -> mb.getWithdrawal())
                .map(wdl -> wdl.oldgetPrimaryReason())
                .orElse("*no reason: someone was not present");

        String dstore = facade.selectMember(2)
                .flatMap(mb -> mb.getWithdrawal())
                .map(wdl -> wdl.oldgetPrimaryReason())
                .orElse("*no reason: someone was not present");

        String amba = facade.selectMember(3)
                .flatMap(mb -> mb.getWithdrawal())
                .flatMap(wdl -> wdl.getPrimaryReason())
                .orElse("*no reason: someone was not present");

        int defaultWithdrawalId = -1;
        Integer miraco = facade.selectMember(2)
                .flatMap(mb -> mb.getWithdrawal())
                .map(wdl -> wdl.getWithdrawalId()) // ID here
                .orElse(defaultWithdrawalId);

        log(sea); // your answer? => music
        // traditional style：oldmemberFirst のフィールドをどんどん深ぼってnullだったらseaに代入する的な処理をif文でネストでやってる。
        log(land); // your answer? => music
        // map style：callback関数を実行してその戻り値をOptionalに包んで返してくれる
        log(piari); // your answer? => music
        // flatMap style：map との違いは、Optionalを返すcallbackを指定すること。Optionalでcallbackが返すので、flashMap自体はOptionalで包んだりしないっぽい
        log(bonvo); // your answer? => music
        log(dstore); // your answer? => *no reason: someone was not present
        // map(wdl -> wdl.oldgetPrimaryReason()) では、primaryReasonがnullなため、Empty な Optional が返される
        log(amba); // your answer? => *no reason: someone was not present
        // mb.getWithdrawal()では、withdrawlがnullなため、Empty な Optional が返される
        // flatMap(wdl -> wdl.getPrimaryReason())でも、Empty な Optional が返される (JavaDoc に value (Optional<T>のT型のvalue)がなかった場合、Empty な Optional が返されると書いてあるため)
        // orElse が適用されて *no reason: someone was not present
        log(miraco); // your answer? => 12
        // あってた
    }

    /**
     * What string is sea variables at the method end? <br>
     * (メソッド終了時の変数 sea の中身は？)
     */
    public void test_java8_optional_orElseThrow() {
        Optional<St8Member> optMember = new St8DbFacade().selectMember(2);
        St8Member member = optMember.orElseThrow(() -> new IllegalStateException("over"));
        String sea = "the";
        try {
            String reason = member.getWithdrawal().map(wdl -> wdl.oldgetPrimaryReason()).orElseThrow(() -> {
                return new IllegalStateException("wave");
            });
            sea = reason;
        } catch (IllegalStateException e) {
            sea = e.getMessage();
        }
        log(sea); // your answer? => wave
        // あってた
    }

    // ===================================================================================
    //                                                                          Stream API
    //                                                                          ==========
    /**
     * What string is sea, land variables at the method end? <br>
     * (メソッド終了時の変数 sea, land の中身は？)
     */
    public void test_java8_stream_concept() {
        List<St8Member> memberList = new St8DbFacade().selectMemberListAll();
        List<String> oldfilteredNameList = new ArrayList<>();
        for (St8Member member : memberList) {
            if (member.getWithdrawal().isPresent()) {
                oldfilteredNameList.add(member.getMemberName());
            }
        }
        String sea = oldfilteredNameList.toString();
        log(sea); // your answer? => broadway, dockerside
        // 答え：[broadway, dockside] []がつく

        List<String> filteredNameList = memberList.stream() //
                .filter(mb -> mb.getWithdrawal().isPresent()) //
                .map(mb -> mb.getMemberName()) //
                .collect(Collectors.toList());
        String land = filteredNameList.toString();
        log(land); // your answer? => broadway, dockerside
        // 答え：[broadway, dockside] []がつく
    }

    /**
     * What string is sea, variables at the method end? <br>
     * (メソッド終了時の変数 sea の中身は？)
     */
    public void test_java8_stream_map_flatMap() {
        List<St8Member> memberList = new St8DbFacade().selectMemberListAll();
        int sea = memberList.stream()
                .filter(mb -> mb.getWithdrawal().isPresent())
                .flatMap(mb -> mb.getPurchaseList().stream())
                .filter(pur -> pur.getPurchaseId() > 100)
                .mapToInt(pur -> pur.getPurchasePrice())
                .distinct()
                .sum();
        log(sea); // your answer? => 
    }

    // *Stream API will return at Step12 again, it's worth the wait!
}
