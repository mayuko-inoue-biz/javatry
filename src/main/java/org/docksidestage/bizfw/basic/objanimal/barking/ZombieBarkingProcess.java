package org.docksidestage.bizfw.basic.objanimal.barking;

import org.docksidestage.bizfw.basic.objanimal.Animal;
import org.docksidestage.bizfw.basic.objanimal.Zombie;

// TODO mayukorin ↑importのunused by jflute (2024/10/28)
/**
 * @author mayukorin
 */
public class ZombieBarkingProcess extends BarkingProcess {

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public ZombieBarkingProcess(Zombie zombie) {
        super(zombie);
    }

    // ===================================================================================
    //                                                                       barkingMethod
    //                                                                       =============
    @Override
    protected void breatheIn() {
        super.breatheIn();
        // TODO mayukorin [いいね] コメントは素晴らしい、どうしようもないときはコメントを by jflute (2024/10/28)
        // TODO mayukorin 一方で、ConstructorでZombieのまま保持してもいいかと by jflute (2024/10/28)
        ((Zombie)animal).getZombieDiary().countBreatheIn(); // キャストできることは保証されている.コンストラクタ呼び出しでanimalにはZombieクラスが入るため
    }
}
