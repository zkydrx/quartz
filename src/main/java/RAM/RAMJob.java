package RAM;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * Created by ZKY on 2017-08-25 00:44.
 */
public class RAMJob implements Job
{
    private static Logger _log= LoggerFactory.getLogger(RAMJob.class);
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException
    {
        _log.info("Say Hello to Quartz "+ new Date());
    }
}
