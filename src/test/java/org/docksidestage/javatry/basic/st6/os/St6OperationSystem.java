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
    // TODO done mayukorin [いいね] ちゃんと流れが再利用できている！ by jflute (2024/10/08)
    public String buildUserResourcePath(String relativePath) {
        String fileSeparator = getFileSeparator();
        String userDirectory = getUserDirectory();
        String resourcePath = userDirectory + fileSeparator + relativePath;
        return resourcePath.replace("/", fileSeparator);
    }

    // TODO done mayukorin protectedを付けないと、抽象クラスとサブクラスでpackageを分けた時にオーバーライドできなくなる by jflute (2024/10/08)
    // TODO jflute 抽象クラスとサブクラスでpackageを分けたい意図をお聞きしたいです by m.inoue (2024/10/09)
    // ファイルの階層で抽象クラスを見つけやすくする意図でしょうか（確かに、サブクラスが増えたときに同一パッケージだとどれが抽象クラスかすぐ見つけにくい問題は出てくる気がしました）？
    abstract protected String getFileSeparator();
    abstract protected String getUserDirectory();
}
