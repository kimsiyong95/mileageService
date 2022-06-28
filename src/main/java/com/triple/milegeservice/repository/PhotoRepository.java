package com.triple.milegeservice.repository;

import com.triple.milegeservice.domain.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PhotoRepository extends JpaRepository<Photo, String> {

    //@Query("delete from Photo p where p.reviewPhotoId in (select p2.reviewPhotoId from Photo p2 join Review r on p2.review.reviewId = r.reviewId and r.reviewId = :reviewId)")
    @Modifying
    @Query("delete from Photo p where p.review.reviewId=:reviewId")
    public void deleteByReviewId(@Param("reviewId") String reviewId);
}
