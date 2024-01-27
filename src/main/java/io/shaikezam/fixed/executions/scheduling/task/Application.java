package io.shaikezam.fixed.executions.scheduling.task;

import io.shaikezam.fixed.executions.scheduling.task.tasks.ISchedulerWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.logging.Logger;

@SpringBootApplication
public class Application implements CommandLineRunner {

    private static final Logger logger = Logger.getLogger(Application.class.getName());
    @Autowired
    private ISchedulerWrapper schedulerWrapper;
    @Value("${scheduler.task.delay:1000}")
    private long schedulerTaskMaxDelay;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) {
        logger.info("Application started...");
        schedulerWrapper.startSchedulerTask(schedulerTaskMaxDelay);
    }
}
