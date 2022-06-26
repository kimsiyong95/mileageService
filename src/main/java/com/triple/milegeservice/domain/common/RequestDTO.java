package com.triple.milegeservice.domain.common;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class RequestDTO {

    @NotEmpty
    private String type;

    @NotEmpty
    private String action;

    @NotEmpty
    @Length(max = 36)
    private String reviewId;

    @NotEmpty
    private String content;

    private List<String> attachedPhotoIds = new ArrayList<>();

    @NotEmpty
    @Length(max = 36)
    private String userId;

    @NotEmpty
    @Length(max = 36)
    private String placeId;
}
