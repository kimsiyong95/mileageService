package com.triple.milegeservice.repository;

import com.triple.milegeservice.config.QueryDslConfigTest;
import com.triple.milegeservice.domain.History;
import com.triple.milegeservice.domain.Review;
import com.triple.milegeservice.common.RequestDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import(QueryDslConfigTest.class)
class HistoryRepositoryTest {

    @Autowired
    private HistoryRepository historyRepository;

    @DisplayName("히스토리 리포 테스트")
    @Test
    public void historyRepositoryIsNotNull(){
        assertThat(historyRepository).isNotNull();
    }

    @DisplayName("히스토리 저장 테스트")
    @Test
    public void addHistoryTest(){

        RequestDTO requestDTO = new RequestDTO();

        Review review = Review.builder().reviewId(UUID.randomUUID().toString())
                                       .content("내용")
                                       .userId(UUID.randomUUID().toString())
                                       .placeId(UUID.randomUUID().toString())
                                       .deleteYn("N")
                                       .build();

        History createHistory = History.createHistory(requestDTO, 10, 3, review);

        History saveHistory = historyRepository.save(createHistory);

        assertThat(saveHistory).isNotNull();
        assertThat(saveHistory.getTotPoint()).isEqualTo(13);
    }
}