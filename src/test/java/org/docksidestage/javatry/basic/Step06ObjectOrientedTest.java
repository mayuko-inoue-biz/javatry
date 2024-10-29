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

import java.time.LocalDateTime;

import org.docksidestage.bizfw.basic.buyticket.*;
import org.docksidestage.bizfw.basic.objanimal.*;
import org.docksidestage.bizfw.basic.objanimal.barking.BarkedSound;
import org.docksidestage.bizfw.basic.objanimal.jumper.HighJumper;
import org.docksidestage.bizfw.basic.objanimal.loud.AlarmClock;
import org.docksidestage.bizfw.basic.objanimal.loud.Loudable;
import org.docksidestage.bizfw.basic.objanimal.runner.FastRunner;
import org.docksidestage.javatry.basic.st6.dbms.St6MySql;
import org.docksidestage.javatry.basic.st6.dbms.St6PostgreSql;
import org.docksidestage.javatry.basic.st6.dbms.St6QL;
import org.docksidestage.javatry.basic.st6.os.St6Mac;
import org.docksidestage.javatry.basic.st6.os.St6OldWindows;
import org.docksidestage.javatry.basic.st6.os.St6OperationSystem;
import org.docksidestage.javatry.basic.st6.os.St6Windows;
import org.docksidestage.unit.PlainTestCase;

// done mayukorin [読み物課題] 別に、プルリクレビューの前にレビューしてもらっていいんだからね by jflute (2024/10/02)
// https://jflute.hatenadiary.jp/entry/20170630/reviewbefore
/**
 * The test of object-oriented. <br>
 * Operate exercise as javadoc. If it's question style, write your answer before test execution. <br>
 * (javadocの通りにエクササイズを実施。質問形式の場合はテストを実行する前に考えて答えを書いてみましょう)
 * @author jflute
 * @author mayukorin
 */
public class Step06ObjectOrientedTest extends PlainTestCase {

    // ===================================================================================
    //                                                                        About Object
    //                                                                        ============
    // -----------------------------------------------------
    //                                        Against Object
    //                                        --------------
    /**
     * Fix several mistakes (except simulation) in buying one-day passport and in-park process. <br>
     * (OneDayPassportを買って InPark する処理の中で、(simulationを除いて)間違いがいくつかあるので修正しましょう)
     */
    public void test_objectOriented_aboutObject_againstObject() {
        //
        // [ticket booth info]
        //
        // simulation: actually these variables should be more wide scope
        int oneDayPrice = 7400;
        int quantity = 10;
        Integer salesProceeds = null;

        //
        // [buy one-day passport]
        //
        // simulation: actually this money should be from customer
        int handedMoney = 10000;
        if (quantity <= 0) {
            throw new IllegalStateException("Sold out");
        }
        --quantity;
        if (handedMoney < oneDayPrice) {
            throw new IllegalStateException("Short money: handedMoney=" + handedMoney);
        }
        salesProceeds = oneDayPrice;

        //
        // [ticket info]
        //
        // simulation: actually these variables should be more wide scope
        int displayPrice = oneDayPrice;
        boolean alreadyIn = false;

        // other processes here...
        // ...
        // ...

        //
        // [do in park now!!!]
        //
        // simulation: actually this process should be called by other trigger
        if (alreadyIn) {
            throw new IllegalStateException("Already in park by this ticket: displayPrice=" + displayPrice);
        }
        alreadyIn = true;

        //
        // [final process]
        //
        // [ふぉろー] まずプログラマーとしての意識
        // o そもそもこういうメソッドを作らないように努力する
        // o 呼び出すしかないときは、意地でも間違えないように指差し確認
        //  i (危ないなと思ったら遠慮なく怖がって集中力を上げる) // 危ないなっていう勘所を知っていること前提
        // [ふぉろー] そしてオブジェクト指向の意識
        // o こうやって値をバラバラに扱っているとプログラミングとしても都合が悪い
        // o 関連しているデータはオブジェクトとしてまとめることで(比較的)安全に扱えるようになる
        saveBuyingHistory(quantity, salesProceeds, displayPrice, alreadyIn);
    }

    private void saveBuyingHistory(int quantity, Integer salesProceeds, int displayPrice, boolean alreadyIn) {
        if (alreadyIn) {
            // simulation: only logging here (normally e.g. DB insert)
            showTicketBooth(quantity, salesProceeds);
            showYourTicket(displayPrice, alreadyIn);
        }
    }

    private void showTicketBooth(int quantity, Integer salesProceeds) {
        log("Ticket Booth: quantity={}, salesProceeds={}", quantity, salesProceeds);
    }

    private void showYourTicket(int displayPrice, boolean alreadyIn) {
        log("Ticket: displayPrice={}, alreadyIn={}", displayPrice, alreadyIn);
    }

    // -----------------------------------------------------
    //                                          Using Object
    //                                          ------------
    /**
     * Read (analyze) this code and compare with the previous test method, and think "what is object?". <br>
     * (このコードを読んで(分析して)、一つ前のテストメソッドと比べて、「オブジェクトとは何か？」を考えてみましょう)
     */
    public void test_objectOriented_aboutObject_usingObject() {
        //
        // [ticket booth info]
        //
        TicketBooth booth = new TicketBooth();

        // *booth has these properties:
        //int oneDayPrice = 7400;
        //int quantity = 10;
        //Integer salesProceeds = null;

        //
        // [buy one-day passport]
        //
        // if step05 has been finished, you can use this code by jflute (2019/06/15)
        Ticket ticket = booth.buyOneDayPassport(10000);
//        booth.buyOneDayPassport(10000); // as temporary, remove if you finished step05
//        Ticket ticket = new Ticket(TicketType.ONE_DAY_PASSPORT); // also here

        // *buyOneDayPassport() has this process:
        //if (quantity <= 0) {
        //    throw new TicketSoldOutException("Sold out");
        //}
        //if (handedMoney < oneDayPrice) {
        //    throw new TicketShortMoneyException("Short money: handedMoney=" + handedMoney);
        //}
        //--quantity;
        //salesProceeds = handedMoney;

        // *ticket has these properties:
        //int displayPrice = oneDayPrice;
        //boolean alreadyIn = false;

        // other processes here...
        // ...
        // ...

        //
        // [do in park now!!!]
        //
        ticket.doInPark(LocalDateTime.of(2017, 11, 17, 12, 0));

        // *doInPark() has this process:
        //if (alreadyIn) {
        //    throw new IllegalStateException("Already in park by this ticket: displayPrice=" + displayPrice);
        //}
        //alreadyIn = true;

        //
        // [final process]
        //
        saveBuyingHistory(booth, ticket);
    }

    private void saveBuyingHistory(TicketBooth booth, Ticket ticket) {
        if (ticket.isUsedUp()) {
            // only logging here (normally e.g. DB insert)
            doShowTicketBooth(booth);
            doShowYourTicket(ticket);
        }
    }

    private void doShowTicketBooth(TicketBooth booth) {
        log("Ticket Booth: quantity={}, salesProceeds={}", booth.getOneDayPassportQuantity(), booth.getSalesProceeds());
    }

    private void doShowYourTicket(Ticket ticket) {
        log("Your Ticket: displayPrice={}, unAvailable={}", ticket.getDisplayPrice(), ticket.isUsedUp());
    }

    // write your memo here:
    // _/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
    // what is object?
    // 関連する特性やそれに関する処理をまとめたもの
    // _/_/_/_/_/_/_/_/_/_/
    // [ふぉろー] シンプルで良い！一方で、関連を見つけるのが難しいことではあるので学んでいく必要はある。
    // done jflute 次回1on1にて、科目クラスの話をする (2024/09/24)
    //Kamoku kokugo = new Kamoku(10, "sea"); // 国語
    //Kamoku suugaku = new Kamoku(12, "land"); // 数学

    // done jflute 次回1on1ここから (2024/09/24)
    // できるだけリモートレビューで。
    // ===================================================================================
    //                                                              Polymorphism Beginning
    //                                                              ======================
    /**
     * What string is sea and land variable at the method end? <br>
     * (メソッド終了時の変数 sea, land の中身は？)
     */
    public void test_objectOriented_polymorphism_1st_concreteOnly() {
        Dog dog = new Dog();
        BarkedSound sound = dog.bark();
        String sea = sound.getBarkWord();
        log(sea); // your answer? => wan
        // sea = soundのbarkWordインスタンス変数
        // dog.bark()は親のAnimalクラスのbark()を呼び出す
        // bark()を見ると、barkWordインスタンス変数=getBarkWord()
        // getBarkWord()はabstractメソッドなのでそれを実装しているDogのgetBarkWord()=wan
        int land = dog.getHitPoint();
        log(land); // your answer? => 7
        // dog.getHitPoint()はAnimalクラスのgetHitPoint()を呼び出す
        // hitPointが代入されるのは、Animal()コンストラクタでのみだけど
        // new Dog() で親の Animal()コンストラクタも呼び出されないような気がする...?
        // いや、でも呼び出されるのか..?一旦呼び出されるとして答えを書いてみよう
        // 「wan, 7」になったから、new Dog() で親の Animal()コンストラクタも呼び出されて初期値10から-3になるっぽい
        // 調べたところ、子コンストラクタで親コンストラクタが呼ばれていない場合、引数なしの親コンストラクタが自動で呼び出されるらしい
        // done mayukorin [いいね] yes, super()が省略可能なので書いてないだけなんですよね by jflute (2024/10/08)
        // 子どもが初期化されるためには、親も初期化されなければならない(しかも先に)、というコンセプトなんですね。
        // 継承してるからにはということで。
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_objectOriented_polymorphism_2nd_asAbstract() {
        Animal animal = new Dog();
        BarkedSound sound = animal.bark();
        String sea = sound.getBarkWord();
        log(sea); // your answer? => wan
        int land = animal.getHitPoint();
        log(land); // your answer? => 7
        // 上との違いは、animal変数がAnimal型になった部分
        // でも変わらずDogのgetBarkWord()=wanは呼び出されるはず。
        // 「wan, 7」になった
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_objectOriented_polymorphism_3rd_fromMethod() {
        Animal animal = createAnyAnimal();
        BarkedSound sound = animal.bark();
        String sea = sound.getBarkWord();
        log(sea); // your answer? => wan
        int land = animal.getHitPoint();
        log(land); // your answer? => 7
        // 上との違いは、new Dog()をcreateAnyAnimal()を介して作るようになった部分
        // でも変わらずDogのgetBarkWord()=wanは呼び出されるはず。
        // でもちょい自信ないかも
        // 「wan, 7」になった
        // Animal animal で代入するときは、createAnyAnimal()の返り値として宣言してる型は関係なく、
        // 中身の実際の型を見てるのか
        // done mayukorin [いいね] 自信持って大丈夫！その通り。 by jflute (2024/10/08)
        // 変数を経由してメソッドが呼び出されるのインスタンスは、実際に中に入ってるインスタンス。
        // 変数の型って、単なる「この箱に入れられるのはAnimalだけだよ」って定義しているだけ、
        // 変数を使う人は「ああ、この箱の中に入ってるのはAnimalなんだね」って認識してるだけ。
        // 中にはDogが入っている。動くのはDogだし、インスタンスとして存在しているのもDogだけ。
    }

    private Animal createAnyAnimal() {
        return new Dog();
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_objectOriented_polymorphism_4th_toMethod() {
        Dog dog = new Dog();
        doAnimalSeaLand_for_4th(dog);
    }

    private void doAnimalSeaLand_for_4th(Animal animal) {
        BarkedSound sound = animal.bark();
        String sea = sound.getBarkWord();
        log(sea); // your answer? => wan
        int land = animal.getHitPoint();
        log(land); // your answer? => 7
        // 上との違いは、animalを引数で受け取るようになった部分
        // でも多分、animalではなく実際の型Dogを見ているはずなので、上と同じ答えになる気がする。
        // 同じように、「wan, 7」になった。
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_objectOriented_polymorphism_5th_overrideWithSuper() {
        Animal animal = new Cat();
        BarkedSound sound = animal.bark();
        String sea = sound.getBarkWord();
        log(sea); // your answer? => nya-
        int land = animal.getHitPoint();
        log(land); // your answer? => 5
        // 上と違うのは、animalがDog->Catのインスタンスになった部分
        // getBarkWord()がDogではなくCatのメソッドになるので
        // nya-になる
        // downHitPoint()もDogとは異なり、hitPointが奇数になるように減っていく
        // nya-, 5 になった
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_objectOriented_polymorphism_6th_overriddenWithoutSuper() {
        Animal animal = new Zombie();
        BarkedSound sound = animal.bark();
        String sea = sound.getBarkWord();
        log(sea); // your answer? => uooo
        int land = animal.getHitPoint();
        log(land); // your answer? => -1
        // 上と違うのは、animalがCat->Zombieのインスタンスになった部分
        // getBarkWord()がCatではなくZombieのメソッドになるので
        // uoooになる
        // Zombieのコンストラクタで呼ばれるgetInitialHitPoint()はZombieでも実装されている
        // hitPoint=-1になる
        // 毎回呼ばれるdownHitPoint()もZombieで実装されているが、hitPointは何も更新しない
        // uooo, -1 になった
    }

    /**
     * What is happy if you can assign Dog or Cat instance to Animal variable? <br>
     * (Animal型の変数に、DogやCatなどのインスタンスを代入できると何が嬉しいのでしょう？)
     */
    public void test_objectOriented_polymorphism_7th_whatishappy() {
        // write your memo here:
        // _/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
        // what is happy?
        // どの具象クラスなのか意識せずにメソッドを呼び出すことができる
        // 具象クラスを変更したいときも、new でインスタンスを生成する部分の名前を変更すれば良いだけ
        // _/_/_/_/_/_/_/_/_/_/
        // done jflute 次回1on1にて、現実の世界のでポリモーフィズムについて話をする (2024/09/24)
        // 受付の話、面談者の話など。
    }

    // ===================================================================================
    //                                                              Polymorphism Interface
    //                                                              ======================
    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_objectOriented_polymorphism_interface_dispatch() {
        Loudable loudable = new Zombie();
        String sea = loudable.soundLoudly();
        log(sea); // your answer? => uooo
        String land = ((Zombie) loudable).bark().getBarkWord();
        log(land); // your answer? => uooo
        // loudable.soundLoudly()で、AnimalのsoundLoudly()(bark().getBarkWord())が実行される
        // seaには、getBarkWord()の返り値が入るので、uooo
        // landでも同じようにZombieのbark().getBarkWord()が実行される
        // uooo、uooo になった
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_objectOriented_polymorphism_interface_hierarchy() {
        Loudable loudable = new AlarmClock();
        String sea = loudable.soundLoudly();
        log(sea); // your answer? => jiri jiri jiri---
        boolean land = loudable instanceof Animal;
        log(land); // your answer? => false
        // loudable.soundLoudly()で、AlarmClockのsoundLoudly()が実行される
        // AlarmClockはLoudableを実装しているだけでAnimalを継承しているわけではないので
        // loudable instanceof Animaはfalse
        // jiri jiri jiri---、false になった
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_objectOriented_polymorphism_interface_partImpl() {
        Animal seaAnimal = new Cat();
        Animal landAnimal = new Zombie();
        boolean sea = seaAnimal instanceof FastRunner;
        log(sea); // your answer? => true
        boolean land = landAnimal instanceof FastRunner;
        log(land); // your answer? => false
        // CatはFastRunnerを実装してるので、seaAnimal instanceof FastRunner：true
        // ZombieはFastRunnerを実装していないので、seaAnimal instanceof FastRunner：false
        // true, false になった
    }

    /**
     * Make Dog class implement FastRunner interface. (the method implementation is same as Cat class) <br>
     * (DogもFastRunnerインターフェースをimplementsしてみましょう (メソッドの実装はCatと同じで))
     */
    public void test_objectOriented_polymorphism_interface_runnerImpl() {
        // your confirmation code here
        doFastRun(new Dog()); // ...Running now になるはず
        // なった
    }

    /**
     * What is difference as concept between abstract class and interface? <br>
     * (抽象クラスとインターフェースの概念的な違いはなんでしょう？)
     */
    public void test_objectOriented_polymorphism_interface_whatisdifference() {
        // write your memo here:
        // _/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
        // what is difference?
        // 抽象クラス：クラスの種類
        // インターフェース：クラスが持ってる振る舞い
        // みたいな感じ？
        // _/_/_/_/_/_/_/_/_/_/
        
        // [ふぉろー]
        // オブジェクト指向という哲学(コンセプト)
        // インターフェースという哲学(コンセプト)
        // それぞれ別物です。
        // オブジェクト指向の多重継承(Javaはない)の話もした
        // https://dbflute.seasar.org/ja/manual/topic/programming/objectop/index.html

        // オブジェクト指向やインターフェースに限らず...
        // Javaに限らず...
        // 何事でもコンセプトを理解するようにすることで知識のつながりを辿ることができて応用力につながる。
        // done mayukorin [読み物課題] 実装方法よりも機能概念を by jflute (2024/09/30)
        // https://jflute.hatenadiary.jp/entry/20110531/1306825539

        // done jflute 1on1にて、インターフェースの機能ドリブンと抽象物ドリブンの使い方について話する (2024/10/14)
        // ColorBox と AbstractColorBox のように、対外的なポリモーフィズムのメリットをinterfaceに任せるやり方もある。
        // この場合、AbstractColorBoxは内部的な構造を構築するために特化したクラスとなる。
        // interfaceに少し寄ってる考え方かも知れないけど、ぼくはよくやっている。
    }

    // ===================================================================================
    //                                                                 Polymorphism Making
    //                                                                 ===================
    /**
     * Make concrete class of Animal, which is not FastRunner, in "objanimal" package. (implementation is as you like) <br>
     * (FastRunnerではないAnimalクラスのコンクリートクラスをobjanimalパッケージに作成しましょう (実装はお好きなように))
     */
    public void test_objectOriented_polymorphism_makeConcrete() {
        // your confirmation code here
        Animal notFastRunnerAnimal = new Turtle();
        boolean notFastRunner = notFastRunnerAnimal instanceof FastRunner;
        log(notFastRunner); // falseになるはず
        // なった
    }

    /**
     * Make interface implemented by part of Animal concrete class in new package under "objanimal" package. (implementation is as you like) <br>
     * (Animalクラスの一部のコンクリートクラスだけがimplementsするインターフェースをobjanimal配下の新しいパッケージに作成しましょう (実装はお好きなように))
     */
    public void test_objectOriented_polymorphism_makeInterface() {
        // your confirmation code here
        // HighJumperインターフェースを作成した
        // CatがHighJumperインターフェースをimplementsするようにした
        Cat cat = new Cat();
        doHighJump(cat); // ...Jumping now になるはず
        doBark(cat); // nya- になるはず
        doFastRun(cat); // ...Running now
        // doHighJump、doBark、doFastRun：各クラス・インターフェースのメソッドを呼ぶ関数を定義した
        // (FastRunner) cat).jump() のように呼び出し時にいちいちキャストしなくて良いので楽な気がする
    }

    private void doFastRun(FastRunner fastRunner) {
        fastRunner.run();
    }

    private void doHighJump(HighJumper highJumper) {
        highJumper.jump();
    }

    private void doBark(Animal animal) {
        String barkWord = animal.bark().getBarkWord();
        log(barkWord);
    }

    // ===================================================================================
    //                                                                           Challenge
    //                                                                           =========
    /**
     * Extract St6MySql, St6PostgreSql (basic.st6.dbms)'s process to abstract class (as super class and sub-class) <br>
     * (St6MySql, St6PostgreSql (basic.st6.dbms) から抽象クラスを抽出してみましょう (スーパークラスとサブクラスの関係に))
     */
    public void test_objectOriented_writing_generalization_extractToAbstract() {
        // your confirmation code here
        doBuildPagingQuery(new St6MySql(), 3, 1); // limit 0, 3 になるはず
        doBuildPagingQuery(new St6PostgreSql(), 3, 1); // offset 0 limit 3 になるはず
        // なった
    }
    
    private void doBuildPagingQuery(St6QL sql, int pageSize, int pageNumber) {
        log(sql.buildPagingQuery(pageSize, pageNumber));
    }

    /**
     * Extract St6OperationSystem (basic.st6.os)'s process to concrete classes (as super class and sub-class) <br>
     * (St6OperationSystem (basic.st6.os) からコンクリートクラスを抽出してみましょう (スーパークラスとサブクラスの関係に))
     */
    public void test_objectOriented_writing_specialization_extractToConcrete() {
        // your confirmation code here
        doBuildUserResourcePath(new St6Mac("1"), "test.txt"); // /Users/1/test.txt になるはず
        doBuildUserResourcePath(new St6Windows("1"), "test.txt"); // \Users\1\test.txt になるはず
        doBuildUserResourcePath(new St6OldWindows("1"), "test.txt"); // \Documents and Settigs\1\test.txt になるはず
        // なった
    }

    private void doBuildUserResourcePath(St6OperationSystem os, String relativePath) {
        log(os.buildUserResourcePath(relativePath));
    }
    // TODO jflute 1on1にて、再利用に関する思考のエクササイズをする予定 (2024/10/08)

    // ===================================================================================
    //                                                                           Good Luck
    //                                                                           =========
    /**
     * Extract Animal's bark() process as BarkingProcess class to also avoid large abstract class. <br>
     * (抽象クラス肥大化を抑制するためにも、Animalのbark()のプロセス(処理)をBarkingProcessクラスとして切り出しましょう)
     */
    public void test_objectOriented_writing_withDelegation() {
        // your confirmation code here
        Animal dog = new Dog();
        BarkedSound sound = dog.bark();
        String sea = sound.getBarkWord();
        log(sea); // wan になるはず
        // なった
    }

    /**
     * Put barking-related classes, such as BarkingProcess and BarkedSound, into sub-package. <br>
     * (BarkingProcessやBarkedSoundなど、barking関連のクラスをサブパッケージにまとめましょう)
     * <pre>
     * e.g.
     *  objanimal
     *   |-barking
     *   |  |-BarkedSound.java
     *   |  |-BarkingProcess.java
     *   |-loud
     *   |-runner
     *   |-Animal.java
     *   |-Cat.java
     *   |-Dog.java
     *   |-...
     * </pre>
     */
    public void test_objectOriented_writing_withPackageRefactoring() {
        // your confirmation code here
        Animal dog = new Dog();
        BarkedSound dogSound = dog.bark();
        String sea = dogSound.getBarkWord();
        log(sea); // wan になるはず
        Animal zombie = new Zombie();
        BarkedSound zombieSound = zombie.bark();
        String land = zombieSound.getBarkWord();
        log(land); // uooo になるはず
        log(((Zombie)zombie).getZombieDiary().getBreatheInCount()); // 1 になるはず
        // なった
        // done m.inoue BarkingProcess を barking パッケージに移動すると、animal.breatheIn() にアクセスできない問題発生する (2024/10/05)
        // 解決策として、breatheIn() を protected から public にする方法しか思いつかないのでそうしたけど
        // 外部から downHitPoint() を実行できて hitPoint が変更できるのでそれは微妙すぎる
        // done mayukorin [ふぉろー] yes, その点を微妙すぎると表現する感覚はとても素晴らしい by jflute (2024/10/08)
        // done m.inoue 各barking methodの中で、animalのインスタンス変数に依存しない処理（logger.debug）だけ BarkingProcess に切り出し、
        //  それをAnimalの各barking methodで呼び出すようにした
        //  今は、各barking methodの中で、animal に依存しない処理 → animal のインスタンス変数に依存する処理 のように綺麗に分かれてるのでいけるけど、
        //  今後、依存しない処理 → 依存する処理 → 依存しない処理 → 依存する処理 のように
        //  animalのインスタンス変数に関与する処理が間に複雑に入ってきたら
        //  この方法だと可読性が下がり微妙な気がする
        //  でもこれ以外に方法思いつかないなぁ (2024/10/12)
        // done mayukorin [ふぉろー] いっぱい考えてて苦しんでて素晴らしいです(^^。良い体験できてると思います。 by jflute (2024/10/14)
        // 確かに、今bark()に流れが戻ってきてしまって、元々の目的だった「抽象クラス肥大化を抑制するためにも」が半減してしまっています。
        //
        // 二つ路線があって...
        // A. breatheIn()はあくまでAnimal, でもpublicにしないでもBarkingProcessで呼び出せる工夫をなんとかしてする
        // B. breatheIn()はコメントに「actually depends on barking」あるのでBarking依存処理ということで、
        //    BarkingProcessにまんま持っていってしまう。だが、Zombieがオーバーライドして日記を付けているので、
        //    それをどうにか工夫して維持する。(downHitPoint()も似た話でCatがオーバーライドしている)
        //
        // どっちも工夫が必要になります。
        // オブジェクト指向のトレーニングとしては "B" が勉強になるので、良ければ "B" でチャレンジしてみてみるのはどうでしょう？
        // どのみち...Bでも行き着く先は "A" と同じ悩みは出てくるような気もするので、両方苦しめてお得ですね(^^
        // なるほど...教えていただきありがとうございます！！Bで頑張ってみます！ by m.inoue (2024/10/16)
        // 一旦、BarkingProcessに、Barking依存処理・Barkingの一連の流れのメソッドを持ってきた
        // Zombie オーバーライド問題は、ZombieBarkingProcessクラスを作ることで解決させた
        // ただ、downHitPoint を今 publicにして BarkingProcessからアクセスできてる問題があるのでそれもこれから解決する必要がある。
        //
        // TODO mayukorin 修行++: "B" は自力でしっかり解決してもらいました... by jflute (2024/10/28)
        // "A" は、チェックは掛かるようになったけども、callerを意識する少々トリッキーな実装なので、
        // できれば完全解決したいところ。(隠蔽した上でBarkingProcessを満たす)
        // ただ、これはstep8をやってからチャレンジした方がわかりやすいかも。
    }

    // TODO mayukorin [読み物課題] プログラマーに求められるデザイン脳 by jflute (2024/10/29)
    // https://jflute.hatenadiary.jp/entry/20170623/desigraming
    
    // TODO mayukorin [読み物課題] 実装方法よりも機能概念を by jflute (2024/10/29)
    // https://jflute.hatenadiary.jp/entry/20110531/1306825539
    // ↑でリンクされている...
    // o 知識を得るためのプロセスを
    // o 答えよりも答えを導くプロセスを
    // の記事もぜひ(^^
    
    /**
     * Is Zombie correct as sub-class of Animal? Analyze it in thirty seconds. (thinking only) <br>
     * (ゾンビは動物クラスのサブクラスとして適切でしょうか？30秒だけ考えてみましょう (考えるだけでOK))
     */
    public void test_objectOriented_zoo() {
        // write your memo here:
        // _/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
        // is it corrent?
        // あまり適切ではない気がする？
        // ゾンビは死んでるので、生きてる動物の概念からちょいはみ出てる感ある
        // ゾンビが Animal を継承してることが原因で、
        // ゾンビ以外のサブクラスでは共通の処理としてあるので Animal に定義したいけど
        // ゾンビでその共通の処理がないため Animal に定義できない的なことが起こりそう
        // _/_/_/_/_/_/_/_/_/_/
        // TODO jflute 1on1にてバイオハザードの話をする予定 (2024/10/14)
    }
}
