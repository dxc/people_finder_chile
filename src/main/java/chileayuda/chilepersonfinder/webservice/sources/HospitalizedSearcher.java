package chileayuda.chilepersonfinder.webservice.sources;

import chileayuda.chilepersonfinder.webservice.resources.Query;
import chileayuda.chilepersonfinder.webservice.resources.Result;
import com.ibatis.sqlmap.client.SqlMapClient;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import java.util.List;
import java.util.PriorityQueue;

//@Service("hospitalizedSearcher")
public class HospitalizedSearcher extends SqlMapClientDaoSupport implements Searcher {

    //@Resource(name = "ibatisSqlMap")
    public void setIbatisSqlMapClient(SqlMapClient client) {
        super.setSqlMapClient(client);
    }

    public PriorityQueue<Result> search(Query query, boolean showName) throws SearcherException {
        PriorityQueue<Result> results = new PriorityQueue<Result>();
        try {
            String queryName = query.getName();
            String queryInfo = query.getInfo();
            QueryBean queryBean = new QueryBean(queryName, queryInfo);

            List<HospitalizedRow> rs = this.getSqlMapClient().queryForList("findHospitalizedByMatch", queryBean);

            for (HospitalizedRow hr: rs) {
                String fuente = "registrohospitalizados";
                String enlace = hr.getUrl();
                String name = hr.getName();
                String info = hr.getHospitalName();
                String estado = "Hospitalizado";
                if (showName) {
                    results.add(new Result("2000-01-01T00:00:00Z", name, info, estado, fuente, enlace));
                } else {
                    results.add(new Result("2000-01-01T00:00:00Z", name + " " + info, estado, fuente, enlace));
                }
            }

        } catch (Exception e) {
            logger.error("Error al ejecutar metodo search", e);
            throw new SearcherException(e);
        }
        return results;
    }
}
