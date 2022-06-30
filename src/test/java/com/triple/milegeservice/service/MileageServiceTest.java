package com.triple.milegeservice.service;

import com.triple.milegeservice.domain.History;
import com.triple.milegeservice.domain.Review;
import com.triple.milegeservice.domain.UserPoint;
import com.triple.milegeservice.domain.common.RequestDTO;
import com.triple.milegeservice.domain.common.ResponseDTO;
import com.triple.milegeservice.repository.HistoryRepository;
import com.triple.milegeservice.repository.ReviewRepository;
import com.triple.milegeservice.repository.UserPointRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class MileageServiceTest {

    @Mock
    private ReviewRepository reviewRepository;

    @Mock
    private UserPointRepository userPointRepository;

    @Mock
    private HistoryRepository historyRepository;

    @InjectMocks
    private MileageService mileageService;


    @DisplayName("리뷰등록 및 포인트 부여 로직 테스트")
    @Test
    public void addReviewTest(){
        List<String> photos = new ArrayList<>();
        photos.add(UUID.randomUUID().toString());

        RequestDTO requestDTO = makeRequestDto("ADD", photos);

        int point = mileageService.createPoint(requestDTO);
        Review saveReview = Review.createReview(requestDTO, point);
        UserPoint userPoint = UserPoint.createUserPoint(requestDTO);
        History createHistory = History.createHistory(requestDTO, userPoint.getPoint(), point, saveReview);

        when(reviewRepository.findByUserIdAndPlaceId(requestDTO)).thenReturn(null);
        when(reviewRepository.findPlaceCount(requestDTO)).thenReturn(0L);
        when(reviewRepository.save(any())).thenReturn(saveReview);
        when(userPointRepository.findById(requestDTO.getUserId())).thenReturn(Optional.ofNullable(userPoint));
        when(historyRepository.save(any())).thenReturn(createHistory);

        ResponseDTO responseDTO = mileageService.addReview(requestDTO);

        assertThat(responseDTO).isNotNull();
        assertThat(responseDTO.getCode()).isEqualTo(201);
        assertThat(responseDTO.getMessage()).isEqualTo("CREATED");
    }


    @DisplayName("리뷰 수정 및 포인트 부여 로직 테스트")
    @Test
    public void modReviewTest(){
        RequestDTO requestDTO = makeRequestDto("MOD", new ArrayList<>());

        List<String> photosId = new ArrayList<>();
        photosId.add(UUID.randomUUID().toString());
        RequestDTO updateDTO = makeRequestDto("MOD", photosId);
        updateDTO.setUserId(requestDTO.getUserId());

        Review findReview = Review.createReview(updateDTO, 3);
        UserPoint userPoint = UserPoint.createUserPoint(requestDTO);
        History createHistory = History.createHistory(requestDTO, userPoint.getPoint(), 3, findReview);

        when(reviewRepository.findByIdAndDeleteYn(any())).thenReturn(Optional.ofNullable(findReview));
        when(userPointRepository.findById(requestDTO.getUserId())).thenReturn(Optional.ofNullable(userPoint));
        when(historyRepository.save(any())).thenReturn(createHistory);

        ResponseDTO responseDTO = mileageService.modReview(requestDTO);

        assertThat(responseDTO).isNotNull();
        assertThat(responseDTO.getCode()).isEqualTo(201);
        assertThat(responseDTO.getMessage()).isEqualTo("CREATED");
    }

    @DisplayName("리뷰 삭제 및 포인트 회수 로직 테스트")
    @Test
    public void deleteReviewTest(){
        RequestDTO requestDTO = makeRequestDto("DELETE", new ArrayList<>());

        Review findReview = Review.createReview(requestDTO, 3);
        UserPoint userPoint = UserPoint.createUserPoint(requestDTO);
        History createHistory = History.createHistory(requestDTO, userPoint.getPoint(), 3, findReview);

        when(reviewRepository.findByIdAndDeleteYn(any())).thenReturn(Optional.ofNullable(findReview));
        when(userPointRepository.findById(any())).thenReturn(Optional.ofNullable(userPoint));
        when(historyRepository.save(any())).thenReturn(createHistory);

        ResponseDTO responseDTO = mileageService.deleteReview(requestDTO);

        assertThat(responseDTO).isNotNull();
        assertThat(responseDTO.getCode()).isEqualTo(200);
        assertThat(responseDTO.getMessage()).isEqualTo("OK");
    }

    @DisplayName("사용자 포인트 조회 테스트")
    @Test
    public void getPoints(){
        String userId = UUID.randomUUID().toString();
        RequestDTO requestDTO = new RequestDTO();
        requestDTO.setUserId(userId);

        UserPoint userPoint = UserPoint.createUserPoint(requestDTO);

        when(userPointRepository.findById(any())).thenReturn(Optional.ofNullable(userPoint));

        ResponseDTO responseDTO = mileageService.getPoints(userId);

        assertThat(responseDTO.getCode()).isEqualTo(200);
        assertThat(responseDTO.getMessage()).isEqualTo("OK");

    }


    public RequestDTO makeRequestDto(String action, List<String> photos){
        RequestDTO requestDTO = new RequestDTO();

        requestDTO.setType("REVIEW");
        requestDTO.setAction(action);
        requestDTO.setReviewId(UUID.randomUUID().toString());
        requestDTO.setContent("test");
        requestDTO.setAttachedPhotoIds(photos);
        requestDTO.setUserId(UUID.randomUUID().toString());
        requestDTO.setPlaceId(UUID.randomUUID().toString());

        return requestDTO;
    }

}