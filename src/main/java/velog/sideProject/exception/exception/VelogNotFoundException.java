package velog.sideProject.exception.exception;

/**
 * 조회 되어야 하지만 조회되지 않았을 때 사용
 */
public class VelogNotFoundException extends RuntimeException{

    public VelogNotFoundException() {
        super();
    }

    public VelogNotFoundException(String message) {
        super(message);
    }

    public VelogNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public VelogNotFoundException(Throwable cause) {
        super(cause);
    }

    protected VelogNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
