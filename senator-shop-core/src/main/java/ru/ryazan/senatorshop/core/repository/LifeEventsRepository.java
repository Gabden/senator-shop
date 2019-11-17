package ru.ryazan.senatorshop.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ryazan.senatorshop.core.domain.admin.LifeEvents;

public interface LifeEventsRepository extends JpaRepository<LifeEvents, Long> {
}
