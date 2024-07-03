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

import java.math.BigDecimal;

import org.docksidestage.unit.PlainTestCase;

/**
 * The test of variable. <br>
 * Operate exercise as javadoc. If it's question style, write your answer before test execution. <br>
 * (javadocの通りにエクササイズを実施。質問形式の場合はテストを実行する前に考えて答えを書いてみましょう)
 * @author jflute
 * @author mayukorin
 */
public class Step01VariableTest extends PlainTestCase {

    // ===================================================================================
    //                                                                      Local Variable
    //                                                                      ==============
    /**
     * What string is sea variable at the method end? <br>
     * (メソッド終了時の変数 sea の中身は？)
     */
    public void test_variable_basic() { // example, so begin from the next method
        String sea = "mystic";
        log(sea); // your answer? => mystic
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_variable_initial() {
        String sea = "mystic";
        Integer land = 8;
        String piari = null;
        String dstore = "mai";
        sea = sea + land + piari + ":" + dstore;
        log(sea); // your answer? => 47行目でエラー吐きそう（理由：String 型に null を代入しているから）
        // 答え：mystic8null:mai
        // そもそも String は型ではなく、クラスだった。
        // Java にはプリミティブ型と参照型(この中に String が含まれる)があるらしい。
        // null とは：参照型変数に値が何も格納されていない状態らしい。参照矢印が何も指していない的な？ https://qiita.com/e99h2121/items/46a624e0fe74e80c3b79
        // → 参照型は null 代入 OK。逆に、プリミティブ型に null 代入 NG（「 <nulltype>を ... に変換できません。」と出てくる）。
        // + でどちらかが String 型の場合、片方も String 型に変換されるらしい。https://docs.oracle.com/javase/specs/jls/se11/html/jls-15.html#jls-15.18.1
        // null も、"null" として変換されるらしい。https://docs.oracle.com/javase/specs/jls/se11/html/jls-5.html#jls-5.1.11:~:text=If%20the%20reference%20is%20null%2C%20it%20is%20converted%20to%20the%20string%20%22null%22
        // done mayukorin 素晴らしい学びですね。参照型は別名「ラッパー型」とも呼ばれます (プリミティブをwrapしてるということで) by jflute (2024/06/30)
        // nullを状態と捉えるのは大事なことですね。ついつい、みんな「nullが入ってる」とか「nullを入れる」とか言っちゃうんですけど、
        // 厳密にはnullは入ってるものではなく、中身が空っぽの状態を指しているだけです。でもぼくも「nullを入れる」って言っちゃうんです。
        // そこはあえてわかった上で便宜上言ってるという感じですね。

        // [memo]
        // オブジェクト型 => 広い
        // ラッパー型 => プリミティブ対応のもの
        // 参照型 => 変数の中はアドレスが入ってるだけ (オブジェクト型が全部そう)

        // [質問] オブジェクト型のメリットなに？
        // オブジェクト指向の思想を取り入れて、そのメリットを享受できるもの
        //
        //int kubo = 3;
        //betunohito.convert(kubo);
        //
        //Integer mayukorin = new Integer(3);
        //double doubleValue = mayukorin.doubleValue();
        //String string = mayukorin.toString();
        //String replaced = sea.replace("a", "b");
        // step5で実感しましょう

        // [質問] オブジェクトの値と振る舞いを一緒にできる仕組みは、参照型だから？
        // yes, そういうイメージでOK

        // done jflute 1on1でもうちょいだけフォロー予定 (他の言語では？とかnull事件とか) (2024/06/30)
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_variable_reassigned_basic() {
        String sea = "mystic";
        String land = "oneman";
        sea = land;
        land = land + "'s dreams";
        log(sea); // your answer? => oneman（理由： 64 行目で sea が oneman を参照するため）
        // 答え：oneman (2024/07/01)
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_variable_reassigned_int() {
        int sea = 94;
        int land = 415;
        sea = land;
        land++;
        log(sea); // your answer? => 415（理由：int はプリミティブ型なので、75行目でlandが416になっても、seaに格納されている数値は415のままだから）
        // 答え：415 (2024/07/01)
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_variable_reassigned_BigDecimal() {
        BigDecimal sea = new BigDecimal(94);
        BigDecimal land = new BigDecimal(415);
        sea = land;
        sea = land.add(new BigDecimal(1));
        // [memo] javadocを読むと良い
        // Returns a BigDecimal whose value is (this + augend),
        // and whose scale is max(this.scale(), augend.scale()).
        sea.add(new BigDecimal(1));
        log(sea); // your answer? =>  417（理由：85行目で 416 の BigDecimal を参照し直し、86行目で +1 で 417 になるから）
        // 答え：416
        // BigDecimal クラスの add method は、val が +1 になった BigDecimalインスタンスを返すっぽい（非破壊的変更的なやつ？）自身の値を+1するわけではない。
        // BigDecimal クラスの add メソッドは、非破壊的変更のやつだよと覚えている必要はない気がする。
        // add にカーソルをかざすと @Contract(pure=true)と出てくる。
        // 「@Contract(pure=true)の場合非破壊的変更を表している」ということさえ覚えておけば、他でも応用できるはず (2024/07/01)。

        // done mayukorin おおぉ、素晴らしい気付きですね。IDEが出すヒントやjavadocを見る習慣を付ける付けないで大きな差になります by jflute (2024/07/01)
        // と言いつつ、ぼくは普段IntelliJを使ってないので@Contractの表示は知らなかったので今度見せてください(^^。
        // 一方で、IntelliJはメソッド補完時のJavaDoc表示は、勝手に出てこなくてショートカットキーが必要だったはずです。
        // twitterで啓蒙してたこともありました。
        // https://twitter.com/jflute/status/877828346070970370
        // ぜひ、普段からcontrol+Jを使ってみてください。

        // done jflute 1on1にてImmutableの話をする予定 (2024/07/01)
    }

    // ===================================================================================
    //                                                                   Instance Variable
    //                                                                   =================
    private String instanceBroadway;
    private int instanceDockside;
    private Integer instanceHangar;
    private String instanceMagiclamp;

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_variable_instance_variable_default_String() {
        String sea = instanceBroadway;
        log(sea); // your answer? =>  null(理由：参照型なため、初期値は何も指してないnullが入りそう)
        //答え：null (2024/07/01)
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_variable_instance_variable_default_int() {
        int sea = instanceDockside;
        log(sea); // your answer? =>  0（理由：プリミティブ型のため、初期値は0が入りそう）
        //答え：0 (2024/07/01)
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_variable_instance_variable_default_Integer() {
        Integer sea = instanceHangar;
        log(sea); // your answer? => null（理由：参照型なため、初期値は何も指してないnullが入りそう）. Integer ってラッパークラスとか呼ばれる気がするけど忘れた
        // 答え：null
        // Integer は、ラッパークラス. https://qiita.com/KenyaSaitoh/items/fff28b132c7e1048ca97#%E3%83%A9%E3%83%83%E3%83%91%E3%83%BC%E3%82%AF%E3%83%A9%E3%82%B9%E3%81%A8%E3%81%AF
        // ラッパークラス：プリミティブ型をオブジェクトとして扱うためのクラス。
        // なぜラッパークラスが必要？：プリミティブ型のままでは、格納している値を変換したりができないから。
        // done jflute ラッパークラスとラッパー型は同じものを指しているのでしょうか？ by mayukorin
        // done mayukorin [へんじ] 同じものと捉えてOKです。要は「クラスと型という言葉が同じものを指すか？」って話になります by jflute (2024/07/01)
        // クラスは、Javaにおけるオブジェクトの定義を表現する文法表現で...
        // 型は、Javaの変数に代入されるインスタンスの種類に制限を掛ける制約のようなもので...
        // そして、型として利用されるものが(Javaでは)クラスなので、指し示すものは実質的に同じとなります。
        // 視点の違う別用語だけど、たまたま指し示すものが同じであると。
        // 結構入り乱れて使われてしまうものなので、ちょっとややこしいですよね。
        // ちなみに、Integerとかをオブジェクト型と表現することもあります。
        // オブジェクト型は、厳密には広い言葉で、StringもObjectもプリミティブ型以外は全部プリミティブ型です。
        // ラッパー型はオブジェクト型の一部で、プリミティブ型をラップ(wrap)したものをラッパー型と呼びます。
        // 最近は、あまりラッパー型と呼ぶ人は現場では少ないような印象ではありますね。
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_variable_instance_variable_via_method() {
        instanceBroadway = "bbb";
        instanceMagiclamp = "magician";
        helpInstanceVariableViaMethod(instanceMagiclamp);
        String sea = instanceBroadway + "|" + instanceDockside + "|" + instanceHangar + "|" + instanceMagiclamp;
        log(sea); // your answer? => bbb|1|null|burn
        // 理由：
        // ① sea は、本関数内のinstanceBroadway変数を使用している & 本関数内のinstanceBroadway変数は、helpInstanceVariableViaMethodで更新されない。
        // ② sea は、本関数外で定義された instanceDockside を使用している & 本関数外で定義された instanceDockside は、helpInstanceVariableViaMethodで更新される
        // ③ sea は、本関数外で定義された instanceHangar を使用している & 本関数外で定義された instanceHangar = null で、<String型> + で "null" に変換される。
        // ④ sea は、本関数内のinstanceMagiclamp変数を使用している & 本関数内のinstanceMagiclamp変数は、helpInstanceVariableViaMethodで更新される...？本関数内のinstanceMagiclamp変数とhelpInstanceVariableViaMethodは同じ値を参照していて、151行目で参照先の値が変わるから。
        // 解答：bigband|1|null|magician
        // 間違えたポイント
        // ・本関数内の instanceBroadwayは、本関数外で定義された変数と同じ（理由：136行目で型で宣言されたわけではないため。）
        // ・「instanceMagiclamp = "burn";」で、本関数内の instanceMagiclamp 変数の参照先の値は変わらない。
        // 「instanceMagiclamp = "burn";」では、メモリ上で新しく "burn" を確保して、それを helpInstanceVariableViaMethod 内の instanceMagiclamp が参照し直す。
        // イメージ図：https://docs.google.com/presentation/d/1J8le87HoefiA3gyiwai2y92ov6hYKtRotXOMuMwLjO0/edit?usp=sharing（2024/07/01）
        // done mayukorin ↑イメージ図の3ページ目、instanceBroadwayじゃなくてinstanceMagiclampの間違いですよね？ by jflute (2024/07/01)
        // 間違いでした！instanceMagiclamp に直しました。教えていただきありがとうございます！by mayukorin（2024/07/02）

        // done jflute 学びが素晴らしすぎるので1on1の時にフォロー予定 (2024/07/01)

        // TODO mayukorin [読み物課題]図を使って理解を深めようとするのとても素敵です！ by jflute (2024/07/03)
        // ぼくもよく図を描いて表現すること多いし、みなさんにオススメをしています。
        // ちょっとお時間ある時に以下のブログをぜひ読んでみてください。(これもjavatryの一環として)
        // 実際はホワイトボードじゃなくてもPC上で気軽に図を描くツールがあればそれで良いですが、コンセプトはこういうこってところです。
        //
        // ホワイトボードを買ってこよう | jfluteの日記
        // https://jflute.hatenadiary.jp/entry/20110607/1307440686
    }

    private void helpInstanceVariableViaMethod(String instanceMagiclamp) {
        instanceBroadway = "bigband";
        ++instanceDockside;
        instanceMagiclamp = "burn";
    }

    // ===================================================================================
    //                                                                     Method Argument
    //                                                                     ===============
    // -----------------------------------------------------
    //                                 Immutable Method-call
    //                                 ---------------------
    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_variable_method_argument_immutable_methodcall() {
        String sea = "harbor";
        int land = 415;
        helpMethodArgumentImmutableMethodcall(sea, land);
        log(sea); // your answer? => 
    }

    private void helpMethodArgumentImmutableMethodcall(String sea, int land) {
        ++land;
        String landStr = String.valueOf(land); // is "416"
        sea.concat(landStr);
    }

    // -----------------------------------------------------
    //                                   Mutable Method-call
    //                                   -------------------
    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_variable_method_argument_mutable_methodcall() {
        StringBuilder sea = new StringBuilder("harbor");
        int land = 415;
        helpMethodArgumentMethodcall(sea, land);
        log(sea); // your answer? => 
    }

    private void helpMethodArgumentMethodcall(StringBuilder sea, int land) {
        ++land;
        sea.append(land);
    }

    // -----------------------------------------------------
    //                                   Variable Assignment
    //                                   -------------------
    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_variable_method_argument_variable_assignment() {
        StringBuilder sea = new StringBuilder("harbor");
        int land = 415;
        helpMethodArgumentVariable(sea, land);
        log(sea); // your answer? => 
    }

    private void helpMethodArgumentVariable(StringBuilder sea, int land) {
        ++land;
        String seaStr = sea.toString(); // is "harbor"
        sea = new StringBuilder(seaStr).append(land);
    }

    // ===================================================================================
    //                                                                           Challenge
    //                                                                           =========
    /**
     * Define variables as followings:
     * <pre>
     * o local variable named sea typed String, initial value is "mystic"
     * o local variable named land typed Integer, initial value is null
     * o instance variable named piari typed int, without initial value
     * o show all variables by log() as comma-separated
     * </pre>
     * (変数を以下のように定義しましょう):
     * <pre>
     * o ローカル変数、名前はsea, 型はString, 初期値は "mystic"
     * o ローカル変数、名前はland, 型はInteger, 初期値は null
     * o インスタンス変数、名前はpiari, 型はint, 初期値なし
     * o すべての変数をlog()でカンマ区切りの文字列で表示
     * </pre>
     */
    public void test_variable_writing() {
        // define variables here
    }

    // ===================================================================================
    //                                                                           Good Luck
    //                                                                           =========
    /**
     * Make your original exercise as question style about variable. <br>
     * (変数についてあなたのオリジナルの質問形式のエクササイズを作ってみましょう)
     * <pre>
     * _/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
     * your question here (ここにあなたの質問を):
     * 
     * _/_/_/_/_/_/_/_/_/_/
     * </pre>
     */
    public void test_variable_yourExercise() {
        // write your code here
    }
}
