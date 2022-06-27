package com.triple.milegeservice.service;


import com.triple.milegeservice.domain.History;
import com.triple.milegeservice.domain.Photo;
import com.triple.milegeservice.domain.UserPoint;
import com.triple.milegeservice.domain.common.RequestDTO;
import com.triple.milegeservice.domain.common.ResponseDTO;
import com.triple.milegeservice.domain.Review;
import com.triple.milegeservice.repository.HistoryRepository;
import com.triple.milegeservice.repository.ReviewRepository;
import com.triple.milegeservice.repository.UserPointRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.triple.milegeservice.domain.common.HttpStatusCustom.*;

@Service
@RequiredArgsConstructor
public class MileageService {

    private final ReviewRepository reviewRepository;
    private final UserPointRepository userPointRepository;

    private final HistoryRepository historyRepository;

    @Transactional
    public ResponseDTO addReview(RequestDTO requestDTO){
        Optional<Review> findReview = Optional.ofNullable(reviewRepository.findByUserIdAndPlaceId(requestDTO));

        findReview.ifPresent(review->{
            throw new IllegalStateException(DUPLACATE_DATA.getMessage());
        });

        int point = createPoint(requestDTO);

        List<Photo> photos = Photo.createPhotos(requestDTO);

        Review saveReview = reviewRepository.save(Review.createReview(requestDTO, photos, point));

        UserPoint findUser = getUser(requestDTO);

        History createHistory = History.createHistory(requestDTO, findUser.getPoint(), point);

        findUser.addPoint(point);

        historyRepository.save(createHistory);

        return new ResponseDTO().setStatus(HttpStatus.CREATED, saveReview.getReviewId());
    }


    public UserPoint getUser(RequestDTO requestDTO){
        Optional<UserPoint> findUserPoint = userPointRepository.findById(requestDTO.getUserId());

        UserPoint userPoint;
        if(!findUserPoint.isPresent()){
            return userPointRepository.save(UserPoint.createUserPoint(requestDTO));
        }

        return findUserPoint.get();
    }

    public int createPoint(RequestDTO requestDTO){
        int totPoint = 0;

        if(reviewRepository.findPlaceCount(requestDTO) == 0){
            totPoint++;
        }

        if(requestDTO.getContent().length() > 0){
            totPoint++;
        }

        if(requestDTO.getAttachedPhotoIds().size() > 0){
            totPoint++;
        }

        return totPoint;
    }


    @Transactional
    public ResponseDTO modReview(RequestDTO requestDTO){
        return new ResponseDTO().setStatus(HttpStatus.OK, "");
    }

    @Transactional
    public ResponseDTO deleteReview(RequestDTO requestDTO){

        return new ResponseDTO().setStatus(HttpStatus.OK, "");
    }


}
