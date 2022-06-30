package com.triple.milegeservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.triple.milegeservice.domain.common.RequestDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest(controllers = MileageRestController.class)
class MileageRestControllerTest {


//    @Autowired
//    private MockMvc mvc;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//
//    @DisplayName("")
//    @Test
//    public void events() throws Exception {
//        mvc.perform(post("/events")
//                .content(objectMapper.writeValueAsString(new RequestDTO()))
//                .contentType(MediaType.APPLICATION_JSON)
//        );
//    }

}