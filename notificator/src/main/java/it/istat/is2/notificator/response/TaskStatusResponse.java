package it.istat.is2.notificator.response;

import it.istat.is2.notificator.domain.enums.TaskStatus;
import lombok.Builder;
import lombok.Data;


public class TaskStatusResponse {
    private Long processId;
    private Long stepInstanceId;
    private TaskStatus status;

    private TaskStatusResponse(Long processId, Long stepInstanceId, TaskStatus status) {
        this.processId = processId;
        this.stepInstanceId = stepInstanceId;
        this.status = status;
    }

    public Long getProcessId() {
        return processId;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public Long getStepInstanceId() {
        return stepInstanceId;
    }

    public static class Builder {
        private Long processId;
        private TaskStatus status;
        private Long stepInstanceId;

        public Builder processId(Long processId) {
            this.processId = processId;
            return this;
        }

        public Builder stepInstanceId(Long stepInstanceId) {
            this.stepInstanceId = stepInstanceId;
            return this;
        }

        public Builder status(TaskStatus status) {
            this.status = status;
            return this;
        }

        public TaskStatusResponse build() {
            return new TaskStatusResponse(this.processId, this.stepInstanceId, this.status);
        }
    }
}
