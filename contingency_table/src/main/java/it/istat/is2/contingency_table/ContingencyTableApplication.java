package it.istat.is2.contingency_table;

import it.istat.is2.contingency_table.service.ContingencyTableService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
public class ContingencyTableApplication {

	private final ContingencyTableService contingencyTableService;

	@Autowired
	public ContingencyTableApplication(ContingencyTableService contingencyTableService) {
		this.contingencyTableService = contingencyTableService;
	}

	public static void main(String[] args) {
		SpringApplication.run(ContingencyTableApplication.class, args);
	}

	@StreamListener(Processor.INPUT)
	@SendTo(Processor.OUTPUT)
	public String process(String parameters) throws Exception {

		log.info("parameters received : {}", parameters);

		var id = Long.parseLong(parameters);
		this.contingencyTableService.contingecyTable(id);

		return parameters;
	}
}
