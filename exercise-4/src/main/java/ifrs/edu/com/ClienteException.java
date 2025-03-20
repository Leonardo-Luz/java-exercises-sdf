package ifrs.edu.com;

public class ClienteException extends RuntimeException {
    public ClienteException() {
        super();
    }

    public ClienteException(String message) {
        super(message);
    }

    public ClienteException(String message, Throwable cause) {
        super(message, cause);
    }

    public ClienteException(Throwable cause) {
        super(cause);
    }
}

