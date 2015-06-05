package chileayuda.personfinder.utils.config;

/**
 * Created by teo on 05/06/15.
 */

public class ParserException extends Exception {
    static final long serialVersionUID = 1L;

    public ParserException(String message) {
        super(message);
    }

    public ParserException(String message, Token token) {
        super(message + " - Line " + token.lineNumber + " Position " + token.position);
    }
}