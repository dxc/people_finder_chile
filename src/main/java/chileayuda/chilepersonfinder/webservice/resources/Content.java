package chileayuda.chilepersonfinder.webservice.resources;

import chileayuda.chilepersonfinder.webservice.sources.Searcher;
import chileayuda.chilepersonfinder.webservice.sources.SearcherException;

import java.util.PriorityQueue;

import java.io.UnsupportedEncodingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service("content")
public class Content {

    @Autowired
    ApplicationContext context = null;

    public byte[] getContent(String source, String jsoncallback, Query query,
            boolean showName) throws SearcherException, UnsupportedEncodingException {
        Searcher searcher = (Searcher)this.context.getBean(source + "Searcher");
        if (searcher == null) {
            return null;
        }
        PriorityQueue<Result> results = searcher.search(query, showName);
        String s = jsoncallback + "([";
        String sep = "";
        for (Result result : results) {
            s += sep + result.toJSon();
            sep = ",";
        }
        s += "])";

        return s.getBytes("UTF-8");
    }
}
