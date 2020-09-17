package it.istat.is2.fellegi_sunter.service;


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

    @Value("${notificator.url}")
    private String notificatorUrl;

    @Autowired
    private RestTemplate restTemplate;

    public void createTask(Long idElaborazione, Long idStepInstance) {
        this.callNotificator(idElaborazione, idStepInstance, TaskStatus.CREATED);
    }

    public void runningTask(Long idElaborazione, Long idStepInstance) {
        this.callNotificator(idElaborazione, idStepInstance, TaskStatus.STARTED);
    }

    public void endTask(Long idElaborazione, Long idStepInstance) {
        this.callNotificator(idElaborazione, idStepInstance, TaskStatus.ENDED);
    }

    public void errorTask(Long idElaborazione, Long idStepInstance) {
        this.callNotificator(idElaborazione, idStepInstance, TaskStatus.ERROR);
    }

    private void callNotificator(Long idElaborazione, Long idStepInstance, TaskStatus status) {

        var object = new TaskStatusRequest();
        object.setIdElaborazione(idElaborazione);
        object.setIdStepInstance(idStepInstance);
        object.setTaskStatus(status);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<?> entity = new HttpEntity<>(object, headers);

        TaskStatusResponse response = restTemplate.exchange(this.notificatorUrl + "/task", HttpMethod.POST, entity, new ParameterizedTypeReference<TaskStatusResponse>() {
        }).getBody();


        log.info("reponse = {}", response);
    }
}
