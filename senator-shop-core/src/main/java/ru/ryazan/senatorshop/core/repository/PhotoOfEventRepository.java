package ru.ryazan.senatorshop.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ryazan.senatorshop.core.domain.admin.PhotoOfEvent;

public interface PhotoOfEventRepository extends JpaRepository<PhotoOfEvent, Long> {
}
