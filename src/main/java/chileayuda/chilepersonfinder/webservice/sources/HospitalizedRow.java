package chileayuda.chilepersonfinder.webservice.sources;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class HospitalizedRow implements Serializable {

    private String rut = null;
    private String name = null;
    private String hospitalName = null;
    private String url = null;
    private Date date = null;

    public HospitalizedRow() {
        // Constructor vacio...
    }

    public HospitalizedRow(String rut, String name, String hospitalName,
            String url, Date date) {
        this.rut = rut;
        this.name = name;
        this.hospitalName = hospitalName;
        this.url = url;
        this.date = date;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this,
                ToStringStyle.SIMPLE_STYLE);
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
