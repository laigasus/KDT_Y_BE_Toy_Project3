package com.example.kdt_y_be_toy_project2.domain.trip.controller;

import com.example.kdt_y_be_toy_project2.domain.trip.dto.TripRequest;
import com.example.kdt_y_be_toy_project2.domain.trip.dto.TripResponse;
import com.example.kdt_y_be_toy_project2.domain.trip.service.TripService;
import com.example.kdt_y_be_toy_project2.global.dummy.DummyObjectForController;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
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

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.patch;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
@ExtendWith(RestDocumentationExtension.class)
public class TripControllerTest extends DummyObjectForController {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    TripService tripService;

    ObjectMapper objectMapper = new ObjectMapper();

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
    public void addTripTest() throws Exception {
        TripRequest sampleTripRequest = dummyTripRequest();
        String tripRequestJson = objectMapper.writeValueAsString(sampleTripRequest);

        TripResponse.TripInfo mockTripInfo = dummyTripResponseTripInfo();

        List<String> itinerariesNames = List.of("일정1", "일정2"); // 예시 일정 이름 목록
        TripResponse.AllTrips mockAllTrips = new TripResponse.AllTrips(mockTripInfo, itinerariesNames);

        Mockito.when(tripService.insertTrip(any(TripRequest.class))).thenReturn(mockAllTrips);


        this.mockMvc.perform(post("/trip")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(tripRequestJson))
                .andExpect(status().isCreated())
                .andDo(document("add-trip-one",
                        requestFields(
                                fieldWithPath("tripName").description("여행 이름"),
                                fieldWithPath("timeSchedule.startTime").description("여행 시작 시간"),
                                fieldWithPath("timeSchedule.endTime").description("여행 종료 시간"),
                                fieldWithPath("tripDestinationEnum").description("여행지 유형"),
                                fieldWithPath("itineraries").description("여행 일정 목록").optional().type(JsonFieldType.ARRAY)
                        ),
                        responseFields(
                                fieldWithPath("tripInfo.tripId").description("여행 ID"),
                                fieldWithPath("tripInfo.tripName").description("여행 이름"),
                                fieldWithPath("tripInfo.timeSchedule.startTime").description("여행 시작 시간"),
                                fieldWithPath("tripInfo.timeSchedule.endTime").description("여행 종료 시간"),
                                fieldWithPath("tripInfo.tripDestinationEnum").description("여행지 유형"),
                                fieldWithPath("tripInfo.createdAt").description("생성된 날짜 및 시간"),
                                fieldWithPath("tripInfo.updatedAt").description("마지막으로 업데이트된 날짜 및 시간"),
                                fieldWithPath("tripInfo.likes").description("여행에 대한 좋아요 수").optional(),
                                fieldWithPath("itinerariesNames").description("여행 일정 이름 목록")
                        )
                ));
    }


    @Test
    public void bringAllTripsTest() throws Exception {
        List<TripResponse.AllTrips> mockResponse = List.of(
                new TripResponse.AllTrips(dummyTripResponseTripInfo(), List.of("일정1", "일정2"))
        );

        Mockito.when(tripService.selectTrips()).thenReturn(mockResponse);

        this.mockMvc.perform(get("/trip").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document("get-all-trips",
                        responseFields(
                                fieldWithPath("[].tripInfo.tripId").description("여행 ID"),
                                fieldWithPath("[].tripInfo.tripName").description("여행 이름"),
                                fieldWithPath("[].tripInfo.timeSchedule.startTime").description("여행 시작 시간"),
                                fieldWithPath("[].tripInfo.timeSchedule.endTime").description("여행 종료 시간"),
                                fieldWithPath("[].tripInfo.tripDestinationEnum").description("여행지 유형"),
                                fieldWithPath("[].tripInfo.createdAt").description("생성된 날짜 및 시간"),
                                fieldWithPath("[]tripInfo.likes").description("여행에 대한 좋아요 수").optional(),
                                fieldWithPath("[].tripInfo.updatedAt").description("마지막으로 업데이트된 날짜 및 시간"),
                                fieldWithPath("[].itinerariesNames").description("여행 일정 이름 목록").type(JsonFieldType.ARRAY)
                        )
                ));
    }

    @Test
    public void bringTripByIdTest() throws Exception {
        TripResponse.TripById mockResponse = dummyTripResponseTripById();

        Mockito.when(tripService.selectTrip(1L)).thenReturn(mockResponse);

        // REST Docs 작성
        this.mockMvc.perform(get("/trip/{id}", 1)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document("get-trip-by-id",
                        pathParameters(
                                parameterWithName("id").description("조회하고자 하는 여행 일정의 ID")
                        ),
                        responseFields(
                                fieldWithPath("tripInfo.tripId").description("여행 ID"),
                                fieldWithPath("tripInfo.tripName").description("여행 이름"),
                                fieldWithPath("tripInfo.timeSchedule.startTime").description("여행 시작 시간"),
                                fieldWithPath("tripInfo.timeSchedule.endTime").description("여행 종료 시간"),
                                fieldWithPath("tripInfo.tripDestinationEnum").description("여행지 유형"),
                                fieldWithPath("tripInfo.createdAt").description("생성된 날짜 및 시간"),
                                fieldWithPath("tripInfo.updatedAt").description("마지막으로 업데이트된 날짜 및 시간"),
                                fieldWithPath("tripInfo.likes").description("여행에 대한 좋아요 수").optional(),
                                subsectionWithPath("itineraries").description("여행 일정 목록"),
                                subsectionWithPath("tripComments").description("여행 댓글 목록")
                        )
                ));
    }


    @Test
    public void editTripTest() throws Exception {


        // 요청 데이터 준비
        Long tripId = 1L;
        TripRequest sampleTripRequest = dummyTripRequest();
        String tripRequestJson = objectMapper.writeValueAsString(sampleTripRequest);

        // Mock 서비스 설정
        TripResponse.TripById mockResponse = dummyTripResponseTripById();
        Mockito.when(tripService.updateTrip(Mockito.eq(tripId), Mockito.any(TripRequest.class))).thenReturn(mockResponse);

        this.mockMvc.perform(patch("/trip/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(tripRequestJson))
                .andExpect(status().isOk())
                .andDo(document("edit-trip",
                        pathParameters(
                                parameterWithName("id").description("수정하고자 하는 여행 일정의 ID")
                        ),
                        requestFields(
                                fieldWithPath("tripName").description("수정할 여행의 이름"),
                                fieldWithPath("timeSchedule.startTime").description("여행 시작 시간"),
                                fieldWithPath("timeSchedule.endTime").description("여행 종료 시간"),
                                fieldWithPath("tripDestinationEnum").description("여행지 유형"),
                                subsectionWithPath("itineraries").description("여행 일정 목록").optional()
                        ),
                        responseFields(
                                fieldWithPath("tripInfo.tripId").description("여행 ID"),
                                fieldWithPath("tripInfo.tripName").description("여행 이름"),
                                fieldWithPath("tripInfo.timeSchedule.startTime").description("여행 시작 시간"),
                                fieldWithPath("tripInfo.timeSchedule.endTime").description("여행 종료 시간"),
                                fieldWithPath("tripInfo.tripDestinationEnum").description("여행지 유형"),
                                fieldWithPath("tripInfo.createdAt").description("생성된 날짜 및 시간"),
                                fieldWithPath("tripInfo.likes").description("여행에 대한 좋아요 수").optional(),
                                fieldWithPath("tripInfo.updatedAt").description("마지막으로 업데이트된 날짜 및 시간"),
                                subsectionWithPath("itineraries").description("여행 일정 목록"),
                                fieldWithPath("itineraries[].id").description("일정 ID"),
                                fieldWithPath("itineraries[].itineraryName").description("일정 이름"),
                                fieldWithPath("itineraries[].tripId").description("여행 ID"),
                                fieldWithPath("itineraries[].accommodation[].accommodationName").description("숙박 시설 이름"),
                                fieldWithPath("itineraries[].accommodation[].checkIn").description("체크인 시간"),
                                fieldWithPath("itineraries[].accommodation[].checkOut").description("체크아웃 시간"),
                                fieldWithPath("itineraries[].residence[].residenceName").description("거주지 이름"),
                                fieldWithPath("itineraries[].residence[].stayTime").description("머무르는 시간"),
                                fieldWithPath("itineraries[].residence[].leaveTime").description("떠나는 시간"),
                                fieldWithPath("itineraries[].activity[].transportEnum").description("교통 수단"),
                                fieldWithPath("itineraries[].activity[].departurePlace").description("출발 장소"),
                                fieldWithPath("itineraries[].activity[].arrivalPlace").description("도착 장소"),
                                fieldWithPath("itineraries[].activity[].description").description("활동 설명"),
                                fieldWithPath("itineraries[].activity[].activityStart").description("활동 시작 시간"),
                                fieldWithPath("itineraries[].activity[].activityEnd").description("활동 종료 시간"),
                                fieldWithPath("itineraries[].timeSchedule.startTime").description("일정 시작 시간"),
                                fieldWithPath("itineraries[].timeSchedule.endTime").description("일정 종료 시간"),
                                fieldWithPath("itineraries[].createdAt").description("생성된 날짜 및 시간"),
                                fieldWithPath("itineraries[].updatedAt").description("마지막으로 업데이트된 날짜 및 시간"),
                                fieldWithPath("tripComments[].tripCommentId").description("여행 댓글 ID"),
                                fieldWithPath("tripComments[].tripId").description("여행 ID"),
                                fieldWithPath("tripComments[].tripName").description("여행 이름"),
                                fieldWithPath("tripComments[].userEmail").description("사용자 이메일"),
                                fieldWithPath("tripComments[].username").description("사용자 이름"),
                                fieldWithPath("tripComments[].tripComment").description("댓글 내용"),
                                fieldWithPath("tripComments[].updatedAt").description("댓글 업데이트 시간")
                        )
                ));
    }

}
