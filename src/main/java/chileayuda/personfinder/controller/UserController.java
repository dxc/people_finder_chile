package chileayuda.personfinder.controller;

import chileayuda.personfinder.model.User;
import chileayuda.personfinder.service.PeopleFinder;
import chileayuda.personfinder.utils.NameValue;
import chileayuda.personfinder.utils.Utils;
import chileayuda.personfinder.utils.config.PeopleFinderBadRequestException;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by teo on 05/06/15.
 */
@Controller
public class UserController {
    protected static Logger logger = Logger.getLogger(UserController.class);
    public Utils util = new Utils();
    public PeopleFinder servicePeopleFinder;

    @RequestMapping(value = "/" ,produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String home() throws JSONException
    {
        PeopleFinder servicePeopleFinder = new PeopleFinder(true);

        JSONObject configJson = servicePeopleFinder.config.toJson();
        return configJson.toString();
    }
    @RequestMapping(value = "/users", method = RequestMethod.POST,  produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody String postUser(HttpServletRequest request) throws Exception {
        // User can specify parameters in JSON or as query parameters
        // Query overrides JSON if query parameter is non-null
        PeopleFinder servicePeopleFinder =new PeopleFinder(true);
         JSONObject userJson = util.getJsonRequest(request);
        String id = request.getParameter("id");
        if (id == null)
            id = userJson.optString("id");
        else
            userJson.put("id", id);
        String password = request.getParameter("password");
        if (password == null) {
            password = userJson.optString("password");
        }
        else {
            userJson.put("password", password);
        }

        String passwordHint = request.getParameter("password_hint");
        if (passwordHint == null)
            passwordHint = userJson.optString("password_hint");
        else
            userJson.put("password_hint", passwordHint);
        String fullName = request.getParameter("full_name");
        if (fullName == null)
            fullName = userJson.optString("full_name");
        else
            userJson.put("full_name", fullName);
        String displayName = request.getParameter("display_name");
        if (displayName == null)
            displayName = userJson.optString("display_name");
        else
            userJson.put("display_name", displayName);
        String nickName = request.getParameter("nick_name");
        if (nickName == null)
            nickName = userJson.optString("nick_name");
        else
            userJson.put("nick_name", nickName);
        String organization = request.getParameter("organization");
        if (organization == null)
            organization = userJson.optString("organization");
        else
            userJson.put("organization", organization);
        String bio = request.getParameter("bio");
        if (bio == null)
            bio = userJson.optString("bio");
        else
            userJson.put("bio", bio);
        String interests = request.getParameter("interests");
        if (interests == null)
            interests = userJson.optString("interests");
        else
            userJson.put("interests", interests);
        String incognitoString = request.getParameter("incognito");
        boolean incognito = incognitoString != null
                && (incognitoString.equalsIgnoreCase("true")
                || incognitoString.equalsIgnoreCase("yes") || incognitoString
                .equalsIgnoreCase("on"));
        if (incognitoString == null)
            incognito = userJson.optBoolean("incognito");
        else
            userJson.put("incognito", incognito ? "true" : false);
        String email = request.getParameter("email");
        if (email == null)
            email = userJson.optString("email");
        else
            userJson.put("email", email);
        String comment = request.getParameter("comment");
        if (comment == null)
            comment = userJson.optString("comment");
        else
            userJson.put("comment", comment);

        if (id == null) {
            throw new PeopleFinderBadRequestException(
                    "Missing id query parameter");
        } else if (id.trim().length() == 0) {
            throw new PeopleFinderBadRequestException(
                    "Empty id query parameter");
        } else if (id.trim().length() < User.MIN_ID_LENGTH) {
            throw new PeopleFinderBadRequestException(                    "Id must be at least 4 characters");
        } else if (password == null) {
            throw new PeopleFinderBadRequestException(
                    "Missing password query parameter");
        } else if (password.trim().length() == 0) {
            throw new PeopleFinderBadRequestException(
                    "Empty password query parameter");
        } else if (password.trim().length() < User.MIN_ID_LENGTH) {
            throw new PeopleFinderBadRequestException(
                    "Password must be at least 4 characters");
        } else if (servicePeopleFinder.users.containsKey(id.trim())) {
            throw new PeopleFinderBadRequestException(
                    "User with that id already exists");
        } else {
            id = id.trim();
            password = password.trim();
            logger.info("Adding new user: " + id);

            User newUser = new User(id, password, passwordHint, fullName,
                    displayName, nickName, organization, bio, interests,
                    email, incognito, comment, true, true, true, null,
                    null);
            newUser.generateSha();
            servicePeopleFinder.serviceUser.addUser(newUser);
            // TODO: Set Location header with URL
            JSONObject message = new JSONObject();
            message.put("message", "Save successful");
            return message.toString();
        }
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody String getUsers() throws Exception {
        PeopleFinder servicePeopleFinder =new PeopleFinder(true);
        JSONArray usersArrayJson = new JSONArray();
        for (NameValue<User> userIdValue : servicePeopleFinder.users) {
            User user = userIdValue.value;
            JSONObject userJson = new JSONObject();
            userJson.put("id", user.id);
            userJson.put("display_name", user.incognito ? "(Incognito)"
                    : (user.displayName == null ? "" : user.displayName));
            userJson.put("full_name", user.fullName);
            userJson.put("nick_name", user.nickName);
            userJson.put("email", user.email);

            usersArrayJson.put(userJson);
        }
        JSONObject usersJson = new JSONObject();
        usersJson.put("users", usersArrayJson);

        return  usersJson.toString();

    }
}
