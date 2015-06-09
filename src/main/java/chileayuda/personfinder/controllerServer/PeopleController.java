package chileayuda.personfinder.controllerServer;

import chileayuda.personfinder.model.Incident;
import chileayuda.personfinder.model.People;
import chileayuda.personfinder.model.PeopleList;
import chileayuda.personfinder.model.User;
import chileayuda.personfinder.service.PeopleFinder;
import chileayuda.personfinder.utils.JsonListMap;
import chileayuda.personfinder.utils.NameValue;
import chileayuda.personfinder.utils.Utils;
import chileayuda.personfinder.utils.config.PeopleFinderException;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by teo on 05/06/15.
 */
@RestController
public class PeopleController {
    protected static Logger logger = Logger.getLogger(PeopleController.class);
    public Utils util = new Utils();

    @RequestMapping(value = "/incident/{idincident}/people", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public
    @ResponseBody
    String postPeople(@PathVariable String idincident, HttpServletRequest request) throws Exception {
        PeopleFinder servicePeopleFinder = new PeopleFinder(true);
        User users = null;
        for (NameValue<User> userIdValue : servicePeopleFinder.users) {
            User user = userIdValue.value;
            //Get all agent Instance for Users
            for (Incident incident : servicePeopleFinder.incidents.get(user.id)) {
                //Get all ScriptDefinitions por agentInstance
                if (incident.incidentId.equals(idincident)) {
                    users = servicePeopleFinder.users.get(incident.user.id);
                    break;
                }

            }
        }
        Incident incident = servicePeopleFinder.serviceIncident.getIncident(users, idincident);
        JSONObject peopleJson = util.getJsonRequest(request);
        if (peopleJson == null)
            throw new PeopleFinderException(
                    "Invalid people JSON object");
        logger.info("Adding new people for user: " + users.id);
        // Parse and add the people
        People people = servicePeopleFinder.servicePeople.addPeople(
                users, incident, peopleJson);
        // Done
        JSONObject message = new JSONObject();
        message.put("message", "Add was successful");
        return message.toString();
    }

    @RequestMapping(value = "/incident/{idincident}/people", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public
    @ResponseBody
    String getPeoples(@PathVariable String idincident) throws Exception {
        PeopleFinder servicePeopleFinder = new PeopleFinder(true);
        // Get all peoples for this user
        JSONArray peoplesArrayJson = new JSONArray();
        // Get all Peoples for all users
        for (NameValue<PeopleList> userPeoples : servicePeopleFinder.peoples) {
            // Get all peoples for this user
            for (People people : servicePeopleFinder.peoples.get(userPeoples.name)) {
                if (people.incident.incidentId.equals(idincident.trim())) {
                    JSONObject PeopleJson = new JsonListMap();
                    PeopleJson.put("person_record_id",people.person_record_id);
                    PeopleJson.put("entry_date",people. entry_date);
                    PeopleJson.put("expiry_date",people. expiry_date);
                    PeopleJson.put("author_name",people.author_name);
                    PeopleJson.put("author_email",people.author_email);
                    PeopleJson.put("source_name", people.source_name);
                    PeopleJson.put("source_date", people.source_date);
                    PeopleJson.put("source_url", people.source_url);
                    PeopleJson.put("full_name", people.full_name);
                    PeopleJson.put("given_name", people.given_name);
                    PeopleJson.put("family_name", people.family_name);
                    PeopleJson.put("alternate_names", people.alternate_names);
                    PeopleJson.put("sex", people.sex);
                    PeopleJson.put("age", people.age);
                    PeopleJson.put("home_street",people.home_street);
                    PeopleJson.put("home_city",people.home_city);
                    PeopleJson.put("description", people.description);
                    PeopleJson.put("photo_url", people.photo_url);
                    peoplesArrayJson.put(PeopleJson);

                }
            }
        }
        JSONObject peoplesJson = new JSONObject();
        peoplesJson.put("peoples", peoplesArrayJson);
        return peoplesJson.toString();
    }

    @RequestMapping(value = "/people/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public
    @ResponseBody
    String getPeoplesId(@PathVariable String id) throws Exception {
        PeopleFinder servicePeopleFinder = new PeopleFinder(true);

        JSONArray peoplesArrayJson = new JSONArray();
        // Get all Peoples for all users
        for (NameValue<PeopleList> userPeoples : servicePeopleFinder.peoples) {
            // Get all peoples for this user
            for (People people : servicePeopleFinder.peoples.get(userPeoples.name)) {
                if (people.person_record_id.equals(id.trim())) {
                    JSONObject PeopleJson = new JsonListMap();
                    PeopleJson.put("user", people.user.id);
                    PeopleJson.put("incident", people.incident.incidentId);

                    PeopleJson.put("person_record_id",people.person_record_id);
                    PeopleJson.put("entry_date",people. entry_date);
                    PeopleJson.put("expiry_date",people. expiry_date);
                    PeopleJson.put("author_name",people.author_name);
                    PeopleJson.put("author_email",people.author_email);
                    PeopleJson.put("source_name", people.source_name);
                    PeopleJson.put("source_date", people.source_date);
                    PeopleJson.put("source_url", people.source_url);
                    PeopleJson.put("full_name", people.full_name);
                    PeopleJson.put("given_name", people.given_name);
                    PeopleJson.put("family_name", people.family_name);
                    PeopleJson.put("alternate_names", people.alternate_names);
                    PeopleJson.put("sex", people.sex);
                    PeopleJson.put("age", people.age);
                    PeopleJson.put("home_street",people.home_street);
                    PeopleJson.put("home_city",people.home_city);
                    PeopleJson.put("description", people.description);
                    PeopleJson.put("photo_url", people.photo_url);


                    peoplesArrayJson.put(PeopleJson);
                    break;
                }
            }
        }
        JSONObject peoplesJson = new JSONObject();
        peoplesJson.put("peoples", peoplesArrayJson);
        return peoplesJson.toString();

    }

    @RequestMapping(value = "/peoples", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public
    @ResponseBody
    String getPeoples() throws JSONException {
        logger.info("Getting list of Peoples");
        PeopleFinder servicePeopleFinder = new PeopleFinder(true);

        JSONArray peoplesArrayJson = new JSONArray();
        // Get all Peoples for all users
        for (NameValue<PeopleList> userPeoples : servicePeopleFinder.peoples) {
            // Get all peoples for this user
            for (People people : servicePeopleFinder.peoples.get(userPeoples.name)) {
                // Generate JSON for short summary of Peoples
                JSONObject PeopleJson = new JsonListMap();
                PeopleJson.put("user", people.user.id);
                PeopleJson.put("incident", people.incident.incidentId);
                PeopleJson.put("person_record_id",people.person_record_id);
                PeopleJson.put("entry_date",people. entry_date);
                PeopleJson.put("expiry_date",people. expiry_date);
                PeopleJson.put("author_name",people.author_name);
                PeopleJson.put("author_email",people.author_email);
                PeopleJson.put("source_name", people.source_name);
                PeopleJson.put("source_date", people.source_date);
                PeopleJson.put("source_url", people.source_url);
                PeopleJson.put("full_name", people.full_name);
                PeopleJson.put("given_name", people.given_name);
                PeopleJson.put("family_name", people.family_name);
                PeopleJson.put("alternate_names", people.alternate_names);
                PeopleJson.put("sex", people.sex);
                PeopleJson.put("age", people.age);
                PeopleJson.put("home_street",people.home_street);
                PeopleJson.put("home_city",people.home_city);
                PeopleJson.put("description", people.description);
                PeopleJson.put("photo_url", people.photo_url);


                peoplesArrayJson.put(PeopleJson);
            }
        }
        JSONObject peoplesJson = new JSONObject();
        peoplesJson
                .put("peoples", peoplesArrayJson);

        return peoplesJson.toString();
    }
    @RequestMapping(value = "/personfinder", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String read()
    {
        PeopleFinder servicePeopleFinder = new PeopleFinder(true);
        return servicePeopleFinder.servicePeople.readurl().toString();

    }
}
