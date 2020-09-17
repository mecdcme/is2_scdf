package it.istat.is2.fellegi_sunter.response;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE)
public class TaskStatusResponse {
    @JsonProperty("processId")
    private Long processId;
    @JsonProperty("stepInstanceId")
    private Long stepInstanceId;
    @JsonProperty("status")
    private String status;
}
