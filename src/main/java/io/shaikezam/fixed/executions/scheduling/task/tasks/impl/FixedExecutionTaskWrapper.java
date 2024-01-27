package io.shaikezam.fixed.executions.scheduling.task.tasks.impl;

import io.shaikezam.fixed.executions.scheduling.task.tasks.ISchedulerWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.TaskScheduler;

import java.util.concurrent.ScheduledFuture;
import java.util.logging.Logger;

@RequiredArgsConstructor
public class FixedExecutionTaskWrapper implements ISchedulerWrapper, Runnable {

    private static final Logger logger = Logger.getLogger(FixedExecutionTaskWrapper.class.getName());

    private ScheduledFuture<?> scheduledFuture;
    private int currentCounter;

    private final TaskScheduler taskScheduler;
    private final int maxRetries;

    @Override
    public void startSchedulerTask(long delay) {
        logger.info("Register scheduler task");
        this.scheduledFuture = taskScheduler.scheduleWithFixedDelay(
                this,
                delay
        );

    }

    @Override
    public void cancelSchedulerTask() {
        logger.info("Cancel scheduler task");
        if (this.scheduledFuture != null) {
            this.scheduledFuture.cancel(true);
            this.scheduledFuture = null;
        }

    }

    @Override
    public void run() {
        if (currentCounter < maxRetries) {
            logger.info("iteration " + currentCounter + " out of " + maxRetries);
        } else {
            this.cancelSchedulerTask();

            currentCounter = 0;
            return;
        }
        currentCounter++;
    }
}
