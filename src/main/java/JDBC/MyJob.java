package JDBC;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ZKY on 2017-08-25 11:23.
 */
public class MyJob implements Job
{
    private static final Logger log = LoggerFactory.getLogger(MyJob.class);

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException
    {
        log.info("MyJob is start .........................");

        log.info("Hello quartz  "+
        new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        log.info("MyJob is end ......................");
    }
}
