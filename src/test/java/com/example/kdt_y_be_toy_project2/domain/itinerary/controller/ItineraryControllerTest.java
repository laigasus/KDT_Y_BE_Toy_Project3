package com.example.kdt_y_be_toy_project2.domain.itinerary.controller;


import com.example.kdt_y_be_toy_project2.domain.itinerary.dto.ItineraryRequest;
import com.example.kdt_y_be_toy_project2.domain.itinerary.dto.ItineraryResponse;
import com.example.kdt_y_be_toy_project2.domain.itinerary.service.ItineraryService;
import com.example.kdt_y_be_toy_project2.global.dummy.DummyObjectForController;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
class ItineraryControllerTest extends DummyObjectForController {

    @MockBean
    ItineraryService itineraryService;

    ObjectMapper objectMapper = new ObjectMapper();

    private List<ItineraryRequest> itineraryRequestList = new ArrayList<>();



    private MockMvc mockMvc;


    @BeforeEach
    void setUp(WebApplicationContext webApplicationContext, RestDocumentationContextProvider restDocument) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(documentationConfiguration(restDocument)
                        .operationPreprocessors()
                        .withRequestDefaults(prettyPrint())
                        .withResponseDefaults(prettyPrint()))
                .build();

        MockitoAnnotations.openMocks(this);

        objectMapper.registerModule(new JavaTimeModule());


    }


    @Test
    void addItineraryTest() throws Exception {
        ItineraryResponse mockResponse = dummyItineraryResponse();

        Mockito.when(itineraryService.insertItineraries(anyLong(), anyList()))
                .thenReturn(List.of(mockResponse));

        itineraryRequestList.add(dummyItineraryRequest());


        this.mockMvc.perform(post("/itinerary/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(itineraryRequestList)))
                .andExpect(status().isCreated()).andDo(document("post-ItineraryId",
                        requestFields(
                                fieldWithPath("[]").description("여행 일정 목록"),
                                fieldWithPath("[].itineraryName").description("여행 일정 이름"),
                                fieldWithPath("[].timeSchedule.startTime").description("일정 시작 시간"),
                                fieldWithPath("[].timeSchedule.endTime").description("일정 종료 시간"),
                                fieldWithPath("[].accommodation[].accommodationName").description("숙박 시설 이름"),
                                fieldWithPath("[].accommodation[].accommodationTimeSchedule.startTime").description("숙박 시작 시간"),
                                fieldWithPath("[].accommodation[].accommodationTimeSchedule.endTime").description("숙박 종료 시간"),
                                fieldWithPath("[].residence[].residenceName").description("거주지 이름"),
                                fieldWithPath("[].residence[].residenceTimeSchedule.startTime").description("거주지 체류 시작 시간"),
                                fieldWithPath("[].residence[].residenceTimeSchedule.endTime").description("거주지 체류 종료 시간"),
                                fieldWithPath("[].activity[].transportEnum").description("교통 수단"),
                                fieldWithPath("[].activity[].departurePlace").description("활동 출발 장소"),
                                fieldWithPath("[].activity[].arrivalPlace").description("활동 도착 장소"),
                                fieldWithPath("[].activity[].arrivalAddress").description("도착지 주소"),
                                fieldWithPath("[].activity[].description").description("활동 설명"),
                                fieldWithPath("[].activity[].activityTimeSchedule.startTime").description("활동 시작 시간"),
                                fieldWithPath("[].activity[].activityTimeSchedule.endTime").description("활동 종료 시간")
                        ),
                        responseFields(
                                fieldWithPath("[].id").type(JsonFieldType.NUMBER).description("여행 일정의 고유 ID"),
                                fieldWithPath("[].itineraryName").type(JsonFieldType.STRING).description("여행 일정의 이름"),
                                fieldWithPath("[].tripId").type(JsonFieldType.NUMBER).description("연결된 여행의 고유 ID"),
                                subsectionWithPath("[].accommodation").description("숙박 시설 목록"),
                                subsectionWithPath("[].residence").description("거주지 목록"),
                                subsectionWithPath("[].activity").description("활동 목록"),
                                fieldWithPath("[].activity[].arrivalAddress").type(JsonFieldType.STRING).description("활동의 도착지 주소").optional(),
                                subsectionWithPath("[].timeSchedule").description("전체 여행 일정 시간표"),
                                fieldWithPath("[].createdAt").type(JsonFieldType.STRING).description("생성된 날짜 및 시간"),
                                fieldWithPath("[].updatedAt").type(JsonFieldType.STRING).description("마지막으로 업데이트된 날짜 및 시간")
                        )
                )).andDo(print());
    }


    @Test
    void bringAllItineraryTest() throws Exception {

        ItineraryResponse mockResponse = dummyItineraryResponse();
        List<ItineraryResponse> mockList = new ArrayList<>();
        mockList.add(mockResponse);

        Mockito.when(itineraryService.selectItineraries(anyLong())).thenReturn(mockList);

        this.mockMvc.perform(get("/itinerary/trip/{id}", 1).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andDo(document("bring-all-itinerary",
                        responseFields(
                                fieldWithPath("[]").type(JsonFieldType.ARRAY).ignored(),
                                fieldWithPath("[].id").type(JsonFieldType.NUMBER).description("일정 ID"),
                                fieldWithPath("[].itineraryName").type(JsonFieldType.STRING).description("일정 이름"),
                                fieldWithPath("[].tripId").type(JsonFieldType.NUMBER).description("여행 ID"),
                                fieldWithPath("[].accommodation").type(JsonFieldType.ARRAY).description("숙소 정보"),
                                fieldWithPath("[].accommodation[].accommodationName").type(JsonFieldType.STRING).description("숙소 이름"),
                                fieldWithPath("[].accommodation[].checkIn").type(JsonFieldType.STRING).description("체크인 시간"),
                                fieldWithPath("[].accommodation[].checkOut").type(JsonFieldType.STRING).description("체크아웃 시간"),
                                fieldWithPath("[].residence").type(JsonFieldType.ARRAY).description("거주지 정보"),
                                fieldWithPath("[].residence[].residenceName").type(JsonFieldType.STRING).description("거주지 이름"),
                                fieldWithPath("[].residence[].stayTime").type(JsonFieldType.STRING).description("체류 시간"),
                                fieldWithPath("[].residence[].leaveTime").type(JsonFieldType.STRING).description("퇴거 시간"),
                                fieldWithPath("[].activity").type(JsonFieldType.ARRAY).description("활동 정보"),
                                fieldWithPath("[].activity[].transportEnum").type(JsonFieldType.STRING).description("교통수단"),
                                fieldWithPath("[].activity[].departurePlace").type(JsonFieldType.STRING).description("출발지"),
                                fieldWithPath("[].activity[].arrivalPlace").type(JsonFieldType.STRING).description("도착지"),
                                fieldWithPath("[].activity[].description").type(JsonFieldType.STRING).description("활동 설명"),
                                fieldWithPath("[].activity[].activityStart").type(JsonFieldType.STRING).description("활동 시작 시간"),
                                fieldWithPath("[].activity[].activityEnd").type(JsonFieldType.STRING).description("활동 종료 시간"),
                                fieldWithPath("[].activity[].arrivalAddress").type(JsonFieldType.STRING).description("활동의 도착지 주소").optional(),
                                fieldWithPath("[].timeSchedule").type(JsonFieldType.OBJECT).description("일정 시간표"),
                                fieldWithPath("[].timeSchedule.startTime").type(JsonFieldType.STRING).description("일정 시작 시간"),
                                fieldWithPath("[].timeSchedule.endTime").type(JsonFieldType.STRING).description("일정 종료 시간"),
                                fieldWithPath("[].createdAt").type(JsonFieldType.STRING).description("생성 시간"),
                                fieldWithPath("[].updatedAt").type(JsonFieldType.STRING).description("수정 시간")
                        ))).andDo(print());
    }


    @Test
    void bringOneItineraryTest() throws Exception {

        ItineraryResponse mockResponse = dummyItineraryResponse();

        Mockito.when(itineraryService.selectItinerary(anyLong())).thenReturn(mockResponse);


        this.mockMvc.perform(RestDocumentationRequestBuilders.get("/itinerary/{id}", 3).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document("get-one-ItineraryId",
                        pathParameters(
                                parameterWithName("id").description("조회하려는 여행 일정의 ID")
                        ),
                        responseFields(
                                fieldWithPath("id").description("여행 일정 ID"),
                                fieldWithPath("itineraryName").description("여행 일정 이름"),
                                fieldWithPath("tripId").description("여행 ID"),
                                fieldWithPath("accommodation[].accommodationName").description("숙소 이름"),
                                fieldWithPath("accommodation[].checkIn").description("체크인 시간"),
                                fieldWithPath("accommodation[].checkOut").description("체크아웃 시간"),
                                fieldWithPath("residence[].residenceName").description("거주지 이름"),
                                fieldWithPath("residence[].stayTime").description("체류 시작 시간"),
                                fieldWithPath("residence[].leaveTime").description("체류 종료 시간"),
                                fieldWithPath("activity[].transportEnum").description("교통 수단"),
                                fieldWithPath("activity[].departurePlace").description("출발 장소"),
                                fieldWithPath("activity[].arrivalPlace").description("도착 장소"),
                                fieldWithPath("activity[].description").description("활동 설명"),
                                fieldWithPath("activity[].activityStart").description("활동 시작 시간"),
                                fieldWithPath("activity[].activityEnd").description("활동 종료 시간"),
                                fieldWithPath("activity[].arrivalAddress").type(JsonFieldType.STRING).description("활동의 도착지 주소"),
                                fieldWithPath("timeSchedule.startTime").description("일정 시작 시간"),
                                fieldWithPath("timeSchedule.endTime").description("일정 종료 시간"),
                                fieldWithPath("createdAt").description("생성된 날짜 및 시간"),
                                fieldWithPath("updatedAt").description("마지막으로 업데이트된 날짜 및 시간")
                        )));
    }

    @Test
    void editItineraryTest() throws Exception {
        Mockito.when(itineraryService.updateItinerary(anyLong(), any(ItineraryRequest.class)))
                .thenReturn(dummyItineraryResponse());

        this.mockMvc.perform(patch("/itinerary/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dummyItineraryRequest())))
                .andExpect(status().isOk())
                .andDo(document("patch-itinerary-id",
                        requestFields(
                                fieldWithPath("itineraryName").description("여행 일정의 이름"),
                                fieldWithPath("timeSchedule.startTime").description("일정 시작 시간"),
                                fieldWithPath("timeSchedule.endTime").description("일정 종료 시간"),
                                fieldWithPath("accommodation[].accommodationName").description("숙박 시설의 이름"),
                                fieldWithPath("accommodation[].accommodationTimeSchedule.startTime").description("숙박 시작 시간"),
                                fieldWithPath("accommodation[].accommodationTimeSchedule.endTime").description("숙박 종료 시간"),
                                fieldWithPath("residence[].residenceName").description("거주지의 이름"),
                                fieldWithPath("residence[].residenceTimeSchedule.startTime").description("거주 시작 시간"),
                                fieldWithPath("residence[].residenceTimeSchedule.endTime").description("거주 종료 시간"),
                                fieldWithPath("activity[].transportEnum").description("교통 수단"),
                                fieldWithPath("activity[].departurePlace").description("출발 장소"),
                                fieldWithPath("activity[].arrivalPlace").description("도착 장소"),
                                fieldWithPath("activity[].description").description("활동 설명"),
                                fieldWithPath("activity[].activityTimeSchedule.startTime").description("활동 시작 시간"),
                                fieldWithPath("activity[].activityTimeSchedule.endTime").description("활동 종료 시간"),
                                fieldWithPath("activity[].arrivalAddress").description("활동의 도착지 주소").optional()

                        ),
                        responseFields(
                                fieldWithPath("id").description("여행 일정의 고유 ID"),
                                fieldWithPath("itineraryName").description("여행 일정의 이름"),
                                fieldWithPath("tripId").description("여행의 고유 식별자"),
                                fieldWithPath("accommodation[].accommodationName").description("숙박 시설의 이름"),
                                fieldWithPath("accommodation[].checkIn").description("숙박 시작 시간"),
                                fieldWithPath("accommodation[].checkOut").description("숙박 종료 시간"),
                                fieldWithPath("residence[].residenceName").description("거주지의 이름"),
                                fieldWithPath("residence[].stayTime").description("거주 시작 시간"),
                                fieldWithPath("residence[].leaveTime").description("거주 종료 시간"),
                                fieldWithPath("activity[].transportEnum").description("교통 수단"),
                                fieldWithPath("activity[].departurePlace").description("출발 장소"),
                                fieldWithPath("activity[].arrivalPlace").description("도착 장소"),
                                fieldWithPath("activity[].description").description("활동 설명"),
                                fieldWithPath("activity[].activityStart").description("활동 시작 시간"),
                                fieldWithPath("activity[].activityEnd").description("활동 종료 시간"),
                                fieldWithPath("activity[].arrivalAddress").type(JsonFieldType.STRING).description("활동의 도착지 주소").optional(),
                                fieldWithPath("timeSchedule.startTime").description("일정 시작 시간"),
                                fieldWithPath("timeSchedule.endTime").description("일정 종료 시간"),
                                fieldWithPath("createdAt").description("생성된 날짜 및 시간"),
                                fieldWithPath("updatedAt").description("마지막으로 업데이트된 날짜 및 시간")
                        )
                ));
    }


}