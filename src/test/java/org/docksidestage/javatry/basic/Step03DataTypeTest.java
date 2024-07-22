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
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.docksidestage.unit.PlainTestCase;

/**
 * The test of data type. <br>
 * Operate exercise as javadoc. If it's question style, write your answer before test execution. <br>
 * (javadocの通りにエクササイズを実施。質問形式の場合はテストを実行する前に考えて答えを書いてみましょう)
 * @author jflute
 * @author mayukorin
 */
public class Step03DataTypeTest extends PlainTestCase {

    // ===================================================================================
    //                                                                          Basic Type
    //                                                                          ==========
    /**
     * What string is sea variable at the method end? <br>
     * (メソッド終了時の変数 sea の中身は？)
     */
    public void test_datatype_basicType() {
        String sea = "mystic";
        Integer land = 416;
        LocalDate piari = LocalDate.of(2001, 9, 4);
        LocalDateTime bonvo = LocalDateTime.of(2001, 9, 4, 12, 34, 56);
        Boolean dstore = true;
        BigDecimal amba = new BigDecimal("9.4");

        piari = piari.plusDays(1);
        land = piari.getYear();
        bonvo = bonvo.plusMonths(1);
        land = bonvo.getMonthValue();
        land--;
        if (dstore) {
            BigDecimal addedDecimal = amba.add(new BigDecimal(land));
            sea = String.valueOf(addedDecimal);
        }
        log(sea); // your answer? => 18.4（2024/07/22）
        // sea を知りたい目的で逆読みしていく。
        // dstore = true 確定なので、addedDecimal が分かれば良い → amba と land が分かれば良い
        // amba は 9.4 確定。land だけあとわかれば良い
        // land は、bonvo の月-1
        // bonvo は 9+1=10
        // land = 10-1=9 なので、addedDecimal = 9.4 + 9 = 18.4
        // 答え：18.4
        // TODO jflute 1on1にてplusDays()がimmutableかどうか判別する話 (2024/07/22)
    }

    // ===================================================================================
    //                                                                           Primitive
    //                                                                           =========
    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_datatype_primitive() {
        byte sea = 127; // max
        short land = 32767; // max
        int piari = 1;
        long bonvo = 9223372036854775807L; // max
        float dstore = 1.1f;
        double amba = 2.3d;
        char miraco = 'a';
        boolean dohotel = miraco == 'a';
        if (dohotel && dstore >= piari) {
            bonvo = sea;
            land = (short) bonvo;
            bonvo = piari;
            sea = (byte) land;
            if (amba == 2.3D) {
                sea = (byte) amba;
            }
        }
        if ((int) dstore > piari) {
            sea = 0;
        }
        log(sea); // your answer? => 2.23146？？（2024/07/22）
        // sea を知りたい目的で逆読みしていく。
        // (int) dstore > piari なのかを確かめる。dstore = 1.1f。piari は 1。
        // (int)1.1f = 1 になる気がするので、(int) dstore > piari にはならなそう残念。
        // 上を読んでいくと、dohotel && dstore >= piari かつ amba == 2.3D だったら sea が確定するので嬉しい。
        // dohotel && dstore >= piar：dohotel は true（∵ dohotel = miraco == 'a'）。dstore >= piari も true？（∵ float と int で比較するときは、int が float に合わせて 1.1 > 1.0 の比較になりそう）
        // 以上より dohotel && dstore >= piar は true
        // amba == 2.3D：true になりそう（∵ 2.3d と 2.3D は同じ意味っぽい）
        // よって、sea = (byte) amba 確定。
        // ただ、(byte) amba が分からない。2.3 を8進数に変換したら 2.23146..の繰り返しになってしまう
        // 答え：2
        // byte の理解が間違っていた
        // byte：全部で 8bit で構成される。整数を表す。最後の1ビットは符号に使うので、127（2^8-1）から -128 まで表現できる。小数点は表現できない。
        // よって、(byte) amba は、0.3 を切り捨てて 2 になる。
        // short は 16 bit で整数を表す。int は 32 bit で整数を表す。long は 64 bit で整数を表す。float は 32 bit で浮動小数点を表す。double は 64 bit で浮動小数点を表す。
        // 表現に使う bit 数が異なる。
        // 数値の最後の f, F, d, D はつけなくても良い。大文字と小文字は同じ。

        // TODO mayukorin [読み物課題] プリミティブ型とラッパー型 by jflute (2024/07/22)
        // https://dbflute.seasar.org/ja/manual/topic/programming/java/beginners.html#primitivewrapper

        // TODO jflute 1on1にてprimitiveの用途のお話 (2024/07/22)
    }

    // ===================================================================================
    //                                                                              Object
    //                                                                              ======
    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_datatype_object() {
        St3ImmutableStage stage = new St3ImmutableStage("hangar");
        String sea = stage.getStageName();
        log(sea); // your answer? => "hangar"（2024/07/22）
        // St3ImmutableStage を new するときに、stageName インスタンス変数に hangar が代入される & getStageName() の返り値は stageName インスタンス変数 なため。
        // 答え：hangar
    }

    private static class St3ImmutableStage {

        private final String stageName;

        public St3ImmutableStage(String stageName) {
            this.stageName = stageName;
        }

        public String getStageName() {
            return stageName;
        }
    }
}
