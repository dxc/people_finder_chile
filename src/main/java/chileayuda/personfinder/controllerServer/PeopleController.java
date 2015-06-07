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
public class PeopleController
{
    protected static Logger logger = Logger.getLogger(PeopleController.class);
    public Utils util = new Utils();
    @RequestMapping(value = "/incident/{idincident}/people", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String postPeople(@PathVariable String idincident, HttpServletRequest request) throws Exception {
        PeopleFinder servicePeopleFinder =new PeopleFinder(true);
        User users = null;
        for (NameValue<User> userIdValue : servicePeopleFinder.users)
        {
            User user = userIdValue.value;
            //Get all agent Instance for Users
            for (Incident incident : servicePeopleFinder.incidents.get(user.id)) {
                //Get all ScriptDefinitions por agentInstance
                if(incident.incidentId.equals(idincident))
                {
                    users= servicePeopleFinder.users.get(incident.user.id);
                    break;
                }

            }
        }
        Incident incident  = servicePeopleFinder.serviceIncident.getIncident(users,idincident);
        JSONObject peopleJson = util.getJsonRequest(request);
        if (peopleJson == null)
            throw new PeopleFinderException(
                    "Invalid people JSON object");
        logger.info("Adding new people for user: " + users.id);
        // Parse and add the people
        People people = servicePeopleFinder.servicePeople.addPeople(
                users,incident, peopleJson);
        // Done
        JSONObject message = new JSONObject();
        message.put("message", "Add was successful");
        return message.toString();
    }
    @RequestMapping(value = "/incident/{idincident}/people", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String getPeoples(@PathVariable String idincident) throws Exception {
        PeopleFinder servicePeopleFinder =new PeopleFinder(true);
        // Get all peoples for this user
        JSONArray peoplesArrayJson = new JSONArray();
        // Get all Peoples for all users
        for (NameValue<PeopleList> userPeoples : servicePeopleFinder.peoples) {
            // Get all peoples for this user
            for (People people : servicePeopleFinder.peoples.get(userPeoples.name)) {
                if(people.incident.incidentId.equals(idincident.trim())) {
                    JSONObject peopleJson = new JsonListMap();
                    peopleJson.put("user", people.user.id);
                    peopleJson.put("incident", people.incident.incidentId);
                    peopleJson.put("Id", people.Id);
                    peopleJson.put("fullname", people.fullname);
                    peopleJson.put("info_location", people.info_location);
                    peopleJson.put("uri", people.uri);
                    peopleJson.put("source_date", people.source_date);
                    peopleJson.put("last_status", people.last_status);
                    peopleJson.put("status_author", people.status_author);
                    peopleJson.put("status_date", people.status_date);
                    peopleJson.put("status_found", people.status_found);
                    peopleJson.put("cont", people.cont);
                    peopleJson.put("last_known_location", people.last_known_location);
                    peopleJson.put("cont_note", people.cont_note);
                    peopleJson.put("home_street", people.home_street);
                    peopleJson.put("home_neighborhood", people.home_neighborhood);
                    peopleJson.put("home_city", people.home_city);
                    peopleJson.put("home_state", people.home_state);
                    peoplesArrayJson.put(peopleJson);

                }
            }
        }
        JSONObject peoplesJson = new JSONObject();
        peoplesJson.put("peoples", peoplesArrayJson);
        return peoplesJson.toString();
    }
    @RequestMapping(value = "/people/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String getPeoplesId(@PathVariable String id) throws Exception {
        PeopleFinder servicePeopleFinder =new PeopleFinder(true);

        JSONArray peoplesArrayJson = new JSONArray();
        // Get all Peoples for all users
        for (NameValue<PeopleList> userPeoples : servicePeopleFinder.peoples) {
            // Get all peoples for this user
            for (People people : servicePeopleFinder.peoples.get(userPeoples.name)) {
                if(people.Id.equals(id.trim())) {
                   JSONObject peopleJson = new JsonListMap();
                    peopleJson.put("user", people.user.id);
                    peopleJson.put("incident", people.incident.incidentId);
                    peopleJson.put("Id", people.Id);
                    peopleJson.put("fullname", people.fullname);
                    peopleJson.put("info_location", people.info_location);
                    peopleJson.put("uri", people.uri);
                    peopleJson.put("source_date", people.source_date);
                    peopleJson.put("last_status", people.last_status);
                    peopleJson.put("status_author", people.status_author);
                    peopleJson.put("status_date", people.status_date);
                    peopleJson.put("status_found", people.status_found);
                    peopleJson.put("cont", people.cont);
                    peopleJson.put("last_known_location", people.last_known_location);
                    peopleJson.put("cont_note", people.cont_note);
                    peopleJson.put("home_street", people.home_street);
                    peopleJson.put("home_neighborhood", people.home_neighborhood);
                    peopleJson.put("home_city", people.home_city);
                    peopleJson.put("home_state", people.home_state);
                    peoplesArrayJson.put(peopleJson);
                    break;
                }
            }
        }
        JSONObject peoplesJson = new JSONObject();
        peoplesJson.put("peoples", peoplesArrayJson);
        return peoplesJson.toString();

    }
    @RequestMapping(value = "/peoples", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public  @ResponseBody String getPeoples() throws JSONException {
        logger.info("Getting list of Peoples");
        PeopleFinder servicePeopleFinder =new PeopleFinder(true);

        JSONArray peoplesArrayJson = new JSONArray();
        // Get all Peoples for all users
        for (NameValue<PeopleList> userPeoples : servicePeopleFinder.peoples) {
            // Get all peoples for this user
            for (People people : servicePeopleFinder.peoples.get(userPeoples.name)) {
                // Generate JSON for short summary of Peoples
                JSONObject peopleJson = new JsonListMap();
                peopleJson.put("user", people.user.id);
                peopleJson.put("incident", people.incident.incidentId);
                peopleJson.put("Id", people.Id);
                peopleJson.put("fullname", people.fullname);
                peopleJson.put("info_location", people. info_location);
                peopleJson.put("uri", people.uri);
                peopleJson.put("source_date", people. source_date);
                peopleJson.put("last_status",people.last_status);
                peopleJson.put("status_author", people.status_author);
                peopleJson.put("status_date", people. status_date);
                peopleJson.put("status_found", people.status_found);
                peopleJson.put("cont", people.cont);
                peopleJson.put("last_known_location", people.last_known_location);
                peopleJson.put("cont_note", people.cont_note);
                peopleJson.put("home_street", people.home_street);
                peopleJson.put("home_neighborhood", people.home_neighborhood);
                peopleJson.put("home_city", people.home_city);
                peopleJson.put("home_state", people.home_state);

                peoplesArrayJson.put(peopleJson);
            }
        }
        JSONObject peoplesJson = new JSONObject();
        peoplesJson
                .put("peoples", peoplesArrayJson);

        return peoplesJson.toString();
    }
}
