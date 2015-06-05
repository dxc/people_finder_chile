package chileayuda.personfinder.service;

import chileayuda.personfinder.model.IncidentList;
import chileayuda.personfinder.model.PeopleList;
import chileayuda.personfinder.model.User;
import chileayuda.personfinder.utils.NameValueList;
import chileayuda.personfinder.utils.config.*;
import chileayuda.personfinder.utils.persistence.Persistence;
import chileayuda.personfinder.utils.persistence.persistenfile.PersistentFileException;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.text.ParseException;

/**
 * Created by teo on 05/06/15.
 */
public class PeopleFinder {
    public Persistence persistence;
    public PeoplefinderProperties peoplefinderProperties;
    public PeoplefinderConfig config;
    public ServiceUser serviceUser;
    public ServiceIncident serviceIncident;
    public ServicePeople servicePeople;
    public NameValueList<User> users;
    public NameValueList<IncidentList> incidents;
    public NameValueList<PeopleList> peoples;
    static final Logger log = Logger.getLogger(PeopleFinder.class);
    public PeopleFinder(boolean b) {
        try {
            start();
        } catch (PeopleFinderException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (PersistentFileException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (TokenizerException e) {
            e.printStackTrace();
        } catch (ParserException e) {
            e.printStackTrace();
        }
    }


    public void start()
            throws PeopleFinderException, InterruptedException, IOException, PersistentFileException, ParseException, TokenizerException, ParserException {
        start(true);
    }
    public void start(boolean start) throws PeopleFinderException, InterruptedException, IOException, PersistentFileException, ParseException, TokenizerException, ParserException {
        log.info("Initialize parameters");
          // Initialize members
        this.users = new NameValueList<User>();
        this.peoples=new NameValueList<PeopleList>();
        this.incidents=new NameValueList<IncidentList> ();


         // Initialize agent server properties
        peoplefinderProperties = new PeoplefinderProperties();
        serviceUser =new ServiceUser(this);
        servicePeople=new ServicePeople(this);
        serviceIncident =new ServiceIncident(this);
        // Initialize persistence
        if (persistence == null) {
            persistence = new Persistence(this, getPersistentStorePath());
        } else {
            persistence.initialize();

        }
        // Force a reload of config
        if (config == null)
        {
            config = new PeoplefinderConfig(this);
        }
        config.load();

    }
    public String getPersistentStorePath() {
        return peoplefinderProperties.persistent_store_dir + "/" + peoplefinderProperties.DEFAULT_PERSISTENT_STORE_FILE_NAME;
    }

}
