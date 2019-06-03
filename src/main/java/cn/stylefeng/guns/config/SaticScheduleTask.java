package cn.stylefeng.guns.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Async
@Component
@Configuration      //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling   // 2.开启定时任务
public class SaticScheduleTask {

    private static Logger logger= LoggerFactory.getLogger(SaticScheduleTask.class);

    //3.添加定时任务

    //或直接指定时间间隔，例如：5秒
    //@Scheduled(fixedRate=5000)
    @Async
    @Scheduled(cron = "${timedTask.task1}")
    public void configureTasks() {
        logger.info("执行静态定时任务时间1: " + LocalDateTime.now());
    }

    @Async
    @Scheduled(cron = "${timedTask.task2}")
    public void configureTasks2() { logger.info("执行静态定时任务时间2: " + LocalDateTime.now());}

}
