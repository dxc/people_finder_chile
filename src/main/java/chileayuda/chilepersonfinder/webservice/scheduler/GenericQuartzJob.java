package chileayuda.chilepersonfinder.webservice.scheduler;

import java.lang.reflect.Method;
import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerContext;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.util.ReflectionUtils;

public class GenericQuartzJob extends QuartzJobBean
{
    protected Logger logger = Logger.getLogger(GenericQuartzJob.class);

    private String targetObject = null;
    private String targetMethod = null;

    protected void executeInternal(JobExecutionContext jobCtx) throws JobExecutionException {
        try {
            SchedulerContext schedCtx = jobCtx.getScheduler().getContext();
            ApplicationContext appCtx = (ApplicationContext)schedCtx.
                    get("applicationContext");
            Object service = appCtx.getBean(this.getTargetObject());
            Method method = service.getClass().getMethod(this.getTargetMethod());
            ReflectionUtils.invokeMethod(method, service);
        }
        catch (Exception ex) {
            logger.error("Unable to complete execution of " + targetObject + "." +
                    targetMethod, ex);
            throw new JobExecutionException("Unable to execute batch job: " +
                    targetObject + "." + targetMethod, ex);
        }
    }

    public String getTargetObject() {
        return targetObject;
    }

    public void setTargetObject(String targetObject) {
        this.targetObject = targetObject;
    }

    public String getTargetMethod() {
        return targetMethod;
    }

    public void setTargetMethod(String targetMethod) {
        this.targetMethod = targetMethod;
    }
}
