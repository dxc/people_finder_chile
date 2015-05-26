package chileayuda.chilepersonfinder.webservice.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import net.htmlparser.jericho.Element;
import org.apache.commons.lang.StringUtils;

public class Utils {

    private static SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

    public static  String getStackTrace(Exception e) {
        StackTraceElement[] x = e.getStackTrace();
        String s = e + "";
        for (int i = 0; i < x.length; i++) {
            s += "\n" + x[i].toString();
        }
        return s;
    }

    public static Date parseDate(String date) {
        Date result = null;
        try {
            return sdf.parse(date);
        } catch (Exception e) {
            // Nada por ahora
        }
        return result;
    }

    public static String getValue(Element element) {
        return StringUtils.trim(element.getContent().toString());
    }

    public static String formatName(Element element) {
        String result = getValue(element);
        result = StringUtils.capitalize(StringUtils.lowerCase(result));
        return result;
    }
}
