package it.istat.is2.contingency_table.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.Serializable;

@Component
public class WorkFlowMonitorClient {
    private final RestTemplate restTemplate;

    @Value("${workflow_monitor.url}")
    private String workflowMonitorUrl;


    public WorkFlowMonitorClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    static class WorkFlowStatutsCreateRequest implements Serializable {

        public static final Long serialVersionUID = 1L;

        private WorkFlowStatutsCreateRequest() {  }

        private Long workSessionId;
        private Long businessProcessId;
        private ProcessStatus status;
    }

    enum ProcessStatus {
        CREATED(10),
        STARTED(20),
        ENDED(30),
        ERROR(900);

        private int code;

        ProcessStatus(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }

        public static ProcessStatus fromCode(int code) {
            switch (code) {
                case 10:
                    return ProcessStatus.CREATED;
                case 20:
                    return ProcessStatus.STARTED;
                case 30:
                    return ProcessStatus.ENDED;
                case 900:
                    return ProcessStatus.ERROR;
                default:
                    throw new IllegalStateException("No values for code : " + code);
            }
        }
    }

    public void updateStatus(Integer processStatusCode, Long workSessionId, Long businessProcessId) {
        WorkFlowStatutsCreateRequest request = new WorkFlowStatutsCreateRequest();
        request.businessProcessId = businessProcessId;
        request.workSessionId = workSessionId;
        request.status = ProcessStatus.fromCode(processStatusCode);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<?> entity = new HttpEntity<>(request, headers);

        restTemplate.exchange(this.workflowMonitorUrl, HttpMethod.POST, entity, new ParameterizedTypeReference<Boolean>() { }).getBody();
    }
}
