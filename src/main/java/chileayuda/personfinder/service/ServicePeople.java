package chileayuda.personfinder.service;

import chileayuda.personfinder.model.People;
import chileayuda.personfinder.model.PeopleList;
import chileayuda.personfinder.model.User;
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
public class ServicePeople {
    public PeopleFinder servicePeopleFinder;
    static final Logger log = Logger.getLogger(ServicePeople.class);

    public ServicePeople(PeopleFinder servicePeopleFinder)
    {
        this.servicePeopleFinder=servicePeopleFinder;
    }
    public void recreatePeopler(String PeopleJsonSource) throws JSONException, PeopleFinderException, ParseException, ParserException, TokenizerException {
        People people = People.fromJson(servicePeopleFinder,new JSONObject(PeopleJsonSource));
        PeopleList usersPeopleList = new PeopleList();
        // Store the new people for the user
        usersPeopleList.put(people);
        servicePeopleFinder.peoples.put(people.Id, usersPeopleList);
    }
    public People addPeople(User user, JSONObject agentJson) throws RuntimeException, PeopleFinderException {
        // Parse the JSON for the people
        log.info("Parse the JSON for the people");
        People people = People.fromJson(servicePeopleFinder, user, agentJson);
        // Add it to table of people
        addPeople(people);
        // Return the new people
        return people;
    }
    public People addPeople(JSONObject agentJson) throws  RuntimeException, PeopleFinderException {
        return addPeople(null, agentJson);
    }

    public People addPeople(People people) throws PeopleFinderException {
        if (people != null) {
            // Check if the user has any people yet
            if (!servicePeopleFinder.peoples.containsKey(people.user.id)) {
                // No, so create an empty people table for user
                servicePeopleFinder.peoples.put(people.user.id, new PeopleList());
            }
            // Get people table for the user
            PeopleList usersPeopleList = servicePeopleFinder.peoples.get(people.user.id);
            // Store the new people for the user
            usersPeopleList.put(people);
            // Persist the new people
            servicePeopleFinder.persistence.put(people);
        }
        // Return the people itself
        return people;
    }
    public People getPeople(User user, String id) {
        PeopleList PeopleMap = servicePeopleFinder.peoples.get(user.id);
        if (PeopleMap == null)
            return null;
        else
            return PeopleMap.get(id);
    }

    public void removePeople(People people) throws PeopleFinderException {
        String userId = people.user.id;
        String PeopleName = people.fullname;
        // Check if the user has any People yet
        if (!servicePeopleFinder.peoples.containsKey(userId))
            throw new PeopleFinderException("Attempt to delete People ('" + PeopleName + "') for a user ('" + userId + "') that has no Peoples");
        // Get People table for the user
        PeopleList usersPeople = servicePeopleFinder.peoples.get(userId);
        // Check if that People exists for user
        if (!usersPeople.containsKey(PeopleName))
            throw new PeopleFinderException("Attempt to delete People ('" + PeopleName + "') that does not exist for user ('" + userId + "')");
        // Delete the named Peoplefor the user
        usersPeople.remove(PeopleName);
    }

    public void removePeople(String userId, String PeopleName) throws PeopleFinderException {
        // Check if the user has any People yet
        if (!servicePeopleFinder.peoples.containsKey(userId))
            throw new PeopleFinderException("Attempt to delete People ('" + PeopleName + "') for a user ('" + userId + "') that has no People");

        // Get People table for the user
        PeopleList usersPeoples = servicePeopleFinder.peoples.get(userId);

        // Check if that People exists for user
        if (!usersPeoples.containsKey(PeopleName))
            throw new PeopleFinderException("Attempt to delete People ('" + PeopleName + "') that does not exist for user ('" + userId + "')");

        // Delete the named People for the user
        usersPeoples.remove(PeopleName);
    }
}
