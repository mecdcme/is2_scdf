package it.istat.is2.contingency_table;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.cloud.stream.test.binder.MessageCollector;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ContingencyTableApplicationTests {

    @Autowired
    private Processor processor;

    @Autowired
    private MessageCollector messageCollector;

    @Test
    public void contextLoads() {
    }

    @Test
    public void testUsageCostProcessor() throws Exception {
        this.processor.input().send(MessageBuilder.withPayload("{\"id\":3,\"type\":\"prova\"}").build());
        Message message = this.messageCollector.forChannel(this.processor.output()).poll(1, TimeUnit.SECONDS);
        log.info("message = {}", message);
        assertTrue(message.getPayload().toString().contains("sampleString"));
    }

}
