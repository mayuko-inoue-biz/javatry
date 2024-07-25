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

import org.docksidestage.unit.PlainTestCase;

/**
 * The test of method. <br>
 * Operate exercise as javadoc. If it's question style, write your answer before test execution. <br>
 * (javadocの通りにエクササイズを実施。質問形式の場合はテストを実行する前に考えて答えを書いてみましょう)
 * @author jflute
 * @author your_name_here
 */
public class Step04MethodTest extends PlainTestCase {

    // ===================================================================================
    //                                                                         Method Call
    //                                                                         ===========
    /**
     * What string is sea variable at the method end? <br>
     * (メソッド終了時の変数 sea の中身は？)
     */
    public void test_method_call_basic() {
        String sea = supplySomething();
        log(sea); // your answer? => over（2024/07/25）
        // sea は supplySomething() の戻り値(= 関数内の sea 変数) & log("in supply: {}", sea); しても sea の中身は変わらない
        // 答え：over
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_method_call_many() {
        String sea = functionSomething("mystic");
        consumeSomething(supplySomething());
        runnableSomething();
        log(sea); // your answer? => mysmys（2024/07/25）
        // sea は functionSomething() の戻り値(= 関数内の replaced 変数) & consumeSomething・runnableSomething の引数に sea は含まれていないので、consumeSomething 以降で sea の中身は変わらない
        // replace 関数は immutable なので、mystic の tic を mys に置き換えた mysmys が返される。
        // 答え：mysmys
    }

    private String functionSomething(String name) {
        String replaced = name.replace("tic", "mys");
        log("in function: {}", replaced);
        return replaced;
    }

    private String supplySomething() {
        String sea = "over";
        log("in supply: {}", sea);
        return sea;
    }

    private void consumeSomething(String sea) {
        log("in consume: {}", sea.replace("over", "mystic"));
    }

    private void runnableSomething() {
        String sea = "outofshadow";
        log("in runnable: {}", sea);
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_method_object() {
        St4MutableStage mutable = new St4MutableStage();
        int sea = 904;
        boolean land = false;
        helloMutable(sea - 4, land, mutable);
        if (!land) {
            sea = sea + mutable.getStageName().length();
        }
        log(sea); // your answer? => 910（2024/07/25）
        // sea は primitive なので、helloMutableを実行しても 904 のまま.
        // land も method_object() 内では primitive なので、helloMutableを実行しても false のまま.
        // 一方で、mutable は、piari.setStageName("mystic") で 参照先の stageName が mystic に書き変わりそう
        // sea = 904 + 6 = 910
        // 答え：910
        // land が Boolean でも、helloMutable で land の値は false のままらしい。helloMutable 内で land = true としても、呼び出し元の land の参照先が false -> true になるわけではないらしい。
    }

    private int helloMutable(int sea, Boolean land, St4MutableStage piari) {
        sea++;
        land = true;
        piari.setStageName("mystic");
        return sea;
    }

    private static class St4MutableStage {

        private String stageName;

        public String getStageName() {
            return stageName;
        }

        public void setStageName(String stageName) {
            this.stageName = stageName;
        }
    }

    // ===================================================================================
    //                                                                   Instance Variable
    //                                                                   =================
    private int inParkCount;
    private boolean hasAnnualPassport;

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_method_instanceVariable() {
        hasAnnualPassport = true;
        int sea = inParkCount;
        offAnnualPassport(hasAnnualPassport);
        for (int i = 0; i < 100; i++) {
            goToPark();
        }
        ++sea;
        sea = inParkCount;
        log(sea); // your answer? => 100（2024/07/25）
        // 逆読みすると、inParkCount が分かれば良い。inParkCount は goToPark() で 100 回更新される可能性あり。
        // for の時点で hasAnnualPassport は true である（∵ offAnnualPassportの引数に hasAnnualPassport を代入しても、hasAnnualPassport のコピーが更新されるだけで hasAnnualPassport 自体は更新されない）。
        // また、goToPark では inParkCount の値は更新される（∵ goToPark では、引数を取らずにグローバル変数の inParkCount を直接更新する）
        // よって、inParkCount = 100（0+1*100)
        // 答え：100
    }

    private void offAnnualPassport(boolean hasAnnualPassport) {
        hasAnnualPassport = false;
    }

    private void goToPark() {
        if (hasAnnualPassport) {
            ++inParkCount;
        }
    }

    // ===================================================================================
    //                                                                           Challenge
    //                                                                           =========
    // write instance variables here
    /**
     * Make private methods as followings, and comment out caller program in test method:
     * <pre>
     * o replaceAwithB(): has one argument as String, returns argument replaced "A" with "B" as String 
     * o replaceCwithB(): has one argument as String, returns argument replaced "C" with "B" as String 
     * o quote(): has two arguments as String, returns first argument quoted by second argument (quotation) 
     * o isAvailableLogging(): no argument, returns private instance variable "availableLogging" initialized as true (also make it separately)  
     * o showSea(): has one argument as String argument, no return, show argument by log()
     * </pre>
     * (privateメソッドを以下のように定義して、テストメソッド内の呼び出しプログラムをコメントアウトしましょう):
     * <pre>
     * o replaceAwithB(): 一つのString引数、引数の "A" を "B" に置き換えたStringを戻す 
     * o replaceCwithB(): 一つのString引数、引数の "C" を "B" に置き換えたStringを戻す 
     * o quote(): 二つのString引数、第一引数を第二引数(引用符)で囲ったものを戻す 
     * o isAvailableLogging(): 引数なし、privateのインスタンス変数 "availableLogging" (初期値:true) を戻す (それも別途作る)  
     * o showSea(): 一つのString引数、戻り値なし、引数をlog()で表示する
     * </pre>
     */
    public void test_method_making() {
        // use after making these methods
        String replaced = replaceCwithB(replaceAwithB("ABC"));
        String sea = quote(replaced, "'");
        if (isAvailableLogging()) {
            showSea(sea); // 'BBB' と表示される。
            // private boolean availableLogging = true; で以下 2 つの警告が出るが、仕方ないと思われる。
            // 1. Field 'availableLogging' may be 'final'：availableLogging をどの関数でも更新してないため。
            // 2. Field can be converted to a local variable ：availableLogging を isAvailableLogging 以外で使用していないため。
        }
    }

    // write methods here
    private String replaceAwithB(String sea) {
        return sea.replace("A", "B");
    }

    private String replaceCwithB(String sea) {
        return sea.replace("C", "B");
    }

    private String quote(String sea, String quotationMark) {
        return quotationMark + sea + quotationMark;
    }

    private boolean availableLogging = true;

    private boolean isAvailableLogging() {
        return availableLogging;
    }

    private void showSea(String sea) {
        log(sea);
    }
}
