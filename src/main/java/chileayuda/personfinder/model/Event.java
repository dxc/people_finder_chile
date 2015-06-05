package chileayuda.personfinder.model;

import org.apache.log4j.Logger;

/**
 * Created by teo on 05/06/15.
 */
public class Event {
    static final Logger log= Logger.getLogger(Event.class);
    public String incidentId;
    public String parentId;
    public String searchId;
    public String incidentName;
    public String shortName;
    public String date;
    public String incidenttype;
    public String latitude;
    public String longitude;
    public String private_group;
    public Boolean closed;
    public String description;
    public String street;
    public String external_report;

}
