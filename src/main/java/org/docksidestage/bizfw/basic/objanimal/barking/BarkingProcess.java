package org.docksidestage.bizfw.basic.objanimal.barking;

import org.docksidestage.bizfw.basic.objanimal.Animal;
import org.docksidestage.bizfw.basic.objanimal.DownHitPointer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author mayukorin
 */
public class BarkingProcess {

    // ===================================================================================
    //                                                                          Definition
    //                                                                          ==========
    private static final Logger logger = LoggerFactory.getLogger(Animal.class);

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    // done mayukorin protectedの方が適切かなと (サブクラスは使っても良いということで) by jflute (2024/10/28)
    // (現状だと、ZombieBarkingProcessとBarkingProcessがパッケージ分かれるとまたコンパイルエラー)
    // done mayukorin そういえば、ここもfinalが欲しいところですね by jflute (2024/10/29)
    // [1on1でのふぉろー] もし、getBarkWord()も依存解決すれば、animalへの依存を無くすことができる。
    // そうすると、完璧に Animal に依存しない BarkingProcess ができあがる。
    // Animalという概念には業務的な面で依存はしてるだろうけど、Animalの実装には依存しなくなる。
    // (Animal以外の概念がBarkingProcessを使いたくなったら使えるようになる: 実際にそれが必要かどうかは別の話だけど)
    // [思い出] getBarkWord()の依存解決させました！
    // protected final Animal animal;
    // TODO jflute 1on1にて、一杯考えて考えて最終的にすっきりとしたコードに仕上げてくれたことめちゃめちゃ褒める予定 (2024/12/26)
    private final DownHitPointer downHitPointer;

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public BarkingProcess(DownHitPointer downHitPointer) {
        // TODO mayukorin [ふぉろー] 思い出コメントアウトありがとう！勉強用なので進化の軌跡を残すとプロセスを振り返ることができますね by jflute (2024/12/26)
        // [思い出]
        // this.animal = animal;
        this.downHitPointer = downHitPointer;
    }

    // ===================================================================================
    //                                                                       barkingMethod
    //                                                                       =============
    // done mayukorin [いいね] callbackでdownHitPointを抽象化してるの素晴らしい、汎用性が増しました。 by jflute (2024/12/09)
    // downHitPointを(タイミングよく)呼び出す人と、実際に実行する人を分離することができている。
    // done mayukorin 一方で、Consumer で Animal を受け取る必要があるだろうか？callback生成側がAnimal本体なので... by jflute (2024/12/09)
    // まず、Animalを属性にもち、引数なしでdownHitPointを実行するDownHitPointerOfAnimalクラスを作成
    // BarkingProcess生成側のAnimalで、DownHitPointerOfAnimalクラスのanimalに自分自身を代入するようにした
    // このようにすれば、BarkingProcess側でAnimalを代入する必要はなくなった
    // ただしそれだと、どのクラスからでもanimalをDownHitPointerOfAnimalクラスに代入すれば、downHitPointを呼び出せてしまう
    // そこで、DownHitPointerOfAnimalクラスはpackage内でしかアクセスできないようにして、その代わりにbarkingProcessのインターフェースDownHitPointerを作成
    // 他パッケージはDownHitPointerを介してdownHitPointを呼び出すようにした
    // done jflute 1on1にてふぉろー予定 (2024/12/16)
    // フォローした。別のところに色々とコメント書いた
    public BarkedSound execute(String barkWord) {
        // done mayukorin 統一性で言うと、TicketBoothとかでthis呼び出しはしてないので、付けなくてもいいかなと by jflute (2024/12/09)
        breatheIn();
        prepareAbdominalMuscle();
        // [思い出]
        // String barkWord = animal.callGetBarkWord("BarkingProcess");
        BarkedSound barkedSound = doBark(barkWord);
        return barkedSound;
    }

    protected void breatheIn() { // actually depends on barking
        logger.debug("...Breathing in for barking"); // dummy implementation
        downHitPointer.downHitPoint();
    }

    protected void prepareAbdominalMuscle() { // also actually depends on barking
        logger.debug("...Using my abdominal muscle for barking"); // dummy implementation
        downHitPointer.downHitPoint();
    }

    protected BarkedSound doBark(String barkWord) {
        downHitPointer.downHitPoint();
        BarkedSound barkedSound = new BarkedSound(barkWord);
        return barkedSound;
    }
}
