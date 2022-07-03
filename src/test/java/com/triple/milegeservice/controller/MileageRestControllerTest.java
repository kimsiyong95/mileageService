package com.triple.milegeservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.triple.milegeservice.domain.UserPoint;
import com.triple.milegeservice.common.RequestDTO;
import com.triple.milegeservice.common.ResponseDTO;
import com.triple.milegeservice.service.MileageService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.when;

@WebMvcTest(controllers = MileageRestController.class)
class MileageRestControllerTest {


    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private MileageService mileageService;

    @DisplayName("리뷰 등록 테스트")
    @Test
    public void addReviewTest() throws Exception {
        RequestDTO requestDTO = makeRequestDto("ADD", new ArrayList<>());

        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setStatus(HttpStatus.CREATED, requestDTO.getReviewId());


        when(mileageService.addReview(any())).thenReturn(responseDTO);

        mvc.perform(post("/events")
                .content(objectMapper.writeValueAsString(requestDTO))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(requestDTO.getReviewId())));
    }

    @DisplayName("리뷰 수정 테스트")
    @Test
    public void modReviewTest() throws Exception {
        RequestDTO requestDTO = makeRequestDto("MOD", new ArrayList<>());


        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setStatus(HttpStatus.CREATED, requestDTO.getReviewId());


        when(mileageService.modReview(any())).thenReturn(responseDTO);

        mvc.perform(post("/events")
                        .content(objectMapper.writeValueAsString(requestDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(requestDTO.getReviewId())));
    }

    @DisplayName("리뷰 삭제 테스트")
    @Test
    public void deleteReviewTest() throws Exception {
        RequestDTO requestDTO = makeRequestDto("DELETE", new ArrayList<>());

        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setStatus(HttpStatus.OK, requestDTO.getReviewId());


        when(mileageService.deleteReview(any())).thenReturn(responseDTO);

        mvc.perform(post("/events")
                        .content(objectMapper.writeValueAsString(requestDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(requestDTO.getReviewId())));
    }


    @DisplayName("요청 데이터 오류 테스트")
    @Test
    public void requestErrorTest() throws Exception {
        RequestDTO requestDTO = makeRequestDto("PUT", new ArrayList<>());

        mvc.perform(post("/events")
                        .content(objectMapper.writeValueAsString(requestDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().is4xxClientError());
    }

    @DisplayName("사용자 포인트 조회 테스트")
    @Test
    public void getPointTest() throws Exception {
        String userId = UUID.randomUUID().toString();
        ResponseDTO responseDTO = new ResponseDTO();
        RequestDTO requestDTO = new RequestDTO();
        requestDTO.setUserId(userId);
        UserPoint findUser = UserPoint.createUserPoint(requestDTO);


        responseDTO.setStatus(HttpStatus.OK, findUser.EntityToDto());

        when(mileageService.getPoints(any())).thenReturn(responseDTO);

        mvc.perform(get("/points/"+userId))
                    .andExpect(status().isOk())
                    .andExpect(content().string(containsString(userId)));
    }










    public RequestDTO makeRequestDto(String action, List<String> photos){
        RequestDTO requestDTO = new RequestDTO();

        requestDTO.setType("REVIEW");
        requestDTO.setAction(action);
        requestDTO.setReviewId(UUID.randomUUID().toString());
        requestDTO.setContent("test");
        requestDTO.setAttachedPhotoIds(photos);
        requestDTO.setUserId(UUID.randomUUID().toString());
        requestDTO.setPlaceId(UUID.randomUUID().toString());

        return requestDTO;
    }


}