package chileayuda.personfinder.controllerClient;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by teo on 05/06/15.
 */
@Controller
public class PeopleControllerClient {
    @RequestMapping(value = "/people.do",method = RequestMethod.GET)
    public String getpeople(ModelMap modelMap)
    {
        return "people";
    }
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String getindex(ModelMap modelMap)
    {
        return "index";
    }
    @RequestMapping(value = "/agent",method = RequestMethod.GET)
    public String getagent(ModelMap modelMap)
    {
        return "agent";
    }
}
