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
public class People {
    static final Logger log = Logger.getLogger(People.class);
    public String Id;
    public String fullname;
    public String info_location;
    public String uri;
    public String source_date;
    public String last_status;
    public String status_author;
    public String status_date;
    public String status_found;
    public String cont;
    public String last_known_location;
    public String cont_note;
    public String home_street;
    public String home_neighborhood;
    public String home_city;
    public String home_state;
    public PeopleFinder servicePeopleFinder;
    public User user;
    public Incident incident;
    public People(PeopleFinder servicePeopleFinder,
                  User user,
                  Incident incident,
                  String Id,
                  String fullname,
                  String info_location,
                  String uri,
                  String source_date,
                  String last_status,
                  String status_author,
                  String status_date,
                  String status_found,
                  String cont,
                  String last_known_location,
                  String cont_note,
                  String home_street,
                  String home_neighborhood,
                  String home_city,
                  String home_state) {
        this.servicePeopleFinder=servicePeopleFinder;
        this.user=user==null?User.noUser:user;
        this.incident=incident;
        this.Id = Id;
        this.fullname = fullname;
        this.info_location = info_location;
        this.uri = uri;
        this.source_date = source_date;
        this.last_status = last_status;
        this.status_author = status_author;
        this.status_date = status_date;
        this.status_found = status_found;
        this.cont = cont;
        this.last_known_location = last_known_location;
        this.cont_note = cont_note;
        this.home_street = home_street;
        this.home_neighborhood = home_neighborhood;
        this.home_city = home_city;
        this.home_state = home_state;
    }

    public JSONObject toJson() throws PeopleFinderException {
        return toJson(true);
    }

    public JSONObject toJson(boolean includeState) throws PeopleFinderException {
        return toJson(includeState, -1);
    }

    public JSONObject toJson(boolean includeState, int stateCount) throws PeopleFinderException {
        try {
            JSONObject PeopleJson = new JsonListMap();
            PeopleJson.put("user", user.id);
            PeopleJson.put("incident", incident.incidentId);
            PeopleJson.put("Id", Id == null ? "" : Id);
            PeopleJson.put("fullname", fullname == null ? "" : fullname);
            PeopleJson.put("info_location", info_location == null ? "" : info_location);
            PeopleJson.put("uri", uri == null ? "" : uri);
            PeopleJson.put("source_date", source_date == null ? "" : source_date);
            PeopleJson.put("last_status", last_status == null ? "" : last_status);
            PeopleJson.put("status_author", status_author == null ? "" : status_author);
            PeopleJson.put("status_date", status_date == null ? "" : status_date);
            PeopleJson.put("status_found", status_found == null ? "" : status_found);
            PeopleJson.put("cont", cont == null ? "" : cont);
            PeopleJson.put("last_known_location", last_known_location == null ? "" : last_known_location);
            PeopleJson.put("cont_note", cont_note == null ? "" : cont_note);
            PeopleJson.put("home_street", home_street == null ? "" : home_street);
            PeopleJson.put("home_neighborhood", home_neighborhood == null ? "" : home_neighborhood);
            PeopleJson.put("home_city", home_city == null ? "" : home_city);
            PeopleJson.put("home_state", home_state == null ? "" : home_state);
            return  PeopleJson;
        } catch (JSONException e) {
            e.printStackTrace();
            throw new PeopleFinderException("JSON exception in Message.toJson -" + e.getMessage());
        }
    }
    public void update(PeopleFinder servicePeopleFinder, People people) throws JSONException, PeopleFinderException {
        if(people.Id!= null);
        {
            this.Id=people.Id;
        }
        if(people.fullname!=null );
        {
            this.fullname=people.fullname;
        }
        if(people.info_location!= null  );
        {
            this.info_location=people.info_location;
        }
        if(people.uri!= null  );
        {
            this.uri=people.uri;
        }
        if(people.source_date!= null  );
        {
            this.source_date=people.source_date;
        }
        if(people.last_status!= null );
        {
            this.last_status=people.last_status;
        }
        if(people.status_author!= null  );
        {
            this.status_author=people.status_author;
        }
        if(people.status_date!= null );
        {
            this.status_date=people.status_date;
        }
        if(people.status_found!= null );
        {
            this.status_found=people.status_found;
        }
        if(people.cont!= null);
        {
            this.cont=people.cont;
        }
        if(people.last_known_location!= null);
        {
            this.last_known_location=people.last_known_location;
        }
        if(people.cont_note!= null);
        {
            this.cont_note=people.cont_note;
        }
        if(people.home_street!= null);
        {
            this.home_street=people.home_street;
        }
        if(people.home_neighborhood!= null);
        {
            this.home_neighborhood=people.home_neighborhood;
        }
        if(people.home_city!= null );
        {
            this.home_city=people.home_city;
        }
        if(people.home_state!= null );
        {
            this.home_state=people.home_state;
        }

        servicePeopleFinder.persistence.put(this);
    }

    static public People fromJson(PeopleFinder servicePeopleFinder, String agentJsonSource) throws PeopleFinderException, JSONException, ParseException, TokenizerException, ParserException {
        return fromJson(servicePeopleFinder, null,null, new JSONObject(agentJsonSource), false);
    }

    static public People fromJson(PeopleFinder servicePeopleFinder, User user,Incident incident, JSONObject agentJson) throws PeopleFinderException {
        return fromJson(servicePeopleFinder, user,incident, agentJson, false);
    }

    static public People fromJson(PeopleFinder servicePeopleFinder, JSONObject agentJson) throws PeopleFinderException, JSONException, ParseException, TokenizerException, ParserException {
        return fromJson(servicePeopleFinder, null,null, agentJson, false);
    }
    static public People fromJson(PeopleFinder servicePeopleFinder, User user, Incident incident, JSONObject agentJson, boolean update) throws PeopleFinderException {

        // Parse the users

        if(user==null)
        {
            String userId=agentJson.optString("user");
            if(userId==null || userId.trim().length()==0)
            {
                throw new PeopleFinderException("Message user id ('user') is missing");
            }
            user = servicePeopleFinder.serviceUser.getUser(userId);
            if(user==User.noUser)
            {
                throw  new PeopleFinderException("Message user id does not exist: '"+userId+"'");
            }
        }
        // Parse the event
        if(incident==null)
        {
            String incidentId=agentJson.optString("incident");
            if(incidentId==null || incidentId.trim().length()==0)
            {
                throw new PeopleFinderException("Message incident Id ('incidentId') is missing");
            }
            incident = servicePeopleFinder.serviceIncident.getIncident(user,incidentId);
            if(incident==null)
            {
                throw  new PeopleFinderException("Message incident id does not exist: '"+incident+"'");
            }
        }

        // Parse the People Id
        String PeopleId=agentJson.optString("Id",null);
        if(!update &&(PeopleId==null) || PeopleId.trim().length()==0)
        {
            PeopleId="";
        }
        // Parse the People fullname
        String Peoplefullname=agentJson.optString("fullname",null);
        if(!update &&(Peoplefullname==null) || Peoplefullname.trim().length()==0)
        {
            Peoplefullname="";
        }

        // Parse the People info_location
        String Peopleinfo_location=agentJson.optString("info_location",null);
        if(!update &&(Peopleinfo_location==null) || Peopleinfo_location.trim().length()==0)
        {
            Peopleinfo_location="";
        }
        // Parse the People uri
        String Peopleuri=agentJson.optString("uri",null);
        if(!update &&(Peopleuri==null) || Peopleuri.trim().length()==0)
        {
            Peopleuri="";
        }
        // Parse the People source_date
        String Peoplesource_date=agentJson.optString("source_date",null);
        if(!update &&(Peoplesource_date==null) || Peoplesource_date.trim().length()==0)
        {
            Peoplesource_date="";
        }

        // Parse the People last_status
        String Peoplelast_status=agentJson.optString("last_status",null);
        if(!update &&(Peoplelast_status==null) || Peoplelast_status.trim().length()==0)
        {
            Peoplelast_status="";
        }
        // Parse the People status_author
        String Peoplestatus_author=agentJson.optString("status_author",null);
        if(!update &&(Peoplestatus_author==null) || Peoplestatus_author.trim().length()==0)
        {
            Peoplestatus_author="";
        }
        // Parse the People status_date
        String Peoplestatus_date=agentJson.optString("status_date",null);
        if(!update &&(Peoplestatus_date==null) || Peoplestatus_date.trim().length()==0)
        {
            Peoplestatus_date="";
        }

        // Parse the People status_found
        String Peoplestatus_found=agentJson.optString("status_found",null);
        if(!update &&(Peoplestatus_found==null) || Peoplestatus_found.trim().length()==0)
        {
            Peoplestatus_found="";
        }
        // Parse the People cont
        String Peoplecont=agentJson.optString("cont",null);
        if(!update &&(Peoplecont==null) || Peoplecont.trim().length()==0)
        {
            Peoplecont="";
        }
        // Parse the People last_known_location
        String Peoplelast_known_locationt=agentJson.optString("last_known_location",null);
        if(!update &&(Peoplelast_known_locationt==null) || Peoplelast_known_locationt.trim().length()==0)
        {
            Peoplelast_known_locationt="";
        }
        // Parse the People cont_note
        String Peoplecont_note=agentJson.optString("cont_note",null);
        if(!update &&(Peoplecont_note==null) || Peoplecont_note.trim().length()==0)
        {
            Peoplecont_note="";
        }
        // Parse the People home_street
        String Peoplehome_street=agentJson.optString("home_street",null);
        if(!update &&(Peoplehome_street==null) || Peoplehome_street.trim().length()==0)
        {
            Peoplehome_street="";
        }
        // Parse the People home_neighborhood
        String Peoplehome_neighborhood=agentJson.optString("home_neighborhood",null);
        if(!update &&(Peoplehome_neighborhood==null) || Peoplehome_neighborhood.trim().length()==0)
        {
            Peoplehome_neighborhood="";
        }
        // Parse the People home_city
        String Peoplehome_city=agentJson.optString("home_city",null);
        if(!update &&(Peoplehome_city==null) || Peoplehome_city.trim().length()==0)
        {
            Peoplehome_city="";
        }
        // Parse the People home_state
        String Peoplehome_state=agentJson.optString("home_state",null);
        if(!update &&(Peoplehome_state==null) || Peoplehome_state.trim().length()==0)
        {
            Peoplehome_state="";
        }
        // Validate keys
        JsonUtils.validateKeys(agentJson, "People Message", new ArrayList<String>(Arrays.asList(
                "user","incident", "Id", "fullname", "info_location", "uri", "source_date",
                "last_status", "status_author", "status_date", "status_found",
                "cont", "last_known_location", "cont_note", "home_street","home_neighborhood","home_city","home_state")));


        People agentPeople = new People(servicePeopleFinder,user,incident,PeopleId, Peoplefullname, Peopleinfo_location, Peopleuri, Peoplesource_date,
                Peoplelast_status, Peoplestatus_author, Peoplestatus_date, Peoplestatus_found,
                Peoplecont, Peoplelast_known_locationt, Peoplecont_note, Peoplehome_street,Peoplehome_neighborhood,Peoplehome_city,Peoplehome_state);

        // Return the new people
        return agentPeople;


    }

    public String toString() {
        try {
            return toJson().toString();
        } catch (PeopleFinderException e) {
            log.info("Unable to output People as string - " + e.getMessage());
            e.printStackTrace();
            return "[People: Unable to output People as string - " + e.getMessage();
        }
    }
}
