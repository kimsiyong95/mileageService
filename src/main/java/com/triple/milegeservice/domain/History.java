package com.triple.milegeservice.domain;


import com.triple.milegeservice.common.RequestDTO;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "t_history")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class History {
    @Id
    private String historyId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reviewId")
    private Review review;

    private String userEvent;

    private int existingPoints;

    private int idPoint;

    private int totPoint;

    @CreationTimestamp
    private LocalDateTime registDt;

    public static History createHistory(RequestDTO requestDTO, int existingPoints, int idPoint, Review review){
        return History.builder()
                      .historyId(UUID.randomUUID().toString())
                      .existingPoints(existingPoints)
                      .review(review)
                      .idPoint(idPoint)
                      .totPoint(existingPoints+idPoint)
                      .userEvent(requestDTO.getAction())
                      .build();
    }
}
