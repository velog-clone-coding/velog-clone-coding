package velog.sideProject.exception.exception;

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
