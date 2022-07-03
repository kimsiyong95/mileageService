package com.triple.milegeservice.repository;

import com.triple.milegeservice.domain.Review;
import com.triple.milegeservice.common.RequestDTO;

public interface RepositoryCustom {
    public Review findByUserIdAndPlaceId(RequestDTO requestDTO);
    public Long findPlaceCount(RequestDTO requestDTO);

}
