package chileayuda.personfinder.service;

import chileayuda.personfinder.model.Incident;
import chileayuda.personfinder.model.People;
import chileayuda.personfinder.model.PeopleList;
import chileayuda.personfinder.model.User;
import chileayuda.personfinder.utils.JsonListMap;
import chileayuda.personfinder.utils.config.ParserException;
import chileayuda.personfinder.utils.config.PeopleFinderException;
import chileayuda.personfinder.utils.config.TokenizerException;
import chileayuda.personfinder.utils.value.PageGetter;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.text.ParseException;

/**
 * Created by teo on 05/06/15.
 */
public class ServicePeople {
    public PeopleFinder servicePeopleFinder;
    static final Logger log = Logger.getLogger(ServicePeople.class);
    public User user;
    public Incident incident;
    public  Document doc;
    public ServicePeople(PeopleFinder servicePeopleFinder)
    {
        this.servicePeopleFinder=servicePeopleFinder;
    }
    public void recreatePeopler(String PeopleJsonSource) throws JSONException, PeopleFinderException, ParseException, ParserException, TokenizerException {
        People people = People.fromJson(servicePeopleFinder,new JSONObject(PeopleJsonSource));
        recreatePeopler(people);
    }
    public People recreatePeopler(People people) throws PeopleFinderException {
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
         }
        // Return the people itself
        return people;
    }

    public People addPeople(User user,Incident incident, JSONObject agentJson) throws RuntimeException, PeopleFinderException {
        // Parse the JSON for the people
        log.info("Parse the JSON for the people");
        People people = People.fromJson(servicePeopleFinder, user, incident,agentJson);
        // Add it to table of people
        addPeople(people);
        // Return the new people
        return people;
    }
    public People addPeople(JSONObject agentJson) throws  RuntimeException, PeopleFinderException {
        return addPeople(null,null, agentJson);
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
        String PeopleName = people.full_name;
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
    public JSONObject readurl()
    { JSONObject json=null;

        try {

            String url = String.format("https://chileagils.appspot.com/norte/feeds/person?key=5Gr3C1ADeFyjqqRi");
            doc = PageGetter.getDocument(url);
            if (doc.hasChildNodes()) {

                NodeList nodeList = doc.getElementsByTagName("pfif:person");

                json = writeperson(nodeList);


            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return  json;
    }
    private JSONObject writeperson(NodeList nodeList) throws PeopleFinderException, JSONException, ParseException, ParserException, TokenizerException {
        JSONArray peoplesArrayJson = new JSONArray();

        for (int itr = 0; itr < nodeList.getLength(); itr++) {

            Node node = nodeList.item(itr);

            StringBuffer textValue = new StringBuffer();
            int length = node.getChildNodes().getLength();
            JSONObject PeopleJson = new JsonListMap();
            for (int i = 0; i < length; i ++) {
                Node c = node.getChildNodes().item(i);
               // System.out.println("NAme:"+ c.getNodeName().trim()+ " : "+c.getTextContent());
                //  textValue.append(c.getNodeName().trim()+ " : "+c.getTextContent());

                if(c.getNodeName().trim().equals("pfif:person_record_id"))
                {
                    PeopleJson.put("person_record_id", c.getTextContent().toString()== null ? "" : c.getTextContent().toString());

                }
                if(c.getNodeName().trim().equals("pfif:entry_date"))
                {
                    PeopleJson.put("entry_date", c.getTextContent().toString()== null ? "" : c.getTextContent().toString());
                }
                if(c.getNodeName().trim().equals("pfif:expiry_date"))
                {
                    PeopleJson.put("expiry_date",c.getTextContent().toString()== null ? "" : c.getTextContent().toString());
                }
                if(c.getNodeName().trim().equals("pfif:author_name"))
                {
                    PeopleJson.put("author_name", c.getTextContent().toString()== null ? "" : c.getTextContent().toString());
                }
                if(c.getNodeName().trim().equals("pfif:author_email"))
                {
                    PeopleJson.put("author_email", c.getTextContent().toString()== null ? "" : c.getTextContent().toString());
                }

                if(c.getNodeName().trim().equals("pfif:source_name"))
                {
                    PeopleJson.put("source_name", c.getTextContent().toString()== null ? "" : c.getTextContent().toString());
                }
                if(c.getNodeName().trim().equals("pfif:source_date"))
                {
                    PeopleJson.put("source_date",  c.getTextContent().toString()== null ? "" : c.getTextContent().toString());
                }
                if(c.getNodeName().trim().equals("pfif:source_url"))
                {
                    PeopleJson.put("source_url", c.getTextContent().toString()== null ? "" : c.getTextContent().toString());
                }
                if(c.getNodeName().trim().equals("pfif:full_name"))
                {
                    PeopleJson.put("full_name",  c.getTextContent().toString()== null ? "" : c.getTextContent().toString());
                }
                if(c.getNodeName().trim().equals("pfif:given_name"))
                {
                    PeopleJson.put("given_name",  c.getTextContent().toString()== null ? "" : c.getTextContent().toString());
                }
                if(c.getNodeName().trim().equals("pfif:family_name"))
                {
                    PeopleJson.put("family_name", c.getTextContent().toString()== null ? "" : c.getTextContent().toString());
                }
                if(c.getNodeName().trim().equals("pfif:alternate_names"))
                {
                    PeopleJson.put("alternate_names",c.getTextContent().toString()== null ? "" : c.getTextContent().toString());
                }
                if(c.getNodeName().trim().equals("pfif:sex"))
                {
                    PeopleJson.put("sex", c.getTextContent().toString()== null ? "" : c.getTextContent().toString());
                }
                if(c.getNodeName().trim().equals("pfif:age"))
                {
                    PeopleJson.put("age", c.getTextContent().toString()== null ? "" : c.getTextContent().toString());
                }
                if(c.getNodeName().trim().equals("pfif:home_street"))
                {
                    PeopleJson.put("home_street", c.getTextContent().toString()== null ? "" : c.getTextContent().toString());
                }
                if(c.getNodeName().trim().equals("pfif:home_city"))
                {
                    PeopleJson.put("home_city",c.getTextContent().toString()== null ? "" : c.getTextContent().toString());
                }
                if(c.getNodeName().trim().equals("pfif:description"))
                {
                    PeopleJson.put("description", c.getTextContent().toString()== null ? "" : c.getTextContent().toString());
                }
                if(c.getNodeName().trim().equals("pfif:photo_url"))
                {
                    PeopleJson.put("photo_url",c.getTextContent().toString()== null ? "" : c.getTextContent().toString());
                }
                if(c.getNodeName().trim().equals("pfif:note"))
                {
                    JSONArray NoteArrayJson = new JSONArray();
                    NodeList nodeListnote = doc.getElementsByTagName("pfif:note");
                    for (int x = 0; x < nodeList.getLength(); x++) {

                        Node nodes = nodeListnote.item(x);
                        JSONObject NoteJson = new JsonListMap();
                        for (int j = 0; j < nodes.getChildNodes().getLength(); j++) {
                            Node cc = nodes.getChildNodes().item(j);

                            if (cc.getNodeName().trim().equals("pfif:note_record_id")) {
                                NoteJson.put("note_record_id", cc.getTextContent().toString() == null ? "" : cc.getTextContent().toString());
                            }
                            if (cc.getNodeName().trim().equals("pfif:person_record_id")) {
                                NoteJson.put("person_record_id", cc.getTextContent().toString() == null ? "" : cc.getTextContent().toString());
                            }
                            if (cc.getNodeName().trim().equals("pfif:entry_date")) {
                                NoteJson.put("entry_date", cc.getTextContent().toString() == null ? "" : cc.getTextContent().toString());
                            }
                            if (cc.getNodeName().trim().equals("pfif:author_name")) {
                                NoteJson.put("author_name", cc.getTextContent().toString() == null ? "" : cc.getTextContent().toString());
                            }
                            if (cc.getNodeName().trim().equals("pfif:source_date")) {
                                NoteJson.put("source_date", cc.getTextContent().toString() == null ? "" : cc.getTextContent().toString());
                            }
                            if (cc.getNodeName().trim().equals("pfif:last_known_location")) {
                                NoteJson.put("last_known_location", cc.getTextContent().toString() == null ? "" : cc.getTextContent().toString());
                            }
                            if (cc.getNodeName().trim().equals("pfif:text")) {
                                NoteJson.put("text", cc.getTextContent().toString() == null ? "" : cc.getTextContent().toString());
                            }
                            if (cc.getNodeName().trim().equals("pfif:photo_url")) {
                                NoteJson.put("photo_url", cc.getTextContent().toString() == null ? "" : cc.getTextContent().toString());
                            }

                        }

                    if (NoteJson.length()!=0) {
                        NoteArrayJson.put(NoteJson);
                    }
                    }
                    PeopleJson.put("note",NoteArrayJson== null ? "" : NoteArrayJson);
                }

            }
            if (PeopleJson.length()!=0) {
                peoplesArrayJson.put(PeopleJson);
            }
        }
        JSONObject peoplesJson = new JSONObject();
        peoplesJson.put("peoples", peoplesArrayJson);
        return peoplesJson;

    }

}
