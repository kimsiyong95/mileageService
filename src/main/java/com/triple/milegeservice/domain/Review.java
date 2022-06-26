package com.triple.milegeservice.domain;


import com.triple.milegeservice.domain.common.RequestDTO;
import lombok.*;

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

    private LocalDateTime registDt;

    private LocalDateTime updtDt;

    private String deleteYn;

    private int point;

    @Builder.Default
    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL)
    private List<Photo> photos = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL)
    private List<History> histories = new ArrayList<>();

    public static Review createReview(RequestDTO requestDTO, List<Photo> photos, int point){
        return Review.builder().reviewId(requestDTO.getReviewId())
                               .content(requestDTO.getContent())
                               .userId(requestDTO.getUserId())
                               .placeId(requestDTO.getPlaceId())
                               .photos(photos)
                               .point(point)
                               .build();
    }
}
