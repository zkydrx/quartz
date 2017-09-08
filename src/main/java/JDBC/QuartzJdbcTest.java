package JDBC;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.List;

/**
 * Created by ZKY on 2017-08-25 11:59.
 */
public class QuartzJdbcTest
{
    public static void main(String[] args)
    {
        startSchedule();
    }

    /**
     * 开始一个simpleSchedule()调度
     */
    public static void startSchedule()
    {
        try
        {
            //1.创建一个JobDetail实例，指定Quartz
            //任务执行类
            JobDetail jobDetail = JobBuilder.newJob(MyJob.class)
                    //任务名，任务组
                    .withIdentity("job1_1","jGroup1")
                    .storeDurably(true)
                    .build();
            //触发器类型
            SimpleScheduleBuilder builder = SimpleScheduleBuilder
                    //设置执行次数
                    .repeatSecondlyForTotalCount(5);
            //2.创建Trigger
            Trigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity("trigger1_1","tGroup1")
                    .startNow()
                    .withSchedule(builder)
                    .build();

            //3.创建Scheduler
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            scheduler.start();

            //4.调度执行
            scheduler.scheduleJob(jobDetail,trigger);
            try
            {
                Thread.sleep(60000);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            //5.关闭调度器
            scheduler.shutdown();
        }
        catch (SchedulerException e)
        {
            e.printStackTrace();
        }
    }


    public static void resumeJob()
    {
        try
        {
            SchedulerFactory schedulerFactory = new StdSchedulerFactory();
            Scheduler scheduler = schedulerFactory.getScheduler();
            JobKey jobKey = new JobKey("job1_1","jGroup1");
            List<? extends Trigger> triggers =scheduler.getTriggersOfJob(jobKey);
            //重新恢复在jGroup1组中，名为job1_1的job的触发器运行
            if(triggers.size() > 0)
            {
                for (Trigger tg: triggers)
                {
                    //根据类型判断
                    if((tg instanceof CronTrigger) || (tg instanceof SimpleTrigger))
                    {
                        //恢复job运行
                        scheduler.resumeJob(jobKey);
                    }
                }
                scheduler.start();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
