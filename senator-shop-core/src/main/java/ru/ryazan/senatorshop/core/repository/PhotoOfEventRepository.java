package ru.ryazan.senatorshop.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ryazan.senatorshop.core.domain.admin.PhotoOfEvent;

import java.util.List;

public interface PhotoOfEventRepository extends JpaRepository<PhotoOfEvent, Long> {
    List<PhotoOfEvent> findByLifeEvents_Id(Long id);
    List<PhotoOfEvent> findAllByLifeEvents_Id(Long id);
}
