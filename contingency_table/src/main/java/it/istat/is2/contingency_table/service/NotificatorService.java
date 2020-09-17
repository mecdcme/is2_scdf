package it.istat.is2.contingency_table.service;

import it.istat.is2.contingency_table.interceptors.LoggingRequestInterceptor;
import it.istat.is2.contingency_table.response.TaskStatus;
import it.istat.is2.contingency_table.response.TaskStatusRequest;
import it.istat.is2.contingency_table.response.TaskStatusResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
