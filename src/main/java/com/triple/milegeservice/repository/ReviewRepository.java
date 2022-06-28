package com.triple.milegeservice.repository;

import com.triple.milegeservice.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, String>, RepositoryCustom{

    @Query("select r from Review r where r.deleteYn = 'N' and r.reviewId = :id")
    Optional<Review> findByIdAndDeleteYn(@Param("id") String id);
}
