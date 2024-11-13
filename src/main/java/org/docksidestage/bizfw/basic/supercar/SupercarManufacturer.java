/*
 * Copyright 2019-2022 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.docksidestage.bizfw.basic.supercar;

import org.docksidestage.bizfw.basic.screw.exception.SteeringWheelCannotMakeException;
import org.docksidestage.bizfw.basic.screw.exception.SuperCarCannotMakeException;
import org.docksidestage.bizfw.basic.supercar.SupercarSteeringWheelManufacturer.SteeringWheel;

/**
 * The manufacturer(製造業者) of supercar.
 * @author jflute
 */
public class SupercarManufacturer {

    private final SupercarEasyCatalog catalog = new SupercarEasyCatalog();

    public Supercar makeSupercar(String catalogKey) {
        Integer steeringWheelId = catalog.findSteeringWheelSpecId(catalogKey);

        try {
            SupercarSteeringWheelManufacturer wheelManufacturer = createSupercarSteeringWheelManufacturer();
            SteeringWheel steeringWheel = wheelManufacturer.makeSteeringWheel(steeringWheelId);
            return new Supercar(steeringWheel);
        } catch (SteeringWheelCannotMakeException e) {
            throw new SuperCarCannotMakeException("we cannot make super car because we cannot make steering wheel: super car catalog key=" + catalogKey + ", steeringWheelId=" + steeringWheelId, e);
        }
    }

    protected SupercarSteeringWheelManufacturer createSupercarSteeringWheelManufacturer() {
        return new SupercarSteeringWheelManufacturer();
    }

    public static class Supercar {

        public Supercar(SteeringWheel steeringWheel) {
            // dummy
        }
    }
}
