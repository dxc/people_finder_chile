package chileayuda.chilepersonfinder.webservice.sources;

import chileayuda.chilepersonfinder.webservice.resources.Query;
import chileayuda.chilepersonfinder.webservice.resources.Result;
import com.ibatis.sqlmap.client.SqlMapClient;
import org.apache.log4j.Logger;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import java.util.List;
import java.util.PriorityQueue;

//@Service("google-cladbSearcher")
public class GoogleChileAyudaDB extends SqlMapClientDaoSupport implements Searcher {

    private static final Logger logger = Logger.getLogger(GoogleChileAyudaDB.class);

  // @Resource(name = "ibatisSqlMap")
    public void setIbatisSqlMapClient(SqlMapClient client) {
        super.setSqlMapClient(client);
    }

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
            
            String queryName = query.getName();
            String queryInfo = query.getInfo();

            QueryBean queryBean = new QueryBean(queryName, queryInfo);

            String ibatisQuery = (queryInfo == null)?"dbQueryTemplateWithoutLocation":"dbQueryTemplate";

            List<QueryResult> rs = this.getSqlMapClient().queryForList(ibatisQuery, queryBean);

            for (QueryResult qrs: rs) {
                String name = qrs.getName();
                String estado = qrs.getEstado();
                if (estado == null) {
                    estado = "unknown";
                }
                String info = qrs.getInfo();
                String fuente = "google";
                String enlace = "http://chileagils.appspot.com/view?id=" + qrs.getId();
                if (showName) {
                    results.add(new Result("2000-01-01T00:00:00Z", name, info, estado, fuente, enlace));
                } else {
                    results.add(new Result("2000-01-01T00:00:00Z", name + " " + info, estado, fuente, enlace));
                }
            }
        } catch (Exception exception) {
            logger.error("Error al ejecutar metodo search", exception);
            throw new SearcherException(exception);
        }
        return results;
    }
}
