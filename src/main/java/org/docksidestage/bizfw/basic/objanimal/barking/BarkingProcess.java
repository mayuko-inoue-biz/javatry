package org.docksidestage.bizfw.basic.objanimal.barking;

import java.util.function.Consumer;

import org.docksidestage.bizfw.basic.objanimal.Animal;
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

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public BarkingProcess(Animal animal) {
        this.animal = animal;
    }

    // ===================================================================================
    //                                                                       barkingMethod
    //                                                                       =============
    public BarkedSound execute(Consumer<Animal> downHitPointFunc) {
        this.breatheIn(downHitPointFunc);
        this.prepareAbdominalMuscle(downHitPointFunc);
        String barkWord = animal.callGetBarkWord("BarkingProcess");
        BarkedSound barkedSound = this.doBark(barkWord, downHitPointFunc);
        return barkedSound;
    }

    protected void breatheIn(Consumer<Animal> downHitPointFunc) { // actually depends on barking
        logger.debug("...Breathing in for barking"); // dummy implementation
        downHitPointFunc.accept(animal);
    }

    protected void prepareAbdominalMuscle(Consumer<Animal> downHitPointFunc) { // also actually depends on barking
        logger.debug("...Using my abdominal muscle for barking"); // dummy implementation
        downHitPointFunc.accept(animal);
    }

    protected BarkedSound doBark(String barkWord, Consumer<Animal> downHitPointFunc) {
        downHitPointFunc.accept(animal);
        BarkedSound barkedSound = new BarkedSound(barkWord);
        return barkedSound;
    }
}
