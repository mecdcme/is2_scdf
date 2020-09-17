package it.istat.is2.notificator.repository;

import it.istat.is2.notificator.domain.enums.entity.ProcessEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProcessRepository extends JpaRepository<ProcessEntity, Long> {

    List<ProcessEntity> findByProcessIdAndStepInstanceId(@Param("processId") Long processId, @Param("stepInstanceId") Long stepInstanceId);
}
