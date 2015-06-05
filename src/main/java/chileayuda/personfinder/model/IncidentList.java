package chileayuda.personfinder.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by teo on 05/06/15.
 */
public class IncidentList implements  Iterable<Incident> {
    List<Incident> incidents=new ArrayList<Incident>();

    public void add(Incident incident)
    {
        incidents.add(incident);
    }
    public boolean containsKey(String incidentId)
    {
        for(Incident incident:this)
        {
            if(incident.incidentId.equals(incidentId))
            {
                return true;
            }
        }
        return false;
    }
    public Incident get(String incidentId)
    {
        for(Incident incident : this)
        {
            if(incident.incidentId.equals(incidentId))
            {
                return incident;
            }
        }
        return null;
    }
    public Incident get(int index)
    {
        return incidents.get(index);
    }

    public Incident put(Incident incident)
    {
        if(!incidents.contains(incident))
        {
            incidents.add(incident);
        }
        return incident;
    }
    public void remove(String incidentId)
    {
        Incident incident=get(incidentId);
        if(incident!=null)
        {
            remove(incident);
        }
    }
    public void remove (Incident incident)
    {
        if(incident!=null)
        {
            incidents.remove(incident);
        }
    }


    public Iterator<Incident> iterator() {
        return incidents.iterator();
    }
}
