package com.example.kdt_y_be_toy_project2.domain.like.controller;

import com.example.kdt_y_be_toy_project2.domain.comment.service.TripCommentService;
import com.example.kdt_y_be_toy_project2.domain.like.dto.*;
import com.example.kdt_y_be_toy_project2.domain.like.service.UserLikeService;
import com.example.kdt_y_be_toy_project2.domain.user.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
@ExtendWith(RestDocumentationExtension.class)
public class UserLikeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserLikeService userLikeService;


    @Autowired
    private ObjectMapper objectMapper;


    @BeforeEach
    public void setUp(WebApplicationContext webApplicationContext, RestDocumentationContextProvider restDocument) {
        objectMapper.registerModule(new JavaTimeModule());

        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(documentationConfiguration(restDocument)
                        .operationPreprocessors()
                        .withRequestDefaults(prettyPrint())
                        .withResponseDefaults(prettyPrint()))
                .build();


        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void addUserLikeTest() throws Exception {
        UserLikeAddTripRequest request = new UserLikeAddTripRequest(1L); // 여행 ID를 예시로 1로 설정
        UserLikeAddTripResponse response = new UserLikeAddTripResponse(1L, 1L, "좋아요 요청 성공");

        when(userLikeService.addUserLike(any(), eq(1L))).thenReturn(response);

        this.mockMvc.perform(post("/userLike")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andDo(document("add-user-like",
                        requestFields(
                                fieldWithPath("tripId").description("좋아요를 추가할 여행 ID")
                        ),
                        responseFields(
                                fieldWithPath("tripId").description("좋아요가 추가된 여행 ID"),
                                fieldWithPath("userId").description("좋아요를 추가한 사용자 ID"),
                                fieldWithPath("message").description("좋아요 추가에 대한 응답 메시지")
                        )))
                .andDo(print());
    }


    @Test
    public void getUserLikeTest() throws Exception {
        // 사용자가 좋아하는 여행 목록의 응답 예시
        List<UserLikeGetTripsResponse> mockResponse = List.of(
                new UserLikeGetTripsResponse(1L, "부산 여행", "2023-11-15")
        );

        when(userLikeService.bringUserLike(any())).thenReturn(mockResponse);

        this.mockMvc.perform(get("/userLike"))
                .andExpect(status().isOk())
                .andDo(document("get-user-like",
                        responseFields(
                                fieldWithPath("[].tripId").description("좋아하는 여행 ID"),
                                fieldWithPath("[].tripName").description("여행 이름"),
                                fieldWithPath("[].createdAt").description("좋아요 생성 날짜")
                        )))
                .andDo(print());
    }

    @Test
    public void removeUserLikeTest() throws Exception {
        UserLikeRemoveTripRequest request = new UserLikeRemoveTripRequest(1L); // 예시 여행 ID
        DeleteUserLikeResponse response = new DeleteUserLikeResponse("좋아요 삭제 성공");

        when(userLikeService.removeUserLike(any(), eq(1L))).thenReturn(response);

        this.mockMvc.perform(delete("/userLike")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andDo(document("remove-user-like",
                        requestFields(
                                fieldWithPath("tripId").description("좋아요를 삭제할 여행 ID")
                        ),
                        responseFields(
                                fieldWithPath("message").description("좋아요 삭제에 대한 응답 메시지")
                        )))
                .andDo(print());
    }



}
