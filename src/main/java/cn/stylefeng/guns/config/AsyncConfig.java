package cn.stylefeng.guns.config;


import java.util.concurrent.Executor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * @Title: AsyncConfig.java
 * @Package com.zhy.springboot.scheduler
 * @Description: 定时任务多线程执行
 * @author John_Hawkings
 * @date 2018年12月25日
 * @version V1.0
 */
@Configuration
@EnableAsync
public class AsyncConfig {

    @Value("${task.corePoolSize}")
    private int corePoolSize;
    @Value("${task.maxPoolSize}")
    private int maxPoolSize;
    @Value("${task.queueCapacity}")
    private int queueCapacity;
    @Value("${task.keepAliveSeconds}")
    private int keepAliveSeconds;

    @Bean
    public Executor taskExecutor() {
		/*
		 * 如果线程池中的数量小于corePoolSize，即使线程池中的线程都处于空闲状态，也要创建新的线程来处理被添加的任务。
               如果此时线程池中的数量等于 corePoolSize，但是缓冲队列 workQueue未满，那么任务被放入缓冲队列。
                如果此时线程池中的数量大于corePoolSize，缓冲队列workQueue满，并且线程池中的数量小于maxPoolSize，建新的线程来处理被添加的任务。
                如果此时线程池中的数量大于corePoolSize，缓冲队列workQueue满，并且线程池中的数量等于maxPoolSize，
                那么通过handler所指定的策略来处理此任务。也就是：处理任务的优先级为：核心线程corePoolSize、任务队列workQueue、最大线程 maximumPoolSize，
                如果三者都满了，使用handler处理被拒绝的任务。
                 当线程池中的线程数量大于corePoolSize时，如果某线程空闲时间超过keepAliveTime，线程将被终止。这样，线程池可以动态的调整池中的线程数。
		 */
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        //线程池维护线程的最少数量
        executor.setCorePoolSize(corePoolSize);
        //线程池维护线程的最大数量
        executor.setMaxPoolSize(maxPoolSize);
        //缓存队列
        executor.setQueueCapacity(queueCapacity);
        //允许的空闲时间
        executor.setKeepAliveSeconds(keepAliveSeconds);
        executor.initialize();
        return executor;
    }
}
