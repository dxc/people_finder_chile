package chileayuda.personfinder.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by teo on 05/06/15.
 */
public class PeopleList  implements Iterable<People>{
    List<People> peoples =new ArrayList<People>();

    public void add(People people)
    {
        peoples.add(people);
    }
    public boolean containsKey(String peopleId)
    {
        for(People people:this)
        {
            if(people.Id.equals(peopleId))
            {
                return true;
            }
        }
        return false;
    }
    public People get(String peopleId )
    {
        for(People people:this)
        {
            if(people.Id.equals(peopleId))
            {
                return people;
            }
        }
        return null;
    }
    public People get(int index)
    {
        return peoples.get(index);
    }

    public People put(People people)
    {
        if(!peoples.contains(people))
        {
            peoples.add(people);
        }
        return people;
    }
    public void remove(String peopleId)
    {
        People agentMessage=get(peopleId);
        if(agentMessage!=null)
        {
            remove(agentMessage);
        }
    }
    public void remove (People agentMessage)
    {
        if(agentMessage!=null)
        {
            peoples.remove(agentMessage);
        }
    }


    public Iterator<People> iterator() {
        return peoples.iterator();
    }
}
