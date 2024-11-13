package org.docksidestage.bizfw.basic.screw.exception;

/**
 * @author mayukorin
 */
public class SuperCarCannotProvideException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public SuperCarCannotProvideException(String msg, RuntimeException causeException) {
        super(msg, causeException);
    }
}
