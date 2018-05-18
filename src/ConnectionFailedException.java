/**
 *
 * Custom exeption in case of the Http Connection is failed for some reason.
 *
 */
public class ConnectionFailedException extends RestHandlerException {

    private static final long serialVersionUID = -709351011314919912L;

    public ConnectionFailedException(final String msg, final int errorCode) {
        super(msg, errorCode);
    }

    public ConnectionFailedException(final String msg) {
        super(msg);
    }
}
