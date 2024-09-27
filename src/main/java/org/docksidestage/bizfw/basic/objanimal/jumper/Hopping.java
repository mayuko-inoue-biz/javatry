package org.docksidestage.bizfw.basic.objanimal.jumper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Hopping implements HighJumper {

    // ===================================================================================
    //                                                                          Definition
    //                                                                          ==========
    private static final Logger logger = LoggerFactory.getLogger(Hopping.class);

    // ===================================================================================
    //                                                                              jumper
    //                                                                          ==========
    @Override
    public void jump() {
        // dummy implementation
        logger.debug("I want to ride my hopping");
    }
}
