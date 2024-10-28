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

/**
 * The object for animal(動物).
 * @author jflute
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
        return new BarkingProcess(this).execute();
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
    // TODO mayukorin [いいね] 発想自体はとてもすごい！ by jflute (2024/10/28)
    /**
     * クラスによりアクセス制御をして getBarkWord() を実行するメソッド.
     * @param CallerClassName このメソッドの呼び出し元クラス名 (NotNull)
     * @return Animalの鳴き声 (NotNull)
     * @throws IllegalStateException getBarkWord()のアクセスが許可されていないクラスからcallGetBarkWord()が呼び出された場合
     */
    public String callGetBarkWord(String CallerClassName) {
        assertCanAccessClass(CallerClassName);
        return getBarkWord();
    }

    /**
     * クラスによりアクセス制御をして downHitPoint() を実行するメソッド.
     * @param CallerClassName このメソッドの呼び出し元クラス名 (NotNull)
     * @throws IllegalStateException downHitPoint()のアクセスが許可されていないクラスからcallDownHitPoint()が呼び出された場合
     */
    public void callDownHitPoint(String CallerClassName) {
        assertCanAccessClass(CallerClassName);
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
