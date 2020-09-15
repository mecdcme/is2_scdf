package it.istat.is2.notificator.repository;

import it.istat.is2.notificator.domain.enums.entity.ProcessEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProcessRepository extends JpaRepository<ProcessEntity, Long> {
}
