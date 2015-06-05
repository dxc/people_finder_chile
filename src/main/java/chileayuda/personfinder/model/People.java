package chileayuda.personfinder.model;

import chileayuda.personfinder.service.ServicePeopleFinder;
import chileayuda.personfinder.utils.JsonListMap;
import chileayuda.personfinder.utils.JsonUtils;
import chileayuda.personfinder.utils.config.ParserException;
import chileayuda.personfinder.utils.config.PeopleFinderException;
import chileayuda.personfinder.utils.config.TokenizerException;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by teo on 05/06/15.
 */
public class People {
    static final Logger log = Logger.getLogger(People.class);
    public String Id;
    public String fullname;
    public String info_location;
    public String uri;
    public String source_date;
    public String last_status;
    public String status_author;
    public String status_date;
    public String status_found;
    public String cont;
    public String last_known_location;
    public String cont_note;
    public String home_street;
    public String home_neighborhood;
    public String home_city;
    public String home_state;

    public People(String Id,
                  String fullname,
                  String info_location,
                  String uri,
                  String source_date,
                  String last_status,
                  String status_author,
                  String status_date,
                  String status_found,
                  String cont,
                  String last_known_location,
                  String cont_note,
                  String home_street,
                  String home_neighborhood,
                  String home_city,
                  String home_state) {
        this.Id = Id;
        this.fullname = fullname;
        this.info_location = info_location;
        this.uri = uri;
        this.source_date = source_date;
        this.last_status = last_status;
        this.status_author = status_author;
        this.status_date = status_date;
        this.status_found = status_found;
        this.cont = cont;
        this.last_known_location = last_known_location;
        this.cont_note = cont_note;
        this.home_street = home_street;
        this.home_neighborhood = home_neighborhood;
        this.home_city = home_city;
        this.home_state = home_state;
    }

    public JSONObject toJson() throws PeopleFinderException {
        return toJson(true);
    }

    public JSONObject toJson(boolean includeState) throws PeopleFinderException {
        return toJson(includeState, -1);
    }

    public JSONObject toJson(boolean includeState, int stateCount) throws PeopleFinderException {
        try {
            JSONObject PeopleJson = new JsonListMap();

            PeopleJson.put("Id", Id == null ? "" : Id);
            PeopleJson.put("fullname", fullname == null ? "" : fullname);
            PeopleJson.put("info_location", info_location == null ? "" : info_location);
            PeopleJson.put("uri", uri == null ? "" : uri);
            PeopleJson.put("source_date", source_date == null ? "" : source_date);
            PeopleJson.put("last_status", last_status == null ? "" : last_status);
            PeopleJson.put("status_author", status_author == null ? "" : status_author);
            PeopleJson.put("status_date", status_date == null ? "" : status_date);
            PeopleJson.put("status_found", status_found == null ? "" : status_found);
            PeopleJson.put("cont", cont == null ? "" : cont);
            PeopleJson.put("last_known_location", last_known_location == null ? "" : last_known_location);
            PeopleJson.put("cont_note", cont_note == null ? "" : cont_note);
            PeopleJson.put("home_street", home_street == null ? "" : home_street);
            PeopleJson.put("home_neighborhood", home_neighborhood == null ? "" : home_neighborhood);
            PeopleJson.put("home_city", home_city == null ? "" : home_city);
            PeopleJson.put("home_state", home_state == null ? "" : home_state);
            return  PeopleJson;
        } catch (JSONException e) {
            e.printStackTrace();
            throw new PeopleFinderException("JSON exception in Message.toJson -" + e.getMessage());
        }
    }
    public void update(ServicePeopleFinder servicePeopleFinder, People people) throws JSONException, PeopleFinderException {
        if(people.Id!= null);
        {
            this.Id=people.Id;
        }
        if(people.fullname!=null );
        {
            this.fullname=people.fullname;
        }
        if(people.info_location!= null  );
        {
            this.info_location=people.info_location;
        }
        if(people.uri!= null  );
        {
            this.uri=people.uri;
        }
        if(people.source_date!= null  );
        {
            this.source_date=people.source_date;
        }
        if(people.last_status!= null );
        {
            this.last_status=people.last_status;
        }
        if(people.status_author!= null  );
        {
            this.status_author=people.status_author;
        }
        if(people.status_date!= null );
        {
            this.status_date=people.status_date;
        }
        if(people.status_found!= null );
        {
            this.status_found=people.status_found;
        }
        if(people.cont!= null);
        {
            this.cont=people.cont;
        }
        if(people.last_known_location!= null);
        {
            this.last_known_location=people.last_known_location;
        }
        if(people.cont_note!= null);
        {
            this.cont_note=people.cont_note;
        }
        if(people.home_street!= null);
        {
            this.home_street=people.home_street;
        }
        if(people.home_neighborhood!= null);
        {
            this.home_neighborhood=people.home_neighborhood;
        }
        if(people.home_city!= null );
        {
            this.home_city=people.home_city;
        }
        if(people.home_state!= null );
        {
            this.home_state=people.home_state;
        }

        servicePeopleFinder.persistence.put(this);
    }

    static public People fromJson(ServicePeopleFinder servicePeopleFinder, String agentJsonSource) throws PeopleFinderException, JSONException, ParseException, TokenizerException, ParserException {
        return fromJson(servicePeopleFinder, null, new JSONObject(agentJsonSource), false);
    }

    static public People fromJson(ServicePeopleFinder servicePeopleFinder, User user, JSONObject agentJson) throws PeopleFinderException {
        return fromJson(servicePeopleFinder, user, agentJson, false);
    }

    static public People fromJson(ServicePeopleFinder servicePeopleFinder, JSONObject agentJson) throws PeopleFinderException, JSONException, ParseException, TokenizerException, ParserException {
        return fromJson(servicePeopleFinder, null, agentJson, false);
    }
    static public People fromJson(ServicePeopleFinder servicePeopleFinder, User user, JSONObject agentJson, boolean update) throws PeopleFinderException {
        // Parse the event

        // Parse the users

        if(user==null)
        {
            String userId=agentJson.optString("user");
            if(userId==null || userId.trim().length()==0)
            {
                throw new PeopleFinderException("Message user id ('user') is missing");
            }
            user = servicePeopleFinder.getUser(userId);
            if(user==User.noUser)
            {
                throw  new PeopleFinderException("Message user id does not exist: '"+userId+"'");
            }
        }

        // Parse the message sender
        String messageSender=agentJson.optString("sender",null);
        if(!update &&(messageSender==null) || messageSender.trim().length()==0)
        {
            messageSender="";
        }
        // Parse the message receiver
        String messageReceiver=agentJson.optString("receiver",null);
        {
            if(!update &&(messageReceiver==null)|| messageReceiver.trim().length()==0)
            {
                messageReceiver="";
            }
        }
        // Parse the message replyTo
        String messageReplyTo=agentJson.optString("replyTo",null);
        {
            if(!update &&(messageReplyTo==null)||messageReplyTo.trim().length()==0)
            {
                messageReplyTo="";
            }
        }
        //Parse the message conversationId
        String messageConversationId=agentJson.optString("conversationId",null);
        {
            if(!update &&(messageConversationId==null) || messageConversationId.trim().length()==0)
            {
                messageConversationId="";
            }
        }
        //Parse the message content
        String messageContent=agentJson.optString("content",null);
        {
            if(!update &&(messageContent==null) || messageContent.trim().length()==0)
            {
                messageContent="";
            }
        }
        //Parse the message lenguaje
        String messageLenguaje=agentJson.optString("lenguaje",null);
        {
            if(!update &&(messageLenguaje==null) || messageLenguaje.trim().length()==0)
            {
                messageLenguaje="";
            }
        }

        //Parse the message enconding
        String messageEnconding=agentJson.optString("enconding",null);
        {
            if(!update &&(messageEnconding==null) || messageEnconding.trim().length()==0)
            {
                messageEnconding="";
            }
        }
        //Parse the message ontology
        String messageOntology=agentJson.optString("ontology",null);
        {
            if(!update &&(messageOntology==null) || messageOntology.trim().length()==0)
            {
                messageOntology="";
            }
        }
        //Parse the message protocol
        String messageProtocol=agentJson.optString("protocol",null);
        {
            if(!update &&(messageProtocol==null) || messageProtocol.trim().length()==0)
            {
                messageProtocol="";
            }
        }

        //Parse the message replyWith
        String messageReplyWith=agentJson.optString("replyWith",null);
        {
            if(!update &&(messageReplyWith==null) || messageReplyWith.trim().length()==0)
            {
                messageReplyWith="";
            }
        }

        //Parse the message inReplyTo
        String messageInReplyTo=agentJson.optString("inReplyTo",null);
        {
            if(!update &&(messageInReplyTo==null) || messageInReplyTo.trim().length()==0)
            {
                messageInReplyTo="";
            }
        }

        //Parse the message replyBy
        String messageReplyBy=agentJson.optString("replyBy",null);
        {
            if(!update &&(messageReplyBy==null) || messageReplyBy.trim().length()==0)
            {
                messageReplyBy="";
            }
        }
        // Validate keys

        JsonUtils.validateKeys(agentJson, "Agent Message", new ArrayList<String>(Arrays.asList(
                "user", "sender", "receiver", "replyTo", "conversationId",
                "content", "lenguage", "encoding", "ontology",
                "protocol", "replyWith", "inReplyTo", "replyBy")));

        People agentPeople = new People(servicePeopleFinder,user,  messageSender, messageReceiver, messageReplyTo, messageConversationId,
                messageContent, messageLenguaje, messageEnconding, messageOntology, messageProtocol, messageReplyWith, messageInReplyTo,
                messageReplyBy, update, false);

        // Return the new people
        return agentPeople;


    }

    public String toString() {
        try {
            return toJson().toString();
        } catch (PeopleFinderException e) {
            log.info("Unable to output People as string - " + e.getMessage());
            e.printStackTrace();
            return "[People: Unable to output People as string - " + e.getMessage();
        }
    }
}
