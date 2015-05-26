package chileayuda.chilepersonfinder.webservice.sources;

import chileayuda.chilepersonfinder.webservice.resources.Query;
import chileayuda.chilepersonfinder.webservice.resources.Result;

import java.util.PriorityQueue;

public interface Searcher {

    public PriorityQueue<Result> search(Query query,
                                        boolean showName) throws SearcherException;
}
