package it.istat.is2.matching_records;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.messaging.handler.annotation.SendTo;

import it.istat.is2.matching_records.bean.AuthenticationTokenHolder;
import it.istat.is2.matching_records.bean.StreamInvokeParameter;
import it.istat.is2.matching_records.service.MatchingRecordService;
import lombok.extern.slf4j.Slf4j;

@ComponentScan("it.istat")
@SpringBootApplication
@EnableBinding(Processor.class)
@Slf4j
public class MatchingRecordsApplication {

	private final MatchingRecordService matchingRecordService;

	@Autowired
	public MatchingRecordsApplication(MatchingRecordService matchingRecordService) {
		this.matchingRecordService = matchingRecordService;
	}

	public static void main(String[] args) {
		SpringApplication.run(MatchingRecordsApplication.class, args);
	}

	@StreamListener(Processor.INPUT)
	@SendTo(Processor.OUTPUT)
	public String process(String parameters) throws Exception {

		log.info("parameters received : {}", parameters);

		StreamInvokeParameter streamInvokeParameter = new StreamInvokeParameter(parameters);
		AuthenticationTokenHolder.getInstance().setAuthenticationToken(streamInvokeParameter.getAuthorizationToken());
		var id = streamInvokeParameter.getId();

		this.matchingRecordService.matchingRecord(id);

		return parameters;
	}
}
