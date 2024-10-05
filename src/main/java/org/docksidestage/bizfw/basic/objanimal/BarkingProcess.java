package org.docksidestage.bizfw.basic.objanimal;

/**
 * @author mayukorin
 */
public class BarkingProcess {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    private final Animal animal;

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public BarkingProcess(Animal animal) {
        this.animal = animal;
    }

    public BarkedSound execute() {
        animal.breatheIn();
        animal.prepareAbdominalMuscle();
        String barkWord = animal.getBarkWord();
        BarkedSound barkedSound = animal.doBark(barkWord);
        return barkedSound;
    }

}
