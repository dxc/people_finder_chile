package chileayuda.chilepersonfinder.webservice.cache;

import java.io.Serializable;
import javax.annotation.Resource;
import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;

public class MethodCacheInterceptor implements MethodInterceptor, InitializingBean {

    private static final Logger logger = Logger.getLogger(MethodCacheInterceptor.class);
    @Resource(name = "cacheEnabled")
    private Boolean cacheEnabled = false;
    private Cache cache;

    public void setCache(Cache cache) {
        this.cache = cache;
    }

    public Object invoke(MethodInvocation invocation) throws Throwable {
        String targetName = invocation.getThis().getClass().getName();
        String methodName = invocation.getMethod().getName();
        Object result = null;
        if ("search".equals(methodName) && cacheEnabled) {
            Object[] arguments = invocation.getArguments();
            String cacheKey = getCacheKey(targetName, methodName, arguments);
            Element element = cache.get(cacheKey);
            if (element == null) {
                logger.debug("Obteniendo version nueva de resultados");
                result = invocation.proceed();
                element = new Element(cacheKey, (Serializable) result);
                cache.put(element);
            } else {
                logger.debug("Obteniendo version en cache de resultados");
                result = element.getValue();
            }
        } else {
            result = invocation.proceed();
        }
        return result;
    }

    private String getCacheKey(String targetName,
            String methodName,
            Object[] arguments) {
        StringBuffer sb = new StringBuffer();
        sb.append(targetName).append(".").append(methodName);
        if ((arguments != null) && (arguments.length != 0)) {
            for (int i = 0; i < arguments.length; i++) {
                sb.append(".").append(arguments[i]);
            }
        }

        return sb.toString();
    }

    public void afterPropertiesSet() throws Exception {
    }
}
