package chileayuda.personfinder.utils.config;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by teo on 05/06/15.
 */
public class PeopleFinderBadRequestException extends PeopleAppServerException {
    static final long serialVersionUID = 1L;

    public PeopleFinderBadRequestException(String message)
    {
        super(HttpServletResponse.SC_BAD_REQUEST, message);
    }

}
