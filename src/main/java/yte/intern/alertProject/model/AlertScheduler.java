package yte.intern.alertProject.model;

import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;

@Component
public class AlertScheduler extends ThreadPoolTaskScheduler{

    Map<Long, ScheduledFuture<?>> jobsMap = new HashMap<>();

    public void scheduleWithFixedDelay(Long id,Runnable task, long delay) {

        ScheduledFuture<?> futureTask = super.scheduleWithFixedDelay(task, delay);
        jobsMap.put(id,futureTask);

    }

    public void removeTask(Long id){
        ScheduledFuture<?> deletedTask = jobsMap.get(id);
        deletedTask.cancel(false);
        jobsMap.remove(id);
    }

}