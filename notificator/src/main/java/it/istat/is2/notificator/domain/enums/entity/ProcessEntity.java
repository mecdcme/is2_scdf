package it.istat.is2.notificator.domain.enums.entity;

import it.istat.is2.notificator.domain.enums.BaseEntity;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "process")
@Data
public class ProcessEntity extends BaseEntity {

    @Id @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "processId")
    private Long processId;

    @Column(name = "stepInstanceId")
    private Long stepInstanceId;

    @Column(name = "status")
    private Integer status;

}
