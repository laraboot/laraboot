package cn.laraboot.app.listeners;

import cn.laraboot.app.events.ExampleEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class ExampleListener implements ApplicationListener<ExampleEvent> {

    @Override
    public void onApplicationEvent(ExampleEvent exampleEvent) {
        System.out.println("事件触发了");
        System.out.println(exampleEvent);
    }
}
