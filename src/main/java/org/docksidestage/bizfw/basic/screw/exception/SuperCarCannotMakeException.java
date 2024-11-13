package org.docksidestage.bizfw.basic.screw.exception;

/**
 * @author mayukorin
 */
public class SuperCarCannotMakeException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public SuperCarCannotMakeException(String msg, SteeringWheelCannotMakeException causeException) {
        super(msg, causeException);
    }
}
