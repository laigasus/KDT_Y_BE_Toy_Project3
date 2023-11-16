package com.example.kdt_y_be_toy_project2.domain.user.controller;

import com.example.kdt_y_be_toy_project2.domain.comment.service.TripCommentService;
import com.example.kdt_y_be_toy_project2.domain.user.dto.CreateUserRequest;
import com.example.kdt_y_be_toy_project2.domain.user.dto.CreateUserResponse;
import com.example.kdt_y_be_toy_project2.domain.user.entity.User;
import com.example.kdt_y_be_toy_project2.domain.user.service.UserService;
import com.example.kdt_y_be_toy_project2.global.dummy.DummyObjectForController;
import com.example.kdt_y_be_toy_project2.global.security.PrincipalDetails;
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
import org.springframework.security.test.context.support.WithUserDetails;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
@WithUserDetails(value = "liyusang1@naver.com")
@ExtendWith(RestDocumentationExtension.class)
public class UserControllerTest extends DummyObjectForController {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    UserService userService;

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
    public void signUpTest() throws Exception {
        CreateUserRequest request = new CreateUserRequest("liyusang1@naver.com", "liyusang1", "123456");
        CreateUserResponse response = new CreateUserResponse("liyusang1@naver.com", "liyusang1");

        when(userService.signup(any(CreateUserRequest.class))).thenReturn(response);

        this.mockMvc.perform(post("/user/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andDo(document("signup",
                        requestFields(
                                fieldWithPath("email").description("사용자 이메일"),
                                fieldWithPath("username").description("사용자 이름"),
                                fieldWithPath("password").description("사용자 비밀번호")
                        ),
                        responseFields(
                                fieldWithPath("email").description("등록된 사용자 이메일"),
                                fieldWithPath("name").description("등록된 사용자 이름") // 수정된 부분
                        )))
                .andDo(print());
    }

    @Test
    public void testUserTest() throws Exception {
        // User 객체 생성
        User testUser = User.builder()
                .email("liyusang1@naver.com")
                .username("liyusang1")
                .password("123456")
                .build();

        // 테스트를 위한 JSON 형태의 응답 생성
        CreateUserResponse expectedResponse = new CreateUserResponse(testUser.getEmail(), testUser.getUsername());
        String expectedJsonResponse = objectMapper.writeValueAsString(expectedResponse);

        this.mockMvc.perform(get("/user/test")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJsonResponse))
                .andDo(document("test-user",
                        responseFields(
                                fieldWithPath("email").description("사용자의 이메일"),
                                fieldWithPath("name").description("사용자의 이름")
                        )))
                .andDo(print());
    }

}
