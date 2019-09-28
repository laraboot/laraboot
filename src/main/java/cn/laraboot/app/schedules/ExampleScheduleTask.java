package cn.laraboot.app.schedules;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ExampleScheduleTask {

//    @Scheduled(fixedRate = 5000)
//    @Scheduled(fixedDelay = 5000)
//    @Scheduled(cron = "* * * * *")
    public void now() {
        System.out.println("The time is now ");
        System.out.println(new Date());
    }
}
