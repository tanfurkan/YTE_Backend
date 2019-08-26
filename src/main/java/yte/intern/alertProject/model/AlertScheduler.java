package yte.intern.alertProject.model;

import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;

@Component
public class AlertScheduler extends ThreadPoolTaskScheduler{

    Map<Long, ScheduledFuture<?>> jobsMap = new HashMap<>();

    public ScheduledFuture<?> scheduleWithFixedDelay(Long id,Runnable task, long delay) {
        System.out.println("A--PoolSize:"+this.getPoolSize());

        ScheduledFuture<?> futureTask = super.scheduleWithFixedDelay(task, delay);
        jobsMap.put(id,futureTask);

        return futureTask;
    }

    public void removeTask(Long id){
        ScheduledFuture<?> deletedTask = jobsMap.get(id);
        deletedTask.cancel(true);
        jobsMap.remove(id);
    }
    
}