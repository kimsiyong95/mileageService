package com.triple.milegeservice.controller;

import com.triple.milegeservice.domain.common.RequestDTO;
import com.triple.milegeservice.service.MileageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.triple.milegeservice.domain.common.HttpStatusCustom.*;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MileageRestController {

    private final MileageService mileageService;

    /**
     * ContentType : application/json
     * @param requestDTO
     * @param bindingResult
     * @return
     */
    @PostMapping("/events")
    public ResponseEntity events(@Valid @RequestBody RequestDTO requestDTO, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new IllegalArgumentException(USER_PARAM_ERROR.getMessage());
        }

        ResponseEntity responseEntity;

        if("ADD".equals(requestDTO.getAction())){
            responseEntity = addReview(requestDTO);

        }else if("MOD".equals(requestDTO.getAction())){
            responseEntity = modReview(requestDTO);

        }else if("DELETE".equals(requestDTO.getAction())){
            responseEntity = deleteReview(requestDTO);

        }else {
            throw new IllegalArgumentException(USER_PARAM_ERROR.getMessage());
        }

        return responseEntity;
    }


    public ResponseEntity addReview(RequestDTO requestDTO){
        return new ResponseEntity(mileageService.addReview(requestDTO), HttpStatus.OK);
    }

    public ResponseEntity modReview(RequestDTO requestDTO){
        return new ResponseEntity(mileageService.modReview(requestDTO), HttpStatus.OK);
    }

    public ResponseEntity deleteReview(RequestDTO requestDTO){
        return new ResponseEntity(mileageService.deleteReview(requestDTO), HttpStatus.OK);
    }

}
