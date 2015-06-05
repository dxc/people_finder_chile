package chileayuda.personfinder.utils.config;

import org.springframework.stereotype.Component;

/**
 * Created by teo on 05/06/15.
 */
@Component
public class ReadPropierties {
    public PeoplefinderProperties getProperties() {
        return properties;
    }

 PeoplefinderProperties properties;
    public void setProperties(PeoplefinderProperties properties)
    {
        this.properties = properties;
    }
}
