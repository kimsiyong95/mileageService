package com.triple.milegeservice.domain;


import com.triple.milegeservice.domain.common.RequestDTO;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "t_review")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Review {

    @Id
    private String reviewId;

    private String content;

    private String placeId;

    private String userId;

    @CreationTimestamp
    private LocalDateTime registDt;

    @UpdateTimestamp
    private LocalDateTime updtDt;

    private String deleteYn;

    private int point;

    @Builder.Default
    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL)
    private List<Photo> photos = new ArrayList<>();

    public static Review createReview(RequestDTO requestDTO, int point){

        Review review = Review.builder().reviewId(requestDTO.getReviewId())
                               .content(requestDTO.getContent())
                               .userId(requestDTO.getUserId())
                               .placeId(requestDTO.getPlaceId())
                               .point(point)
                               .deleteYn("N")
                               .build();

        for(Photo photo : Photo.createPhotos(requestDTO)){
            review.addPhoto(photo);
        }

        return review;
    }

    public void addPhoto(Photo photo){
        this.photos.add(photo);
        photo.setReview(this);
    }

    public void updateReview(RequestDTO requestDTO, int point){
        this.content = requestDTO.getContent();
        this.point += point;

        this.photos.clear();
        for(Photo photo : Photo.createPhotos(requestDTO)){
            this.addPhoto(photo);
        }
    }

    public void deleteReview(){
        this.deleteYn = "Y";
    }


    public boolean pointIncreaseOrDecreaseCheck(int point){
        if(this.point!=point)
            return false;
        return true;
    }

    public int deletePoint(){
        int originPoint = this.point;
        this.point = 0;

        if(originPoint > 0){
            return -originPoint;
        }

        return 0;
    }


}
