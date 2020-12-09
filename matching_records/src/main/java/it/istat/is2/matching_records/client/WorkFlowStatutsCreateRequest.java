package it.istat.is2.matching_records.client;

import java.io.Serializable;

import it.istat.is2.matching_records.client.WorkFlowMonitorClient.ProcessStatus;
import lombok.Data;

@Data
public class WorkFlowStatutsCreateRequest implements Serializable {

	private static final long serialVersionUID = -760512245633732785L;

	private Long workSessionId;
	private Long businessProcessId;
	private ProcessStatus status;
}