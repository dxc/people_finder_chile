package chileayuda.personfinder.utils.config;

/**
 * Created by teo on 05/06/15.
 */

    public class PeopleAppServerException extends PeopleFinderException {
        static final long serialVersionUID = 1L;
        public int statusCode;

        public PeopleAppServerException(int statusCode, String message) {
            super(message);
            this.statusCode = statusCode;
        }

    }

