package chileayuda.personfinder.controller;

import chileayuda.personfinder.model.Incident;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by teo on 05/06/15.
 */
public class IncidentController {
    protected static Logger logger = Logger.getLogger(IncidentController.class);
    public Utils util = new Utils();
    @RequestMapping(value = "/incident/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String postincident(@PathVariable String id, HttpServletRequest request) throws Exception {
        PeopleFinder servicePeopleFinder =new PeopleFinder(true);
        User user = servicePeopleFinder.users.get(id);
        JSONObject peopleJson = util.getJsonRequest(request);
        if (peopleJson == null)
            throw new PeopleFinderException(
                    "Invalid incident JSON object");
        logger.info("Adding new incident for user: " + user.id);
        // Parse and add the incident
        Incident incident = servicePeopleFinder.serviceIncident.addIncident(
                user, peopleJson);
        // Done
        JSONObject message = new JSONObject();
        message.put("message", "Add was successful");
        return message.toString();
    }
    @RequestMapping(value = "/incident/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String getIncident(@PathVariable String id) throws Exception {
        PeopleFinder servicePeopleFinder =new PeopleFinder(true);
        User user = servicePeopleFinder.users.get(id);
        // Get all incident for this user
        JSONArray incidentsArrayJson = new JSONArray();
        for (Incident incident : servicePeopleFinder.incidents.get(user.id)) {
            // Generate JSON for short summary of incident
            logger.info("Getting list of all  incident for user Id: " + incident.user.id);
            JSONObject incidentJson = new JsonListMap();
            incidentJson.put("user", incident.user.id);
            incidentJson.put("Id", incident.incidentId);
            incidentJson.put("incidentName", incident.incidentName);
            incidentsArrayJson.put(incidentJson);
        }
        JSONObject peoplesJson = new JSONObject();
        peoplesJson.put("incidents", incidentsArrayJson);
        return peoplesJson.toString();

    }
    @RequestMapping(value = "/incidents", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public  @ResponseBody String getIncident() throws JSONException {
        logger.info("Getting list of Peoples");
        PeopleFinder servicePeopleFinder =new PeopleFinder(true);

        JSONArray incidentsArrayJson = new JSONArray();
        // Get all incident for all users
        for (NameValue<PeopleList> userPeoples : servicePeopleFinder.peoples) {
            // Get all incident for this user
            for (Incident incident : servicePeopleFinder.incidents.get(userPeoples.name)) {
                // Generate JSON for short summary of incident
                JSONObject incidentJson = new JsonListMap();
                incidentJson.put("user", incident.user.id);
                incidentJson.put("Id", incident.incidentId);
                incidentJson.put("incidentName", incident.incidentName);
                incidentsArrayJson.put(incidentJson);
            }
        }
        JSONObject peoplesJson = new JSONObject();
        peoplesJson
                .put("incidents", incidentsArrayJson);

        return peoplesJson.toString();
    }
}
