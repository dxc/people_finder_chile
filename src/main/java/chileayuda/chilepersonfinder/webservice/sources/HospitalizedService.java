package chileayuda.chilepersonfinder.webservice.sources;

import chileayuda.chilepersonfinder.webservice.utils.Utils;
import com.ibatis.sqlmap.client.SqlMapClient;
import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.HTMLElementName;
import net.htmlparser.jericho.Source;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.net.URL;
import java.util.Date;
import java.util.List;

/* Servicio que se encarga de leer los hospitalizados e insertarlos en el modelo
 * local
 * TODO: El modelo deberia ser normalizado. Los hospitales ser una entidad y creo
 * que la URL tambien. 
 */
//@Service("hospitalizedService")
public class HospitalizedService extends SqlMapClientDaoSupport {

    private static final Logger logger = Logger.getLogger(HospitalizedService.class);
    //@Resource(name = "hospitalizedURL")
    private String hospitalizedURL = null;
    @Resource(name = "hospitalizedPerPage")
    private Integer hospitalizedPerPage = null;

    @Resource(name = "ibatisSqlMap")
    public void setIbatisSqlMapClient(SqlMapClient client) {
        super.setSqlMapClient(client);
    }

    private List<Element> getRows(Source src) {
        List<Element> rows = src.getAllElements(HTMLElementName.TR);
        return (List<Element>) CollectionUtils.select(rows, new Predicate() {

            public boolean evaluate(Object o) {
                Element element = (Element) o;
                return StringUtils.startsWith(element.getAttributeValue("onmouseover"),
                        "setPointer");
            }
        });
    }

    @Transactional
    public boolean loadInfoOffset(int offset) throws Exception {
        String url = hospitalizedURL + offset;
        Source src = new Source(new URL(url));
        src.fullSequentialParse();
        List<Element> rows = getRows(src);
        for (Element element : rows) {

            List<Element> columns = element.getAllElements(HTMLElementName.TD);
            String rut = Utils.getValue(columns.get(0));
            String name = Utils.formatName(columns.get(1));
            name += (" " + Utils.formatName(columns.get(2)));
            name += (" " + Utils.formatName(columns.get(3)));
            name = StringUtils.trim(name);
            Date date = Utils.parseDate(Utils.getValue(columns.get(4)));
            String hospital = Utils.getValue(columns.get(5));

            HospitalizedRow hospitalizedRow = new HospitalizedRow(rut, name, hospital,
                    url, date);

            List<HospitalizedRow> stored = (List<HospitalizedRow>) this.getSqlMapClient().
                    queryForList("findHospitalizedByName", name);

            if (stored.isEmpty()) {
                logger.debug("Insertando " + hospitalizedRow);
                this.getSqlMapClient().insert("insertHospitalized", hospitalizedRow);
            } else {
                logger.debug("Registro ya existe : " + hospitalizedRow);
            }
        }
        return !rows.isEmpty();
    }

    public void loadInfo() throws Exception {
        int page = 0;
        boolean processCompleted = false;
        while (!processCompleted) {
            int pageOffSet = page * hospitalizedPerPage;
            processCompleted = !loadInfoOffset(pageOffSet);
            page++;
        }
    }
}
