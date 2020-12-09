package it.istat.is2.matching_records.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import it.istat.is2.matching_records.client.WorkFlowMonitorClient;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class NotificatorService {

	private final WorkFlowMonitorClient client;
	private final RestTemplate restTemplate;

	@Autowired
	public NotificatorService(WorkFlowMonitorClient client, RestTemplate restTemplate) {
		this.client = client;
		this.restTemplate = restTemplate;
	}

	public void createTask(Long idElaborazione, Long idStepInstance) {
		this.callNotificator(idElaborazione, idStepInstance, 10);
	}

	public void runningTask(Long idElaborazione, Long idStepInstance) {
		this.callNotificator(idElaborazione, idStepInstance, 20);
	}

	public void endTask(Long idElaborazione, Long idStepInstance) {
		this.callNotificator(idElaborazione, idStepInstance, 30);
	}

	public void errorTask(Long idElaborazione, Long idStepInstance) {
		this.callNotificator(idElaborazione, idStepInstance, 900);
	}

	private void callNotificator(Long idElaborazione, Long idStepInstance, int codeStatus) {
		this.client.updateStatus(codeStatus, idElaborazione, idStepInstance);
	}
}
