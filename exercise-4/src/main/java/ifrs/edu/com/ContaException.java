package ifrs.edu.com;

public class ContaException extends RuntimeException {
    public ContaException() {
        super();
    }

    public ContaException(String message) {
        super(message);
    }

    public ContaException(String message, Throwable cause) {
        super(message, cause);
    }

    public ContaException(Throwable cause) {
        super(cause);
    }
}

