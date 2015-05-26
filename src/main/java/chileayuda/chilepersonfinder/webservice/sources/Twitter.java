package chileayuda.chilepersonfinder.webservice.sources;

import chileayuda.chilepersonfinder.webservice.resources.Query;
import chileayuda.chilepersonfinder.webservice.resources.Result;

import java.net.URLEncoder;


import java.util.PriorityQueue;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import org.springframework.stereotype.Service;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@Service("twitterSearcher")
public class Twitter implements Searcher {

    public static void main(String[] args) throws Exception {
        PriorityQueue<Result> results = new PriorityQueue<Result>();
        for (Result result : results) {
            System.out.println(result.toJSon());
        }
    }

    public PriorityQueue<Result> search(Query query, boolean showName)
            throws SearcherException {
        PriorityQueue<Result> results = new PriorityQueue<Result>();
        try {
            String url = String.format(
                    "http://search.twitter.com/search.atom?q=%s", URLEncoder.encode(query.getAll(), "UTF-8"));

            Document document = PageGetter.getDocument(url);
            XPath xPath = XPathFactory.newInstance().newXPath();

            NodeList nodesEntries = (NodeList) xPath.evaluate(".//entry", document, XPathConstants.NODESET);
            Node nodeEntry;
            for (int i = 0; i < nodesEntries.getLength(); i++) {
                nodeEntry = nodesEntries.item(i);
                String info = (String) xPath.evaluate(".//title/text()", nodeEntry, XPathConstants.STRING);
                String timestamp = (String) xPath.evaluate(".//published/text()", nodeEntry, XPathConstants.STRING);
                String fuente = (String) xPath.evaluate(".//author/name/text()", nodeEntry, XPathConstants.STRING);
                String enlace = (String) xPath.evaluate(".//author/uri/text()", nodeEntry, XPathConstants.STRING);
                String status = "";

                if (info != null) {
                    info = info.replaceAll("\"", "'").replaceAll("\\p{Space}+", " ").trim();
                }

                results.add(new Result(timestamp, info, status, fuente, enlace));
            }
        } catch (Exception exception) {
            throw new SearcherException(exception);
        }
        return results;
    }
}
