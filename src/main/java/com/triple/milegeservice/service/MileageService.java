package com.triple.milegeservice.service;


import com.triple.milegeservice.domain.History;
import com.triple.milegeservice.domain.Photo;
import com.triple.milegeservice.domain.Review;
import com.triple.milegeservice.domain.UserPoint;
import com.triple.milegeservice.common.RequestDTO;
import com.triple.milegeservice.common.ResponseDTO;
import com.triple.milegeservice.repository.HistoryRepository;
import com.triple.milegeservice.repository.ReviewRepository;
import com.triple.milegeservice.repository.UserPointRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.triple.milegeservice.common.HttpStatusCustom.*;

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
            throw new IllegalArgumentException(DUPLACATE_DATA.getMessage());
        });

        int point = createPoint(requestDTO);

        Review saveReview = reviewRepository.save(Review.createReview(requestDTO, point));

        UserPoint findUser = getUser(requestDTO);

        History createHistory = History.createHistory(requestDTO, findUser.getPoint(), point, saveReview);

        findUser.addPoint(point);

        historyRepository.save(createHistory);

        return new ResponseDTO().setStatus(HttpStatus.CREATED, saveReview.getReviewId());
    }

    @Transactional
    public ResponseDTO modReview(RequestDTO requestDTO){
        Optional<Review> findReview = reviewRepository.findByIdAndDeleteYn(requestDTO.getReviewId());

        if(!findReview.isPresent()){
            throw new IllegalArgumentException(USER_PARAM_ERROR.getMessage());
        }

        authorizedCheck(findReview.get(),requestDTO.getUserId());


        int originPoint = findReview.get().getPoint();

        int updatePoint = updatePoint(requestDTO, findReview.get().getPhotos());

        findReview.get().updateReview(requestDTO, updatePoint);

        if(!findReview.get().pointIncreaseOrDecreaseCheck(originPoint)){

            UserPoint findUser = getUser(requestDTO);

            History createHistory = History.createHistory(requestDTO, findUser.getPoint(), updatePoint, findReview.get());

            findUser.addPoint(updatePoint);

            historyRepository.save(createHistory);
        }

        return new ResponseDTO().setStatus(HttpStatus.CREATED, findReview.get().getReviewId());
    }

    @Transactional
    public ResponseDTO deleteReview(RequestDTO requestDTO){
        Optional<Review> findReview = reviewRepository.findByIdAndDeleteYn(requestDTO.getReviewId());

        if(!findReview.isPresent()){
            throw new IllegalArgumentException(USER_PARAM_ERROR.getMessage());
        }

        authorizedCheck(findReview.get(),requestDTO.getUserId());



        findReview.get().deleteReview();

        if(!findReview.get().pointIncreaseOrDecreaseCheck(0)){
            int deletePoint = findReview.get().deletePoint();

            UserPoint findUser = getUser(requestDTO);

            History createHistory = History.createHistory(requestDTO, findUser.getPoint(), deletePoint, findReview.get());

            findUser.addPoint(deletePoint);

            historyRepository.save(createHistory);

        }

        return new ResponseDTO().setStatus(HttpStatus.OK, findReview.get().getReviewId());
    }

    @Transactional(readOnly = true)
    public ResponseDTO getPoints(String userId) {
        Optional<UserPoint> findUser = userPointRepository.findById(userId);

        if(!findUser.isPresent()){
            throw new IllegalArgumentException(USER_NOT_FOUND.getMessage());
        }

        return new ResponseDTO().setStatus(HttpStatus.OK, findUser.get().EntityToDto());
    }

    public int updatePoint(RequestDTO requestDTO, List<Photo> photos){
        int point = 0;

        if(photos.size() > 0 && requestDTO.getAttachedPhotoIds().size() == 0){ // ?????? ????????? ????????? ??????
            point--;
        }else if(photos.size() == 0 && requestDTO.getAttachedPhotoIds().size() > 0){ // ????????? ????????? ??????
            point++;
        }

        return point;
    }

    public int createPoint(RequestDTO requestDTO){
        int totPoint = 0;

        if("ADD".equals(requestDTO.getAction())&&reviewRepository.findPlaceCount(requestDTO) == 0){
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

    public UserPoint getUser(RequestDTO requestDTO){
        Optional<UserPoint> findUserPoint = userPointRepository.findById(requestDTO.getUserId());

        if(!findUserPoint.isPresent()){
            return userPointRepository.save(UserPoint.createUserPoint(requestDTO));
        }

        return findUserPoint.get();
    }

    public boolean authorizedCheck(Review review, String userId){
        if(review.getUserId().equals(userId))
            return true;

        throw new IllegalStateException(USER_NOT_ALLOWED.getMessage());
    }



}
