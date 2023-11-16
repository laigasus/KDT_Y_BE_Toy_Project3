package com.example.kdt_y_be_toy_project2.domain.comment.controller;

import com.example.kdt_y_be_toy_project2.domain.comment.dto.*;
import com.example.kdt_y_be_toy_project2.domain.comment.service.TripCommentService;
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
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
@ExtendWith(RestDocumentationExtension.class)
class CommentControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    TripCommentService tripCommentService;

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
    void bringCommentsTest() throws Exception {

        List<TripCommentGetResponse> mockResponse = List.of(
                new TripCommentGetResponse(1L, 1L, "부산 여행 일정 수정", "liyusang1@naver.com", "liyusang1", "즐거운여행입니다. 여행테스트", "2023년 11월 15일 00시 06분")
        );

        when(tripCommentService.bringTripComments(anyLong())).thenReturn(mockResponse);


        this.mockMvc.perform(get("/trip/{tripId}/comments", 1))
                .andExpect(status().isOk())
                .andDo(document("bring-comments",
                        pathParameters(
                                parameterWithName("tripId").description("여행 ID")
                        ),
                        responseFields(
                                fieldWithPath("[].tripCommentId").type(JsonFieldType.NUMBER).description("여행 코멘트 ID"),
                                fieldWithPath("[].tripId").type(JsonFieldType.NUMBER).description("여행 ID"),
                                fieldWithPath("[].tripName").type(JsonFieldType.STRING).description("여행 이름"),
                                fieldWithPath("[].userEmail").type(JsonFieldType.STRING).description("사용자 이메일"),
                                fieldWithPath("[].username").type(JsonFieldType.STRING).description("사용자 이름"),
                                fieldWithPath("[].tripComment").type(JsonFieldType.STRING).description("여행 코멘트"),
                                fieldWithPath("[].updatedAt").type(JsonFieldType.STRING).description("코멘트 수정 시간")
                        )));
    }


    @Test
    void addCommentTest() throws Exception {

        TripCommentAddRequest request = new TripCommentAddRequest("즐거운여행입니다. 여행테스트");

        TripCommentAddResponse mockResponse = new TripCommentAddResponse(4L, 1L, 1L, "liyusang1", "즐거운여행입니다. 여행테스트", "2023년 11월 15일 01시 34분");

        when(tripCommentService.insertTripComment(anyLong(), any(TripCommentAddRequest.class), any()))
                .thenReturn(mockResponse);


        this.mockMvc.perform(post("/trip/{tripId}/comments", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.tripCommentId").exists())
                .andDo(document("add-comment",
                        pathParameters(
                                parameterWithName("tripId").description("여행 ID")
                        ),
                        requestFields(
                                fieldWithPath("tripComment").description("여행 코멘트")
                        ),
                        responseFields(
                                fieldWithPath("tripCommentId").type(JsonFieldType.NUMBER).description("여행 코멘트 ID"),
                                fieldWithPath("tripId").type(JsonFieldType.NUMBER).description("여행 ID"),
                                fieldWithPath("userId").type(JsonFieldType.NUMBER).description("사용자 ID"),
                                fieldWithPath("username").type(JsonFieldType.STRING).description("사용자 이름"),
                                fieldWithPath("tripComment").type(JsonFieldType.STRING).description("여행 코멘트"),
                                fieldWithPath("createdAt").type(JsonFieldType.STRING).description("코멘트 생성 시간")
                        )));
    }


    @Test
    void editCommentTest() throws Exception {
        long tripId = 1L;
        long commentId = 1L;
        TripCommentUpdateRequest updateRequest = new TripCommentUpdateRequest("수정된 코멘트 내용");

        TripCommentUpdateResponse mockResponse = new TripCommentUpdateResponse(
                commentId, tripId, 1L, "수정된 코멘트", "2023년 11월 15일 02시 34분");

        when(tripCommentService.updateTripComment(eq(tripId), eq(commentId), eq(updateRequest), any()))
                .thenReturn(mockResponse);


        this.mockMvc.perform(patch("/trip/{tripId}/comments/{commentId}", tripId, commentId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.tripCommentId").value(mockResponse.tripCommentId()))
                .andExpect(jsonPath("$.tripId").value(mockResponse.tripId()))
                .andExpect(jsonPath("$.userId").value(mockResponse.userId()))
                .andExpect(jsonPath("$.tripComment").value(mockResponse.tripComment()))
                .andExpect(jsonPath("$.createdAt").value(mockResponse.createdAt()))
                .andDo(document("edit-comment",
                        pathParameters(
                                parameterWithName("tripId").description("여행 ID"),
                                parameterWithName("commentId").description("코멘트 ID")
                        ),
                        requestFields(
                                fieldWithPath("tripComment").description("수정할 코멘트 내용")
                        ),
                        responseFields(
                                fieldWithPath("tripCommentId").type(JsonFieldType.NUMBER).description("여행 코멘트 ID"),
                                fieldWithPath("tripId").type(JsonFieldType.NUMBER).description("여행 ID"),
                                fieldWithPath("userId").type(JsonFieldType.NUMBER).description("사용자 ID"),
                                fieldWithPath("tripComment").type(JsonFieldType.STRING).description("수정된 여행 코멘트"),
                                fieldWithPath("createdAt").type(JsonFieldType.STRING).description("코멘트 수정 시간")
                        )));
    }

    @Test
    void deleteCommentTest() throws Exception {
        long tripId = 1L;
        long commentId = 1L;

        when(tripCommentService.deleteTripComment(eq(tripId), eq(commentId), any()))
                .thenReturn(true);

        this.mockMvc.perform(delete("/trip/{tripId}/comments/{commentId}", tripId, commentId))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("번 댓글 삭제 성공")))
                .andDo(document("delete-comment",
                        pathParameters(
                                parameterWithName("tripId").description("여행 ID"),
                                parameterWithName("commentId").description("삭제할 코멘트 ID")
                        ),
                        responseBody()));
    }






}