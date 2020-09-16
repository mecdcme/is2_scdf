package it.istat.is2.contingency_table;

import it.istat.is2.contingency_table.input.TaskDetail;
import it.istat.is2.contingency_table.output.ContingencyTableOutput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.messaging.handler.annotation.SendTo;

import java.util.HashMap;

@SpringBootApplication
@EnableBinding(Processor.class)
@Slf4j
public class ContingencyTableApplication {

	public static void main(String[] args) {
		SpringApplication.run(ContingencyTableApplication.class, args);
	}

	@StreamListener(Processor.INPUT)
	@SendTo(Processor.OUTPUT)
	public ContingencyTableOutput process(TaskDetail usageDetail) {

		ContingencyTableOutput output = new ContingencyTableOutput();
		output.setId(1L);
		output.setSampleString("stringa casuale");
		output.setData(new HashMap<>());
		output.getData().put(1L, "S1");
		output.getData().put(2L, "S2");
		output.getData().put(3L, "S3");
		output.getData().put(4L, "S4");

		return output;
	}
}
