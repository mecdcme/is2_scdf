package it.istat.is2.fellegi_sunter.client;

import java.io.Serializable;

import it.istat.is2.fellegi_sunter.client.WorkFlowMonitorClient.ProcessStatus;
import lombok.Data;

@Data
public class WorkFlowStatutsCreateRequest implements Serializable {

	private static final long serialVersionUID = -760517445633731785L;

	private Long workSessionId;
	private Long businessProcessId;
	private ProcessStatus status;
}