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
    protected final Animal animal;
    private final DownHitPointer downHitPointer;

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public BarkingProcess(Animal animal, DownHitPointer downHitPointer) {
        this.animal = animal;
        this.downHitPointer = downHitPointer;
    }

    // ===================================================================================
    //                                                                       barkingMethod
    //                                                                       =============
    // done mayukorin [いいね] callbackでdownHitPointを抽象化してるの素晴らしい、汎用性が増しました。 by jflute (2024/12/09)
    // downHitPointを(タイミングよく)呼び出す人と、実際に実行する人を分離することができている。
    // TODO done mayukorin 一方で、Consumer で Animal を受け取る必要があるだろうか？callback生成側がAnimal本体なので... by jflute (2024/12/09)
    // まず、Animalを属性にもち、引数なしでdownHitPointを実行するDownHitPointerOfAnimalクラスを作成
    // BarkingProcess生成側のAnimalで、DownHitPointerOfAnimalクラスのanimalに自分自身を代入するようにした
    // このようにすれば、BarkingProcess側でAnimalを代入する必要はなくなった
    // ただしそれだと、どのクラスからでもanimalをDownHitPointerOfAnimalクラスに代入すれば、downHitPointを呼び出せてしまう
    // そこで、DownHitPointerOfAnimalクラスはpackage内でしかアクセスできないようにして、その代わりにbarkingProcessのインターフェースDownHitPointerを作成
    // 他パッケージはDownHitPointerを介してdownHitPointを呼び出すようにした
    public BarkedSound execute() {
        // TODO done mayukorin 統一性で言うと、TicketBoothとかでthis呼び出しはしてないので、付けなくてもいいかなと by jflute (2024/12/09)
        breatheIn();
        prepareAbdominalMuscle();
        String barkWord = animal.callGetBarkWord("BarkingProcess");
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
