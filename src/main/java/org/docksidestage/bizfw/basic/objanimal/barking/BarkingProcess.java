package org.docksidestage.bizfw.basic.objanimal.barking;

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
    // TODO done mayukorin protectedの方が適切かなと (サブクラスは使っても良いということで) by jflute (2024/10/28)
    // (現状だと、ZombieBarkingProcessとBarkingProcessがパッケージ分かれるとまたコンパイルエラー)
    protected Animal animal;

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public BarkingProcess(Animal animal) {
        this.animal = animal;
    }

    // ===================================================================================
    //                                                                       barkingMethod
    //                                                                       =============
    public BarkedSound execute() {
        this.breatheIn();
        this.prepareAbdominalMuscle();
        String barkWord = animal.callGetBarkWord("BarkingProcess");
        BarkedSound barkedSound = this.doBark(barkWord);
        return barkedSound;
    }

    protected void breatheIn() { // actually depends on barking
        logger.debug("...Breathing in for barking"); // dummy implementation
        animal.callDownHitPoint("BarkingProcess");
    }

    protected void prepareAbdominalMuscle() { // also actually depends on barking
        logger.debug("...Using my abdominal muscle for barking"); // dummy implementation
        animal.callDownHitPoint("BarkingProcess");
    }

    protected BarkedSound doBark(String barkWord) {
        animal.callDownHitPoint("BarkingProcess");
        BarkedSound barkedSound = new BarkedSound(barkWord);
        return barkedSound;
    }
}
