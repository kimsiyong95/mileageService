package com.triple.milegeservice.repository;

import com.triple.milegeservice.config.QueryDslConfigTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

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
}