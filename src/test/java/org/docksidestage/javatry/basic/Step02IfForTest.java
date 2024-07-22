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
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;

import org.docksidestage.unit.PlainTestCase;

/**
 * The test of if-for. <br>
 * Operate exercise as javadoc. If it's question style, write your answer before test execution. <br>
 * (javadocの通りにエクササイズを実施。質問形式の場合はテストを実行する前に考えて答えを書いてみましょう)
 * @author jflute
 * @author mayukorin
 */
public class Step02IfForTest extends PlainTestCase {

    // ===================================================================================
    //                                                                        if Statement
    //                                                                        ============
    /**
     * What string is sea variable at the method end? <br>
     * (メソッド終了時の変数 sea の中身は？)
     */
    public void test_if_basic() { // example, so begin from the next method
        int sea = 904;
        if (sea >= 904) {
            sea = 2001;
        }
        log(sea); // your answer? => 2001
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_if_else_basic() {
        int sea = 904;
        if (sea > 904) {
            sea = 2001;
        } else {
            sea = 7;
        }
        log(sea); // your answer? => 7（理由：904以下の場合は、elseに行くため。
        // 答え：7
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_if_elseif_basic() {
        int sea = 904;
        if (sea > 904) {
            sea = 2001;
        } else if (sea >= 904) {
            sea = 7;
        } else if (sea >= 903) {
            sea = 8;
        } else {
            sea = 9;
        }
        log(sea); // your answer? => 7（理由：sea > 904 は、904 より大なのでひっかからないが、次の sea >= 904 で 904 が該当するため）
        // 答え：7
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_if_elseif_nested() {
        boolean land = false;
        int sea = 904;
        if (sea > 904) {
            sea = 2001;
        } else if (land && sea >= 904) {
            sea = 7;
        } else if (sea >= 903 || land) {
            sea = 8;
            if (!land) {
                land = true;
            } else if (sea <= 903) {
                sea++;
            }
        } else if (sea == 8) {
            sea++;
            land = false;
        } else {
            sea = 9;
        }
        if (sea >= 9 || (sea > 7 && sea < 9)) {
            sea--;
        }
        if (land) {
            sea = 10;
        }
        log(sea); // your answer? => 10（理由：sea >= 903 || land で、sea >= 903 が該当 & || (or) なのでそこで中に入り sea = 8, land = true となる。そして、land = true なので、sea = 10 になる. sea = 10 と代入しているので、96 行目は見なくても良い）
        // 答え：10
        // done mayukorin [いいね] "96 行目は見なくても良い" ってのは素晴らしい、そういう読み方大切です by jflute (2024/07/13)
        // done jflute 1on1にてコードリーディングのコツ補足 (2024/07/13)
        // まず漠然読みで全体構造を把握、すると当たりが見えてくる、そこから逆読みとかピンポイント読み
    }

    // ===================================================================================
    //                                                                       for Statement
    //                                                                       =============
    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_for_inti_basic() {
        List<String> stageList = prepareStageList();
        String sea = null;
        for (int i = 0; i < stageList.size(); i++) {
            String stage = stageList.get(i);
            if (i == 1) {
                sea = stage;
            }
        }
        log(sea); // your answer? => dockside (理由：if (i==1) で stageList の 2 番目の要素が sea に入る && それ以降は if (i==1) に該当しないので、sea が更新されない)
        // 答え：dockside
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_for_foreach_basic() {
        List<String> stageList = prepareStageList();
        String sea = null;
        for (String stage : stageList) {
            sea = stage;
        }
        log(sea); // your answer? => magiclamp（理由：for で stageList の各要素が順番に取り出されるごとに、 sea がその要素で代入されるから）
        // 答え：magiclamp
        // for (String stage : stageList) みたいな for の回し方、あったなー。名前までは知らない。
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_for_foreach_continueBreak() {
        List<String> stageList = prepareStageList();
        String sea = null;
        for (String stage : stageList) {
            if (stage.startsWith("br")) {
                continue;
            }
            stageList = null;
            sea = stage;
            if (stage.contains("ga")) {
                break;
            }
        }
        log(sea); // your answer? => hangar（理由：if (stage.contains("ga")) で break を見て、ga を含む hangar (= stage) を見てるときに break で for から抜けることが分かる。その上を見ると、sea = stage なので、sea = stage = hanger であることがわかる）
        // hangar
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_for_listforeach_basic() {
        List<String> stageList = prepareStageList();
        StringBuilder sb = new StringBuilder();
        stageList.forEach(stage -> {
            if (sb.length() > 0) {
                return;
            }
            if (stage.contains("i")) {
                sb.append(stage);
            }
        });
        String sea = sb.toString();
        log(sea); // your answer? => docksidemagiclamp
        // 自信ない
        // 今回の forEach 内の 「return」 は、for文でいう continue を表しているのかな？
        // （理由：この前の mutable なのに return する件のように、メソッドチェーンでforEachのそれぞれを後続の処理に回したいケースがある気がする。
        // return は、メソッドチェーンで後続の処理にその値を送る役割があるのではないか？
        // そのため、今回のような後続の処理がない場合の return は、for文でいう continue 的なことを表しているのでは？
        // 答え：dockside
        // forEach を javaDoc で見たが、引数の Consumer<? super T> の Consumer からわからないので調べる。
        // Consumer.java を見ると、interface らしい。void accept(T t) がメソッドとして登録されている。
        // Consumer を試しに使ってみた（下の test_use_of_consumer method）。
        // accept(T t) の具体的な実装の中身が、Consumer<String> c = (s) -> log(s); の右辺（(s) -> log(s)）。
        // forEach は、accept(T t) method を繰り返している。
        // accept method の具体的な中身が、stage -> { ... 以下。
        // そのため、上の 159行目の return; は、accept method 内で return してるだけで、後続の処理に値を送ったりはしていない。
        // 1番目の broadway：sb.length() > 0 も stage.contains("i") も当てはまらないのでスルー。
        // 2番目の dockside：sb.length() > 0 は当てはまらないが、stage.contains("i") で当てはまるので、sb に dockside append.
        // 3, 4 番目：sb.length() > 0 が当てはまるので return
        // 答え：dockside
        // done mayukorin [いいね] forEach()のコードまで読んでいるのは素晴らしい by jflute (2024/07/13)
        // done jflute 1on1にてコールバックの話を少し含めてforとforEach()の違いを (2024/07/13)
        // forEach()は(極端な言い方をすると)単なるメソッド
        // 機能が少ないほうが安全で使いやすいという考え方もある
    }

    interface Runnable {
        void run();
    }

    // done mayukorin [いいね] すごい！もうコールバックのお試しを既にしている。こういう風にお試し実装とても良いです by jflute (2024/07/13)
    public void test_local_class() {

        Runnable lc = new Runnable() {
            @Override
            public void run() {
                log("running!!");
            }
        };
        lc.run();
    }

    public void test_use_of_consumer() {
        Consumer<String> c = (s) -> log(s);
        c.accept("Hello");
    }

    // ===================================================================================
    //                                                                           Challenge
    //                                                                           =========
    /**
     * Make list containing "a" from list of prepareStageList() and show it as log by loop. (without Stream API) <br>
     * (prepareStageList()のリストから "a" が含まれているものだけのリストを作成して、それをループで回してログに表示しましょう。(Stream APIなしで))
     */
    public void test_iffor_making() {
        // write if-for here
        List<String> stageList = prepareStageList();
        List<String> onlyAList = new ArrayList<>();
        for (String stage : stageList) {
            if (stage.contains("a")) {
                onlyAList.add(stage);
            }
        }
        for (String aStage : onlyAList) {
            log(aStage);
        }
    }

    // ===================================================================================
    //                                                                           Good Luck
    //                                                                           =========
    /**
     * Change foreach statement to List's forEach() (keep result after fix) <br>
     * (foreach文をforEach()メソッドへの置き換えてみましょう (修正前と修正後で実行結果が同じになるように))
     */
    public void test_iffor_refactor_foreach_to_forEach() {
        List<String> stageList = prepareStageList();
        StringBuilder sea = new StringBuilder();
        stageList.forEach(stage -> {
            if (stage.startsWith("br")) {
                return;
            }
            if (sea.indexOf("ga") != -1) {
                return;
            }
            sea.replace(0, sea.length(), stage);
        });
        log(sea.toString());
//        List<String> stageList = prepareStageList();
//        String sea = null;
//        for (String stage : stageList) {
//            if (stage.startsWith("br")) {
//                continue;
//            }
//            sea = stage;
//            if (stage.contains("ga")) {
//                break;
//            }
//        }
        // log(sea); // should be same as before-fix
        // 一応同じ hangar にはなった。
        // ただ、AtomicReference<String> sea や AtomicBoolean isGAComing は IntelliJ が自動生成してくれたもの
        // String 型の sea で、forEach 内で sea = stage; をしたり、Boolean 型の isGAComing で isGAComing = false; とすると、
        // 「Variable used in lambda expression should be final or effectively final」というエラーが出た。
        // 「Convert to atomic」というボタンをクリックすると、AtomicReference<String> sea や AtomicBoolean isGAComing が自動生成された。
        // forEach 内で、forEach 外の変数の値が変わるような処理をするのはダメらしい（例：String 型の sea で、forEach 内で sea = stage; をすると、sea に格納されてる変数のアドレス値が変わる）。
        // 確かに、forEach はインスタンスメソッドで、インスタンスメソッドが引数なしでクラス外の変数を更新できるのは、変数管理の点で微妙そう。
        // TODO mayukorin 「Convert to atomic」の atomic が何を表しているのかよく分からないので、後で調べる。
        // ※ isGAComing で ga が含まれている単語が登場したかどうかを判定している意図：forEach で、 for 文中の 「stage.contains("ga") だったら break する」のと同等の処理を行うため。
        // TODO mayukorin [ははは] AtomicBooleanが出てきてビックリした(^^。すごいの使ってくるねっと by jflute (2024/07/19)
        // TODO jflute 1on1にて変わっちゃうmutableのフォロー (2024/07/19)
        // TODO done mayukorin 修行++: Atomicなしでやってみましょう。今までjavatryで見たことのあるクラスで代用できます by jflute (2024/07/19)
        // hanger になった （2024/07/22）。
        // 「sea が何になるのか知りたい」という目的で元のコードを読んでみると、sea = stage の後に、stage.contains("ga") だったら break しているので、最終的な sea は stageList の要素の中で "ga" が含まれる一番最初の要素になるはず。
        // それを forEach で表現すると、sea.indexOf("ga") != -1 だったら return 出いけるはず。
        // また、mutable な sea は forEach では使えることを知った。
        // sea.replace すると、アドレス先の文字列が mutable でそのまま変更されるが、sea に格納されたアドレス自体は変わらない = forEach 的にはローカル変数 sea は変わっていないので OK という扱いになるのだと思われる。
    }

    /**
     * Make your original exercise as question style about if-for statement. <br>
     * (if文for文についてあなたのオリジナルの質問形式のエクササイズを作ってみましょう)
     * <pre>
     * _/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
     * your question here (ここにあなたの質問を):
     * 
     * _/_/_/_/_/_/_/_/_/_/
     * </pre>
     */
    public void test_iffor_yourExercise() {
        // write your code here
    }

    // ===================================================================================
    //                                                                        Small Helper
    //                                                                        ============
    private List<String> prepareStageList() {
        List<String> stageList = new ArrayList<>();
        stageList.add("broadway");
        stageList.add("dockside");
        stageList.add("hangar");
        stageList.add("magiclamp");
        return stageList;
    }
}
