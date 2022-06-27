package com.triple.milegeservice.repository;

import com.triple.milegeservice.config.QueryDslConfigTest;
import com.triple.milegeservice.domain.UserPoint;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

@DataJpaTest
@Import(QueryDslConfigTest.class)
class UserPointRepositoryTest {

    @Autowired
    private UserPointRepository userPointRepository;


    @DisplayName("유저포인트 리포 테스트")
    @Test
    public void userPointRepositoryIsNotNull(){
        assertThat(userPointRepository).isNotNull();
    }

    @DisplayName("유저포인트 등록 테스트")
    @Test
    public void addUserPointTest(){
        UserPoint createUserPoint = UserPoint.builder().userId(UUID.randomUUID().toString())
                                                 .point(10)
                                                 .build();

        UserPoint saveUserPoint = userPointRepository.save(createUserPoint);


        assertThat(createUserPoint.getUserId()).isEqualTo(saveUserPoint.getUserId());
        assertThat(saveUserPoint.getPoint()).isEqualTo(10);
        assertThat(userPointRepository.count()).isEqualTo(1);

    }

    @DisplayName("유저포인트 조회 테스트")
    @Test
    public void findUserPointTest(){
        UserPoint createUserPoint = UserPoint.builder().userId(UUID.randomUUID().toString())
                                                       .point(10)
                                                       .build();

        UserPoint saveUserPoint = userPointRepository.save(createUserPoint);

        Optional<UserPoint> findByUserPoint = userPointRepository.findById(saveUserPoint.getUserId());

        if(findByUserPoint.isPresent()){
            assertThat(findByUserPoint).isNotNull();
            assertThat(createUserPoint.getUserId()).isEqualTo(findByUserPoint.get().getUserId());

            return;
        }

        fail("조회 실패");




    }

}