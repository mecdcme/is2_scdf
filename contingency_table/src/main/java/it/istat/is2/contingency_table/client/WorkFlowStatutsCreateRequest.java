package it.istat.is2.contingency_table.client;

import java.io.Serializable;

import it.istat.is2.contingency_table.client.WorkFlowMonitorClient.ProcessStatus;
import lombok.Data;
@Data
public class WorkFlowStatutsCreateRequest implements Serializable {

    public static final Long serialVersionUID = 1L;

     

    private Long workSessionId;
    private Long businessProcessId;
    private ProcessStatus status;
}