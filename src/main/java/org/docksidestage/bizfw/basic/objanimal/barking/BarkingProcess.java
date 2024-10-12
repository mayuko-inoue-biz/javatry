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
    //                                                                       barkingMethod
    //                                                                       =============
    public static void doBreatheIn() {
        logger.debug("...Breathing in for barking"); // dummy implementation
    }

    public static void doPrepareAbdominalMuscle() {
        logger.debug("...Using my abdominal muscle for barking"); // dummy implementation
    }

    public static BarkedSound makeSounds(String barkWord) {
        return new BarkedSound(barkWord);
    }

}
