package com.triple.milegeservice.repository;

import com.triple.milegeservice.domain.UserPoint;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserPointRepository extends JpaRepository<UserPoint, String> {
}
