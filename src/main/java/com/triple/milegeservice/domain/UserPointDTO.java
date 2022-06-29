package com.triple.milegeservice.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class UserPointDTO {
    private String userId;
    private int point;
}
