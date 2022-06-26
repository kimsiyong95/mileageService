package com.triple.milegeservice.repository;

import com.triple.milegeservice.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, String>, RepositoryCustom{
}
