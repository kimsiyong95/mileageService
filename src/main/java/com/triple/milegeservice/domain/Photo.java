package com.triple.milegeservice.domain;

import com.triple.milegeservice.domain.common.RequestDTO;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "t_review_photo")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Photo {

    @Id
    private String reviewPhotoId;

    private String photoId;

    @CreationTimestamp
    private LocalDateTime registDt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reviewId")
    private Review review;


    public void setReview(Review review){
        this.review = review;
    }


    public static List<Photo> createPhotos(RequestDTO requestDTO){
        List<Photo> photos = new ArrayList<>();

        for(String requestPhotoId : requestDTO.getAttachedPhotoIds()){
            photos.add(
                Photo.builder().reviewPhotoId(UUID.randomUUID().toString())
                               .photoId(requestPhotoId)
                               .build()
            );
        }

        return photos;
    }

}
