package chileayuda.chilepersonfinder.webservice.sources;

import chileayuda.chilepersonfinder.webservice.resources.Query;
import chileayuda.chilepersonfinder.webservice.resources.Result;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import java.net.URLEncoder;
import java.util.PriorityQueue;

//@Service("googleSearcher")
public class Google implements Searcher {

    //@Resource(name = "maxResults")
    private Integer maxResults = 0;


    public static void main(String[] args) throws Exception {
       PriorityQueue<Result> results = new PriorityQueue<Result>();
       // PriorityQueue<Result> results = search("sasas",true);//new PriorityQueue<Result>();
        for (Result result : results) {
            System.out.println(result.toJSon());
        }
    }

    public PriorityQueue<Result> search(Query query, boolean showName)
            throws SearcherException {

        PriorityQueue<Result> results = new PriorityQueue<Result>();
        try {
            String url = String.format(
                    "http://chileagils.appspot.com/results?role=seek&query=%s", URLEncoder.encode(query.getAll(), "UTF-8"));

            Document document = PageGetter.getDocument(url);
            XPath xPath = XPathFactory.newInstance().newXPath();

            NodeList nodesEntries = (NodeList) xPath.evaluate(".//li[@class='resultItem']", document, XPathConstants.NODESET);
            Node nodeEntry;
            for (int i = 0; i < nodesEntries.getLength() && i < maxResults; i++) {
                nodeEntry = nodesEntries.item(i);
                String name = (String) xPath.evaluate(".//span[@class='resultDataTitle']/text()", nodeEntry, XPathConstants.STRING);
                String estado = ((String) xPath.evaluate(".//li[2]/text()", nodeEntry, XPathConstants.STRING)).replaceAll("\\p{Space}+", " ").replaceAll("^Status:", "").trim();
                if (estado.equals("")) {
                    estado = ((String) xPath.evaluate(".//li[2]/span/text()", nodeEntry, XPathConstants.STRING)).replaceAll("\\p{Space}+", " ").trim();
                }
                String info = ((String) xPath.evaluate(".//li[1]/text()", nodeEntry, XPathConstants.STRING)).replaceAll("\\p{Space}+", " ").replaceAll("Home address:", "").trim();
                if (estado.equals("")) {
                    estado = info.replaceAll("^Status:", "").trim();
                    if (estado.equals("")) {
                        estado = ((String) xPath.evaluate(".//li[1]/span/text()", nodeEntry, XPathConstants.STRING)).replaceAll("\\p{Space}+", " ").replaceAll("^Status:", "").trim();
                    }
                    info = "";
                }
                String fuente = "google";
                String enlace = "http://chileagils.appspot.com" + ((String) xPath.evaluate("./a/@href", nodeEntry, XPathConstants.STRING));

                if (showName) {
                    results.add(new Result("2000-01-01T00:00:00Z", name, info, estado, fuente, enlace));
                } else {
                    results.add(new Result("2000-01-01T00:00:00Z", name + ", " + info, estado, fuente, enlace));
                }

            }
        } catch (Exception exception) {
            throw new SearcherException(exception);
        }
        return results;
    }



}
