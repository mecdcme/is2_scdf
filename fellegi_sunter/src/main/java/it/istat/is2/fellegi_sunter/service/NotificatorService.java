package it.istat.is2.fellegi_sunter.service;


import it.istat.is2.fellegi_sunter.client.WorkFlowMonitorClient;
import it.istat.is2.fellegi_sunter.response.TaskStatus;
import it.istat.is2.fellegi_sunter.response.TaskStatusRequest;
import it.istat.is2.fellegi_sunter.response.TaskStatusResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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
