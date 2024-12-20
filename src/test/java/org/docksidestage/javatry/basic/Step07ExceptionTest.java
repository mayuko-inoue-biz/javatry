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

import java.io.File;
import java.io.IOException;

import org.docksidestage.bizfw.basic.supercar.SupercarClient;
import org.docksidestage.javatry.basic.st7.St7BasicExceptionThrower;
import org.docksidestage.javatry.basic.st7.St7ConstructorChallengeException;
import org.docksidestage.unit.PlainTestCase;

// done mayukorin [読み物課題] 良かったことを続けるために、良かったことを見失わないこと by jflute (2024/11/11)
// https://jflute.hatenadiary.jp/entry/20170826/keepgoodtime
// 1on1でのKPTの話から。自分の良かったところ、を分析してあげてくださいという話。
/**
 * The test of variable. <br>
 * Operate as javadoc. If it's question style, write your answer before test execution. <br>
 * (javadocの通りに実施。質問形式の場合はテストを実行する前に考えて答えを書いてみましょう)
 * @author jflute
 * @author mayukorin
 */
public class Step07ExceptionTest extends PlainTestCase {

    // ===================================================================================
    //                                                                               Basic
    //                                                                               =====
    /**
     * What string is sea variable at the method end? <br>
     * (メソッド終了時の変数 sea の中身は？)
     */
    public void test_exception_basic_catchfinally() {
        St7BasicExceptionThrower thrower = new St7BasicExceptionThrower();
        StringBuilder sea = new StringBuilder();
        try {
            thrower.land();
            sea.append("dockside");
        } catch (IllegalStateException e) {
            sea.append("hangar");
        } finally {
            sea.append("broadway");
        }
        log(sea); // your answer? = > hangarbroadway
        // thrower.land() で投げられた IllegalStateException を catch するから & 最後に finally 実行されるから
        // あってた
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_exception_basic_message() {
        St7BasicExceptionThrower thrower = new St7BasicExceptionThrower();
        String sea = null;
        try {
            thrower.land();
            fail("no way here");
        } catch (IllegalStateException e) {
            sea = e.getMessage();
        }
        log(sea); // your answer? => oneman at showbase
        // thrower.land() で投げられた IllegalStateException の message を出力するから
        // あってた
    }

    /**
     * What class name and method name and row number cause the exception? (you can execute and watch logs) <br>
     * (例外が発生したクラス名とメソッド名と行番号は？(実行してログを見て良い))
     */
    public void test_exception_basic_stacktrace() {
        St7BasicExceptionThrower thrower = new St7BasicExceptionThrower();
        try {
            thrower.land();
            fail("no way here");
        } catch (IllegalStateException e) {
            log(e);
        }
        // your answer? =>
        // クラス名：St7BasicExceptionThrower
        // メソッド名：oneman
        // 行番号：40
        
        // [1on1でのふぉろー] まずは、例外スタックトレースの読み方を知ることが大切。
        // 読み方を知っていればだんだん読み慣れていって、読もうという気になる。
        // done mayukorin [読み物課題] エラーメッセージ読め読め大合唱 by jflute (2024/11/11)
        // https://jflute.hatenadiary.jp/entry/20130522/errorsinging
    }

    // ===================================================================================
    //                                                                           Hierarchy
    //                                                                           =========
    /**
     * What string is sea variable at the method end? <br>
     * (メソッド終了時の変数 sea の中身は？)
     */
    public void test_exception_hierarchy_Runtime_instanceof_RuntimeException() {
        Object exp = new IllegalStateException("mystic");
        boolean sea = exp instanceof RuntimeException;
        log(sea); // your answer? => true
        // コードを見ると、IllegalStateException は RuntimeException を継承しているらしい
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_exception_hierarchy_Runtime_instanceof_Exception() {
        Object exp = new IllegalStateException("mystic");
        boolean sea = exp instanceof Exception;
        log(sea); // your answer? => true
        // コードを見ると、IllegalStateException は RuntimeException, その先の Exception を継承しているらしい
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_exception_hierarchy_Runtime_instanceof_Error() {
        Object exp = new IllegalStateException("mystic");
        boolean sea = exp instanceof Error;
        log(sea); // your answer? => false
        // コードを見ると、Exception は Error を継承しているわけではない。
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_exception_hierarchy_Runtime_instanceof_Throwable() {
        Object exp = new IllegalStateException("mystic");
        boolean sea = exp instanceof Throwable;
        log(sea); // your answer? => true
        // コードを見ると、Exception は Throwable を継承している。
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_exception_hierarchy_Throwable_instanceof_Exception() {
        Object exp = new Throwable("mystic");
        boolean sea = exp instanceof Exception;
        log(sea); // your answer? => false
        // コードを見ると、Exception は Throwable を継承している関係なので、これは False
    }
    
    // [1on1でのふぉろー] エラーと例外の違いについて話をした by jflute (2024/11/11)
    //
    // Java - Exception (例外) | DBFlute
    // https://dbflute.seasar.org/ja/manual/topic/programming/java/exception.html
    //
    // その中断イベントが発生した瞬間は「正常なレアケース(例外)」なのか「システム上のエラー」なのかわからない。
    // なのでthrowされるのは大抵は "例外" (Exception) というニュアンスのもの。
    // catchする人が(システムとしての)エラーなのか？業務的なレアケースなのか？を判断する。
    //
    // ただ、とても微妙というか絶妙なコンセプトなので、ぼくらは会話の中であまりエラーと例外を区別しない。
    // エラーメッセージと呼んだり、例外メッセージと呼んだり。
    // (Javaの実装を意識している文脈のときはできるだけ例外メッセージと呼ぶかな!?)

    // done jflute 次回1on1ここから (2024/11/11)
    // ===================================================================================
    //                                                                         NullPointer
    //                                                                         ===========
    /**
     * What variable (is null) causes the NullPointerException? And what row number? (you can execute and watch logs) <br>
     * (NullPointerが発生する変数(nullだった変数)と、発生する行番号は？(実行してログを見ても良い))
     */
    public void test_exception_nullpointer_basic() {
        try {
            String sea = "mystic";
            String land = sea.equals("mystic") ? null : "oneman";
            String lowerCase = land.toLowerCase();
            log(lowerCase);
        } catch (NullPointerException e) {
            log(e);
        }
        // your answer? => land, 145行目(land.toLowerCase())
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_exception_nullpointer_headache() {
        try {
            String sea = "mystic";
            String land = !!!sea.equals("mystic") ? null : "oneman";
            String piari = !!!sea.equals("mystic") ? "plaza" : null;
            int sum = land.length() + piari.length();
            log(sum);
        } catch (NullPointerException e) {
            log(e);
        }
        // your answer? => piari, 159行目(piari.length())
    }

    /**
     * Refactor to immediately understand what variable (is null) causes the NullPointerException by row number in stack trace. <br>
     * (どの変数がNullPointerを引き起こしたのか(nullだったのか)、スタックトレースの行番号だけでわかるようにリファクタリングしましょう)
     */
    public void test_exception_nullpointer_refactorCode() {
        try {
            String sea = "mystic";
            String land = !!!sea.equals("mystic") ? null : "oneman";
            String piari = !!!sea.equals("mystic") ? "plaza" : null;
            int landLength = land.length();
            int piariLength = piari.length();
            int sum = landLength + piariLength;
            log(sum);
        } catch (NullPointerException e) {
            log(e);
        }
        // nullPointerExceptionになり得るメソッドを個別に1行ずつ実行するようにした
        // [1on1でのふぉろー] 実際に切り出すかは、個人的には7,8割くらいな感覚。
        // 可能性ありそうだなと思ったらときにやるくらいな感じ。
        // 一方で、Java17 (!?) くらいからは、NullPointerの例外メッセージで教えてくれる。
        // ただ、どのみち1行になんこも処理を入れまくるのは良いことでもないので、切り出す習慣自体は良い。
        // 
        // 一方で一方で、方法論を覚えてるだけだと、実践で(思い付かなくて)発揮できないこともよくある。
        // その方法論が習慣付いてないと、とっさに思い付かなかったりするので、反復練習が大事。
    }

    // ===================================================================================
    //                                                                   Checked Exception
    //                                                                   =================
    /**
     * Show canonical path of new java.io.File(".") by log(), and if I/O error, show message and stack-trace instead <br>
     * (new java.io.File(".") の canonical path を取得してログに表示、I/Oエラーの時はメッセージとスタックトレースを代わりに表示)
     */
    public void test_exception_checkedException_basic() {
        try {
            File file = new File(".");
            String canonicalPath = file.getCanonicalPath();
            log(canonicalPath);
        } catch (IOException | SecurityException e) {
            log(e);
        }
        // ドキュメント（以下）をみたところ、getCanonicalPath() は、IOExceptionだけではなくSecurityExceptionも投げるようなので、どちらもキャッチするようにした
        // https://docs.oracle.com/javase/jp/8/docs/api/java/io/File.html#getCanonicalPath--
        // しかし、getCanonicalPath()でthrowするのはIOExceptionのみ
        // getCanonicalPath()でthrowしたいExceptionとそうでないExceptionの違いは何なのか、後で考えてみたい
        
        // チェック例外 (Checked Exception) と言われるもの。
        // 普段(!?)の例外が非チェック例外と言える。
        // チェック例外は例外ハンドリングをコンパイルセーフにさせる理想的な文法でありつつ...流行ってない。
        // 流行ってない理由を一緒に考察。(でも、知っておかないと、いざってときに対処できないので一度経験しておく)
        // 文法的なところでは、RuntimeExceptionとRuntimeExceptionを継承した例外は非チェック例外。
        // (実は、全体のクラス構造で言うと、チェック例外が全体的で、非チェック例外が局所的だったりする)
    }

    // ===================================================================================
    //                                                                               Cause
    //                                                                               =====
    /**
     * What string is sea variable in the catch block?
     * And What is exception class name displayed at the last "Caused By:" of stack trace? <br>
     * (catchブロックの変数 sea, land の中身は？また、スタックトレースの最後の "Caused By:" で表示されている例外クラス名は？)
     */
    public void test_exception_cause_basic() {
        String sea = "mystic";
        String land = "oneman";
        try {
            throwCauseFirstLevel();
            fail("always exception but none");
        } catch (IllegalStateException e) {
            Throwable cause = e.getCause();
            sea = cause.getMessage();
            land = cause.getClass().getSimpleName();
            log(sea); // your answer? =>
            // Failed to call the second help method: symbol=1
            // throwCauseFirstLevel()でのnew IllegalStateExceptionの第一引数がmessageらしいからそれが表示される?
            // 答え：Failed to call the third help method: symbol=-1
            // e.getCause() の Cause は、そのExceptionが発生した原因的なイメージ？
            // Exception new 時に第2引数に指定する Exceptionみたい
            log(land); // your answer? => Integer?
            // 答え：classはExceptionのクラス
            // IllegalArgumentException
            log(e); // your answer? => 分からない
            // 答え：一連のExceptionが全て出力されてた
            // at をよくみてみると、
            // at Exception がNewされた場所
            // at そのメソッドの呼び出し元..
            // みたいな感じで出力されてるんだ
        }
    }

    private void throwCauseFirstLevel() {
        int symbol = Integer.MAX_VALUE - 0x7ffffffe;
        try {
            throwCauseSecondLevel(symbol);
        } catch (IllegalArgumentException e) {
            throw new IllegalStateException("Failed to call the second help method: symbol=" + symbol, e);
        }
    }

    private void throwCauseSecondLevel(int symbol) {
        try {
            --symbol;
            symbol--;
            if (symbol < 0) {
                throwCauseThirdLevel(symbol);
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Failed to call the third help method: symbol=" + symbol, e);
        }
    }

    private void throwCauseThirdLevel(int symbol) {
        if (symbol < 0) {
            Integer.valueOf("piari");
        }
    }
    
    // [1on1でのフォロー] 例外が例外を保持することができる構造になっている。
    // なぜ？ (すべての機能や構造には意味があるからそうなっているはず)
    // done jflute 次回1on1, なぜを聞く (できると何が嬉しいのか？できないと何で困るのか？) (2024/11/18)
    // 1on1にて例外の翻訳の話した。 (2024/11/25)
    //
    // 1つのエラー事象に付き、複数のエラーメッセージがある。レイヤーごとの言い分がある。
    // https://dbflute.seasar.org/ja/manual/topic/programming/java/exception.html
    //
    // catchすべき例外を見つけ出すコツ:
    // o テストの期待値の洗い出しの経験をたくさん積む
    // o 呼び出してるメソッドがなんの例外をthrowするのか？の把握/意識を少しでも
    //

    // ===================================================================================
    //                                                                         Translation
    //                                                                         ===========
    /**
     * Execute this test and read exception message and write situation and cause on comment for non-programmer. <br>
     * テストを実行して例外メッセージを読んで、プログラマーでない人にもわかるように状況と原因をコメント上に書きましょう。
     */
    public void test_exception_translation_debugChallenge() {
        try {
            new SupercarClient().buySupercar();
            fail("always exception but none");
        } catch (RuntimeException e) {
            log("*No hint here for training.", e);
            // _/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
            // What happens? Write situation and cause here. (何が起きた？状況と原因をここに書いてみましょう)
            // - - - - - - - - - -
            // 前提：
            // SuperCarの工場では、注文を受けてから車を製造するようになっている。
            // 車を製造する過程で、ネジを作る工程がある
            // 作るネジは、注文によって変わる
            // 状況：
            // ネジを作ことができないため、注文を受けても車を製造できない。
            // ネジを作ることができない原因：
            // 注文を受けて、kawaii faceという規格のネジを作っていたが、その規格がサポートされなくなったため
            // _/_/_/_/_/_/_/_/_/_/
        }
    }

    /**
     * Improve exception handling in supercar's classes to understand the situation
     * by only exception information as far as possible. <br>
     * できるだけ例外情報だけでその状況が理解できるように、Supercarのクラスたちの例外ハンドリングを改善しましょう。
     */
    public void test_exception_translation_improveChallenge() {
        try {
            new SupercarClient().buySupercar(); // you can fix the classes
            fail("always exception but none");
        } catch (RuntimeException e) {
            log("*No hint here for training.", e);
            // Supercarを作るための各層のクラスでそれぞれ例外を投げてみた
        }
        // [1on1でのふぉろー] DBFluteの例外メッセージをちょろっと見てみた。
    }

    // ===================================================================================
    //                                                                           Challenge
    //                                                                           =========
    /**
     * Fix terrible (YABAI in Japanese) exception handling. (you can modify exception class) <br>
     * (やばい例外ハンドリングがあるので修正しましょう (例外クラスを修正してOK))
     */
    public void test_exception_writing_constructorChallenge() {
        try {
            helpSurprisedYabaiCatch();
        } catch (St7ConstructorChallengeException e) {
            log("Thrown by help method", e); // should show also "Caused-by" information
        }
    }

    private void helpSurprisedYabaiCatch() {
        try {
            helpThrowIllegalState();
        } catch (IllegalStateException e) {
            // [1on1でのふぉろー] good
            // done mayukorin 例外クラスにauthorをお願いします by jflute (2024/12/02)
            throw new St7ConstructorChallengeException("Failed to do something.", e);
        }
    }

    private void helpThrowIllegalState() {
        if (true) { // simulate something illegal
            String importantValue = "dummy"; // important to debug
            throw new IllegalStateException("something illegal: importantValue=" + importantValue);
        }
    }

    // ===================================================================================
    //                                                                           Challenge
    //                                                                           =========
    /**
     * What is the concept difference between Exception and Error? Write it on comment. <br>
     * (ExceptionとErrorのコンセプトの違いはなんでしょうか？コメント上に書きましょう)
     */
    public void test_exception_zone_differenceExceptionError() {
        // _/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
        // Write here. (ここに書いてみましょう)
        // - - - - - - - - - -
        //
        //
        //
        // _/_/_/_/_/_/_/_/_/_/
        // 1on1でのふぉろーで話をしたのでここはOK
    }
}
