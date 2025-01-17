package org.docksidestage.bizfw.basic.objanimal;

// done mayukorin こちら使われなくなってしまいました。一応コメントで利用されなくなったことを記しておきましょう by jflute (2024/12/26)
// 現場でも「調べてみると実は使われてない」ってクラスよくありますからね。残すメリットもあるので残すのはアリですがコメントを。
/**
 * @author mayukorin
 * 現在未使用 (2025/01/06)
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
