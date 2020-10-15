package it.istat.is2.contingency_table.service;

import it.istat.is2.contingency_table.client.WorkFlowMonitorClient;
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
