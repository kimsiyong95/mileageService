package com.triple.milegeservice.common;


import com.triple.milegeservice.annotation.Type;
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

    @Type
    @NotEmpty
    private String type;

    @NotEmpty
    private String action;

    @NotEmpty
    @Length(max = 36)
    private String reviewId;

    private String content;

    private List<String> attachedPhotoIds = new ArrayList<>();

    @NotEmpty
    @Length(max = 36)
    private String userId;

    @NotEmpty
    @Length(max = 36)
    private String placeId;
}
