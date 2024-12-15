package org.docksidestage.bizfw.basic.objanimal;

/**
 * @author mayukorin
 */
class DownHitPointerOfAnimal implements DownHitPointer {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    private final Animal animal;

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public DownHitPointerOfAnimal(Animal animal) {
        this.animal = animal;
    }

    // ===================================================================================
    //                                                                           Hit Point
    //                                                                           =========
    @Override
    public void downHitPoint() {
        animal.downHitPoint();
    }
}
