package com.triple.milegeservice.repository;

import com.triple.milegeservice.config.QueryDslConfigTest;
import com.triple.milegeservice.domain.Review;
import com.triple.milegeservice.domain.common.RequestDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import(QueryDslConfigTest.class)
class ReviewRepositoryTest {

    @Autowired
    private ReviewRepository reviewRepository;

    @DisplayName("리뷰리포 의존성 주입테스트")
    @Test
    public void reviewRepositoryIsNotNull(){
        assertThat(reviewRepository).isNotNull();
    }

    @DisplayName("리뷰 등록 테스트")
    @Test
    public void addReviewTest(){

        Review createReview = createReview(UUID.randomUUID().toString(),UUID.randomUUID().toString());

        Review saveReview = reviewRepository.save(createReview);


        assertThat(createReview.getUserId()).isEqualTo(saveReview.getUserId());
        assertThat(saveReview.getReviewId()).isNotNull();
        assertThat(reviewRepository.count()).isEqualTo(1);
    }

    @DisplayName("1사용자 1장소 테스트")
    @Test
    public void findByUserIdAndPlaceIdTest(){
        String placeId = UUID.randomUUID().toString();
        String userId = UUID.randomUUID().toString();

        Review review = createReview(placeId, userId);
        
        RequestDTO requestDTO = new RequestDTO();
        requestDTO.setPlaceId(placeId);
        requestDTO.setUserId(userId);

        Optional<Review> findReview = Optional.ofNullable(reviewRepository.findByUserIdAndPlaceId(requestDTO));

        assertThat(findReview).isNotNull();
    }


    @DisplayName("장소에 등록된 리뷰 수 테스트")
    @Test
    public void findPlaceCount(){
        String placeId = UUID.randomUUID().toString();

        RequestDTO requestDTO = new RequestDTO();
        requestDTO.setPlaceId(placeId);


        List<Review> reviews = new ArrayList<>();
        reviews.add(createReview(placeId, UUID.randomUUID().toString()));
        reviews.add(createReview(placeId, UUID.randomUUID().toString()));
        reviews.add(createReview(placeId, UUID.randomUUID().toString()));
        reviews.add(createReview(placeId, UUID.randomUUID().toString()));

        reviewRepository.saveAll(reviews);
        Long placeCount = reviewRepository.findPlaceCount(requestDTO);

        System.out.println(placeCount);
    }

    public Review createReview(String placeId, String userId){
        return Review.builder().reviewId(UUID.randomUUID().toString())
                               .content("내용")
                               .userId(userId)
                               .placeId(placeId)
                               .deleteYn("N")
                               .build();
    }

}