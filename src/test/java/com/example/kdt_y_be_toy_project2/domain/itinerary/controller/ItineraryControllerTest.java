package com.example.kdt_y_be_toy_project2.domain.itinerary.controller;

import com.example.kdt_y_be_toy_project2.domain.itinerary.dto.ItineraryRequest;
import com.example.kdt_y_be_toy_project2.domain.itinerary.dto.ItineraryResponse;
import com.example.kdt_y_be_toy_project2.domain.itinerary.dto.sub.AccommodationDTO;
import com.example.kdt_y_be_toy_project2.domain.itinerary.dto.sub.ActivityDTO;
import com.example.kdt_y_be_toy_project2.domain.itinerary.dto.sub.ResidenceDTO;
import com.example.kdt_y_be_toy_project2.domain.itinerary.entity.Accommodation;
import com.example.kdt_y_be_toy_project2.domain.itinerary.entity.Activity;
import com.example.kdt_y_be_toy_project2.domain.itinerary.entity.Residence;
import com.example.kdt_y_be_toy_project2.domain.itinerary.entity.TransportEnum;
import com.example.kdt_y_be_toy_project2.domain.itinerary.service.ItineraryService;
import com.example.kdt_y_be_toy_project2.global.dto.TimeScheduleDTO;
import com.example.kdt_y_be_toy_project2.global.entity.TimeSchedule;
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

import java.time.LocalDateTime;
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
public class ItineraryControllerTest {

    @MockBean
    ItineraryService itineraryService;

    ObjectMapper objectMapper = new ObjectMapper();

    List<ItineraryRequest> itineraryRequestList = new ArrayList<>();


    List<Accommodation> accommodations = List.of(
            Accommodation.builder()
                    .accommodationName("신라 스테이 해운대 수정test")
                    .accommodationTimeSchedule(new TimeSchedule(
                            LocalDateTime.parse("2023-10-27T15:00:00"),
                            LocalDateTime.parse("2023-10-28T18:00:00")))
                    .build()

    );

    List<Residence> residences = List.of(
            Residence.builder()
                    .residenceName("부산")
                    .residenceTimeSchedule(new TimeSchedule(
                            LocalDateTime.parse("2023-10-27T15:00:00"),
                            LocalDateTime.parse("2023-10-28T18:00:00")))
                    .build()

    );


    List<Activity> activities = List.of(
            Activity.builder()
                    .transportEnum(TransportEnum.KTX)
                    .departurePlace("집")
                    .arrivalPlace("부산역")
                    .description("KTX타고 드디어 부산입성!")
                    .activityTimeSchedule(new TimeSchedule(
                            LocalDateTime.parse("2023-01-02T14:00:00"),
                            LocalDateTime.parse("2023-01-03T11:00:00")))
                    .build(),

            Activity.builder()
                    .transportEnum(TransportEnum.CAR)
                    .departurePlace("부산역")
                    .arrivalPlace("해운대 블루라인파크")
                    .description("스카이캡슐 타서 경치보기")
                    .activityTimeSchedule(new TimeSchedule(
                            LocalDateTime.parse("2023-10-27T13:20:00"),
                            LocalDateTime.parse("2023-10-27T14:00:00")))
                    .build()

    );

    ItineraryRequest itineraryRequest = new ItineraryRequest(
            "부산 수정test",
            accommodations,
            residences,
            activities,
            new TimeSchedule(
                    LocalDateTime.parse("2023-10-28T13:05:00"),
                    LocalDateTime.parse("2023-10-28T11:15:00"))
    );


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


    private ItineraryResponse createMockItineraryResponse() {

        List<AccommodationDTO> mockAccommodations = List.of(
                new AccommodationDTO(
                        "신라 스테이 해운대 수정test",
                        "2023-10-27T15:00:00",
                        "2023-10-28T18:00:00"
                )

        );


        List<ResidenceDTO> mockResidences = List.of(
                new ResidenceDTO(
                        "부산",
                        "2023-10-27T15:00:00",
                        "2023-10-28T18:00:00"
                )

        );


        List<ActivityDTO> mockActivities = List.of(
                new ActivityDTO(
                        "KTX",
                        "집",
                        "부산역",
                        "KTX타고 드디어 부산입성!",
                        "2023-01-02T14:00:00",
                        "2023-01-03T11:00:00"
                ),
                new ActivityDTO(
                        "CAR",
                        "부산역",
                        "해운대 블루라인파크",
                        "스카이캡슐 타서 경치보기",
                        "2023-10-27T13:20:00",
                        "2023-10-27T14:00:00"
                )

        );


        TimeScheduleDTO mockTimeSchedule = new TimeScheduleDTO(
                "2023-10-28T13:05:00",
                "2023-10-28T11:15:00"
        );


        return new ItineraryResponse(
                7L,
                "부산 수정test",
                1L,
                mockAccommodations,
                mockResidences,
                mockActivities,
                mockTimeSchedule,
                "2023-01-01T00:00:00",
                "2023-01-02T00:00:00"
        );
    }


    @Test
    void addItineraryTest() throws Exception {
        ItineraryResponse mockResponse2 = createMockItineraryResponse();
        Mockito.when(itineraryService.insertItineraries(anyLong(), anyList()))
                .thenReturn(List.of(mockResponse2));

        itineraryRequestList.add(itineraryRequest);


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
                                subsectionWithPath("[].timeSchedule").description("전체 여행 일정 시간표"),
                                fieldWithPath("[].createdAt").type(JsonFieldType.STRING).description("생성된 날짜 및 시간"),
                                fieldWithPath("[].updatedAt").type(JsonFieldType.STRING).description("마지막으로 업데이트된 날짜 및 시간")
                        )
                )).andDo(print());
    }


    @Test
    void bringAllItineraryTest() throws Exception {

        ItineraryResponse mockResponse = createMockItineraryResponse();
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
                                fieldWithPath("[].timeSchedule").type(JsonFieldType.OBJECT).description("일정 시간표"),
                                fieldWithPath("[].timeSchedule.startTime").type(JsonFieldType.STRING).description("일정 시작 시간"),
                                fieldWithPath("[].timeSchedule.endTime").type(JsonFieldType.STRING).description("일정 종료 시간"),
                                fieldWithPath("[].createdAt").type(JsonFieldType.STRING).description("생성 시간"),
                                fieldWithPath("[].updatedAt").type(JsonFieldType.STRING).description("수정 시간")
                        ))).andDo(print());
    }


    @Test
    void bringOneItineraryTest() throws Exception {

        ItineraryResponse mockResponse = createMockItineraryResponse();

        Mockito.when(itineraryService.selectItinerary(anyLong())).thenReturn(mockResponse);


        this.mockMvc.perform(RestDocumentationRequestBuilders.get("/itinerary/{id}", 3).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document("get-one-ItineraryId", pathParameters(
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
                                fieldWithPath("timeSchedule.startTime").description("일정 시작 시간"),
                                fieldWithPath("timeSchedule.endTime").description("일정 종료 시간"),
                                fieldWithPath("createdAt").description("생성된 날짜 및 시간"),
                                fieldWithPath("updatedAt").description("마지막으로 업데이트된 날짜 및 시간")
                        )));
    }

    @Test
    void editItineraryTest() throws Exception {
        Mockito.when(itineraryService.updateItinerary(anyLong(), any(ItineraryRequest.class)))
                .thenReturn(createMockItineraryResponse());

        this.mockMvc.perform(patch("/itinerary/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(itineraryRequest)))
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
                                fieldWithPath("activity[].activityTimeSchedule.endTime").description("활동 종료 시간")
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
                                fieldWithPath("timeSchedule.startTime").description("일정 시작 시간"),
                                fieldWithPath("timeSchedule.endTime").description("일정 종료 시간"),
                                fieldWithPath("createdAt").description("생성된 날짜 및 시간"),
                                fieldWithPath("updatedAt").description("마지막으로 업데이트된 날짜 및 시간")
                        )
                ));
    }


}