package chileayuda.personfinder.controller;

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
    @RequestMapping(value = "/people/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String postPeople(@PathVariable String id, HttpServletRequest request) throws Exception {
        PeopleFinder servicePeopleFinder =new PeopleFinder(true);
        User user = servicePeopleFinder.users.get(id);
        JSONObject peopleJson = util.getJsonRequest(request);
        if (peopleJson == null)
            throw new PeopleFinderException(
                    "Invalid people JSON object");
        logger.info("Adding new people for user: " + user.id);
        // Parse and add the people
        People people = servicePeopleFinder.servicePeople.addPeople(
                user, peopleJson);
        // Done
        JSONObject message = new JSONObject();
        message.put("message", "Add was successful");
        return message.toString();
    }
    @RequestMapping(value = "/people/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String getPeoples(@PathVariable String id) throws Exception {
        PeopleFinder servicePeopleFinder =new PeopleFinder(true);
        User user = servicePeopleFinder.users.get(id);
         // Get all peoples for this user
        JSONArray peoplesArrayJson = new JSONArray();
        for (People people  : servicePeopleFinder.peoples.get(user.id)) {
            // Generate JSON for short summary of people
            logger.info("Getting list of all peoplesfor user Id: " + people.user.id);
            JSONObject peopleJson = new JsonListMap();
            peopleJson.put("user", people.user.id);
            peopleJson.put("Id", people.Id);
            peopleJson.put("fullname", people.fullname);
            peoplesArrayJson.put(peopleJson);
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
                peopleJson.put("Id", people.Id);
                peopleJson.put("fullname", people.fullname);
                peoplesArrayJson.put(peopleJson);
            }
        }
        JSONObject peoplesJson = new JSONObject();
        peoplesJson
                .put("peoples", peoplesArrayJson);

        return peoplesJson.toString();
    }
}
