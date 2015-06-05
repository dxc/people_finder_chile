package chileayuda.personfinder.utils.config;

import chileayuda.personfinder.service.ServicePeopleFinder;
import chileayuda.personfinder.utils.persistence.persistenfile.PersistentFileException;
import chileayuda.personfinder.utils.JsonListMap;
import chileayuda.personfinder.utils.ListMap;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by teo on 05/06/15.
 */
public class PeoplefinderConfig {
    public ServicePeopleFinder modelPeopleFinder;
    static final Logger log = Logger.getLogger(PeoplefinderConfig.class);
    public ListMap<String, String> config;
    public PeoplefinderProperties peoplefinderProperties;

public PeoplefinderConfig(ServicePeopleFinder modelPeopleFinder)
{   this.modelPeopleFinder=modelPeopleFinder;
    this.config=new ListMap<String, String>();

    this.peoplefinderProperties = modelPeopleFinder.peoplefinderProperties == null ? new PeoplefinderProperties()
            : modelPeopleFinder.peoplefinderProperties;
}
 public void load() throws IOException, PeopleFinderException, PersistentFileException {
     log.info("Loading config table");
     if(modelPeopleFinder.persistence!=null)
         config = modelPeopleFinder.persistence.get("config");
     else
     {
         config=new ListMap<String, String>();
     }
     if (config.size() == 0)
         restoreDefaults();

 }
    public String get(String key) {
        return config.get(key);
    }

    public boolean getBoolean(String key) {
        String value = config.get(key);
        if (value == null)
            return false;
        value = value.trim();
        return value.equalsIgnoreCase("true") || value.equalsIgnoreCase("yes")
                || value.equalsIgnoreCase("on")
                || value.equalsIgnoreCase("enabled");
    }
    public int getInt(String key) {
        return Integer.parseInt(config.get(key));
    }

    public long getLong(String key) {
        return Long.parseLong(config.get(key));
    }

    public void persist(String key) throws PeopleFinderException {
        modelPeopleFinder.persistence.put("config", key, config.get(key));
    }

    public void put(String key, Object value) throws PeopleFinderException {
        config.put(key, value.toString());
        persist(key);
    }
    public JSONObject toJson() throws JSONException {
        JSONObject configJson = new JsonListMap();
        for (String key : config)
            configJson.put(key, config.get(key));
        return configJson;
    }

    public String toString() {
        return "AgentServerConfig " + config;
    }

    private void restoreDefaults() throws PeopleFinderException {
        log.info("Setting defaults for config properties");
        this.config = new ListMap<String, String>();

        put("software", "Api People Finder Chile");
        put("version", "1.0");
        put("website", "http://dcc.uchile.cl");

    }
}
