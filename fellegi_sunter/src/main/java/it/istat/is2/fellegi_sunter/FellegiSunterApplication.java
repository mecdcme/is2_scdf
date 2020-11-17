package it.istat.is2.fellegi_sunter;

import it.istat.is2.fellegi_sunter.service.FellegiSunterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.messaging.handler.annotation.SendTo;

@ComponentScan("it.istat")
@SpringBootApplication
@EnableBinding(Processor.class)
@Slf4j
public class FellegiSunterApplication {

    private final FellegiSunterService fellegiSunterService;

    public FellegiSunterApplication(FellegiSunterService fellegiSunterService) {
        this.fellegiSunterService = fellegiSunterService;
    }

    public static void main(String[] args) {
        SpringApplication.run(FellegiSunterApplication.class, args);
    }

    @StreamListener(Processor.INPUT)
    @SendTo(Processor.OUTPUT)
    public String process(String parameters) throws Exception {

        log.info("parameters received : {}", parameters);

        var id = Long.parseLong(parameters);
        this.fellegiSunterService.fellegiSunter(id);

        return parameters;
    }

}
