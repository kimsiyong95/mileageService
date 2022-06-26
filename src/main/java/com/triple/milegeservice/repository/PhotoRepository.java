package com.triple.milegeservice.repository;

import com.triple.milegeservice.domain.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoRepository extends JpaRepository<Photo, String> {
}
