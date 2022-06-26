package com.triple.milegeservice.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.triple.milegeservice.domain.QReview;
import com.triple.milegeservice.domain.Review;
import com.triple.milegeservice.domain.common.RequestDTO;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

@Repository
public class RepositoryCustomImpl extends QuerydslRepositorySupport implements RepositoryCustom {

    private final JPAQueryFactory queryFactory;

    private final QReview qReview;

    public RepositoryCustomImpl(JPAQueryFactory queryFactory){
        super(Review.class);
        this.queryFactory = queryFactory;
        this.qReview = QReview.review;
    }

    @Override
    public Review findByUserIdAndPlaceId(RequestDTO requestDTO) {
        return queryFactory.select(qReview)
                           .from(qReview)
                           .where(
                                   eqUserId(requestDTO),
                                   eqPlaceId(requestDTO)
                           )
                           .fetchOne();
    }

    @Override
    public Long findPlaceCount(RequestDTO requestDTO) {
        return queryFactory.select(qReview.count())
                    .from(qReview)
                    .where(eqPlaceId(requestDTO))
                    .limit(1)
                    .fetchOne();
    }

    public BooleanExpression eqUserId(RequestDTO requestDTO){
        return StringUtils.hasText(requestDTO.getUserId())?qReview.userId.eq(requestDTO.getUserId()):null;
    }

    public BooleanExpression eqPlaceId(RequestDTO requestDTO){
        return StringUtils.hasText(requestDTO.getPlaceId())?qReview.placeId.eq(requestDTO.getPlaceId()):null;
    }

}
