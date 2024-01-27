package io.shaikezam.fixed.executions.scheduling.task.configuration;

import io.shaikezam.fixed.executions.scheduling.task.tasks.ISchedulerWrapper;
import io.shaikezam.fixed.executions.scheduling.task.tasks.impl.FixedExecutionTaskWrapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@Configuration
public class SchedulerConfiguration {

    @Value("${scheduler.task.max.retries:6}")
    private int schedulerTaskMaxRetries;

    @Bean
    public TaskScheduler schedulerTaskScheduler() {
        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
        taskScheduler.setPoolSize(1); // Set the number of threads in the pool
        taskScheduler.setThreadNamePrefix("scheduler-task-");
        taskScheduler.setAwaitTerminationSeconds(60); // Set the time to wait for scheduled tasks to finish
        taskScheduler.setWaitForTasksToCompleteOnShutdown(true);

        return taskScheduler;
    }

    @Bean
    ISchedulerWrapper FixedExecutionTaskWrapper(TaskScheduler schedulerTaskScheduler) {

        return new FixedExecutionTaskWrapper(schedulerTaskScheduler, schedulerTaskMaxRetries);
    }

}
