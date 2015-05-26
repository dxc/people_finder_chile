package chileayuda.chilepersonfinder.webservice.sources;

public class QueryBean {

    private String queryName = null;
    private String queryInfo = null;

    public QueryBean() {

    }

    public QueryBean(String queryName, String queryInfo) {
        this.queryInfo = queryInfo;
        this.queryName = queryName;
    }

    public String getQueryName() {
        return queryName;
    }

    public void setQueryName(String queryName) {
        this.queryName = queryName;
    }

    public String getQueryInfo() {
        return queryInfo;
    }

    public void setQueryInfo(String queryInfo) {
        this.queryInfo = queryInfo;
    }

}
