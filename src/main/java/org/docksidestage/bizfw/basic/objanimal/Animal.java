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
package org.docksidestage.bizfw.basic.objanimal;

import java.util.Arrays;
import java.util.List;

import org.docksidestage.bizfw.basic.objanimal.barking.BarkedSound;
import org.docksidestage.bizfw.basic.objanimal.barking.BarkingProcess;
import org.docksidestage.bizfw.basic.objanimal.loud.Loudable;

// done mayukorin そういえば、既存クラスにも関わったらauthorをお願いします〜 by jflute (2024/10/29)
/**
 * The object for animal(動物).
 * @author jflute
 * @author mayukorin
 */
public abstract class Animal implements Loudable {

    // ===================================================================================
    //                                                                          Definition
    //                                                                          ==========
    private static final List<String> ClassesWithAccessToProtectedMethod = Arrays.asList("BarkingProcess");

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    protected int hitPoint; // is HP

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public Animal() {
        hitPoint = getInitialHitPoint();
    }

    protected int getInitialHitPoint() {
        return 10; // as default
    }

    // ===================================================================================
    //                                                                               Bark
    //                                                                              ======
    public BarkedSound bark() {
        BarkingProcess barkingProcess = createBarkingProcess();
        // [思い出]
        // return barkingProcess.execute();
        return barkingProcess.execute(getBarkWord());
        // [1on1でのふぉろー] 具象クラスを使わず、LambdaのままAnimalへの依存を無くす方法: 
        //
        // return barkingProcess.execute(animal -> animal.downHitPoint());
        //   animal変数は、結局コンストラクターで指定したthisのインスタンスが入ってそのdownHitPoint()...
        //   なので、呼びたいのは this の downHitPoint() になるので...
        //  ↓↓↓
        // return barkingProcess.execute(animal -> this.downHitPoint());
        //  ↓↓↓
        // return barkingProcess.execute(() -> this.downHitPoint());
        //  ↓↓↓
        // return barkingProcess.execute(() -> downHitPoint());
        //
        // 文法的なフォロー: Lambda式の中では、外側(Lambdaを生成してるクラス)のリソースを使える。
        // インスタンスメソッドを呼び出すこともできるし、インスタンス変数とかも使えちゃう。
        // return barkingProcess.execute(() -> {
        //     downHitPoint() // this.downHitPoint()を呼べる
        //     ++hitPoint; // this.hitPointを参照できる
        // });
        // 
        // まあ、文法を知らなくて思いつけなかったのしょうがない。
        // その文法をないことを前提に書いた実装はとても良いと思う。(縛りプレーとしてはとても良い)
        // (packageスコープに頼る部分を分離したことでうまく解決したというのは素晴らしい)
        //
        // DownHitPointerインターフェースを、Animal側に持たせるか？BarkingProcess側に持たせるか？
        // ここは少々悩みどころで、どちらでも良い解釈ができる。
        // 前者: Animal側が汎用的にヒットポイントを下げる方法を提供するニュアンス。// 今の実装
        // 後者: BarkingProcess側が自分でヒットポイントを下げる方法を募集するニュアンス。
    }

    // done mayukorin ちょっと状況違うけど、こちらの記事を参考に... by jflute (2024/10/29)
    //
    // // 単純な話、getであんまり検索したくない
    // https://jflute.hatenadiary.jp/entry/20151020/stopgetselect
    //
    // インスタンスを生成するメソッドは、createとかnewとか使うことが多いです。
    // // フレームワークでの例: LastaFilter.java
    // https://github.com/lastaflute/lastaflute/blob/0912a7e03ee4054c965f9934a5ae0c3a7c2e4434/src/main/java/org/lastaflute/web/LastaFilter.java#L65
    //
    // 一方で、getBarkWord()はgetで良いでしょう。本当に単に取得してるだけなので。
    //
    protected BarkingProcess createBarkingProcess() {
        // [1on1でのふぉろー] 改めて、Lambda式って何やってるのか？の学びになったかも？
        // new DownHitPointerOfAnimal(this) と () -> downHitPoint() が同じ(等価)になるので。
        // [思い出]
        //  return new BarkingProcess(this, new DownHitPointerOfAnimal(this));
        return new BarkingProcess(() -> downHitPoint());
    }

    protected abstract String getBarkWord();

    // ===================================================================================
    //                                                                           Hit Point
    //                                                                           =========
    protected void downHitPoint() {
        --hitPoint;
        if (hitPoint <= 0) {
            throw new IllegalStateException("I'm very tired, so I want to sleep" + getBarkWord());
        }
    }

    // ===================================================================================
    //                                                                               Loud
    //                                                                              ======
    @Override
    public String soundLoudly() {
        return bark().getBarkWord();
    }

    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    public int getHitPoint() {
        return hitPoint;
    }

    // ===================================================================================
    //                                                  Access Control to protected method
    //                                                                            ========
    // done mayukorin [いいね] 発想自体はとてもすごい！ by jflute (2024/10/28)
    // done mayukorin [補足] ただ、ソースコード読んで文字列を知っていれば呼べちゃうというので厳密には隠せているわけではないけど... by jflute (2024/12/16)
    // 気軽に呼んでしまうっての防げそうです。一方で、getBarkWord()はもっとシンプルに隠蔽して両立する方法はあります。
    // 二つ方法がありますね。一つはご自分で実際に実装してみた downHitPoint() の方のやり方。もう一つは...
    // done jflute 1on1にてgetBarkWord()の二つ方法のフォロー予定 (2024/12/16)
    // ご自身で、「ああ、渡しちゃえば」が出てきたので素晴らしい。
    // BarkingProcessが欲しいのは、getする行為ではなくStringのbarkWordが欲しいだけ。
    // (一方で、downHitPointの方は、downHitPointする行為を所定のタイミングで実行したい)
    // 引数には大きく二つ、物を渡しているのか？処理(指示書)を渡しているのか？
    // barkWordの方は物を渡すだけで解決できるので、コールバックを使う必要性がない。
    // barkWordの値をbarkingProcessに渡すようにしてみました！
    // TODO done mayukorin 引数のCallerClassNameを先頭小文字に (javaの慣習) by jflute (2024/12/16)
    /**
     * クラスによりアクセス制御をして getBarkWord() を実行するメソッド.
     * @param callerClassName このメソッドの呼び出し元クラス名 (NotNull)
     * @return Animalの鳴き声 (NotNull)
     * @throws IllegalStateException getBarkWord()のアクセスが許可されていないクラスからcallGetBarkWord()が呼び出された場合
     */
    public String callGetBarkWord(String callerClassName) {
        assertCanAccessClass(callerClassName);
        return getBarkWord();
    }

    /**
     * クラスによりアクセス制御をして downHitPoint() を実行するメソッド.
     * @param callerClassName このメソッドの呼び出し元クラス名 (NotNull)
     * @throws IllegalStateException downHitPoint()のアクセスが許可されていないクラスからcallDownHitPoint()が呼び出された場合
     */
    public void callDownHitPoint(String callerClassName) {
        assertCanAccessClass(callerClassName);
        downHitPoint();
    }

    private void assertCanAccessClass(String className) {
        // [ふぉろー] もし本気でcallerチェックをするなら、StackTraceを使うってのもアリ by jflute (2024/10/28)
        // でも、通常業務のプログラムの中でここまですることはほぼなくて、
        // フレームワークとかで共通的に＆隠蔽して使うってことの方がほとんどではある。
        //StackTraceElement[] stackTrace = new RuntimeException().getStackTrace();
        //StackTraceElement firstElement = stackTrace[1];
        //String className2 = firstElement.getClassName();
        //if ("BarkingProcess".equals(className2)) {
        //    // OK
        //}
        
        if (!ClassesWithAccessToProtectedMethod.contains(className)) {
            throw new IllegalStateException("Can't access to protected method: caller class = " + className);
        }
    }
}
