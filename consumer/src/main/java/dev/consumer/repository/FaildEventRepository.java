package dev.consumer.repository;

import dev.consumer.domain.FailedEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FaildEventRepository extends JpaRepository<FailedEvent, Long> {
}
