package com.triple.milegeservice.domain;

import com.triple.milegeservice.common.RequestDTO;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_user_point")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class UserPoint {

    @Id
    private String userId;

    private int point;

    public static UserPoint createUserPoint(RequestDTO requestDTO){
        return UserPoint.builder()
                        .userId(requestDTO.getUserId())
                        .point(0).build();
    }

    public UserPointDTO EntityToDto(){
        return new UserPointDTO(userId,point);
    }

    public void addPoint(int point){
        this.point += point;
    }

}
