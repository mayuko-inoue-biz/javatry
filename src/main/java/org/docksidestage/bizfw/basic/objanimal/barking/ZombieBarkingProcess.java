package org.docksidestage.bizfw.basic.objanimal.barking;

import org.docksidestage.bizfw.basic.objanimal.Animal;
import org.docksidestage.bizfw.basic.objanimal.Zombie;

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
        ((Zombie)animal).getZombieDiary().countBreatheIn(); // キャストできることは保証されている.コンストラクタ呼び出しでanimalにはZombieクラスが入るため
    }
}
