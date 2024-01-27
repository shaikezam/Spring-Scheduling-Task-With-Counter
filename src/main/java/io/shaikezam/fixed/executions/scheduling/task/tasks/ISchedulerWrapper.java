package io.shaikezam.fixed.executions.scheduling.task.tasks;

public interface ISchedulerWrapper {
    void startSchedulerTask(long delay);
    void cancelSchedulerTask();
}
