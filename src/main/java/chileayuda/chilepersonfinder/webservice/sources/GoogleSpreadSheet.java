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

/**
 * Clase que usa como fuente de datos un Spreadsheet de google 
 * para realizar consultas sobre la informacion que se vaya actualizando en linea.
 * 
 * Se necesita el key del spreadsheet que se usara como origen de datos.
 *
 */
//@Service("google-spreadsheetsSearcher")
public class GoogleSpreadSheet implements Searcher {

    private static final String FUENTE = "Google Docs"; //Google SpreadSheet
   // @Resource(name = "maxResults")
    private Integer maxResults = 0;

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
            String key = "0AtGZBDg1MfnrdHdVU2FoTnFPX3ZpVkxlRWR3TWg0S2c"; //SpreadSheet
            String out = "html"; //csv, tsv, html, json

            StringBuilder gQuery = new StringBuilder();
            gQuery.append("select * ");
            gQuery.append(" where upper(D) contains \"" + query.getName().toUpperCase() + "\"");
            if (query.getInfo() != null) {
                gQuery.append(" AND upper(E) contains \"" + query.getInfo().toUpperCase() + "\"");
            }

            String url = String.format(
                    "http://spreadsheets.google.com/tq?tqx=out:" + out + "&key=" + key + "&tq=%s", URLEncoder.encode(gQuery.toString(), "UTF-8"));

            Document document = PageGetter.getDocument(url);
            XPath xPath = XPathFactory.newInstance().newXPath();

            NodeList nodesEntries = (NodeList) xPath.evaluate(".//tr", document, XPathConstants.NODESET);
            Node nodeEntry;
            for (int i = 1; i < nodesEntries.getLength() && i < maxResults; i++) {
                nodeEntry = nodesEntries.item(i);

                String name = (String) xPath.evaluate(".//td[4]/text()", nodeEntry, XPathConstants.STRING);
                String info = ((String) xPath.evaluate(".//td[8]/text()", nodeEntry, XPathConstants.STRING)).replaceAll("\\p{Space}+", " ").replaceAll("Home address:", "").trim();
                String estado = ((String) xPath.evaluate(".//td[3]/text()", nodeEntry, XPathConstants.STRING)).replaceAll("\\p{Space}+", " ").replaceAll("^Status:", "").trim();

                if (estado.equals("")) {
                    estado = ((String) xPath.evaluate(".//td[6]/text()", nodeEntry, XPathConstants.STRING)).replaceAll("\\p{Space}+", " ").trim();
                }

                String enlace = ""; //Definir que url se debe mostrar, de aplicar.

                if (showName) {
                    results.add(new Result("2000-01-01T00:00:00Z", name, info, estado, FUENTE, enlace));
                } else {
                    results.add(new Result("2000-01-01T00:00:00Z", name + ", " + info, estado, FUENTE, enlace));
                }

            }
        } catch (Exception exception) {
            throw new SearcherException(exception);
        }
        return results;
    }
}
