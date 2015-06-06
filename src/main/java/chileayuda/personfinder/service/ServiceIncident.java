package chileayuda.personfinder.service;

import chileayuda.personfinder.model.*;
import chileayuda.personfinder.utils.config.ParserException;
import chileayuda.personfinder.utils.config.PeopleFinderException;
import chileayuda.personfinder.utils.config.TokenizerException;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;

/**
 * Created by teo on 05/06/15.
 */
public class ServiceIncident {
    public PeopleFinder servicePeopleFinder;
    static final Logger log = Logger.getLogger(ServiceIncident.class);
    public ServiceIncident(PeopleFinder servicePeopleFinder)
    {
        this.servicePeopleFinder=servicePeopleFinder;
    }
    public void recreateIncident(String PeopleJsonSource) throws JSONException, PeopleFinderException, ParseException, ParserException, TokenizerException {
        Incident incident = Incident.fromJson(servicePeopleFinder,new JSONObject(PeopleJsonSource));
        recreateIncident(incident);
       }
    public  Incident recreateIncident(Incident incident) throws PeopleFinderException {
        if (incident != null) {
            // Check if the user has any incident yet
            if (!servicePeopleFinder.incidents.containsKey(incident.user.id)) {
                // No, so create an empty incident table for user
                servicePeopleFinder.incidents.put(incident.user.id, new IncidentList());
            }
            // Get incident table for the user
            IncidentList usersincidenteList = servicePeopleFinder.incidents.get(incident.user.id);
            // Store the new incident for the user
            usersincidenteList.put(incident);
            // Persist the new incident
                }
        // Return the incident itself
        return incident;
    }

    public Incident addIncident(User user, JSONObject agentJson) throws RuntimeException, PeopleFinderException {
        // Parse the JSON for the incident
        log.info("Parse the JSON for the people");
        Incident incident = Incident.fromJson(servicePeopleFinder, user, agentJson);
        // Add it to table of incident
        addIncident(incident);
        // Return the new incident
        return incident;
    }
    public Incident addIncident(JSONObject agentJson) throws  RuntimeException, PeopleFinderException {
        return addIncident(null, agentJson);
    }

    public Incident addIncident(Incident incident) throws PeopleFinderException {
        if (incident != null) {
            // Check if the user has any incident yet
            if (!servicePeopleFinder.incidents.containsKey(incident.user.id)) {
                // No, so create an empty incident table for user
                servicePeopleFinder.incidents.put(incident.user.id, new IncidentList());
            }
            // Get incident table for the user
            IncidentList usersincidenteList = servicePeopleFinder.incidents.get(incident.user.id);
            // Store the new incident for the user
            usersincidenteList.put(incident);
            // Persist the new incident
            servicePeopleFinder.persistence.put(incident);
        }
        // Return the incident itself
        return incident;
    }
    public Incident getIncident(User user, String id) {
        IncidentList incidentseMap = servicePeopleFinder.incidents.get(user.id);
        if (incidentseMap == null)
            return null;
        else
            return incidentseMap.get(id);
    }
    public void removeIncident(Incident incident) throws PeopleFinderException {
        String userId = incident.user.id;
        String incidentName = incident.incidentName;
        // Check if the user has any incident yet
        if (!servicePeopleFinder.peoples.containsKey(userId))
            throw new PeopleFinderException("Attempt to delete incident ('" + incidentName + "') for a user ('" + userId + "') that has no incidents");
        // Get incident table for the user
        IncidentList usersincident = servicePeopleFinder.incidents.get(userId);
        // Check if that incident exists for user
        if (!usersincident.containsKey(incidentName))
            throw new PeopleFinderException("Attempt to delete incident ('" + incidentName + "') that does not exist for user ('" + userId + "')");
        // Delete the named incident for the user
        usersincident.remove(incidentName);
    }

    public void removeIncident(String userId, String incidentName) throws PeopleFinderException {
        // Check if the user has any incident yet
        if (!servicePeopleFinder.peoples.containsKey(userId))
            throw new PeopleFinderException("Attempt to delete incident ('" + incidentName + "') for a user ('" + userId + "') that has no incident");

        // Get incident table for the user
        IncidentList usersincident = servicePeopleFinder.incidents.get(userId);

        // Check if that incident exists for user
        if (!usersincident.containsKey(incidentName))
            throw new PeopleFinderException("Attempt to delete incident ('" + incidentName + "') that does not exist for user ('" + userId + "')");

        // Delete the named incident for the user
        usersincident.remove(incidentName);
    }

}
