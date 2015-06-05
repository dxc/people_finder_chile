/**
 * Copyright 2012 John W. Krupansky d/b/a Base Technology
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package chileayuda.personfinder.utils.persistence;


import chileayuda.personfinder.model.People;
import chileayuda.personfinder.service.ServicePeopleFinder;
import chileayuda.personfinder.utils.config.ParserException;
import chileayuda.personfinder.utils.config.PeopleFinderException;
import chileayuda.personfinder.utils.config.TokenizerException;
import chileayuda.personfinder.utils.persistence.persistenfile.PersistentFile;
import chileayuda.personfinder.utils.persistence.persistenfile.PersistentFileException;
import chileayuda.personfinder.model.User;
import chileayuda.personfinder.utils.ListMap;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Persistence {
   public ServicePeopleFinder modelPeopleFinder;
    public String path;
    public PersistentFile file;
    static final Logger log = Logger.getLogger(Persistence.class);

    public Persistence(ServicePeopleFinder modelPeopleFinder, String path) throws PeopleFinderException, ParseException, TokenizerException, ParserException {
        this.modelPeopleFinder =modelPeopleFinder;
        this.path = path;
        initialize();
    }

    public void initialize() throws PeopleFinderException, ParseException, TokenizerException, ParserException {
        try {
            // Get a new persistent file object
            if (file == null)
            {
                file = new PersistentFile();
            }
            // Does our persistent file exists yet?
            if (checkForPersistenceFile()) {
                // Yes, open it
                file.open(path);

                // And load all the tables and create all the agent server objects

                loadAllTables();
            } else {
                // No, create a new persistent file for this agent server
                List<String> tableNames = Arrays.asList("config", "users");
                file.create(path, "People Finder", "1.0", tableNames);

                // And open it
                file.open(path);
            }
        } catch (PersistentFileException e) {
            e.printStackTrace();
            throw new PeopleFinderException("PersistentFileException: " + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            throw new PeopleFinderException("IOException reading persistent store: " + e.getMessage());
        }
    }

    public void close() throws IOException {
        file.close();
    }

    public boolean checkForPersistenceFile() {
        File file = new File(path);
        return file.exists();
    }

    public JSONObject getJson(String tableName, String key) throws PeopleFinderException {
        String value = get(tableName, key);
        if (value == null)
            return null;
        else
            try {
                return new JSONObject(value);
            } catch (JSONException e) {
                e.printStackTrace();
                throw new PeopleFinderException("JSONException reading persistent store: " + e.getMessage());
            }
    }

    public String get(String tableName, String key) throws PeopleFinderException {
        try {
            return file.get(tableName, key);
        } catch (PersistentFileException e) {
            e.printStackTrace();
            throw new PeopleFinderException("PersistentFileException: " + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            throw new PeopleFinderException("IOException reading persistent store: " + e.getMessage());
        }
    }

    public ListMap<String, String> get(String tableName) throws IOException, PersistentFileException {
        return file.get(tableName);
    }

    public Iterator<String> iterator(String tableName) throws PersistentFileException {
        return file.iterator(tableName);
    }

    public void put(User user) throws PeopleFinderException {
        log.info("Init put Users:" + user.id + user.toJson().toString());
        put("users", user.id, user.toJson().toString());
    }
    public void put(People people) throws PeopleFinderException {
        log.info("Init put people:" + people.Id + people.toJson().toString());
        put("people",  people.Id, people.toJson().toString());
    }

    public void put(String tableName, String key, String value) throws PeopleFinderException {
        try {
            file.put(tableName, key, value);
        } catch (PersistentFileException e) {
            e.printStackTrace();
            throw new PeopleFinderException("PersistentFileException: " + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            throw new PeopleFinderException("IOException reading persistent store: " + e.getMessage());
        }
    }

    public void loadAllTables() throws PeopleFinderException, ParseException, TokenizerException, ParserException {
       loadUsers();

        // TODO: Status of the scheduler - is it suspended, when is it started?
        // TODO: What to do about pending activities - store/load them? Or, can they be ignored?
    }

    public void loadUsers() throws PeopleFinderException {
        try {
            // Load all users
            for (String userId : file.iterable("users")) {
                String userJsonSource = file.get("users", userId);
                      modelPeopleFinder.recreateUser(userJsonSource);

            }
        } catch (PersistentFileException e) {
            e.printStackTrace();
            throw new PeopleFinderException("PersistentFileException loading users: " + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            throw new PeopleFinderException("IOException loading users from persistent store: " + e.getMessage());
        } catch (JSONException e) {
            e.printStackTrace();
            throw new PeopleFinderException("JSONException loading users from persistent store: " + e.getMessage());
        }
    }


}
