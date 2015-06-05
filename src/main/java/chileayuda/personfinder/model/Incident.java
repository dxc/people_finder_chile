package chileayuda.personfinder.model;

import chileayuda.personfinder.service.PeopleFinder;
import chileayuda.personfinder.utils.JsonListMap;
import chileayuda.personfinder.utils.JsonUtils;
import chileayuda.personfinder.utils.config.ParserException;
import chileayuda.personfinder.utils.config.PeopleFinderException;
import chileayuda.personfinder.utils.config.TokenizerException;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by teo on 05/06/15.
 */
public class Incident {
    static final Logger log = Logger.getLogger(Incident.class);
    public String incidentId;
    public String parentId;
    public String searchId;
    public String incidentName;
    public String shortName;
    public String date;
    public String incidenttype;
    public String latitude;
    public String longitude;
    public String privategroup;
    public String closed;
    public String description;
    public String street;
    public String externalreport;
    public PeopleFinder servicePeopleFinder;
    public User user;

    public Incident(PeopleFinder servicePeopleFinder,
                    User user,
                    String incidentId,
                    String parentId,
                    String searchId,
                    String incidentName,
                    String shortName,
                    String date,
                    String incidenttype,
                    String latitude,
                    String longitude,
                    String privategroup,
                    String closed,
                    String description,
                    String street,
                    String externalreport) {
        this.servicePeopleFinder = servicePeopleFinder;
        this.user = user == null ? User.noUser : user;
        this.incidentId = incidentId;
        this.parentId = parentId;
        this.searchId = searchId;
        this.incidentName = incidentName;
        this.shortName = shortName;
        this.date = date;
        this.incidenttype = incidenttype;
        this.latitude = latitude;
        this.longitude = longitude;
        this.privategroup = privategroup;
        this.closed = closed;
        this.description = description;
        this.street = street;
        this.externalreport = externalreport;
    }
    public JSONObject toJson() throws PeopleFinderException {
        return toJson(true);
    }

    public JSONObject toJson(boolean includeState) throws PeopleFinderException {
        return toJson(includeState, -1);
    }
    public JSONObject toJson(boolean includeState, int stateCount) throws PeopleFinderException {
        try {

            JSONObject IncidentJson = new JsonListMap();
            IncidentJson.put("user", user.id);
            IncidentJson.put("incidentId", incidentId == null ? "" : incidentId);
            IncidentJson.put("parentId", parentId == null ? "" : parentId);
            IncidentJson.put("searchId", searchId == null ? "" : searchId);
            IncidentJson.put("incidentName", incidentName == null ? "" : incidentName);
            IncidentJson.put("shortName", shortName == null ? "" : shortName);
            IncidentJson.put("date", date == null ? "" : date);
            IncidentJson.put("incidenttype", incidenttype == null ? "" : incidenttype);
            IncidentJson.put("latitude", latitude == null ? "" : latitude);
            IncidentJson.put("longitude", longitude == null ? "" : longitude);
            IncidentJson.put("privategroup", privategroup == null ? "" : privategroup);
            IncidentJson.put("closed", closed == null ? "" : closed);
            IncidentJson.put("description", description == null ? "" : description);
            IncidentJson.put("street", street == null ? "" : street);
            IncidentJson.put("externalreport", externalreport == null ? "" : externalreport);

            return  IncidentJson;
        } catch (JSONException e) {
            e.printStackTrace();
            throw new PeopleFinderException("JSON exception in Message.toJson -" + e.getMessage());
        }
    }
    public void update(PeopleFinder servicePeopleFinder, Incident incident) throws JSONException, PeopleFinderException {

        if (incident.incidentId != null) ;
        {
            this.incidentId = incident.incidentId;
        }

        if (incident.parentId != null) ;
        {
            this.parentId = incident.parentId;
        }
        if (incident.searchId != null) ;
        {
            this.searchId = incident.searchId;
        }
        if (incident.incidentName != null) ;
        {
            this.incidentName = incident.incidentName;
        }
        if (incident.shortName != null) ;
        {
            this.shortName = incident.shortName;
        }
        if (incident.date != null) ;
        {
            this.date = incident.date;
        }
        if (incident.incidenttype != null) ;
        {
            this.incidenttype = incident.incidenttype;
        }
        if (incident.latitude != null) ;
        {
            this.latitude = incident.latitude;
        }
        if (incident.longitude != null) ;
        {
            this.longitude = incident.longitude;
        }
        if (incident.privategroup != null) ;
        {
            this.privategroup = incident.privategroup;
        }
        if (incident.closed != null) ;
        {
            this.closed = incident.closed;
        }
        if (incident.description != null) ;
        {
            this.description = incident.description;
        }
        if (incident.street != null) ;
        {
            this.street = incident.street;
        }
        if (incident.externalreport != null) ;
        {
            this.externalreport = incident.externalreport;
        }
        servicePeopleFinder.persistence.put(this);
    }
    static public Incident fromJson(PeopleFinder servicePeopleFinder, String IncidentJsonSource) throws PeopleFinderException, JSONException, ParseException, TokenizerException, ParserException {
        return fromJson(servicePeopleFinder, null, new JSONObject(IncidentJsonSource), false);
    }

    static public Incident fromJson(PeopleFinder servicePeopleFinder, User user, JSONObject IncidentJson) throws PeopleFinderException {
        return fromJson(servicePeopleFinder, user, IncidentJson, false);
    }

    static public Incident fromJson(PeopleFinder servicePeopleFinder, JSONObject IncidentJson) throws PeopleFinderException, JSONException, ParseException, TokenizerException, ParserException {
        return fromJson(servicePeopleFinder, null, IncidentJson, false);
    }
    static public Incident fromJson(PeopleFinder servicePeopleFinder, User user, JSONObject IncidentJson, boolean update) throws PeopleFinderException {
           // Parse the users
        if (user == null) {
            String userId = IncidentJson.optString("user");
            if (userId == null || userId.trim().length() == 0) {
                throw new PeopleFinderException("Message user id ('user') is missing");
            }
            user = servicePeopleFinder.serviceUser.getUser(userId);
            if (user == User.noUser) {
                throw new PeopleFinderException("Message user id does not exist: '" + userId + "'");
            }
        }
        // Parse the Incident incidentId
        String IncidentincidentId=IncidentJson.optString("incidentId",null);
        if(!update &&(IncidentincidentId==null) || IncidentincidentId.trim().length()==0)
        {
            IncidentincidentId="";
        }
        // Parse the Incident parentId,
        String IncidentparentId=IncidentJson.optString("parentId",null);
        if(!update &&(IncidentparentId==null) || IncidentparentId.trim().length()==0)
        {
            IncidentparentId="";
        }
        // Parse the Incident searchId,
        String IncidentsearchId=IncidentJson.optString("searchId",null);
        if(!update &&(IncidentsearchId==null) || IncidentsearchId.trim().length()==0)
        {
            IncidentsearchId="";
        }
        // Parse the Incident incidentName,
        String IncidentincidentName=IncidentJson.optString("incidentName",null);
        if(!update &&(IncidentincidentName==null) || IncidentincidentName.trim().length()==0)
        {
            IncidentincidentName="";
        }
        // Parse the Incident shortName,
        String IncidentshortName=IncidentJson.optString("shortName",null);
        if(!update &&(IncidentshortName==null) || IncidentshortName.trim().length()==0)
        {
            IncidentshortName="";
        }
        // Parse the Incident date,
        String Incidentdate=IncidentJson.optString("date",null);
        if(!update &&(Incidentdate==null) || Incidentdate.trim().length()==0)
        {
            Incidentdate="";
        }
        // Parse the Incident incidenttype,
        String Incidentincidenttype=IncidentJson.optString("incidenttype",null);
        if(!update &&(Incidentincidenttype==null) || Incidentincidenttype.trim().length()==0)
        {
            Incidentincidenttype="";
        }
        // Parse the Incident latitude,
        String Incidentlatitude=IncidentJson.optString("latitude",null);
        if(!update &&(Incidentlatitude==null) || Incidentlatitude.trim().length()==0)
        {
            Incidentlatitude="";
        }
        // Parse the Incident longitude,
        String Incidentlongitude=IncidentJson.optString("longitude",null);
        if(!update &&(Incidentlongitude==null) || Incidentlongitude.trim().length()==0)
        {
            Incidentlongitude ="";
        }
        // Parse the Incident privategroup,
        String Incidentprivategroup=IncidentJson.optString("privategroup",null);
        if(!update &&(Incidentprivategroup==null) || Incidentprivategroup.trim().length()==0)
        {
            Incidentprivategroup="";
        }
        // Parse the Incident closed,
        String Incidentclosed=IncidentJson.optString("closed",null);
        if(!update &&(Incidentclosed==null) || Incidentclosed.trim().length()==0)
        {
            Incidentclosed="";
        }
        // Parse the Incident description,
        String Incidentdescription=IncidentJson.optString("description",null);
        if(!update &&(Incidentdescription==null) || Incidentdescription.trim().length()==0)
        {
            Incidentdescription="";
        }
        // Parse the Incident street,
        String Incidentstreet=IncidentJson.optString("street",null);
        if(!update &&(Incidentstreet==null) || Incidentstreet.trim().length()==0)
        {
            Incidentstreet="";
        }
        // Parse the Incident externalreport
        String Incidentexternalreport=IncidentJson.optString("externalreport",null);
        if(!update &&(Incidentexternalreport==null) || Incidentexternalreport.trim().length()==0)
        {
            Incidentexternalreport="";
        }
        // Validate keys
        JsonUtils.validateKeys(IncidentJson, "Incident Message", new ArrayList<String>(Arrays.asList(
                "user", "incidentId", "parentId", "searchId", "incidentName", "shortName",
                "date", "incidenttype", "latitude", "longitude",
                "privategroup", "closed", "description", "street", "externalreport" )));


        Incident agentIncident = new Incident(servicePeopleFinder,user,  IncidentincidentId, IncidentparentId, IncidentsearchId, IncidentincidentName, IncidentshortName,
                Incidentdate, Incidentincidenttype, Incidentlatitude, Incidentlongitude,
                Incidentprivategroup, Incidentclosed, Incidentdescription, Incidentstreet, Incidentexternalreport );

        // Return the new Incident
        return agentIncident;
    }
    public String toString() {
        try {
            return toJson().toString();
        } catch (PeopleFinderException e) {
            log.info("Unable to output Incident as string - " + e.getMessage());
            e.printStackTrace();
            return "[Incident: Unable to output Incident as string - " + e.getMessage();
        }
    }
}
