package it.istat.is2.notificator.domain.enums.entity;

import it.istat.is2.notificator.domain.enums.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "process")
public class ProcessEntity extends BaseEntity {

    @Id @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "processId")
    private Long processId;

    @Column(name = "status")
    private Integer status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProcessId() {
        return processId;
    }

    public void setProcessId(Long processId) {
        this.processId = processId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
