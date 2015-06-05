package chileayuda.personfinder.service;

import chileayuda.personfinder.utils.config.*;
import chileayuda.personfinder.model.User;
import chileayuda.personfinder.utils.persistence.Persistence;
import chileayuda.personfinder.utils.persistence.persistenfile.PersistentFileException;
import chileayuda.personfinder.utils.NameValueList;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.ParseException;

/**
 * Created by teo on 05/06/15.
 */
public class ModelPeopleFinder {
    public Persistence persistence;
    public PeoplefinderProperties peoplefinderProperties;
    public PeoplefinderConfig config;
    public NameValueList<User> users;
    static final Logger log = Logger.getLogger(ModelPeopleFinder.class);
    public long startTime = 0;

    public void start()
            throws PeopleFinderException, InterruptedException, IOException, PersistentFileException, ParseException, TokenizerException, ParserException {
        start(true);
    }
    public void start(boolean start) throws PeopleFinderException, InterruptedException, IOException, PersistentFileException, ParseException, TokenizerException, ParserException {
        // No-op if already started
        if (startTime > 0) {
            return;
        }
        // Record start time for server
        startTime = System.currentTimeMillis();
        // Initialize members
        this.users = new NameValueList<User>();
         // Initialize agent server properties
        peoplefinderProperties = new PeoplefinderProperties();
        // Initialize persistence
        if (persistence == null) {
            persistence = new Persistence(this, getPersistentStorePath());
        } else {
            persistence.initialize();

        }
        // Force a reload of config
        if (config == null)
            config = new PeoplefinderConfig(this);
        config.load();

    }
    public String getPersistentStorePath() {
        return peoplefinderProperties.persistent_store_dir + "/" + peoplefinderProperties.DEFAULT_PERSISTENT_STORE_FILE_NAME;
    }
    public void recreateUser(String userJsonSource) throws JSONException, PeopleFinderException {
        User newUser = User.fromJson(new JSONObject(userJsonSource));
        NameValueList<User> userlist=new NameValueList<User>();
        users.put(newUser.id, newUser);
    }
    public User addUser(User user) throws PeopleFinderException {
        // Add the new user
        users.add(user.id, user);
        log.info("Create Users: Add the new user" + persistence);
        // Persist the new user
        persistence.put(user);
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
            User user = users.get(userId);
            return user == null ? User.noUser : user;
        }
    }
}
