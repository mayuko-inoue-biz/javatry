package org.docksidestage.bizfw.basic.screw.exception;

/**
 * @author mayukorin
 */
public class SteeringWheelCannotMakeException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    // 第二引数をScrewCannotMakeBySpecExceptionにしようと思ったが、
    // SteeringWheelCannotMakeExceptionはSteeringWheelを作る工程で常に使うExceptionとして考えていて、
    // そのExceptionとなる原因はScrewCannotMakeBySpecException以外にも様々あると思ったのでRuntimeException型にした
    public SteeringWheelCannotMakeException(String msg, RuntimeException causeException) {
        super(msg, causeException);
    }
}
