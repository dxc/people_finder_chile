package chileayuda.personfinder.service;

import chileayuda.personfinder.model.User;
import chileayuda.personfinder.utils.config.PeopleFinderException;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by teo on 05/06/15.
 */
public class ServiceUser {
    public PeopleFinder servicePeopleFinder;
    static final Logger log = Logger.getLogger(ServiceUser.class);

    public ServiceUser(PeopleFinder servicePeopleFinder)
    {
        this.servicePeopleFinder=servicePeopleFinder;
    }

    public void recreateUser(String userJsonSource) throws JSONException, PeopleFinderException {
        User newUser = User.fromJson(new JSONObject(userJsonSource));

        servicePeopleFinder.users.put(newUser.id, newUser);
    }
    public User addUser(User user) throws PeopleFinderException {
        // Add the new user
        servicePeopleFinder.users.add(user.id, user);
        log.info("Create Users: Add the new user" + servicePeopleFinder.persistence);
        // Persist the new user
        servicePeopleFinder.persistence.put(user);
        // Return the new user
        return user;
    }
    public User addUser(String userId)
            throws PeopleFinderException {
        return addUser(new User(userId));
    }

    public User getUser(String userId) {
        if (userId == null || userId.trim().length() == 0)
            return null;
        else if (userId.equals("*"))
            return User.allUser;
        else {
            User user =servicePeopleFinder.users.get(userId);
            return user == null ? User.noUser : user;
        }
    }
}
