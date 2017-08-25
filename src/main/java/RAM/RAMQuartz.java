package RAM;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * Created by ZKY on 2017-08-25 00:51.
 */
public class RAMQuartz
{
    private static Logger logger = LoggerFactory.getLogger(RAMQuartz.class);

    public static void main(String[] args) throws SchedulerException
    {
        //1. 创建Scheduler的工厂
        SchedulerFactory sf = new StdSchedulerFactory();

        //2. 从工厂中获取调度器实例
        Scheduler scheduler = sf.getScheduler();

        JobDetail jb = JobBuilder.newJob(RAMJob.class)
                .withDescription(" this is a ram job")//job的描述
                .withIdentity("ramJob","ramGroup")//job的name和group
                .build();
        // 任务运行的时间，SimpleSchedle类型触发器有效
        Long time = System.currentTimeMillis() +3*1000L;//3秒后启动任务。
        Date statTime = new Date(time);

        //4. 创建Trigger
        // 使用SimpleScheduleBuilder或者CronScheduleBuilder
        Trigger t = TriggerBuilder.newTrigger()
                .withDescription("")
                .withIdentity("ramTrigger","ramTriggerGroup")
                //.withSchedule(SimpleScheduleBuilder.simpleSchedule())
                .startAt(statTime)// 默认当前时间启动。
                .withSchedule(CronScheduleBuilder.cronSchedule("0/2 * * * * ?"))//两秒执行一次
                .build();

        //5. 注册任务和定时器
        scheduler.scheduleJob(jb,t);

        //6. 启动调度器
        scheduler.start();
//        logger.info("启动时间："+ new Date());
    }
}
