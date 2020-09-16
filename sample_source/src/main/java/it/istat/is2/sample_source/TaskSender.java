package it.istat.is2.sample_source;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Random;

@EnableScheduling
@EnableBinding(Source.class)
public class TaskSender {

    @Autowired
    private Source source;

    private String[] users = {"user1", "user2", "user3", "user4", "user5"};

    @Scheduled(fixedDelay = 1000)
    public void sendEvents() {
        TaskDetail usageDetail = new TaskDetail();

        usageDetail.setId(new Random().nextLong());
        usageDetail.setType(this.users[new Random().nextInt(5)]);
        this.source.output().send(MessageBuilder.withPayload(usageDetail).build());
    }

}
