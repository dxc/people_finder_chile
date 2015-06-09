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
    public String person_record_id;
    public String entry_date;
    public String expiry_date;
    public String author_name;
    public String author_email;
    public String source_name;
    public String source_date;
    public String source_url;
    public String full_name;
    public String given_name;
    public String family_name;
    public String alternate_names;
    public String sex;
    public String age;
    public String home_street;
    public String home_city;
    public String description;
    public String photo_url;
    public PeopleFinder servicePeopleFinder;
    public User user;
    public Incident incident;

    public People(PeopleFinder servicePeopleFinder,
                  User user,
                  Incident incident,
                  String person_record_id,
                  String entry_date,
                  String expiry_date,
                  String author_name,
                  String author_email,
                  String source_name,
                  String source_date,
                  String source_url,
                  String full_name,
                  String given_name,
                  String family_name,
                  String alternate_names,
                  String sex,
                  String age,
                  String home_street,
                  String home_city,
                  String description,
                  String photo_url
    ) {
        this.servicePeopleFinder = servicePeopleFinder;
        this.user = user == null ? User.noUser : user;
        this.incident = incident;
        this.person_record_id = person_record_id;
        this.entry_date = entry_date;
        this.expiry_date = expiry_date;
        this.author_name = author_name;
        this.author_email = author_email;
        this.source_name = source_name;
        this.source_date = source_date;
        this.source_url = source_url;
        this.full_name = full_name;
        this.given_name = given_name;
        this.family_name = family_name;
        this.alternate_names = alternate_names;
        this.sex = sex;
        this.age = age;
        this.home_street = home_street;
        this.home_city = home_city;
        this.description = description;
        this.photo_url = photo_url;
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
            PeopleJson.put("person_record_id", person_record_id == null ? "" : person_record_id);
            PeopleJson.put("entry_date", entry_date == null ? "" : entry_date);
            PeopleJson.put("expiry_date", expiry_date == null ? "" : expiry_date);
            PeopleJson.put("author_name", author_name == null ? "" : author_name);
            PeopleJson.put("author_email", author_email == null ? "" : author_email);
            PeopleJson.put("source_name", source_name == null ? "" : source_name);
            PeopleJson.put("source_date", source_date == null ? "" : source_date);
            PeopleJson.put("source_url", source_url == null ? "" : source_url);
            PeopleJson.put("full_name", full_name == null ? "" : full_name);
            PeopleJson.put("given_name", given_name == null ? "" : given_name);
            PeopleJson.put("family_name", family_name == null ? "" : family_name);
            PeopleJson.put("alternate_names", alternate_names == null ? "" : alternate_names);
            PeopleJson.put("sex", sex == null ? "" : sex);
            PeopleJson.put("age", age == null ? "" : age);
            PeopleJson.put("home_street", home_street == null ? "" : home_street);
            PeopleJson.put("home_city", home_city == null ? "" : home_city);
            PeopleJson.put("description", description == null ? "" : description);
            PeopleJson.put("photo_url", photo_url == null ? "" : photo_url);


            return PeopleJson;
        } catch (JSONException e) {
            e.printStackTrace();
            throw new PeopleFinderException("JSON exception in Message.toJson -" + e.getMessage());
        }
    }

    public void update(PeopleFinder servicePeopleFinder, People people) throws JSONException, PeopleFinderException {
        if (people.person_record_id != null) ;
        {
            this.person_record_id = people.person_record_id;
        }
        if (people.entry_date != null) ;
        {
            this.entry_date = people.entry_date;
        }
        if (people.expiry_date != null) ;
        {
            this.expiry_date = people.expiry_date;
        }
        if (people.author_name != null) ;
        {
            this.author_name = people.author_name;
        }
        if (people.author_email != null) ;
        {
            this.author_email = people.author_email;
        }
        if (people.source_name != null) ;
        {
            this.source_name = people.source_name;
        }
        if (people.source_date != null) ;
        {
            this.source_date = people.source_date;
        }
        if (people.source_url != null) ;
        {
            this.source_url = people.source_url;
        }
        if (people.full_name != null) ;
        {
            this.full_name = people.full_name;
        }
        if (people.given_name != null) ;
        {
            this.given_name = people.given_name;
        }
        if (people.family_name != null) ;
        {
            this.family_name = people.family_name;
        }
        if (people.alternate_names != null) ;
        {
            this.alternate_names = people.alternate_names;
        }
        if (people.sex != null) ;
        {
            this.sex = people.sex;
        }
        if (people.age != null) ;
        {
            this.age = people.age;
        }
        if (people.home_street != null) ;
        {
            this.home_street = people.home_street;
        }
        if (people.home_city != null) ;
        {
            this.home_city = people.home_city;
        }
        if (people.description != null) ;
        {
            this.description = people.description;
        }
        if (people.photo_url != null) ;
        {
            this.photo_url = people.photo_url;
        }

        servicePeopleFinder.persistence.put(this);
    }

    static public People fromJson(PeopleFinder servicePeopleFinder, String agentJsonSource) throws PeopleFinderException, JSONException, ParseException, TokenizerException, ParserException {
        return fromJson(servicePeopleFinder, null, null, new JSONObject(agentJsonSource), false);
    }

    static public People fromJson(PeopleFinder servicePeopleFinder, User user, Incident incident, JSONObject agentJson) throws PeopleFinderException {
        return fromJson(servicePeopleFinder, user, incident, agentJson, false);
    }

    static public People fromJson(PeopleFinder servicePeopleFinder, JSONObject agentJson) throws PeopleFinderException, JSONException, ParseException, TokenizerException, ParserException {
        return fromJson(servicePeopleFinder, null, null, agentJson, false);
    }

    static public People fromJson(PeopleFinder servicePeopleFinder, User user, Incident incident, JSONObject agentJson, boolean update) throws PeopleFinderException {

        // Parse the users

        if (user == null) {
            String userId = agentJson.optString("user");
            if (userId == null || userId.trim().length() == 0) {
                throw new PeopleFinderException("Message user id ('user') is missing");
            }
            user = servicePeopleFinder.serviceUser.getUser(userId);
            if (user == User.noUser) {
                throw new PeopleFinderException("Message user id does not exist: '" + userId + "'");
            }
        }
        // Parse the event
        if (incident == null) {
            String incidentId = agentJson.optString("incident");
            if (incidentId == null || incidentId.trim().length() == 0) {
                throw new PeopleFinderException("Message incident Id ('incidentId') is missing");
            }
            incident = servicePeopleFinder.serviceIncident.getIncident(user, incidentId);
            if (incident == null) {
                throw new PeopleFinderException("Message incident id does not exist: '" + incident + "'");
            }
        }

        // Parse the People person_record_id
        String Peopleperson_record_id= agentJson.optString("person_record_id", null);
        if (!update && (Peopleperson_record_id == null) || Peopleperson_record_id.trim().length() == 0) {
            Peopleperson_record_id = "";
        }

        // Parse the People  entry_date;
        String Peopleentry_date= agentJson.optString("entry_date", null);
        if (!update && ( Peopleentry_date== null) || Peopleentry_date.trim().length() == 0) {
            Peopleentry_date = "";
        }
        // Parse the People  expiry_date;
        String Peopleexpiry_date= agentJson.optString("expiry_date", null);
        if (!update && ( Peopleexpiry_date== null) || Peopleexpiry_date.trim().length() == 0) {
            Peopleexpiry_date = "";
        }
        // Parse the People  author_name;
        String Peopleauthor_name= agentJson.optString("author_name", null);
        if (!update && ( Peopleauthor_name== null) || Peopleauthor_name.trim().length() == 0) {
            Peopleauthor_name= "";
        }
        // Parse the People  author_email;
        String Peopleauthor_email= agentJson.optString("author_email", null);
        if (!update && (Peopleauthor_email == null) || Peopleauthor_email.trim().length() == 0) {
            Peopleauthor_email = "";
        }
        // Parse the People  source_name;
        String Peoplesource_name= agentJson.optString("source_name", null);
        if (!update && (Peoplesource_name == null) || Peoplesource_name.trim().length() == 0) {
            Peoplesource_name= "";
        }
        // Parse the People  source_date;
        String Peoplesource_date= agentJson.optString("source_date", null);
        if (!update && ( Peoplesource_date== null) || Peoplesource_date.trim().length() == 0) {
            Peoplesource_date= "";
        }
        // Parse the People  source_url;
        String Peoplesource_url= agentJson.optString("source_url", null);
        if (!update && (Peoplesource_url == null) || Peoplesource_url.trim().length() == 0) {
            Peoplesource_url= "";
        }
        // Parse the People  full_name;
        String Peoplefull_name= agentJson.optString("full_name", null);
        if (!update && ( Peoplefull_name== null) || Peoplefull_name.trim().length() == 0) {
            Peoplefull_name = "";
        }
        // Parse the People  given_name;
        String Peoplegiven_name= agentJson.optString("given_name", null);
        if (!update && (Peoplegiven_name == null) || Peoplegiven_name.trim().length() == 0) {
            Peoplegiven_name= "";
        }
        // Parse the People  family_name;
        String Peoplefamily_name= agentJson.optString("family_name", null);
        if (!update && ( Peoplefamily_name== null) || Peoplefamily_name.trim().length() == 0) {
            Peoplefamily_name= "";
        }
        // Parse the People  alternate_names;
        String Peoplealternate_names= agentJson.optString("alternate_names", null);
        if (!update && ( Peoplealternate_names== null) || Peoplealternate_names.trim().length() == 0) {
            Peoplealternate_names= "";
        }
        // Parse the People  sex;
        String Peoplesex= agentJson.optString("sex", null);
        if (!update && ( Peoplesex== null) || Peoplesex.trim().length() == 0) {
            Peoplesex= "";
        }
        // Parse the People  age;
        String Peopleage= agentJson.optString("age", null);
        if (!update && ( Peopleage== null) || Peopleage.trim().length() == 0) {
            Peopleage= "";
        }
        // Parse the People  home_street;
        String Peoplehome_street= agentJson.optString("home_street", null);
        if (!update && ( Peoplehome_street== null) || Peoplehome_street.trim().length() == 0) {
            Peoplehome_street= "";
        }
        // Parse the People  home_city;
        String Peoplehome_city= agentJson.optString("home_city", null);
        if (!update && ( Peoplehome_city== null) || Peoplehome_city.trim().length() == 0) {
            Peoplehome_city = "";
        }
        // Parse the People  description;
        String Peopledescription= agentJson.optString("description", null);
        if (!update && ( Peopledescription== null) || Peopledescription.trim().length() == 0) {
            Peopledescription= "";
        }
        // Parse the People  photo_url;
        String Peoplephoto_url= agentJson.optString("photo_url", null);
        if (!update && ( Peoplephoto_url== null) || Peoplephoto_url.trim().length() == 0) {
            Peoplephoto_url= "";
        }

        // Validate keys
        JsonUtils.validateKeys(agentJson, "People Message", new ArrayList<String>(Arrays.asList(
                "user", "incident", "person_record_id", "entry_date", "expiry_date", "author_name", "author_email",
                "source_name", "source_date", "source_url", "full_name",
                "given_name", "family_name", "alternate_names", "sex", "age", "home_street", "home_city","description","photo_url")));


        People agentPeople = new People(servicePeopleFinder, user, incident,  Peopleperson_record_id, Peopleentry_date, Peopleexpiry_date, Peopleauthor_name, Peopleauthor_email,
                Peoplesource_name, Peoplesource_date, Peoplesource_url, Peoplefull_name,
                Peoplegiven_name, Peoplefamily_name, Peoplealternate_names, Peoplesex, Peopleage, Peoplehome_street, Peoplehome_city,Peopledescription,Peoplephoto_url);

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
