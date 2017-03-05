package net.tuxun.customer.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 定时执行任务
 * @author liuqiang
 *
 */
@Component("scheduledTask")  
public class ScheduledTask {

  @Scheduled(cron = "0 0 3 * * ?")  
  public void job1() {
    // TODO your first task
  }
  
  public void job2() {
    // TODO your second task
  }
  
}
