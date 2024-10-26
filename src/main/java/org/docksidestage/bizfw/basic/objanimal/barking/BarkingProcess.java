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
    Animal animal;

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public BarkingProcess(Animal animal) {
        this.animal = animal;
    }

    // ===================================================================================
    //                                                                       barkingMethod
    //                                                                       =============
    public BarkedSound bark() {
        this.breatheIn();
        this.prepareAbdominalMuscle();
        String barkWord = animal.getBarkWord();
        BarkedSound barkedSound = this.doBark(barkWord);
        return barkedSound;
    }

    protected void breatheIn() { // actually depends on barking
        logger.debug("...Breathing in for barking"); // dummy implementation
        animal.downHitPoint();
    }

    protected void prepareAbdominalMuscle() { // also actually depends on barking
        logger.debug("...Using my abdominal muscle for barking"); // dummy implementation
        animal.downHitPoint();
    }

    protected BarkedSound doBark(String barkWord) {
        animal.downHitPoint();
        BarkedSound barkedSound = new BarkedSound(barkWord);;
        return barkedSound;
    }
}
