package com.triple.milegeservice.repository;

import com.triple.milegeservice.domain.History;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryRepository extends JpaRepository<History, String> {
}
