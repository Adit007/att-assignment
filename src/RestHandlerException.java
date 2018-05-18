/**
 *
 * This is a generic exception handler class that will be inherited by specific custom exceptions.
 *
 */
public class RestHandlerException extends Exception {

    private static final long serialVersionUID = 8021138033811646269L;

    private int errorCode = 200;

    public RestHandlerException(final String msg) {
        super(msg);
        return;
    }

    public RestHandlerException(final String msg, final Throwable cause) {
        super(msg, cause);
        return;
    }

    protected RestHandlerException(final String msg, final int errorCode) {
        super(msg);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
