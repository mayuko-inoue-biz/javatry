package org.docksidestage.bizfw.basic.objanimal.barking;

import java.util.function.Consumer;

import org.docksidestage.bizfw.basic.objanimal.Animal;
import org.docksidestage.bizfw.basic.objanimal.Zombie;

// done mayukorin ↑importのunused by jflute (2024/10/28)
/**
 * @author mayukorin
 */
public class ZombieBarkingProcess extends BarkingProcess {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    // done mayukorin finalを付けちゃって良いです〜 by jflute (2024/10/29)
    protected final Zombie zombie;

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public ZombieBarkingProcess(Zombie zombie) {
        super(zombie);
        this.zombie = zombie;
    }

    // ===================================================================================
    //                                                                       barkingMethod
    //                                                                       =============
    @Override
    protected void breatheIn(Consumer<Animal> downHitPointFunc) {
        super.breatheIn(downHitPointFunc);
        // done mayukorin [いいね] コメントは素晴らしい、どうしようもないときはコメントを by jflute (2024/10/28)
        // done mayukorin 一方で、ConstructorでZombieのまま保持してもいいかと by jflute (2024/10/28)
        zombie.getZombieDiary().countBreatheIn();
    }
}
