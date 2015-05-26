package chileayuda.chilepersonfinder.webservice.servlets;

import chileayuda.chilepersonfinder.webservice.resources.Content;
import chileayuda.chilepersonfinder.webservice.resources.Query;
import chileayuda.chilepersonfinder.webservice.utils.Utils;

import java.io.OutputStream;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * Servlet que realiza la busqueda de personas por distintos mecanismos.
 * El mecanismo es resuelto dentro del contexto Spring usando la siguiente
 * regla:
 *
 * El parametro "source" corresponde al nombre del mecanismo requerido, luego,
 * un bean llamado source + "Searcher" es buscado dentro del contexto. Por ejemplo,
 * el mecanismo source = "google" es atendido por la clase Google marcada con el
 * nombre "googleSearcher" (ver anotacion Service)
 *
 */
public class SearchServlet extends HttpServlet {

    private final static Logger logger = Logger.getLogger(SearchServlet.class);

    public void doGet(HttpServletRequest request, HttpServletResponse response) {

        try {
            ServletContext servletContext = this.getServletContext();

            String name = request.getParameter("name");
            String pjsonId = request.getParameter("jsoncallback");
            if (name == null || pjsonId == null || (name = name.trim()).equals("")) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                return;
            }

            WebApplicationContext wac = WebApplicationContextUtils.
                    getRequiredWebApplicationContext(servletContext);
            String source = request.getParameter("source");
            if (source == null) {
                source = "google";
            }
            boolean showName = request.getParameter("showname") != null;

            Query query = new Query(name, request.getParameter("info"));
            long t0 = System.currentTimeMillis();
            Content contentObj = (Content)wac.getBean("content");
            byte[] content = contentObj.getContent(source, pjsonId, query, showName);
            if (content == null) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                return;
            }
            this.logger.info(String.format("QUERY: \"%s\"\tSearch time: %d(ms)", query, System.currentTimeMillis() - t0));
            response.setContentType("application/javascript; charset=utf8");
            response.setContentLength(content.length);
            OutputStream out = response.getOutputStream();
            out.write(content);
            out.flush();
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (Exception exception) {
            this.logger.error(Utils.getStackTrace(exception));
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }

    }
}
