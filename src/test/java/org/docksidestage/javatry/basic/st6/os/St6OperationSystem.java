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
package org.docksidestage.javatry.basic.st6.os;

/**
 * @author jflute
 * @author mayukorin
 */
public abstract class St6OperationSystem {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    protected final String loginId;

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public St6OperationSystem(String loginId) {
        this.loginId = loginId;
    }

    // ===================================================================================
    //                                                                      User Directory
    //                                                                      ==============
    // done mayukorin [いいね] ちゃんと流れが再利用できている！ by jflute (2024/10/08)
    public String buildUserResourcePath(String relativePath) {
        String fileSeparator = getFileSeparator();
        String userDirectory = getUserDirectory();
        String resourcePath = userDirectory + fileSeparator + relativePath;
        return resourcePath.replace("/", fileSeparator);
    }

    // done mayukorin protectedを付けないと、抽象クラスとサブクラスでpackageを分けた時にオーバーライドできなくなる by jflute (2024/10/08)
    // TODO jflute 抽象クラスとサブクラスでpackageを分けたい意図をお聞きしたいです by m.inoue (2024/10/09)
    // ファイルの階層で抽象クラスを見つけやすくする意図でしょうか（確かに、サブクラスが増えたときに同一パッケージだとどれが抽象クラスかすぐ見つけにくい問題は出てくる気がしました）？
    // TODO done mayukorin [へんじ] Good Question! まず一つは見やすさの観点があります。 by jflute (2024/10/14)
    // 抽象クラスの名前の付け方によっては大量の具象クラスの.javaファイルに埋もれて見つけづらくなります。重要人物なので目立たせたいですね。
    // ただ、よく抽象クラスには AbstractColorBox というようにクラス名にも Abstract を付けることが多いです。
    // すると自然と一番上に来て目立つという。
    // (なぜ付けるのか？ってのは別の理由があって、それは別途step6でコメントしている「インターフェースの機能ドリブンと抽象物ドリブン」にて)
    //
    // ただ、もう一つの方が大きいきっかけですね。抽象クラスはフレームワークとかライブラリで提供されて、
    // アプリで具象クラスを作ってフレームワークにインスタンスを渡して動作させるって使われ方も多いです。
    // この場合、packageは絶対に変わりますので、abstractメソッドは絶対にprotectedということになります。
    // なるほど...！！意図的にパッケージを変えたいというよりも、抽象クラスを使う以上そうせざるを得ないケースが多いのですね！教えていただきありがとうございます！ by m.inoue (2024/10/16)
    // TODO done mayukorin 細かいですが、感覚的にはabstract protectedでなくprotected abstractの方が多いかなと by jflute (2024/10/14)
    // Javaの文法的にはどっちでも大丈夫っていう風になってはいるのですが。合わせてもらえると嬉しいです。(可視性が常に先頭ということで)
    protected abstract String getFileSeparator();
    protected abstract String getUserDirectory();
}
