package org.docksidestage.bizfw.basic.objanimal;

/**
 * The object for turtle(亀).
 * @author mayukorin
 */
public class Turtle extends Animal {

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public Turtle() {
    }

    // ===================================================================================
    //                                                                                Bark
    //                                                                              ======
    protected String getBarkWord() {
        return "kyu";
    }
}
